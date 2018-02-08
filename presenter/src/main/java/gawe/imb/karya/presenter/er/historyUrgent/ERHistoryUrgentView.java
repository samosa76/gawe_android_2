package gawe.imb.karya.presenter.er.historyUrgent;

import java.util.List;

import gawe.imb.karya.model.objects.GaweHistoryUrgent;
import gawe.imb.karya.presenter.base.BaseView;

/**
 * Created by korneliussendy on 2/7/18.
 */

public interface ERHistoryUrgentView extends BaseView {

    void showLoadingLoadHistoryEmployee();

    void hideLoadingLoadHistoryEmployee();

    void failedLoadingLoadHistoryEmployee(Throwable throwable);

    void successLoadingLoadHistoryEmployee(List<GaweHistoryUrgent> list);

    void showLoadingLoadMoreHistoryEmployee();

    void hideLoadingLoadMoreHistoryEmployee();

    void failedLoadingLoadMoreHistoryEmployee(Throwable throwable);

    void successLoadingLoadMoreHistoryEmployee(List<GaweHistoryUrgent> list);

    void toHistoryUrgentDetailsPage(GaweHistoryUrgent data);

    void clearList();
}
