package karya.imb.gawe.view.er.urgent;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import butterknife.BindView;
import gawe.imb.karya.presenter.er.urgent.ERUrgentPresenter;
import gawe.imb.karya.presenter.er.urgent.ERUrgentView;
import karya.imb.gawe.R;
import karya.imb.gawe.view.er.BaseTabFragment;
import karya.imb.gawe.view.er.home.ERHomeActivity;
import karya.imb.gawe.view.er.urgentPassive.ERUrgentPassiveFragment;

/**
 * Created by korneliussendy on 1/26/18.
 */

public class ERUrgentFragment extends BaseTabFragment<ERUrgentPresenter> implements ERUrgentView {

    @BindView(R.id.progressJob) ProgressBar progressJob;

    public static String NEW_URGENT_CATEGORY = "urgent category";

    @Override
    protected ERUrgentPresenter createPresenter() {
        return new ERUrgentPresenter(this);
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_er_urgent;
    }

    @Override
    protected boolean showToolbar() {
        return true;
    }

    @Override
    protected boolean showLogo() {
        return true;
    }

    @Override
    protected boolean showLeftIcon() {
        return true;
    }

    @Override
    protected void setView(View view) {
        if (getChildFragmentManager().findFragmentById(R.id.flUrgent) == null) {
            progressJob.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onResume() {
        presenter = new ERUrgentPresenter(this);
        presenter.listenPage();
        super.onResume();
    }

    @Override
    public void onPause() {
        presenter.done();
        super.onPause();
//        presenter.pause();
    }

    @Override
    public void showActivePage(List<String> strings) {
        progressJob.setVisibility(View.GONE);
        if (getActivity() instanceof ERHomeActivity)
            ((ERHomeActivity) getActivity()).notifTab(ERUrgentFragment.class.getSimpleName(), String.valueOf(strings.size()));
    }

    @Override
    public void showPassivePage() {
        progressJob.setVisibility(View.GONE);
        Fragment f = getChildFragmentManager().findFragmentById(R.id.flUrgent);
        if (f instanceof ERUrgentPassiveFragment)
            return;

        getChildFragmentManager().beginTransaction().replace(R.id.flUrgent, new ERUrgentPassiveFragment()).commit();
    }
}
