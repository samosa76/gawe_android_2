package gawe.imb.karya.presenter.er.historyUrgent;

import java.util.List;

import gawe.imb.karya.model.objects.GaweHistoryUrgent;
import gawe.imb.karya.presenter.base.BaseView;

/**
 * Created by korneliussendy on 2/7/18.
 */

public abstract class ERHistoryUrgentView implements BaseView {

    public abstract void showLoadingLoadHistoryEmployee();

    public abstract void hideLoadingLoadHistoryEmployee();

    public abstract void failedLoadingLoadHistoryEmployee(Throwable throwable);

    public abstract void successLoadingLoadHistoryEmployee(List<GaweHistoryUrgent> list);

    public abstract void showLoadingLoadMoreHistoryEmployee();

    public abstract void hideLoadingLoadMoreHistoryEmployee();

    public abstract void failedLoadingLoadMoreHistoryEmployee(Throwable throwable);

    public abstract void successLoadingLoadMoreHistoryEmployee(List<GaweHistoryUrgent> list);

    public abstract void toHistoryUrgentDetailsPage(GaweHistoryUrgent data);

    public abstract void clearList();
}
