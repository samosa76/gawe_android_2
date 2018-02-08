package karya.imb.gawe.view.er.pickCategoryFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.common.base.Strings;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gawe.imb.karya.model.objects.GaweCategoryNested;
import gawe.imb.karya.presenter.er.pickCategoryFragment.ERPickCategoryFragmentPresenter;
import gawe.imb.karya.presenter.er.pickCategoryFragment.ERPickCategoryFragmentView;
import karya.imb.gawe.R;
import karya.imb.gawe.utils.BaseRecyclerAdapter;
import gawe.imb.karya.model.utils.Helper;

/**
 * Created by korneliussendy on 2/6/18.
 */

public class ERPickCategoryFragment extends Fragment implements ERPickCategoryFragmentView {

    @BindView(R.id.ivSearch) ImageView ivSearch;
    @BindView(R.id.etSearch) EditText etSearch;
    @BindView(R.id.tvTitleCategory) TextView tvTitleCategory;
    @BindView(R.id.rv) RecyclerView rv;
    @BindView(R.id.progressBar) ProgressBar progressBar;

    private ArrayList<GaweCategoryNested> listDetails = new ArrayList<>();
    private ArrayList<GaweCategoryNested> listSelectedDetails = new ArrayList<>();

    private int iteration = 0;
    private String parentData = "";
    private CategoryAdapter adapter;
    private ERPickCategoryFragmentPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_er_pick_category, container);
        ButterKnife.bind(v);
        presenter = new ERPickCategoryFragmentPresenter(this);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new CategoryAdapter(getContext(), listSelectedDetails, R.layout.cell_er_category);
        Helper.setupRecyclerView(rv, adapter);
        presenter.loadCategory(parentData);
    }

    @Override
    public void setPageTitle(String title) {
//        setTitle(Strings.isNullOrEmpty(title) ? "PICK CATEGORY" : title);
        tvTitleCategory.setText(Strings.isNullOrEmpty(title) ? "PICK CATEGORY" : title);
    }

    @Override
    public void showLoadingCategory() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingCategory() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void failedLoadCategory(String message) {
        log(message);
    }

    @Override
    public void categoryLoaded(List<GaweCategoryNested> categories) {
        log("CATEGORY LOADED" + " size : " + categories.size());
        for (GaweCategoryNested g : categories) {
            log("Category : " + g.getId() + " child size : " + g.getChildren().size());
            for (GaweCategoryNested c : g.getChildren()) {
                log("Child Category : " + c.getId());
            }
        }
        log("ITERATION = " + iteration);
        if (iteration > 0) {
            listDetails.addAll(categories.get(0).getChildren());
            listSelectedDetails.addAll(categories.get(0).getChildren());
        } else {
            listDetails.addAll(categories);
            listSelectedDetails.addAll(categories);
        }
        adapter.notifyDataSetChanged();
//        expandableListAdapter.notifyDataSetChanged();

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filterCategory(editable.toString());
            }
        });
    }

    private void filterCategory(String keyword) {
        listSelectedDetails.clear();
        if (Strings.isNullOrEmpty(keyword) || keyword.length() <= 3) {
            listSelectedDetails.addAll(listDetails);
            adapter.notifyDataSetChanged();
            return;
        }

        for (GaweCategoryNested g : listDetails) {
            if (g.getName().toUpperCase().contains(keyword.toUpperCase())) {
                listSelectedDetails.add(g);
            }
        }
        adapter.notifyDataSetChanged();

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
            Holder holder = (Holder) objectHolder;
            GaweCategoryNested category = (GaweCategoryNested) getItem(position);

            holder.tvTitle.setText(category.getName());
            holder.container.setOnClickListener(view -> itemSelected(position));
        }

        @Override
        public void setHeaderView(RecyclerView.ViewHolder objectHolder) {

        }

        @Override
        public void itemSelected(int position) {
            GaweCategoryNested details = (GaweCategoryNested) getItem(position);
            Gson g = new Gson();
            String data = g.toJson(details);
//            if (details.getChildren() != null && details.getChildren().size() > 0) {
//                log(details.getName() + " has children opening");
//                Intent i = new Intent(getContext(), ERPickCategoryActivity.class);
//                i.putExtra(KEY_DATA, data);
//                i.putExtra(ITERATION, iteration + 1);
//                startActivityForResult(i, REQUEST_CODE);
//            } else {
//                log(details.getName() + " selected");
//                Intent i = new Intent();
//                i.putExtra(KEY_RESULT, data);
//                setResult(RESULT_OK, i);
//                finish();
//            }
        }

        private class Holder extends RecyclerView.ViewHolder {

            ImageView ivIcon;
            TextView tvTitle;
            TextView tvDescription;
            RelativeLayout container;
            LinearLayout parent;

            Holder(View v) {
                super(v);
                ivIcon = v.findViewById(R.id.ivIcon);
                tvTitle = v.findViewById(R.id.tvTitle);
                tvDescription = v.findViewById(R.id.tvDescription);
                container = v.findViewById(R.id.container);
                parent = v.findViewById(R.id.parent);
            }
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
}
