package gawe.imb.karya.presenter.router.splash;


import android.util.Log;

import com.google.common.base.Strings;

import java.util.concurrent.TimeUnit;

import gawe.imb.karya.model.BuildConfig;
import gawe.imb.karya.model.manager.PermissionManager;
import gawe.imb.karya.model.manager.UserManager;
import gawe.imb.karya.model.modules.FirebaseClient;
import gawe.imb.karya.model.modules.LocalClient;
import gawe.imb.karya.model.objects.Employee;
import gawe.imb.karya.model.objects.Employer;
import gawe.imb.karya.model.objects.GaweSettings;
import gawe.imb.karya.model.objects.User;
import gawe.imb.karya.presenter.base.BasePresenter;
import io.reactivex.Single;

/**
 * Created by korneliussendy on 1/22/18.
 */

public class SplashPresenter extends BasePresenter<SplashView> {

    public SplashPresenter(SplashView view) {
        attachView(view);
    }

    private int trial = 0, maxTrial = 3;
    private int referralAttempt = 0, MAX_ATTEMPT_REFERRAL = 2;
    private String[] permission;

    public void loadBackground() {
        //ambil dan set background image
        view.setBackground(LocalClient.getRandomBackground());
    }

    public void init() {
        checkPermission();
    }

    public void checkPermission() {
        permission = PermissionManager.getSplashPermission();
        view.checkPermission(permission);
    }

    public void permissionGranted(String[] splashPermission) {
        view.checkPlayServiceVersion();
    }

    public void permissionDenied() {
        view.showPermissionRationale();
    }

    public void permissionDeniedPermanently() {
        view.showOpenSettings();
    }

    public void openSettings() {
        view.openSettings();
    }

    public void continueWithoutPermission() {
        view.checkPlayServiceVersion();
    }

    public void returnFromSettings() {
        checkPermission();
    }

    public void playServiceAvailability(boolean googlePlayServicesAvailable) {
        if (googlePlayServicesAvailable) {
            view.checkReferral(0);
        } else {
            view.showPlayServiceNotAvailable();
        }
    }

    public void referralRetrieved(String referral) {
        if (Strings.isNullOrEmpty(referral)) {
            checkUser();
            return;
        }
        view.showLoadingGetReferral();

        //get upline user
        addDisposable(UserManager.getUserByReferral(referral).subscribe(
                user -> {
                    view.hideLoadingReferral();
                    if (!Strings.isNullOrEmpty(user.getId())) {
                        view.saveUpline(user.getId(), user.getFullName());
                    }
                    checkUser();
                },
                throwable -> {
                    if (referralAttempt < maxTrial) {
                        referralAttempt++;
                        referralRetrieved(referral);
                        return;
                    }
                    view.hideLoadingReferral();
                    checkUser();
                    Log.d("SplashPresenter", "error");
                }
        ));
    }

    public void referralFailed(int iteration) {
        if (maxTrial < iteration) {
            iteration++;
            view.checkReferral(iteration);
        } else {
            checkUser();
        }
    }

    private void checkUser() {
        view.log("CHECK USER");
        User u = UserManager.getUserPreference();
        //user not empty loadUser
        if (u != null && !Strings.isNullOrEmpty(u.getId())) {
            //load current user
            loadUserAndSettings(u.getId());
        } else {
            //no user to login page
            loadUserAndSettings("");
        }


//        //SEMI LOGIN -> USER EXIST && SEMILOGIN TRUE
//        if (u != null && UserManager.isSemiLogin()) {
//            view.toPreLoginPage();
//        } else if (u == null && !UserManager.isSemiLogin()) {
//            view.toLoginPage();
//        } else {
//            //userLoggedIn check selected role masuk ke role yg dipilih
//            String role = UserManager.getUserRole();
//            switch (role) {
//                case UserManager.ROLE_EMPLOYEE:
//                    view.toEmployeeHomePage();
//                    break;
//                case UserManager.ROLE_EMPLOYER:
//                    view.toEmployeeHomePage();
//                    break;
//                default:
//                    view.toSelectRolePage();
//                    break;
//            }
//        }
    }

