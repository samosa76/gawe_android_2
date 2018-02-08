package gawe.imb.karya.presenter.router.loginSettings;


import gawe.imb.karya.model.objects.User;
import gawe.imb.karya.presenter.base.BaseView;

/**
 * Created by korneliussendy on 2/2/18.
 */

public interface LoginSettingsView extends BaseView {

    void userLoaded(User u);

    void toLoginPage();

    void toSelectRolePage();

    void toSplashPage();
}
