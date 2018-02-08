package karya.imb.gawe.view.loginSettings;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import gawe.imb.karya.model.objects.User;
import gawe.imb.karya.presenter.router.loginSettings.LoginSettingsPresenter;
import gawe.imb.karya.presenter.router.loginSettings.LoginSettingsView;
import karya.imb.gawe.GlideApp;
import karya.imb.gawe.R;
import karya.imb.gawe.view.er.ERBaseActivity;
import karya.imb.gawe.view.login.LoginActivity;
import karya.imb.gawe.view.selectRole.SelectRoleActivity;
import karya.imb.gawe.view.splash.SplashActivity;

/**
 * Created by korneliussendy on 2/2/18.
 */

public class LoginSettingsActivity extends ERBaseActivity<LoginSettingsPresenter> implements LoginSettingsView {

    @BindView(R.id.ivProfilePic) ImageView ivProfilePic;
    @BindView(R.id.tvName) TextView tvName;
    @BindView(R.id.vMain) LinearLayout vMain;
    @BindView(R.id.vLogout) LinearLayout vLogout;

    @Override
    protected LoginSettingsPresenter createPresenter() {
        return new LoginSettingsPresenter(this);
    }

    @Override
    protected boolean showToolbar() {
        return true;
    }

    @Override
    protected boolean showLeftIcon() {
        return true;
    }

    @Override
    public int setLeftIconRes() {
        return R.drawable.ic_left_arrow;
    }

    @Override
    public View.OnClickListener onLeftIconClick() {
        return view -> onBackPressed();
    }

    @Override
    protected boolean showTitle() {
        return true;
    }

    @Override
    public int setContentView() {
        return R.layout.activity_login_settings;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("LOGIN SETTINGS");
        vMain.setOnClickListener(view -> presenter.onProfileClick());
        vLogout.setOnClickListener(view -> presenter.onLogoutButtonClick());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getSavedProfile();
    }

    @Override
    public void userLoaded(User u) {
        GlideApp.with(this)
                .load(u.getProfile_pic())
                .placeholder(R.drawable.logo_g_yellow_transparent)
                .error(R.drawable.logo_g_yellow_transparent)
                .into(ivProfilePic);
        tvName.setText(u.getFullName());
    }

    @Override
    public void toLoginPage() {
        Intent i = new Intent(this, LoginActivity.class);
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
    public void toSplashPage() {
        Intent i = new Intent(this, SplashActivity.class);
        startActivity(i);
        finishAffinity();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
