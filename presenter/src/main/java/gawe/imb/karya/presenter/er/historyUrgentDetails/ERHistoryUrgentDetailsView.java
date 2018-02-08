package gawe.imb.karya.presenter.er.historyUrgentDetails;

import gawe.imb.karya.model.objects.GaweHistoryUrgent;
import gawe.imb.karya.presenter.base.BaseView;

/**
 * Created by korneliussendy on 2/7/18.
 */

interface ERHistoryUrgentDetailsView extends BaseView {

    void showLoadingData();

    void hideLoadingData();

    void failedLoadingData(Throwable throwable);

    void successLoadingData(GaweHistoryUrgent data);


}
