package gawe.imb.karya.presenter.er.urgentPassive;

import com.google.common.base.Strings;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import gawe.imb.karya.model.manager.CategoryManager;
import gawe.imb.karya.model.objects.GaweCategoryNested;
import gawe.imb.karya.presenter.base.BasePresenter;

/**
 * Created by korneliussendy on 2/6/18.
 */

public class ERUrgentPassivePresenter extends BasePresenter<ERUrgentPassiveView> {

    private List<GaweCategoryNested> list;

    public ERUrgentPassivePresenter(ERUrgentPassiveView view) {
        attachView(view);
        list = new ArrayList<>();
    }

    public void loadCategory() {
        if (list != null && list.size() > 0) {
            view.categoryLoaded(list);
            return;
        }

        view.showLoadingCategory();
        view.log("Loading Category");
        addDisposable(CategoryManager.getAllCategoryNested("")
                .subscribe((categories, throwable) -> {
                    view.hideLoadingCategory();
                    if (throwable != null) {
                        view.loadCategoryFailed(throwable.getMessage());
                    } else if (categories.size() <= 0) {
                        view.categoryLoadedButEmpty();
                    } else {
                        list.addAll(categories);
                        view.categoryLoaded(list);
                    }
                }));

//        addDisposable(CategoryManager.getAllCategory()
//                .subscribe((gaweCategories, throwable) -> {
//
//                    view.hideLoadingCategory();
//
//                    if (throwable != null) {
//                        dataLoaded = false;
//                        view.loadCategoryFailed(throwable.getMessage());
//                    } else if (gaweCategories.size() <= 0) {
//                        dataLoaded = true;
//                        view.categoryLoadedButEmpty();
//                    } else {
//                        dataLoaded = true;
//                        list = gaweCategories;
//                        view.categoryLoaded(gaweCategories);
//                    }
//                })
//        );
    }

    public void reloadCategory() {
        list.clear();
        view.clearList();
        loadCategory();
    }

    public void pause() {
        clearDisposable();
    }

    public void onCategorySelected(String data) {
        if (Strings.isNullOrEmpty(data)) {
            view.failedSelectCategory();
            return;
        }
        Gson g = new Gson();
        GaweCategoryNested category = g.fromJson(data, GaweCategoryNested.class);
        view.toUrgentFormPage(category);
    }
}

