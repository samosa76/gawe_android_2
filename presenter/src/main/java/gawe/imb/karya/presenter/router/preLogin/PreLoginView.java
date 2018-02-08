package gawe.imb.karya.presenter.router.preLogin;


import gawe.imb.karya.model.objects.User;
import gawe.imb.karya.presenter.base.BaseView;

/**
 * Created by korneliussendy on 2/1/18.
 */

public interface PreLoginView extends BaseView {

    void userLoaded(User u);

    void toLoginPage();

    void toSelectRolePage();

    void toLoginSettingsPage();
}
