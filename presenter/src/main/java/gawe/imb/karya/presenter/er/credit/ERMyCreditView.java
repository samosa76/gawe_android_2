package gawe.imb.karya.presenter.er.credit;

import java.util.List;

import gawe.imb.karya.model.objects.GawePurchase;
import gawe.imb.karya.presenter.base.BaseView;

/**
 * Created by korneliussendy on 2/7/18.
 */

public interface ERMyCreditView extends BaseView {

    void showLoadingCredit();

    void hideLoadingCredit();

    void failedLoadingCredit(Throwable throwable);

    void successLoadingCredit(Long credits);

    void showLoadingPreparePayment();

    void hideLoadingPreparePayment();

    void failedPreparePayment(Throwable throwable);

    void successPreparePayment();

    void showLoadingPurchaseHistory();

    void hideLoadingPurchaseHistory();

    void failedLoadingPurchaseHistory(Throwable throwable);

    void successLoadingPurchaseHistory(List<GawePurchase> list);

    void toTopUpPage();
}
