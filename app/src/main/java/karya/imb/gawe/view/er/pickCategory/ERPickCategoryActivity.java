package karya.imb.gawe.view.er.pickCategory;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
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
import gawe.imb.karya.model.objects.GaweCategoryNested;
import gawe.imb.karya.presenter.er.pickCategory.ERPickCategoryPresenter;
import gawe.imb.karya.presenter.er.pickCategory.ERPickCategoryView;
import karya.imb.gawe.R;
import karya.imb.gawe.utils.BaseRecyclerAdapter;
import gawe.imb.karya.model.utils.Helper;
import karya.imb.gawe.view.er.ERBaseActivity;

/**
 * Created by korneliussendy on 1/30/18.
 */

public class ERPickCategoryActivity extends ERBaseActivity<ERPickCategoryPresenter> implements ERPickCategoryView {

    @BindView(R.id.ivSearch) ImageView ivSearch;
    @BindView(R.id.etSearch) EditText etSearch;
    @BindView(R.id.tvTitleCategory) TextView tvTitleCategory;
    @BindView(R.id.rv) RecyclerView rv;
    @BindView(R.id.progressBar) ProgressBar progressBar;

//    @BindView(R.id.elv) ExpandableListView expandableListView;

    private ArrayList<GaweCategoryNested> listDetails = new ArrayList<>();
    private ArrayList<GaweCategoryNested> listSelectedDetails = new ArrayList<>();
    //    private CustomExpandableListAdapter expandableListAdapter;
    private CategoryAdapter adapter;

    public static final String KEY_DATA = "data";
    public static final String KEY_RESULT = "result_data";
    public static final String ITERATION = "iteration";

    public static int REQUEST_CODE = 100;
    //GET THIS RESULT CODE
//    public static int RESULT_CODE = 103;
    private int iteration = 0;
    private String parentData = "";


    @Override
    protected ERPickCategoryPresenter createPresenter() {
        return new ERPickCategoryPresenter(this);
    }

    @Override
    protected boolean showToolbar() {
        return true;
    }

