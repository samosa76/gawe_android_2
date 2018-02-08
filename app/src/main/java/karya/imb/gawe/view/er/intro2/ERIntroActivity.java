package karya.imb.gawe.view.er.intro2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.List;

import gawe.imb.karya.model.objects.intro.Intro;
import gawe.imb.karya.presenter.er.intro.ErIntroPresenter;
import gawe.imb.karya.presenter.er.intro.ErIntroView;
import karya.imb.gawe.R;
import karya.imb.gawe.view.er.ERBaseActivity;
import karya.imb.gawe.view.er.home.ERHomeActivity;
import karya.imb.gawe.view.selectRole.SelectRoleActivity;

/**
 * Created by korneliussendy on 1/30/18.
 */

public class ERIntroActivity extends ERBaseActivity<ErIntroPresenter> implements ErIntroView {


    @Override
    protected ErIntroPresenter createPresenter() {
        return new ErIntroPresenter(this);
    }

    @Override
    public int setContentView() {
        return R.layout.activity_er_intro;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        if (b == null)
            b = new Bundle();

        presenter.extractIntro(b.getString(SelectRoleActivity.ER_INTRO, ""));

    }

    @Override
    public void showPager(List<Intro> intros) {

    }

    @Override
    public void toEmployerPage() {
        Intent i = new Intent(this, ERHomeActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
