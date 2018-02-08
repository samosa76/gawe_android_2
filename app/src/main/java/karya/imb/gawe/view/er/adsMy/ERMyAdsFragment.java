package karya.imb.gawe.view.er.adsMy;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import gawe.imb.karya.model.objects.GaweBursa;
import gawe.imb.karya.model.objects.GaweCategoryNested;
import gawe.imb.karya.presenter.er.adsMy.ERMyAdsPresenter;
import gawe.imb.karya.presenter.er.adsMy.ERMyAdsView;
import karya.imb.gawe.R;
import gawe.imb.karya.model.manager.UserManager;
import karya.imb.gawe.utils.BaseRecyclerAdapter;
import karya.imb.gawe.view.er.BaseTabFragment;
import karya.imb.gawe.view.er.adsAdd.ERAdsAddActivity;
import karya.imb.gawe.view.er.adsDetails.ERAdsDetailsActivity;
import karya.imb.gawe.view.er.pickCategory.ERPickCategoryActivity;

import static android.app.Activity.RESULT_OK;
import static karya.imb.gawe.view.er.pickCategory.ERPickCategoryActivity.KEY_RESULT;

/**
 * Created by korneliussendy on 1/26/18.
 */

public class ERMyAdsFragment extends BaseTabFragment<ERMyAdsPresenter> implements ERMyAdsView {

