package karya.imb.gawe.view.preLogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import gawe.imb.karya.model.objects.User;
import gawe.imb.karya.presenter.router.preLogin.PreLoginPresenter;
import gawe.imb.karya.presenter.router.preLogin.PreLoginView;
import karya.imb.gawe.GlideApp;
import karya.imb.gawe.R;
import karya.imb.gawe.mvp.MvpActivity;
import karya.imb.gawe.view.login.LoginActivity;
import karya.imb.gawe.view.loginSettings.LoginSettingsActivity;
import karya.imb.gawe.view.selectRole.SelectRoleActivity;

/**
 * Created by korneliussendy on 2/1/18.
 */

public class PreLoginActivity extends MvpActivity<PreLoginPresenter> implements PreLoginView {

    @BindView(R.id.vMain) View vMain;
    @BindView(R.id.ivProfilePic) ImageView ivProfilePic;
    @BindView(R.id.tvName) TextView tvName;
    @BindView(R.id.vSettings) View vSettings;

    @Override
    protected PreLoginPresenter createPresenter() {
        return new PreLoginPresenter(this);
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_pre_login;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vSettings.setOnClickListener(view -> presenter.onSettingsClick());
        vMain.setOnClickListener(view -> presenter.onProfileClick());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getSavedProfile();
    }

    @Override
    public void toLoginPage() {
        Intent i = new Intent(this, LoginActivity.class);
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
    public void toLoginSettingsPage() {
        Intent i = new Intent(this, LoginSettingsActivity.class);
        startActivity(i);
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
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

}