    private void loadUserAndSettings(String userId) {
        view.showLoadingUser();
        //load data
        if (Strings.isNullOrEmpty(userId)) {
            view.log("skipping null user id");
            compositeDisposable.add(
                    FirebaseClient.getSettings().subscribe(gaweSettings -> handleData(new Holder(gaweSettings)))
            );
        } else {
            view.log("USER ID : " + userId);

            Single<Holder> single = Single.zip(
                    FirebaseClient.getUser(userId).doOnError(this::handleFirebaseError),
                    FirebaseClient.getEmployee(userId).doOnError(this::handleFirebaseError),
                    FirebaseClient.getEmployer(userId).doOnError(this::handleFirebaseError),
                    FirebaseClient.getSettings().cache().onErrorReturnItem(new GaweSettings()),
                    Holder::new
            );

            addDisposable(single.timeout(30000, TimeUnit.MILLISECONDS)
                    .subscribe(
                            this::handleData
                            , throwable -> view.errorLoadUser(throwable)
                    )
            );
        }
    }

    private void handleData(Holder holder) {
        view.hideLoadingUser();
        if (holder == null) {
            view.log("HOLDER  null");
        }

        GaweSettings g = holder.gaweSettings;
        if (holder.gaweSettings != null) {
            int myVersion = BuildConfig.VERSION_CODE;
            String[] shits = g.getVersion_android_gawe().split(";");
            int serverVersion = shits.length >= 1 ? Integer.parseInt(shits[0]) : myVersion;
            boolean forceUpdate = shits.length >= 2 && shits[1].trim().equals("1");

            if (myVersion < serverVersion) {
                view.popupUpdateApps(forceUpdate);
                return;
            }
        }

        if (holder.user == null || holder.employee == null || holder.employer == null || !UserManager.isUserLoggedIn()) {
            view.log("SETTINGS " + (holder.gaweSettings == null ? "NULL" : holder.gaweSettings.toString()) + "\n\n" +
                    "USER " + (holder.user == null ? "NULL" : holder.user.toString()) + "\n\n" +
                    "EMPLOYEE " + (holder.employee == null ? "NULL" : holder.employee.toString()) + "\n\n" +
                    "EMPLOYER " + (holder.employer == null ? "NULL" : holder.employer.toString()) + "\n\n"
            );
            view.toLoginPage();
        } else {
            settingsLoaded(holder.gaweSettings);
            view.userLoaded(holder.user);

            view.log("semi login? " + UserManager.isSemiLogin());
            if (UserManager.isSemiLogin()) {
                view.toPreLoginPage();
                return;
            }


            UserManager.saveUserLocationAndDeviceToken(holder.user.getId());

            String role = UserManager.getUserRole();
            switch (role) {
                case UserManager.ROLE_EMPLOYEE:
                    view.toEmployeeHomePage();
                    break;
                case UserManager.ROLE_EMPLOYER:
                    view.toEmployerHomePage();
                    break;
                default:
                    view.toSelectRolePage();
                    break;
            }
        }
        view.hideLoading();
    }


    private void handleFirebaseError(Throwable throwable) {
        if (trial < maxTrial) {
            trial++;
            view.errorExitOrTryAgain(throwable.getMessage());
        } else {
            view.errorExitOrLogout(throwable.getMessage());
        }
    }

    private void settingsLoaded(GaweSettings gaweSettings) {
        view.settingsLoaded(gaweSettings);
    }

    private class Holder {
        GaweSettings gaweSettings;
        User user;
        Employee employee;
        Employer employer;

        Holder(GaweSettings gaweSettings) {
            this.gaweSettings = gaweSettings;
        }

        Holder(User user, Employee employee, Employer employer, GaweSettings gaweSettings) {
            this.gaweSettings = gaweSettings;
            this.user = user;
            this.employee = employee;
            this.employer = employer;
        }

    }

}
