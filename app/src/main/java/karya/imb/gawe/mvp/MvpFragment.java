package karya.imb.gawe.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import gawe.imb.karya.presenter.base.BasePresenter;
import karya.imb.gawe.base.BaseFragment;

/**
 * Created by korneliussendy on 1/22/18.
 */

public abstract class MvpFragment<P extends BasePresenter> extends BaseFragment {
    protected P presenter;

    protected abstract P createPresenter();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        presenter = createPresenter();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.done();
        }
    }
}
