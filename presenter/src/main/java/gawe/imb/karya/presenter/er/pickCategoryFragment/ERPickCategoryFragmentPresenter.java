package gawe.imb.karya.presenter.er.pickCategoryFragment;

import com.google.common.base.Strings;
import com.google.gson.Gson;

import gawe.imb.karya.model.manager.CategoryManager;
import gawe.imb.karya.model.objects.GaweCategoryNested;
import gawe.imb.karya.presenter.base.BasePresenter;

/**
 * Created by korneliussendy on 2/6/18.
 */

public class ERPickCategoryFragmentPresenter extends BasePresenter<ERPickCategoryFragmentView> {

    public ERPickCategoryFragmentPresenter(ERPickCategoryFragmentView view) {
        attachView(view);
    }

    public void loadCategory(String parent) {
        String path = "", pageTitle = "";

        if (!Strings.isNullOrEmpty(parent)) {
            Gson g = new Gson();
            GaweCategoryNested category = g.fromJson(parent, GaweCategoryNested.class);
            pageTitle = (category.getName().toUpperCase());
            path = category.getId();
        }
        view.setPageTitle(pageTitle);
        view.showLoadingCategory();
        view.log("opening path = " + path);
        CategoryManager.getAllCategoryNested(path).subscribe((categories, throwable) -> {
            view.hideLoadingCategory();
            if (throwable != null) {
                view.failedLoadCategory(throwable.getMessage());
                return;
            }
            view.categoryLoaded(categories);
        });
    }
}
