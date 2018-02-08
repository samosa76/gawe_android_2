package karya.imb.gawe.view.er.history;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import gawe.imb.karya.presenter.er.history.ERHistoryPresenter;
import gawe.imb.karya.presenter.er.history.ERHistoryView;
import karya.imb.gawe.R;
import karya.imb.gawe.view.er.BaseTabFragment;


/**
 * Created by korneliussendy on 1/26/18.
 */

public class ERHistoryFragment extends BaseTabFragment<ERHistoryPresenter> implements ERHistoryView {

    @BindView(R.id.ivIconUrgent) ImageView ivIconUrgent;
    @BindView(R.id.tvTitleUrgent) TextView tvTitleUrgent;
    @BindView(R.id.tvDescriptionUrgent) TextView tvDescriptionUrgent;
    @BindView(R.id.containerUrgent) RelativeLayout containerUrgent;
    @BindView(R.id.ivIconEmployee) ImageView ivIconEmployee;
    @BindView(R.id.tvTitleEmployee) TextView tvTitleEmployee;
    @BindView(R.id.tvDescriptionEmployee) TextView tvDescriptionEmployee;
    @BindView(R.id.containerEmployee) RelativeLayout containerEmployee;
    @BindView(R.id.cvParent) CardView cvParent;


    @Override
    protected ERHistoryPresenter createPresenter() {
        return new ERHistoryPresenter(this);
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
    protected int layoutRes() {
        return R.layout.fragment_er_history;
    }

    @Override
    protected void setView(View v) {
        
        containerUrgent.setOnClickListener(view -> presenter.onUrgentHistoryClick());
        containerEmployee.setOnClickListener(view -> presenter.onEmployeeHistoryClick());
    }

    @Override
    public void toUrgentHistoryPage() {

    }

    @Override
    public void toEmployeeHistoryPage() {

    }
}
