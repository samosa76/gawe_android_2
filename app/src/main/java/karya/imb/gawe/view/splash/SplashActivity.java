package karya.imb.gawe.view.splash;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import gawe.imb.karya.model.objects.GaweSettings;
import gawe.imb.karya.model.objects.User;
import gawe.imb.karya.presenter.router.splash.SplashPresenter;
import gawe.imb.karya.presenter.router.splash.SplashView;
import karya.imb.gawe.BuildConfig;
import karya.imb.gawe.R;
import gawe.imb.karya.model.modules.DynamicLinkService;
import karya.imb.gawe.mvp.MvpActivity;
import gawe.imb.karya.mainlibs.utils.Constants;
import gawe.imb.karya.model.utils.Helper;
import karya.imb.gawe.view.er.home.ERHomeActivity;
import karya.imb.gawe.view.login.LoginActivity;
import karya.imb.gawe.view.preLogin.PreLoginActivity;
import karya.imb.gawe.view.selectRole.SelectRoleActivity;

/**
 * Created by korneliussendy on 1/22/18.
 */

public class SplashActivity extends MvpActivity<SplashPresenter> implements SplashView {

    public static final String BACKGROUND_KEY = "backgroundImage";
    @BindView(R.id.ivBackground) ImageView ivBackground;
    @BindView(R.id.tvStatus) TextView tvStatus;
    @BindView(R.id.tvVersion) TextView tvVersion;
    @BindView(R.id.progressBar) ProgressBar progressBar;

    private static int OPEN_SETTINGS = 1901;

    int resBackground;

    @Override
    protected SplashPresenter createPresenter() {
        return new SplashPresenter(this);
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewCompat.setTranslationZ(progressBar, 100);
        presenter.loadBackground();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.init();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.clearDisposable();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.done();
    }

    @Override
    public void setBackground(int resDrawable) {
        resBackground = resDrawable;
        ivBackground.setImageDrawable(ContextCompat.getDrawable(this, resDrawable));
    }

    @Override
    public void settingsLoaded(GaweSettings settings) {

    }

    @Override
    public void checkPermission(String[] splashPermission) {
        if (splashPermission == null || splashPermission.length == 0) {
            presenter.permissionGranted(splashPermission);
        }

        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.requestEachCombined(splashPermission)
                .subscribe(permission -> {
                    // will emit 1 Permission object
                    if (permission.granted) {
                        // All permissions are granted !
                        presenter.permissionGranted(splashPermission);
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        presenter.permissionDenied();
                    } else {
                        presenter.permissionDeniedPermanently();
                    }
                });
    }

    @Override
    public void showPermissionRationale() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("permission required");
        builder.setPositiveButton("ok", (dialogInterface, i) -> presenter.checkPermission());
        builder.create().show();
    }

    @Override
    public void showOpenSettings() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("permission required");
        builder.setPositiveButton("Open Settings", (dialogInterface, i) -> presenter.openSettings());
        builder.setNegativeButton("Cancel", (dialogInterface, i) -> presenter.continueWithoutPermission());
        builder.create().show();
    }

    @Override
    public void openSettings() {
        Intent i = new Intent(
                android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.parse("package:" + BuildConfig.APPLICATION_ID)
        );
        startActivityForResult(i, OPEN_SETTINGS);

    }

    @Override
    public void checkPlayServiceVersion() {
        presenter.playServiceAvailability(Helper.isGooglePlayServicesAvailable(this));
    }

    @Override
    public void showPlayServiceNotAvailable() {
        //TODO popup tapi engga bisa cancel
    }

    @Override
    public void checkReferral(int iteration) {
        DynamicLinkService.checkCode(new DynamicLinkService.GaweDynamicListener() {
            @Override
            public void onSuccess(String referral) {
                log(referral);
                presenter.referralRetrieved(referral);
            }

            @Override
            public void onFailed(Exception failed) {
                presenter.referralFailed(iteration);
            }
        }, this, BuildConfig.DEBUG);
    }

    @Override
    public void showLoadingGetReferral() {
        progressBar.setVisibility(View.VISIBLE);
        tvStatus.setText("Loading Referral");
    }

    @Override
    public void hideLoadingReferral() {
        progressBar.setVisibility(View.INVISIBLE);
        tvStatus.setText("");
    }

    @Override
    public void saveUpline(String id, String fullName) {
        savePreference(Constants.UPLINE_ID, id);
        savePreference(Constants.UPLINE_NAME, fullName);
        Toast.makeText(this, "Hallo Teman " + fullName, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadingUser() {
        progressBar.setVisibility(View.VISIBLE);
        tvStatus.setText("Loading User");
    }

    @Override
    public void hideLoadingUser() {
        progressBar.setVisibility(View.INVISIBLE);
        tvStatus.setText("");
    }

    @Override
    public void errorLoadUser(Throwable throwable) {
        Log.d(TAG, "errorLoadUser", throwable);
    }

    @Override
    public void userLoaded(User user) {

    }

    @Override
    public void popupUpdateApps(boolean forceUpdate) {
        log("FORCE UPDATE " + forceUpdate);
    }

    @Override
    public void toLoginPage() {
        Intent i = new Intent(this, LoginActivity.class);
        i.putExtra(BACKGROUND_KEY, resBackground);
        startActivity(i);
        finish();
    }

    @Override
    public void toPreLoginPage() {
        Intent i = new Intent(this, PreLoginActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void toEmployeeHomePage() {

    }

    @Override
    public void toEmployerHomePage() {
        Intent i = new Intent(this, ERHomeActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void toSelectRolePage() {
        Intent i = new Intent(this, SelectRoleActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        tvStatus.setText("Loading");
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
        tvStatus.setText("");
    }

    @Override
    public void errorExitOrTryAgain(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.try_again, (dialogInterface, i) -> presenter.init());
        builder.setNegativeButton(R.string.exit, (dialogInterface, i) -> finish());
        builder.create().show();
    }

    @Override
    public void errorExitOrLogout(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.logout, (dialogInterface, i) -> presenter.init());
        builder.setNegativeButton(R.string.exit, (dialogInterface, i) -> finish());
        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == OPEN_SETTINGS) {
            presenter.returnFromSettings();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
