package gawe.imb.karya.presenter.er.creditHistory;

import java.util.List;

import gawe.imb.karya.model.objects.GawePurchase;
import gawe.imb.karya.presenter.base.BaseView;

/**
 * Created by korneliussendy on 2/7/18.
 */

public interface ERCreditHistoryView extends BaseView {

    void showLoadingLoadPurchaseHistory();

    void hideLoadingLoadPurchaseHistory();

    void failedLoadingLoadPurchaseHistory(Throwable throwable);

    void successLoadingLoadPurchaseHistory(List<GawePurchase> list);

    void showLoadingLoadMorePurchaseHistory();

    void hideLoadingLoadMorePurchaseHistory();

    void failedLoadingLoadMorePurchaseHistory(Throwable throwable);

    void successLoadingLoadMorePurchaseHistory(List<GawePurchase> list);

    void clearPurchaseHistory();
}
