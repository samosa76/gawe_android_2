package karya.imb.gawe.view.er.credit;

import java.util.List;

import gawe.imb.karya.model.objects.GawePurchase;
import gawe.imb.karya.presenter.er.credit.ERMyCreditPresenter;
import gawe.imb.karya.presenter.er.credit.ERMyCreditView;
import karya.imb.gawe.R;
import karya.imb.gawe.view.er.ERBaseActivity;

/**
 * Created by korneliussendy on 2/8/18.
 */

public class ERMyCreditActivity extends ERBaseActivity<ERMyCreditPresenter> implements ERMyCreditView {
    @Override
    protected ERMyCreditPresenter createPresenter() {
        return new ERMyCreditPresenter(this);
    }

    @Override
    public int setContentView() {
        return R.layout.activity_er_my_credit;
    }

    @Override
    public void showLoadingCredit() {

    }

    @Override
    public void hideLoadingCredit() {

    }

    @Override
    public void failedLoadingCredit(Throwable throwable) {

    }

    @Override
    public void successLoadingCredit(Long credits) {

    }

    @Override
    public void showLoadingPreparePayment() {

    }

    @Override
    public void hideLoadingPreparePayment() {

    }

    @Override
    public void failedPreparePayment(Throwable throwable) {

    }

    @Override
    public void successPreparePayment() {

    }

    @Override
    public void showLoadingPurchaseHistory() {

    }

    @Override
    public void hideLoadingPurchaseHistory() {

    }

    @Override
    public void failedLoadingPurchaseHistory(Throwable throwable) {

    }

    @Override
    public void successLoadingPurchaseHistory(List<GawePurchase> list) {

    }

    @Override
    public void toTopUpPage() {

    }
}
