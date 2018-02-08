package gawe.imb.karya.presenter.er.partners;

import java.util.List;

import gawe.imb.karya.model.objects.GaweBrowse;
import gawe.imb.karya.presenter.base.BaseView;

/**
 * Created by korneliussendy on 1/26/18.
 */

public interface ERPartnersView extends BaseView {
    void toFilterPage();

    void failedLoadBrowse(String message);

    void browseLoaded(List<GaweBrowse> results);

    void stillLoadingBrowse(int page, int perPage);

    void allBrowseLoaded();

    void showLoadingBrowse();

    void hideLoadingBrowse();

    void clearCurrentData();

    void setDistance(int distance);

    void dataLoadedZeroResult();

}
