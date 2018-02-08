package gawe.imb.karya.presenter.er.pickCategoryFragment;

import java.util.List;

import gawe.imb.karya.model.objects.GaweCategoryNested;
import gawe.imb.karya.presenter.base.BaseView;

/**
 * Created by korneliussendy on 2/6/18.
 */

public interface ERPickCategoryFragmentView extends BaseView {
    void setPageTitle(String title);

    void showLoadingCategory();

    void hideLoadingCategory();

    void failedLoadCategory(String message);

    void categoryLoaded(List<GaweCategoryNested> categories);
}
