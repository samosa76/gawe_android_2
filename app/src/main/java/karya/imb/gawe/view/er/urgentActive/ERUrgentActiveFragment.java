package karya.imb.gawe.view.er.urgentActive;

import android.view.View;

import gawe.imb.karya.presenter.er.urgentActive.ERUrgentActivePresenter;
import gawe.imb.karya.presenter.er.urgentActive.ERUrgentActiveView;
import karya.imb.gawe.R;
import karya.imb.gawe.view.er.BaseTabFragment;

/**
 * Created by korneliussendy on 2/7/18.
 */

public class ERUrgentActiveFragment extends BaseTabFragment<ERUrgentActivePresenter> implements ERUrgentActiveView {

    @Override
    protected ERUrgentActivePresenter createPresenter() {
        return new ERUrgentActivePresenter(this);
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_er_urgent_active;
    }

    @Override
    protected void setView(View view) {

    }

}
