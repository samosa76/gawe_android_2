package gawe.imb.karya.presenter.er.adsDetails;

import gawe.imb.karya.model.objects.Employee;
import gawe.imb.karya.model.objects.Employer;
import gawe.imb.karya.model.objects.GaweBursa;
import gawe.imb.karya.model.objects.User;
import gawe.imb.karya.presenter.base.BaseView;

/**
 * Created by korneliussendy on 1/30/18.
 */

public interface ERAdsDetailsView extends BaseView {

    void fatalError(String message);

    void dataLoaded(GaweBursa bursa);

    void showLoadingUser();

    void hideLoadingUser();

    void failedLoadingUser();

    void userLoaded(User user, Employer employer, Employee employee);

    void displayUser();

    void showLoadingCategory();

    void hideLoadingCategory();

    void failedLoadingCategory(String message);

    void categoryLoaded(String categoryName);

    void openCall(String phone);

    void sendMessage(String phone);

    void addToContact(String employerName, String phone);

    void showBursaCanceled();

    void showBursaExpired();

    void showBursaCompleted();

    void showBursaApproved();

    void showBursaPending();

    void showPromptCancel();

    void showLoadingCancelAds();

    void hideLoadingCancelAds();

    void successCancelAds();

    void failedCancelAds(Throwable throwable);
}
