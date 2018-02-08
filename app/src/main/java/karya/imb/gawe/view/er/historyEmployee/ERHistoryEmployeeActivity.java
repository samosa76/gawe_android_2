package karya.imb.gawe.view.er.historyEmployee;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.List;

import gawe.imb.karya.model.objects.GaweHistoryEmployee;
import gawe.imb.karya.presenter.er.historyEmployee.ERHistoryEmployeePresenter;
import gawe.imb.karya.presenter.er.historyEmployee.ERHistoryEmployeeView;
import karya.imb.gawe.R;
import karya.imb.gawe.view.er.ERBaseActivity;

/**
 * Created by korneliussendy on 2/8/18.
 */

public class ERHistoryEmployeeActivity extends ERBaseActivity<ERHistoryEmployeePresenter> implements ERHistoryEmployeeView {

    @Override
    protected ERHistoryEmployeePresenter createPresenter() {
        return new ERHistoryEmployeePresenter(this);
    }

    @Override
    public int setContentView() {
        return R.layout.activity_er_history_employee;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    public void successLoadingLoadHistoryEmployee(List<GaweHistoryEmployee> list) {

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
    public void successLoadingLoadMoreHistoryEmployee(List<GaweHistoryEmployee> list) {

    }

    @Override
    public void toHistoryEmployeeDetailsPage(GaweHistoryEmployee data) {

    }

    @Override
    public void clearList() {

    }
}
