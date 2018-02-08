package karya.imb.gawe.view.er.historyUrgent;

import java.util.List;

import gawe.imb.karya.model.objects.GaweHistoryUrgent;
import gawe.imb.karya.presenter.er.historyUrgent.ERHistoryUrgentPresenter;
import gawe.imb.karya.presenter.er.historyUrgent.ERHistoryUrgentView;
import karya.imb.gawe.R;
import karya.imb.gawe.view.er.ERBaseActivity;

/**
 * Created by korneliussendy on 2/8/18.
 */

public class ERHistoryUrgentActivity extends ERBaseActivity<ERHistoryUrgentPresenter> implements ERHistoryUrgentView {
    @Override
    protected ERHistoryUrgentPresenter createPresenter() {
        return new ERHistoryUrgentPresenter(this);
    }

    @Override
    public int setContentView() {
        return R.layout.activity_er_history_urgent;
    }

    @Override
    public void showLoadingLoadHistoryEmployee() {

    }

    @Override
    public void hideLoadingLoadHistoryEmployee() {

    }

    @Override
    public void failedLoadingLoadHistoryEmployee(Throwable throwable) {

    }

    @Override
    public void successLoadingLoadHistoryEmployee(List<GaweHistoryUrgent> list) {

    }

    @Override
    public void showLoadingLoadMoreHistoryEmployee() {

    }

    @Override
    public void hideLoadingLoadMoreHistoryEmployee() {

    }

    @Override
    public void failedLoadingLoadMoreHistoryEmployee(Throwable throwable) {

    }

    @Override
    public void successLoadingLoadMoreHistoryEmployee(List<GaweHistoryUrgent> list) {

    }

    @Override
    public void toHistoryUrgentDetailsPage(GaweHistoryUrgent data) {

    }

    @Override
    public void clearList() {

    }
}
