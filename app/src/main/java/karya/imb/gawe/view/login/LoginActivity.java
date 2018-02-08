package karya.imb.gawe.view.login;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;
import com.facebook.accountkit.ui.SkinManager;
import com.facebook.accountkit.ui.UIManager;

import butterknife.BindView;
import gawe.imb.karya.presenter.router.login.LoginPresenter;
import gawe.imb.karya.presenter.router.login.LoginView;
import karya.imb.gawe.R;
import karya.imb.gawe.mvp.MvpActivity;
import gawe.imb.karya.mainlibs.utils.Constants;
import karya.imb.gawe.view.er.home.ERHomeActivity;
import karya.imb.gawe.view.preLogin.PreLoginActivity;
import karya.imb.gawe.view.selectRole.SelectRoleActivity;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by korneliussendy on 1/24/18.
 */

public class LoginActivity extends MvpActivity<LoginPresenter> implements LoginView {

    @BindView(R.id.ivBackground) ImageView ivBackground;
    @BindView(R.id.btnLogin) FancyButton btnLogin;
    @BindView(R.id.vLayer) View vLayer;
    @BindView(R.id.loaderBackground) View loaderBackground;
    @BindView(R.id.progressBar) ProgressBar progressBar;

    public static int APP_REQUEST_CODE = 99;

    @Override
    protected int layoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        int resImage = presenter.getResBackground(getIntent().getExtras().getInt(SplashActivity.BACKGROUND_KEY, -1));
        int resImage = presenter.getResBackground();
        ivBackground.setImageDrawable(ContextCompat.getDrawable(this, resImage));
        vLayer.setOnClickListener(view -> presenter.onLoginButtonClicked());
        btnLogin.setOnClickListener(view -> presenter.onLoginButtonClicked());
        loaderBackground.setOnClickListener(view -> {
        });


    }

    @Override
    public void loginToAccountKit() {
        ///SKIN STYLE AND LAYOUT
        UIManager uiManager;
        uiManager = new SkinManager(
                SkinManager.Skin.CLASSIC,
                ContextCompat.getColor(this, R.color.colorAccent));

        final Intent intent = new Intent(this, AccountKitActivity.class);
        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(
                        LoginType.PHONE,
                        AccountKitActivity.ResponseType.TOKEN // or .ResponseType.TOKEN
                );
        configurationBuilder.setFacebookNotificationsEnabled(false);
        configurationBuilder.setUIManager(uiManager);

        // ... perform additional configuration ...
        intent.putExtra(
                AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                configurationBuilder.build());
        startActivityForResult(intent, APP_REQUEST_CODE);
    }

    @Override
    public void showLoadingUser() {
        showLoading();
    }

    @Override
    public void hideLoadingUser() {
        hideLoading();
    }

    @Override
    public void showLoadingLoginToFirebase() {
        showLoading();
    }

    @Override
    public void hideLoadingToFirebase() {
        hideLoading();
    }

    @Override
    public void userIsBanned(String bannedReason, String bannedUntil, Long bannedUntilMillis) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("user banned");
        builder.setMessage(bannedReason + " " + bannedUntil);
        builder.setPositiveButton("OK", (dialogInterface, i) -> finishAffinity());
        builder.create().show();
    }

    @Override
    public void toPreLoginPage() {
        Intent i = new Intent(this, PreLoginActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void toSignUpPage(String accountId, String phone) {
        Intent i = new Intent(this, PreLoginActivity.class);
        i.putExtra(Constants.PHONE_NUMBER, phone);
        i.putExtra(Constants.ACCOUNT_ID, accountId);
        startActivity(i);
    }

    @Override
    public void toEmployeePage() {
        toast("to employee page");
    }

    @Override
    public void toEmployerPage() {
        Intent i = new Intent(this, ERHomeActivity.class);
        startActivity(i);
        finishAffinity();
    }

    @Override
    public void toSelectRolePage() {
        Intent i = new Intent(this, SelectRoleActivity.class);
        startActivity(i);
        finishAffinity();
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == APP_REQUEST_CODE) { // confirm that this response matches your request
            log("incoming FB results");
            AccountKitLoginResult loginResult = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            String toastMessage;
            if (loginResult.getError() != null) {
                log(loginResult.getError().getUserFacingMessage());
                presenter.onLoadingAccountKitFailed(loginResult.getError().getUserFacingMessage());
            } else if (loginResult.wasCancelled()) {
                toastMessage = "Login Cancelled";
                log(toastMessage);
            } else {
                if (loginResult.getAccessToken() != null) {
                    log("onLoadingAccountKitSuccess");
                    presenter.onLoadingAccountKitSuccess(loginResult.getAccessToken().getToken());
                } else {
                    toastMessage = String.format(
                            "Success:%s...",
                            loginResult.getAuthorizationCode().substring(0, 10));
                    log(toastMessage);
                }
            }

        }
    }

    @Override
    protected void onDestroy() {
        hideLoading();
        super.onDestroy();
        presenter.done();
    }


    @Override
    public void showLoading() {
        ViewCompat.setTranslationZ(loaderBackground, 100);
        loaderBackground.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loaderBackground.setVisibility(View.VISIBLE);
    }
}
