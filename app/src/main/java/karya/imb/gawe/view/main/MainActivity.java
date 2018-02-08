package karya.imb.gawe.view.main;

import gawe.imb.karya.presenter.router.main.MainPresenter;
import gawe.imb.karya.presenter.router.main.MainView;
import karya.imb.gawe.R;
import karya.imb.gawe.mvp.MvpActivity;

/**
 * Created by korneliussendy on 1/24/18.
 */

public class MainActivity extends MvpActivity<MainPresenter> implements MainView {

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
