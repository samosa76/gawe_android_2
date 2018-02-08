package gawe.imb.karya.presenter.router.login;


import gawe.imb.karya.presenter.base.BaseView;

/**
 * Created by korneliussendy on 1/24/18.
 */

public interface LoginView extends BaseView {

    void loginToAccountKit();

    void showLoadingUser();

    void hideLoadingUser();

    void showLoadingLoginToFirebase();

    void hideLoadingToFirebase();

    void userIsBanned(String bannedReason, String bannedUntil, Long bannedUntilMillis);

    void toPreLoginPage();

    void toSignUpPage(String accountId, String phone);

    void toEmployeePage();

    void toEmployerPage();

    void toSelectRolePage();
}