    @BindView(R.id.swipeRefreshLayout) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.rv) RecyclerView rv;
    @BindView(R.id.progressBursa) ProgressBar progressBursa;
    @BindView(R.id.fabAdd) FloatingActionButton fabAdd;

    private static final int REQUEST_PICK_CAT_FOR_NEW = 129;

    @Override
    protected ERMyAdsPresenter createPresenter() {
        return new ERMyAdsPresenter(this);
    }

    @Override
    protected boolean showToolbar() {
        return true;
    }

    @Override
    protected boolean showLogo() {
        return true;
    }

    @Override
    protected boolean showLeftIcon() {
        return true;
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_er_my_ads;
    }

    private MyAdsAdapter adapter;
    private List<GaweBursa> list = new ArrayList<>();
    private LinearLayoutManager layoutManager;
    private static int REQUEST_VIEW_DETAILS = 901;
    public static final String KEY_VIEW_DETAILS = "view details";
    public static final String KEY_CATEGORY = "job details category";


    @Override
    protected void setView(View view) {
        ViewCompat.setNestedScrollingEnabled(rv, false);

        layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        adapter = new MyAdsAdapter(getContext(), list, R.layout.cell_er_my_ads);
        adapter.setHasStableIds(true);
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        swipeRefreshLayout.setOnRefreshListener(() -> {
            presenter.reloadBursa(UserManager.getUserPreference().getId());
            swipeRefreshLayout.setRefreshing(false);
        });

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleThreshold = 5;
                layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int totalItemCount = layoutManager.getItemCount();
                int lastVisibleItem = layoutManager.findLastCompletelyVisibleItemPosition();
                if ((totalItemCount <= (lastVisibleItem + visibleThreshold) && (totalItemCount > visibleThreshold))) {
                    presenter.loadBursa(UserManager.getUserPreference(getContext()).getId());
                }
            }
        });
        fabAdd.setOnClickListener(v -> presenter.onButtonAddClicked());
        presenter.loadBursa(UserManager.getUserPreference(getContext()).getId());
    }

    @Override
    public void stillLoadingBursa(int page, int dataSize) {
    }

    @Override
    public void showLoadingBursa() {
        fabAdd.setEnabled(false);
        progressBursa.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingBursa() {
        fabAdd.setEnabled(true);
        progressBursa.setVisibility(View.GONE);
    }

    @Override
    public void errorLoadingBursa(String message) {
        alert(message);
    }

    @Override
    public void allDataLoaded() {

    }

    @Override
    public void bursaLoaded(List<GaweBursa> listBursa) {
        list.addAll(listBursa);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void clearCurrentBursa() {
        list.clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void toAddAdsPage(GaweCategoryNested category) {
        Intent i = new Intent(getContext(), ERAdsAddActivity.class);
        Gson g = new Gson();
        i.putExtra(KEY_CATEGORY, g.toJson(category));
        startActivityForResult(i, 1);
    }

    @Override
    public void pickCategoryPage() {
        Intent i = new Intent(getContext(), ERPickCategoryActivity.class);
        startActivityForResult(i, REQUEST_PICK_CAT_FOR_NEW);
    }

    @Override
    public void toAdsDetailsPage(GaweBursa gaweBursa) {
        Intent i = new Intent(getContext(), ERAdsDetailsActivity.class);
        Gson g = new Gson();
        i.putExtra(KEY_VIEW_DETAILS, g.toJson(gaweBursa));
        startActivityForResult(i, REQUEST_VIEW_DETAILS);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        log("REQUEST CODE = " + requestCode + " RESULT CODE = " + resultCode);
        if (requestCode == REQUEST_PICK_CAT_FOR_NEW && resultCode == RESULT_OK) {
            Gson g = new Gson();
            GaweCategoryNested cat = g.fromJson(data.getStringExtra(KEY_RESULT), GaweCategoryNested.class);
            toast(cat.getName() + "selected");
            presenter.onCategorySelected(cat);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private class MyAdsAdapter extends BaseRecyclerAdapter {
        MyAdsAdapter(Context context, List<?> items, int cellLayout) {
            super(context, items, cellLayout);
        }

        @Override
        public RecyclerView.ViewHolder objectHolder(View v) {
            return new Holder(v);
        }

        @Override
        public RecyclerView.ViewHolder headerHolder(View v) {
            return null;
        }

        @Override
        public void setView(RecyclerView.ViewHolder objectHolder, int position) {
            Holder holder = (Holder) objectHolder;
            GaweBursa bursa = (GaweBursa) getItem(position);

            holder.tvTitle.setText(bursa.getDescription());
            if (bursa.getStApproved()) {
                holder.vStatus.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.bursa_indicator_active));
                holder.tvStatus.setText("APPROVED");
                holder.tvStatus.setTextColor(ContextCompat.getColor(getContext(), R.color.bursa_indicator_active));
            } else if (!bursa.getHasBeenReviewed()) {
                holder.vStatus.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.bursa_indicator_pending));
                holder.tvStatus.setText("PENDING");
                holder.tvStatus.setTextColor(ContextCompat.getColor(getContext(), R.color.bursa_indicator_pending));
            } else if (bursa.getStRejected()) {
                holder.vStatus.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.bursa_indicator_rejected));
                holder.tvStatus.setText("REJECTED");
                holder.tvStatus.setTextColor(ContextCompat.getColor(getContext(), R.color.bursa_indicator_rejected));
            } else {
                holder.vStatus.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.transparent));
                holder.tvStatus.setText("");
                holder.tvStatus.setTextColor(ContextCompat.getColor(getContext(), R.color.text));
            }
            holder.parent.setOnClickListener(view -> presenter.viewAdsDetails(position, bursa));

        }

        @Override
        public void setHeaderView(RecyclerView.ViewHolder objectHolder) {

        }

        @Override
        public void itemSelected(int position) {
        }

        @Override
        public long getItemId(int position) {
            GaweBursa bursa = (GaweBursa) getItem(position);
            return (bursa.getId() + bursa.getEmployerId()).hashCode();
        }

        private class Holder extends RecyclerView.ViewHolder {
            CardView parent;
            TextView tvTitle;
            View vStatus;
            TextView tvStatus;
            TextView tvNotif;
            TextView tvInfo;

            Holder(View view) {
                super(view);
                parent = view.findViewById(R.id.parent);
                tvTitle = view.findViewById(R.id.tvTitle);
                vStatus = view.findViewById(R.id.vStatus);
                tvStatus = view.findViewById(R.id.tvStatus);
                tvNotif = view.findViewById(R.id.tvNotif);
                tvInfo = view.findViewById(R.id.tvInfo);
            }
        }
    }

}
