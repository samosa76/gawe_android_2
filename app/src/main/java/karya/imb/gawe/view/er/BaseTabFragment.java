package karya.imb.gawe.view.er;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import gawe.imb.karya.presenter.base.BasePresenter;
import gawe.imb.karya.presenter.base.BaseView;
import karya.imb.gawe.R;

/**
 * Created by korneliussendy on 1/26/18.
 */

public abstract class BaseTabFragment<P extends BasePresenter> extends Fragment implements BaseView {
    protected P presenter;
    Unbinder unbinder;
    public String TAG = "@@@TAG";

    @BindView(R.id.ivMenu) ImageView ivMenu;
    @BindView(R.id.ivIconRight) ImageView ivIconRight;
    @BindView(R.id.tvButtonRight) TextView tvButtonRight;
    @BindView(R.id.llRight) RelativeLayout llRight;
    @BindView(R.id.toolbarTitle) TextView toolbarTitle;
    @BindView(R.id.vLogo) LinearLayout vLogo;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.rlParent) RelativeLayout rlParent;

    protected abstract P createPresenter();

    protected abstract int layoutRes();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        presenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.er_base_fragment, container, false);
        View content = inflater.inflate(layoutRes(), null);

        rlParent = v.findViewById(R.id.rlParent);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        rlParent.addView(content, params);

        unbinder = ButterKnife.bind(this, v);

        toolbar.setVisibility(showToolbar() ? View.VISIBLE : View.GONE);
        toolbar.setBackgroundColor(ContextCompat.getColor(getContext(), resBackgroundToolbar()));
        vLogo.setVisibility(showLogo() ? View.VISIBLE : View.GONE);
        toolbarTitle.setVisibility(showTitle() ? View.VISIBLE : View.GONE);
        ivMenu.setVisibility(showLeftIcon() ? View.VISIBLE : View.INVISIBLE);
        ivIconRight.setVisibility(showRightIcon() ? View.VISIBLE : View.GONE);

        if (resLeftIcon() > 0) {
            ivMenu.setImageDrawable(ContextCompat.getDrawable(getContext(), resLeftIcon() > 0 ? resLeftIcon() : R.drawable.ic_hamburger));
        }
        if (onLeftIconClick() != null) {
            ivMenu.setOnClickListener(onLeftIconClick());
        }

        if (resRightIcon() > 0) {
            ivIconRight.setImageDrawable(ContextCompat.getDrawable(getContext(), resRightIcon() > 0 ? resRightIcon() : R.drawable.ic_option));
        }

        if (onRightIconClick() != null) {
            ivIconRight.setOnClickListener(onRightIconClick());
        }

        setView(v);

        return v;

    }

    protected abstract void setView(View view);


    //HIDESHOW
    protected boolean showToolbar() {
        return false;
    }

    protected boolean showLogo() {
        return true;
    }

    protected boolean showTitle() {
        return false;
    }

    protected boolean showLeftIcon() {
        return true;
    }

    protected int resLeftIcon() {
        return R.drawable.ic_hamburger;
    }

    protected View.OnClickListener onLeftIconClick() {
        return null;
    }

    protected boolean showRightIcon() {
        return false;
    }

    protected int resRightIcon() {
        return R.drawable.ic_option;
    }

    protected View.OnClickListener onRightIconClick() {
        return null;
    }

    protected int resBackgroundToolbar() {
        return R.color.toolbar_background;
    }

    @Override
    public void log(String log) {
        Log.d(getClass().getSimpleName(), "" + log);
    }

    @Override
    public void toast(String message) {
        Toast.makeText(getContext(), "" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void alert(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(message);
        builder.setPositiveButton("OK", (dialogInterface, i) -> dialogInterface.dismiss());
        builder.create().show();
    }

    @Override
    public void savePreference(String key, String value) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.clearDisposable();
        unbinder.unbind();
    }
}
