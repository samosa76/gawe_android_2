package gawe.imb.karya.presenter.er.urgentPassive;

import java.util.List;

import gawe.imb.karya.model.objects.GaweCategoryNested;
import gawe.imb.karya.presenter.base.BaseView;

/**
 * Created by korneliussendy on 2/6/18.
 */

public interface ERUrgentPassiveView extends BaseView {

    void loadCategoryFailed(String message);

    void categoryLoadedButEmpty();

    void categoryLoaded(List<GaweCategoryNested> gaweCategories);

    void showLoadingCategory();

    void hideLoadingCategory();

    void clearList();

    void failedSelectCategory();

    void toUrgentFormPage(GaweCategoryNested category);
}
