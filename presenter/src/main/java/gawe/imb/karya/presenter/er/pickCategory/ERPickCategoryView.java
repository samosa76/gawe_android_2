package gawe.imb.karya.presenter.er.pickCategory;

import java.util.List;

import gawe.imb.karya.model.objects.GaweCategoryNested;
import gawe.imb.karya.presenter.base.BaseView;

/**
 * Created by korneliussendy on 1/30/18.
 */

public interface ERPickCategoryView extends BaseView {
    void showLoadingCategory();

    void hideLoadingCategory();

    void failedLoadCategory(String message);

    void categoryLoaded(List<GaweCategoryNested> categories);

    void setPageTitle(String title);
}
