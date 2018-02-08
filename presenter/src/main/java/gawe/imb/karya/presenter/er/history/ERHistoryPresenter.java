package gawe.imb.karya.presenter.er.history;

import gawe.imb.karya.presenter.base.BasePresenter;

/**
 * Created by korneliussendy on 1/26/18.
 */

public class ERHistoryPresenter extends BasePresenter<ERHistoryView> {

    public ERHistoryPresenter(ERHistoryView view) {
        attachView(view);
    }

    public void onUrgentHistoryClick() {
        view.toUrgentHistoryPage();
    }

    public void onEmployeeHistoryClick() {
        view.toEmployeeHistoryPage();
    }
}
