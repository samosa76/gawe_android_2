package gawe.imb.karya.presenter.er.historyEmployee;

import java.util.List;

import gawe.imb.karya.model.objects.GaweHistoryEmployee;
import gawe.imb.karya.presenter.base.BaseView;

/**
 * Created by korneliussendy on 2/7/18.
 */

public interface ERHistoryEmployeeView extends BaseView {

    void showLoadingLoadHistoryEmployee();

    void hideLoadingLoadHistoryEmployee();

    void failedLoadingLoadHistoryEmployee(Throwable throwable);

    void successLoadingLoadHistoryEmployee(List<GaweHistoryEmployee> list);

    void showLoadingLoadMoreHistoryEmployee();

    void hideLoadingLoadMoreHistoryEmployee();

    void failedLoadingLoadMoreHistoryEmployee(Throwable throwable);

    void successLoadingLoadMoreHistoryEmployee(List<GaweHistoryEmployee> list);

    void toHistoryEmployeeDetailsPage(GaweHistoryEmployee data);

    void clearList();

}
