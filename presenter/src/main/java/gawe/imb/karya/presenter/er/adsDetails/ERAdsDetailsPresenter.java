package gawe.imb.karya.presenter.er.adsDetails;

import com.google.common.base.Strings;
import com.google.gson.Gson;

import gawe.imb.karya.model.manager.BursaManager;
import gawe.imb.karya.model.manager.CategoryManager;
import gawe.imb.karya.model.manager.UserManager;
import gawe.imb.karya.model.objects.GaweBursa;
import gawe.imb.karya.model.objects.GaweCategory;
import gawe.imb.karya.model.objects.UserBulk;
import gawe.imb.karya.presenter.base.BasePresenter;

/**
 * Created by korneliussendy on 1/30/18.
 */

public class ERAdsDetailsPresenter extends BasePresenter<ERAdsDetailsView> {

    private GaweBursa bursa;
    private UserBulk userBulk;
    private GaweCategory category;

    public ERAdsDetailsPresenter(ERAdsDetailsView view) {
        attachView(view);
    }

    public void loadBursaPage(String data) {
        if (Strings.isNullOrEmpty(data)) {
            view.fatalError("Empty Data");
            return;
        }
        try {
            Gson g = new Gson();
            bursa = g.fromJson(data, GaweBursa.class);
        } catch (Exception e) {
            view.fatalError("Invalid Data");
            return;
        }

        loadCategory(bursa.getCategory());
        loadUser(bursa.getEmployerId(), false);
        handleBursa(bursa);
        view.dataLoaded(bursa);
    }

    public void onButtonContactUserClicked() {
        if (userBulk == null)
            loadUser(bursa.getEmployerId(), true);
        else
            view.displayUser();
    }

    private void handleBursa(GaweBursa bursa) {
        if (bursa.getStCancelled()) {
            view.showBursaCanceled();
        } else if (bursa.getStExpired()) {
            view.showBursaExpired();
        } else if (bursa.getStCompleted()) {
            view.showBursaCompleted();
        } else if (bursa.getStApproved()) {
            view.showBursaApproved();
        } else {
            view.showBursaPending();
        }
    }

    private void loadUser(String employerId, boolean shouldOpenUser) {
        view.showLoadingUser();
        addDisposable(UserManager.loadUserBulk(employerId, false).subscribe(
                userBulk -> {
                    this.userBulk = userBulk;
                    view.hideLoadingUser();
                    view.userLoaded(userBulk.getUser(), userBulk.getEmployer(), userBulk.getEmployee());
                    if (shouldOpenUser)
                        view.displayUser();
                },
                throwable -> {
                    view.hideLoading();
                    view.failedLoadingUser();
                }
        ));
    }

    private void loadCategory(String categoryId) {
        view.showLoadingCategory();
        if (Strings.isNullOrEmpty(categoryId)) {
            view.hideLoadingCategory();
            view.categoryLoaded("DEFAULT");
        }
        addDisposable(CategoryManager.getCategory(categoryId).subscribe((gaweCategory, throwable) -> {
            view.hideLoadingCategory();
            if (throwable != null) {
                view.failedLoadingCategory(throwable.getMessage());
                return;
            }
            this.category = gaweCategory;
            view.categoryLoaded(gaweCategory.getCat_name());
        }));
    }

    public void onCallClick(String phone) {
        view.openCall(phone);
    }

    public void onMessageClick(String phone) {
        view.sendMessage(phone);
    }

    public void onAddContactClick(String phone) {
        view.addToContact(userBulk.getUser().getFullName(), phone);
    }

    public void onAdsCancelClick() {
        view.showPromptCancel();
    }

    public void cancelAds() {
        view.showLoadingCancelAds();
        addDisposable(BursaManager.cancelAds(bursa.getId())
                .subscribe(() -> {
                    view.hideLoadingCancelAds();
                    view.successCancelAds();
                }, throwable -> {
                    view.hideLoadingCancelAds();
                    view.failedCancelAds(throwable);
                }));
    }
}
