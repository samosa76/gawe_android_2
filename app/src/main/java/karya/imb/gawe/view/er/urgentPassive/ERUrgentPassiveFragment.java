package karya.imb.gawe.view.er.urgentPassive;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import gawe.imb.karya.model.objects.GaweCategoryNested;
import gawe.imb.karya.presenter.er.urgentPassive.ERUrgentPassivePresenter;
import gawe.imb.karya.presenter.er.urgentPassive.ERUrgentPassiveView;
import karya.imb.gawe.GlideApp;
import karya.imb.gawe.R;
import karya.imb.gawe.utils.BaseRecyclerAdapter;
import gawe.imb.karya.model.utils.Helper;
import karya.imb.gawe.view.er.pickCategory.ERPickCategoryActivity;
import karya.imb.gawe.view.er.urgentForm.ERUrgentFormActivity;

import static android.app.Activity.RESULT_OK;
import static karya.imb.gawe.view.er.pickCategory.ERPickCategoryActivity.ITERATION;
import static karya.imb.gawe.view.er.pickCategory.ERPickCategoryActivity.KEY_DATA;
import static karya.imb.gawe.view.er.pickCategory.ERPickCategoryActivity.KEY_RESULT;
import static karya.imb.gawe.view.er.pickCategory.ERPickCategoryActivity.REQUEST_CODE;

/**
 * Created by korneliussendy on 2/6/18.
 */

public class ERUrgentPassiveFragment extends Fragment implements ERUrgentPassiveView {

    private ERUrgentPassivePresenter presenter;
    @BindView(R.id.ivSearch) ImageView ivSearch;
    @BindView(R.id.ivDelete) ImageView ivDelete;
    @BindView(R.id.etSearch) EditText etSearch;
    @BindView(R.id.rv) RecyclerView rv;
    @BindView(R.id.progressCategory) ProgressBar progressCategory;
    @BindView(R.id.cvParent) CardView cvParent;
    @BindView(R.id.swipeRefreshLayout) SwipeRefreshLayout swipeRefreshLayout;
    private Unbinder unbinder;

    private CategoryAdapter adapter;
    private List<GaweCategoryNested> list = new ArrayList<>();

    public static String NEW_URGENT_CATEGORY = "urgent category";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_er_urgent_passive, container, false);
        unbinder = ButterKnife.bind(this, v);

        ViewCompat.setNestedScrollingEnabled(rv, false);
        adapter = new CategoryAdapter(getContext(), list, R.layout.cell_er_category);
        Helper.setupRecyclerView(rv, adapter);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            presenter.reloadCategory();
            swipeRefreshLayout.setRefreshing(false);
        });
        return v;
    }

    @Override
    public void onStart() {
        presenter = new ERUrgentPassivePresenter(this);
        super.onStart();
    }

    @Override
    public void onPause() {
        if (presenter != null)
            presenter.pause();
        super.onPause();
    }

    @Override
    public void onResume() {
        presenter.reloadCategory();
        super.onResume();
    }

    @Override
    public void clearList() {
        list.clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadCategoryFailed(String message) {

    }

    @Override
    public void categoryLoadedButEmpty() {

    }

    @Override
    public void categoryLoaded(List<GaweCategoryNested> gaweCategories) {
        Log.d("Category", "Category loaded : " + gaweCategories.size());
        list.clear();
        list.addAll(gaweCategories);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showLoadingCategory() {
        progressCategory.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingCategory() {
        progressCategory.setVisibility(View.GONE);
    }

    @Override
    public void failedSelectCategory() {
        toast("failedSelectCategory");
    }

    @Override
    public void toUrgentFormPage(GaweCategoryNested category) {
        toast("Category Selected " + category.getName());
        Gson g = new Gson();
        Intent i = new Intent(getContext(), ERUrgentFormActivity.class);
        i.putExtra(NEW_URGENT_CATEGORY, g.toJson(category));
        startActivity(i);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            presenter.onCategorySelected(data.getStringExtra(KEY_RESULT));
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void log(String log) {

    }

    @Override
    public void toast(String message) {

    }

    @Override
    public void alert(String message) {

    }

    @Override
    public void savePreference(String key, String value) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    private class CategoryAdapter extends BaseRecyclerAdapter {

        CategoryAdapter(Context context, List<?> items, int cellLayout) {
            super(context, items, cellLayout);
        }

        @Override
        public RecyclerView.ViewHolder objectHolder(View v) {
            return new CategoryAdapter.Holder(v);
        }

        @Override
        public RecyclerView.ViewHolder headerHolder(View v) {
            return null;
        }

        @Override
        public void setView(RecyclerView.ViewHolder objectHolder, int position) {
            CategoryAdapter.Holder holder = (CategoryAdapter.Holder) objectHolder;
            final GaweCategoryNested category = (GaweCategoryNested) getItem(position);
            holder.tvTitle.setText(category.getName());
            holder.tvDescription.setText(category.getDescription());

            Log.d("ICON", "" + category.getIcon());

            GlideApp.with(getContext())
                    .load(category.getIcon())
                    .error(R.drawable.logo_g_yellow_transparent)
                    .into(holder.ivIcon);


//            holder.container.setBackgroundColor(
//                    ContextCompat.getColor(
//                            getContext(),
//                            position % 2 == 0 ?
//                                    R.color.background_list_primary : R.color.background_list_secondary
//                    )
//            );

            holder.vDivider.setVisibility((position == (getItemCount() - 1)) ? View.INVISIBLE : View.VISIBLE);


            holder.container.setOnClickListener(v -> itemSelected(position));
        }

        @Override
        public void setHeaderView(RecyclerView.ViewHolder objectHolder) {

        }

        @Override
        public void itemSelected(int position) {
            GaweCategoryNested details = (GaweCategoryNested) getItem(position);
            Gson g = new Gson();
            String data = g.toJson(details);
            if (details.getChildren() != null && details.getChildren().size() > 0) {
                log(details.getName() + " has children opening");
                Intent i = new Intent(getContext(), ERPickCategoryActivity.class);
                i.putExtra(KEY_DATA, data);
                i.putExtra(ITERATION, 1);
                startActivityForResult(i, REQUEST_CODE);
            } else {
                presenter.onCategorySelected(data);
            }
        }

        private class Holder extends RecyclerView.ViewHolder {

            View parent, container;
            TextView tvTitle, tvDescription;
            ImageView ivIcon;
            View vDivider;

            Holder(View v) {
                super(v);
                parent = v.findViewById(R.id.parent);
                ivIcon = v.findViewById(R.id.ivIcon);
                container = v.findViewById(R.id.container);
                tvTitle = v.findViewById(R.id.tvTitle);
                tvDescription = v.findViewById(R.id.tvDescription);
                vDivider = v.findViewById(R.id.vDivider);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
