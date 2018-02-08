package karya.imb.gawe.view.er;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import gawe.imb.karya.presenter.base.BasePresenter;
import gawe.imb.karya.presenter.base.BaseView;
import karya.imb.gawe.R;
import gawe.imb.karya.model.utils.Helper;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by korneliussendy on 1/26/18.
 */

public abstract class ERBaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {

    @BindView(R.id.ivMenu) ImageView ivMenu;
    @BindView(R.id.ivIconRight) ImageView ivIconRight;
    @BindView(R.id.tvButtonRight) TextView tvButtonRight;
    @BindView(R.id.llRight) View llRight;
    @BindView(R.id.vLogo) View vLogo;
    @BindView(R.id.toolbarTitle) TextView toolbarTitle;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.rlParent) RelativeLayout rlParent;
    @BindView(R.id.flMain) FrameLayout flMain;
    @BindView(R.id.drawerImage) CircleImageView drawerImage;
    @BindView(R.id.tvUserName) TextView tvUserName;
    @BindView(R.id.profileBox) LinearLayout profileBox;
    @BindView(R.id.navList) ListView navList;
    @BindView(R.id.drawerPane) RelativeLayout drawerPane;
    @BindView(R.id.drawerLayout) DrawerLayout drawerLayout;

    protected P presenter;
    Unbinder unbinder;
    protected String TAG = getClass().getSimpleName();

    protected abstract P createPresenter();

    public abstract int setContentView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        presenter = createPresenter();
        super.onCreate(savedInstanceState);
        View parent = LayoutInflater.from(this).inflate(R.layout.er_drawer_activity, null);
        View content = LayoutInflater.from(this).inflate(setContentView(), null);

        rlParent = parent.findViewById(R.id.rlParent);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        rlParent.addView(content, params);
        setContentView(parent);

        //INIT BASE VIEW
        setContentView(parent);
        unbinder = ButterKnife.bind(this);

        toolbarTitle.setVisibility(showTitle() ? View.VISIBLE : View.GONE);
        vLogo.setVisibility(showLogo() ? View.VISIBLE : View.GONE);
        tvButtonRight.setVisibility(showRightButton() ? View.VISIBLE : View.GONE);
        ivIconRight.setVisibility(showRightIcon() ? View.VISIBLE : View.GONE);
        ivMenu.setVisibility(showLeftIcon() ? View.VISIBLE : View.INVISIBLE);

        if (showRightButton() && onRightButtonClick() != null) {
            tvButtonRight.setOnClickListener(onRightButtonClick());
        }

        if (showRightIcon() && onRightIconClick() != null) {
            ivIconRight.setOnClickListener(onRightIconClick());
        }

        if (showLeftIcon() && onLeftIconClick() != null) {
            ivMenu.setOnClickListener(onLeftIconClick());
        }

        if (setLeftIconRes() > 0) {
            ivMenu.setImageDrawable(ContextCompat.getDrawable(this, setLeftIconRes()));
        }

        if (setRightIconRes() > 0) {
            ivIconRight.setImageDrawable(ContextCompat.getDrawable(this, setRightIconRes()));
        }

        drawerLayout.setDrawerLockMode(enableDrawer() ? DrawerLayout.LOCK_MODE_UNLOCKED : DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        if (showToolbar()) {
            toolbar.setVisibility(View.VISIBLE);
            setSupportActionBar(toolbar);
            final ActionBar ab = getSupportActionBar();
            if (ab != null) {
                ab.setDisplayShowHomeEnabled(false); // show or hide the default home button
                ab.setDisplayHomeAsUpEnabled(false);
                ab.setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout
                ab.setDisplayShowTitleEnabled(false); // disable the default title element here (for centered title)
            }
        } else {
            toolbar.setVisibility(View.GONE);
        }

    }

    protected boolean enableDrawer() {
        return false;
    }

    //HIDE SHOW HIDE SHOW

    protected boolean showTitle() {
        return false;
    }

    protected boolean showLogo() {
        return false;
    }

    protected boolean showToolbar() {
        return false;
    }

    protected boolean showRightIcon() {
        return false;
    }

    protected boolean showRightButton() {
        return false;
    }

    protected boolean showLeftIcon() {
        return false;
    }

    //SETGETSETGET

    public void setTitle(String title) {
        toolbarTitle.setText(title);
    }

    public void setTitle(CharSequence title) {
        setTitle(title.toString());
    }

    public void setTitle(@StringRes int resTitle) {
        setTitle(getString(resTitle));
    }


    public void setRightButtonText(String title) {
        tvButtonRight.setText(title);
    }

    public void setRightButtonText(CharSequence title) {
        setRightButtonText(title.toString());
    }

    public void setRightButtonText(@StringRes int resTitle) {
        setRightButtonText(getString(resTitle));
    }

    @DrawableRes
    public int setRightIconRes() {
        return 0;
    }

    @DrawableRes
    public int setLeftIconRes() {
        return 0;
    }


    public View.OnClickListener onRightButtonClick() {
        return null;
    }

    public View.OnClickListener onRightIconClick() {
        return null;
    }

    public View.OnClickListener onLeftIconClick() {
        return view -> onBackPressed();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.done();
        }
    }

    @Override
    public void savePreference(String key, String value) {
        Helper.saveStringPref(this, key, value);
    }

    @Override
    public void log(String log) {
        Log.d(this.getClass().getSimpleName(), "" + log);
    }

    @Override
    public void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void alert(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setPositiveButton("OK", (dialogInterface, i) -> dialogInterface.dismiss());
        builder.create().show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}

