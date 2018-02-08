package gawe.imb.karya.presenter.router.preLogin;

import gawe.imb.karya.model.manager.UserManager;
import gawe.imb.karya.model.objects.User;
import gawe.imb.karya.presenter.base.BasePresenter;

/**
 * Created by korneliussendy on 2/1/18.
 */

public class PreLoginPresenter extends BasePresenter<PreLoginView> {

    public PreLoginPresenter(PreLoginView view) {
        attachView(view);
    }

    public void getSavedProfile() {
        User u = UserManager.getUserPreference();
        if (u == null) {
            view.toLoginPage();
        } else {
            view.userLoaded(u);
        }
    }

    public void onProfileClick() {
        User u = UserManager.getUserPreference();
        if (u == null) {
            view.toLoginPage();
            return;
        }
        UserManager.semiLogin();
        UserManager.saveUserLocationAndDeviceToken(u.getId());
        view.toSelectRolePage();

    }

    public void onSettingsClick() {
        view.toLoginSettingsPage();
    }
}
