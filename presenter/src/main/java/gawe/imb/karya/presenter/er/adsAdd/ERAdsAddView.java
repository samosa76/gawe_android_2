package gawe.imb.karya.presenter.er.adsAdd;

import gawe.imb.karya.model.objects.GaweCategoryNested;
import gawe.imb.karya.presenter.base.BaseView;

/**
 * Created by korneliussendy on 2/3/18.
 */

public interface ERAdsAddView extends BaseView {
    void categoryLoaded(GaweCategoryNested category);

    void dataLoaded();

    void toPickCategoryPage();

    void openMapSelectPage(double selectedLat, double selectedLng);

    void locationLoaded(String address);

    void showLoadingLocation();

    void hideLoadingLocation();

    void failedLoadingLocation(Throwable message);

    void failedOrIncompleteForm(String errorMessage);

    void showLoadingSaveBursa();

    void hideLoadingSaveBursa();

    void failedSaveBursa(String message);

    void successSaveBursa();
}