    @Override
    protected boolean showTitle() {
        return false;
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
    public int setLeftIconRes() {
        return R.drawable.ic_left_arrow;
    }

    @Override
    public View.OnClickListener onLeftIconClick() {
        setResult(RESULT_CANCELED);
        return view -> onBackPressed();
    }

    @Override
    public int setContentView() {
        return R.layout.activity_er_pick_category;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        expandableListAdapter = new CustomExpandableListAdapter(this, listDetails);
//        expandableListView.setAdapter(expandableListAdapter);
//        expandableListView.setOnGroupExpandListener(
//                groupPosition -> {
//                    Toast.makeText(getApplicationContext(),
////                        listDetails.get(groupPosition) +
//                            " List Expanded.",
//                            Toast.LENGTH_SHORT).show();
//                    log("EXPANDED " + listDetails.get(groupPosition).getId());
//
//                });
//
//        expandableListView.setOnGroupCollapseListener(
//                groupPosition -> {
//                    Toast.makeText(getApplicationContext(),
////                        listDetails.get(groupPosition) +
//                            " List Collapsed.",
//                            Toast.LENGTH_SHORT).show();
//                    log("COLLAPSE " + listDetails.get(groupPosition).getId());
//                });
//
//        expandableListView.setOnChildClickListener(
//                (parent, v, groupPosition, childPosition, id) -> {
//                    log(listDetails.get(groupPosition).getChildren().get(childPosition).getName());
//                    GaweCategoryNested details = listDetails.get(groupPosition).getChildren().get(childPosition);
//                    Gson g = new Gson();
//                    String data = g.toJson(details);
//                    if (details.getChildren() != null && details.getChildren().size() > 0) {
//                        log(details.getName() + " has children opening");
//                        Intent i = new Intent(this, ERPickCategoryActivity.class);
//                        i.putExtra(KEY_DATA, data);
//                        i.putExtra(ITERATION, iteration++);
//                        startActivityForResult(i, REQUEST_CODE);
//                    } else {
//                        log(details.getName() + " selected");
//                        Intent i = new Intent();
//                        i.putExtra(KEY_RESULT, data);
//                        setResult(RESULT_OK, i);
//                        finish();
//                    }
//                    return false;
//                });
//
//
//        expandableListAdapter.notifyDataSetChanged();

        adapter = new CategoryAdapter(this, listSelectedDetails, R.layout.cell_er_category);
        Helper.setupRecyclerView(rv, adapter);
        iteration = getIntent().getIntExtra(ITERATION, 0);
        parentData = getIntent().getStringExtra(KEY_DATA);
        log("PARENT DATA " + parentData);
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

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            log("activity results for " + iteration);
            setResult(RESULT_OK, data);
            finish();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private class CategoryAdapter extends BaseRecyclerAdapter {

        CategoryAdapter(Context context, List<?> items, int cellLayout) {
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
            if (details.getChildren() != null && details.getChildren().size() > 0) {
                log(details.getName() + " has children opening");
                Intent i = new Intent(ERPickCategoryActivity.this, ERPickCategoryActivity.class);
                i.putExtra(KEY_DATA, data);
                i.putExtra(ITERATION, iteration + 1);
                startActivityForResult(i, REQUEST_CODE);
            } else {
                log(details.getName() + " selected");
                Intent i = new Intent();
                i.putExtra(KEY_RESULT, data);
                setResult(RESULT_OK, i);
                finish();
            }
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

//    public class CustomExpandableListAdapter extends BaseExpandableListAdapter {
//
//        private Context context;
//        //        private List<GaweCategoryNested> expandableListTitle;
//        private List<GaweCategoryNested> expandableListDetail;
//
//        CustomExpandableListAdapter(Context context,
//                                    List<GaweCategoryNested> expandableListDetail) {
//            this.context = context;
////            this.expandableListTitle = expandableListTitle;
//            this.expandableListDetail = expandableListDetail;
//        }
//
//        @Override
//        public Object getChild(int listPosition, int expandedListPosition) {
//            log("GET CHILD " + listPosition + " EXPAND POS " + expandedListPosition);
//            return expandableListDetail.get(listPosition).getChildren().get(expandedListPosition);
//        }
//
//        @Override
//        public long getChildId(int listPosition, int expandedListPosition) {
//            return expandedListPosition;
//        }
//
//        @Override
//        public View getChildView(int listPosition, int expandedListPosition,
//                                 boolean isLastChild, View convertView, ViewGroup parent) {
//            GaweCategoryNested category = (GaweCategoryNested) getChild(listPosition, expandedListPosition);
//            if (convertView == null) {
//                convertView = LayoutInflater.from(context).inflate(R.layout.cell_category_child, null);
//            }
//            View container = convertView.findViewById(R.id.container);
////            container.setBackgroundColor(ContextCompat.getColor(context, R.color.cpb_blue));
//            TextView tvTitle = convertView.findViewById(R.id.tvTitle);
//            TextView tvDescription = convertView.findViewById(R.id.tvDescription);
//            tvTitle.setText(category.getName());
//            tvDescription.setText(category.getDescription());
//            return convertView;
//        }
//
//        @Override
//        public int getChildrenCount(int listPosition) {
//            return this.expandableListDetail.get(listPosition).getChildren().size();
//        }
//
//        @Override
//        public Object getGroup(int listPosition) {
//            return this.expandableListDetail.get(listPosition);
//        }
//
//        @Override
//        public int getGroupCount() {
//            return this.expandableListDetail.size();
//        }
//
//        @Override
//        public long getGroupId(int listPosition) {
//            return listPosition;
//        }
//
//        @Override
//        public View getGroupView(int listPosition, boolean isExpanded, View convertView, ViewGroup parent) {
//            GaweCategoryNested listTitle = (GaweCategoryNested) getGroup(listPosition);
//            if (convertView == null) {
//                convertView = LayoutInflater.from(context).inflate(R.layout.cell_category_parent, null);
//            }
//            TextView tvTitle = convertView.findViewById(R.id.tvTitle);
//            TextView tvDescription = convertView.findViewById(R.id.tvDescription);
//            tvTitle.setText(listTitle.getName());
//            tvDescription.setText(listTitle.getDescription());
//
//            //EXPAND KALAU CUMA 1
//            if (getGroupCount() == 1) {
//                ExpandableListView mExpandableListView = (ExpandableListView) parent;
//                mExpandableListView.expandGroup(listPosition);
//            }
//
//            return convertView;
//        }
//
//        @Override
//        public boolean hasStableIds() {
//            return false;
//        }
//
//        @Override
//        public boolean isChildSelectable(int listPosition, int expandedListPosition) {
//            return true;
//        }
//    }

}
