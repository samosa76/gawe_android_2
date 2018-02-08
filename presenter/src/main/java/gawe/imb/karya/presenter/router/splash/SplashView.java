package gawe.imb.karya.presenter.router.splash;

import gawe.imb.karya.model.objects.GaweSettings;
import gawe.imb.karya.model.objects.User;
import gawe.imb.karya.presenter.base.BaseView;

/**
 * Created by korneliussendy on 1/22/18.
 */

public interface SplashView extends BaseView {

    void setBackground(int resDrawable);

    void checkPermission(String[] splashPermission);

    void checkPlayServiceVersion();

    void showPermissionRationale();

    void showOpenSettings();

    void showPlayServiceNotAvailable();

    void openSettings();

    void checkReferral(int iteration);

    void showLoadingGetReferral();

    void hideLoadingReferral();

    void saveUpline(String id, String fullName);

    void settingsLoaded(GaweSettings gaweSettings);

    void userLoaded(User user);

    void showLoadingUser();

    void hideLoadingUser();

    void errorLoadUser(Throwable throwable);

    void toLoginPage();

    void toPreLoginPage();

    void toEmployeeHomePage();

    void toEmployerHomePage();

    void toSelectRolePage();

    void errorExitOrTryAgain(String message);

    void errorExitOrLogout(String message);

    void popupUpdateApps(boolean forceUpdate);

}
