package karya.imb.gawe.view.er.partners;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import gawe.imb.karya.mainlibs.utils.MapsGeo;
import gawe.imb.karya.model.objects.GaweBrowse;
import gawe.imb.karya.presenter.er.partners.ERPartnersPresenter;
import gawe.imb.karya.presenter.er.partners.ERPartnersView;
import karya.imb.gawe.GlideApp;
import karya.imb.gawe.R;
import karya.imb.gawe.utils.BaseRecyclerAdapter;
import karya.imb.gawe.view.er.BaseTabFragment;
import karya.imb.gawe.view.er.partnerFilter.ERFilterActivity;

/**
 * Created by korneliussendy on 1/26/18.
 */

public class ERPartnersFragment extends BaseTabFragment<ERPartnersPresenter> implements ERPartnersView {

    @BindView(R.id.swipeRefreshLayout) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.tvCategory) TextView tvCategory;
    @BindView(R.id.tvDistance) TextView tvDistance;
    @BindView(R.id.rv) RecyclerView rv;
    @BindView(R.id.progressBrowse) ProgressBar progressBrowse;

    public static final String KEY_GAWE_BROWSE = "key_gawe_browse";
    public static final String KEY_SELECTED_CATEGORY = "key_gawe_selected";

    @Override
    protected ERPartnersPresenter createPresenter() {
        return new ERPartnersPresenter(this);
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_er_partners;
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
    protected boolean showRightIcon() {
        return true;
    }

    @Override
    protected int resRightIcon() {
        return R.drawable.ic_option;
    }

    @Override
    protected View.OnClickListener onRightIconClick() {
        return view -> presenter.onFilterClick();
    }

    private List<GaweBrowse> list = new ArrayList<>();
    private BrowseAdapter adapter;
    private MapsGeo mg;
    private LinearLayoutManager layoutManager;
    private static int CODE_FILTER = 192;
    private boolean isLoading = false;

    @Override
    protected void setView(View view) {
//        ViewCompat.setNestedScrollingEnabled(rv, false);
//        adapter = new BrowseAdapter(getContext(), list, R.layout.cell_partner_employee);
//        layoutManager = Helper.setupRecyclerView(rv, adapter);


        layoutManager = new LinearLayoutManager(getContext());

        rv.setLayoutManager(layoutManager);
        adapter = new BrowseAdapter(getContext(), list, R.layout.cell_partner_employee);
        adapter.setHasStableIds(true);
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(false);
            mg = new MapsGeo(getContext());
            presenter.reloadBrowse(mg.getLatitude(), mg.getLongitude());
            mg.unBind();
        });
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleThreshold = 5;
                layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int totalItemCount = layoutManager.getItemCount();
                int lastVisibleItem = layoutManager.findLastCompletelyVisibleItemPosition();
                if (!isLoading && (totalItemCount <= (lastVisibleItem + visibleThreshold) && (totalItemCount > visibleThreshold))) {
                    mg = new MapsGeo(getContext());
                    presenter.loadBrowse(mg.getLatitude(), mg.getLongitude());
                    mg.unBind();
                    isLoading = true;
                }
            }
        });

        mg = new MapsGeo(getContext());
        presenter.loadBrowse(mg.getLatitude(), mg.getLongitude());
        mg.unBind();
    }

    @Override
    public void setDistance(int distance) {
        tvDistance.setText("" + distance + " Km");
    }

    @Override
    public void toFilterPage() {
        Intent i = new Intent(getActivity(), ERFilterActivity.class);
        startActivityForResult(i, CODE_FILTER);
    }

    @Override
    public void failedLoadBrowse(String message) {
        alert(message);
    }

    @Override
    public void browseLoaded(List<GaweBrowse> results) {
        int lastPos = list.size();
        list.addAll(results);
        adapter.notifyItemRangeInserted(lastPos - 1, results.size());
        isLoading = false;
    }

    @Override
    public void stillLoadingBrowse(int page, int perPage) {
        isLoading = true;
        log("still loading browse");
    }

    @Override
    public void allBrowseLoaded() {

    }

    @Override
    public void dataLoadedZeroResult() {
        toast("No Results");
    }

    @Override
    public void openPartnerDetails(GaweBrowse partner, String categoryId) {
        //TODO ke halaman details kasi gawebrowse dengan key dan kasi categoryId

    }

    @Override
    public void showLoadingBrowse() {
        progressBrowse.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingBrowse() {
        progressBrowse.setVisibility(View.GONE);
    }

    @Override
    public void clearCurrentData() {
        list.clear();
        adapter.notifyDataSetChanged();
    }

    private class BrowseAdapter extends BaseRecyclerAdapter {

        BrowseAdapter(Context context, List<?> items, int cellLayout) {
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
            GaweBrowse browse = (GaweBrowse) getItem(position);

            log(browse.getProfilePic());

            holder.tvName.setText(browse.getFullName());
            GlideApp.with(holder.ivImage)
                    .load(browse.getProfilePic())
                    .placeholder(R.drawable.logo_g_yellow_transparent)
                    .error(R.drawable.logo_g_yellow_transparent)
                    .into(holder.ivImage);
            holder.parent.setOnClickListener(view -> itemSelected(position));
        }

        @Override
        public void setHeaderView(RecyclerView.ViewHolder objectHolder) {

        }

        @Override
        public void itemSelected(int position) {
            presenter.onPartnerClick((GaweBrowse) getItem(position));
        }

        @Override
        public long getItemId(int position) {
            GaweBrowse browse = (GaweBrowse) getItem(position);
            return (browse.getId() + browse.getFullName()).hashCode();
        }

        class Holder extends RecyclerView.ViewHolder {

            View parent;
            CircleImageView ivImage;
            TextView tvRating;
            TextView tvName;
            TextView tvJobComplete;
            TextView tvJobCanceled;

            Holder(View v) {
                super(v);
                parent = v.findViewById(R.id.parent);
                ivImage = v.findViewById(R.id.ivImage);
                tvRating = v.findViewById(R.id.tvRating);
                tvName = v.findViewById(R.id.tvName);
                tvJobComplete = v.findViewById(R.id.tvJobComplete);
                tvJobCanceled = v.findViewById(R.id.tvJobCanceled);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CODE_FILTER) {
            if (resultCode == Activity.RESULT_OK) {
                log("return from filter");
                mg = new MapsGeo(getContext());
                presenter.reloadBrowse(mg.getLatitude(), mg.getLongitude());
                mg.unBind();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
