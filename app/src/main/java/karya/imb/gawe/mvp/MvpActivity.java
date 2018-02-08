package karya.imb.gawe.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import gawe.imb.karya.presenter.base.BasePresenter;
import gawe.imb.karya.presenter.base.BaseView;
import karya.imb.gawe.base.BaseActivity;
import gawe.imb.karya.model.utils.Helper;

/**
 * Created by korneliussendy on 1/22/18.
 */

public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity implements BaseView {

    protected P presenter;
    protected abstract P createPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        presenter = createPresenter();
        super.onCreate(savedInstanceState);
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
}