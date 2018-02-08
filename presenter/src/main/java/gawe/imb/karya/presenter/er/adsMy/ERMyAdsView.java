package gawe.imb.karya.presenter.er.adsMy;

import java.util.List;

import gawe.imb.karya.model.objects.GaweBursa;
import gawe.imb.karya.model.objects.GaweCategoryNested;
import gawe.imb.karya.presenter.base.BaseView;

/**
 * Created by korneliussendy on 1/26/18.
 */

public interface ERMyAdsView extends BaseView {
    void stillLoadingBursa(int page, int dataSize);

    void showLoadingBursa();

    void hideLoadingBursa();

    void errorLoadingBursa(String message);

    void allDataLoaded();

    void bursaLoaded(List<GaweBursa> list);

    void clearCurrentBursa();

    void toAddAdsPage(GaweCategoryNested category);

    void toAdsDetailsPage(GaweBursa gaweBursa);

    void pickCategoryPage();
}
