package gawe.imb.karya.presenter.router.login;


import com.google.common.base.Strings;

import gawe.imb.karya.model.manager.AccountKitManager;
import gawe.imb.karya.model.manager.UserManager;
import gawe.imb.karya.model.modules.FirebaseClient;
import gawe.imb.karya.model.modules.LocalClient;
import gawe.imb.karya.model.objects.User;
import gawe.imb.karya.model.objects.UserBulk;
import gawe.imb.karya.presenter.base.BasePresenter;

/**
 * Created by korneliussendy on 1/24/18.
 */

public class LoginPresenter extends BasePresenter<LoginView> {

    public LoginPresenter(LoginView loginView) {
        attachView(loginView);
    }

    int getResBackground(int resInt) {
        return (resInt <= 0) ? LocalClient.getRandomBackground() : resInt;
    }

    public int getResBackground() {
        return LocalClient.getLoginBackground();
    }

    public void onLoginButtonClicked() {
//        checkUserLoggedIn
        if (UserManager.isUserLoggedIn()) {
            loadUser(UserManager.getUserPreference().getId());
        } else {
            view.loginToAccountKit();
        }
    }

    private void loadUser(String userId) {
        view.log("loading user");
        if (Strings.isNullOrEmpty(userId)) {
            view.hideLoading();
            view.log("null user");
        }
        view.showLoadingUser();
        UserManager.loadUserBulk(userId, true).subscribe(
                userBulk -> {
                    view.hideLoadingUser();
                    navigateToNextPage(userBulk);
                }, throwable -> {
                    view.hideLoadingUser();
                    view.loginToAccountKit();
                });
    }

    public void onLoadingAccountKitSuccess(String accountKitAccessToken) {
        view.showLoadingLoginToFirebase();
        UserManager.getCustomToken(accountKitAccessToken).subscribe(
                this::tryLoginToFirebase,
                throwable -> onLoadingAccountKitFailed(throwable.getLocalizedMessage())
        );
    }

    public void onLoadingAccountKitFailed(String reason) {
        view.hideLoadingToFirebase();
    }


    private void tryLoginToFirebase(String firebaseToken) {
        view.log("tryLoginToFirebase => access token : " + firebaseToken);
        view.showLoadingLoginToFirebase();
        FirebaseClient.loginToFirebase(firebaseToken)
                .subscribe(() -> AccountKitManager.getAccessTokenId()
                        .subscribe(s -> {
                            view.log("getAccessTokenId Success");
                            view.hideLoadingToFirebase();
                            loadUser(s);
                        }), throwable -> view.log(throwable.getLocalizedMessage()));

    }

    private void navigateToNextPage(UserBulk userBulk) {
        if (userBulk == null || userBulk.getUser() == null || !userBulk.getUser().isHasRegistered()) {
            AccountKitManager.getCurrentAccount().doOnSuccess(accountKitData ->
                    view.toSignUpPage(accountKitData.getAccountId(), accountKitData.getPhone()
                    ));
            return;
        }

        User u = userBulk.getUser();
        if (UserManager.isStillBanned(userBulk.getUser(), true)) {
            UserManager.logout();
            view.userIsBanned(u.getBannedReason(), u.getBannedUntil(), u.getBannedUntilMillis());
        }

        if (UserManager.isUserLoggedIn()) {
            view.log("User is logged id");
            if (UserManager.isSemiLogin()) {
                view.toPreLoginPage();
            } else if (UserManager.getUserRole().equals(UserManager.ROLE_EMPLOYEE)) {
                view.toEmployeePage();
            } else if (UserManager.getUserRole().equals(UserManager.ROLE_EMPLOYER)) {
                view.toEmployerPage();
            } else {
                view.toSelectRolePage();
            }
        } else {
            AccountKitManager.getCurrentAccessToken().subscribe(s -> {
                if (Strings.isNullOrEmpty(s)) {
                    view.hideLoading();
                    view.loginToAccountKit();
                } else {
                    loadCurrentUser();
                }
            });
        }

    }

    private void loadCurrentUser() {

    }

//public void onLoginButtonClicked() {
//        view.showLoading();
//        if (UserManager.isUserLoggedIn()) {
//            view.log("User is logged id");
//            if (UserManager.isSemiLogin()) {
//                view.toPreLoginPage();
//            } else if (UserManager.getUserRole().equals(UserManager.ROLE_EMPLOYEE)) {
//                view.toEmployeePage();
//            } else if (UserManager.getUserRole().equals(UserManager.ROLE_EMPLOYER)) {
//                view.toEmployerPage();
//            } else {
//                view.toSelectRolePage();
//            }
//        } else {
//            AccountKitManager.getCurrentAccessToken().subscribe(s -> {
//                if (Strings.isNullOrEmpty(s)) {
//                    view.hideLoading();
//                    view.loginToAccountKit();
//                } else {
//                    loadCurrentUser();
//                }
//            });
//        }
//    }
//
//    private void loadCurrentUser() {
//        AccountKitManager.getCurrentAccount().subscribe((accountKitData, throwable) -> {
//            view.hideLoading();
//            if (throwable != null) {
//                view.errorLoadCurrentUser();
//                return;
//            }
//
//        });
//    }
//
//    public void loginToFirebase(String accountKitToken) {
//        view.showLoading();
//        FirebaseClient.loginToFirebase(accountKitToken).subscribe(
//                () -> {
//                    view.loginSuccessful();
//                }, throwable -> {
//                    view.loginFailed();
//                });
//
//    }
//
//public void loadUserBulk(String userId) {
//        UserManager.loadUserBulk(userId).subscribe(
//                userBulk -> view.allUserLoaded(userBulk.getUser(), userBulk.getEmployee(), userBulk.getEmployer()
//                )
//        );
//    }


}
