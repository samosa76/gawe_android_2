package karya.imb.gawe.view.er.intro;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import com.github.paolorotolo.appintro.AppIntro2;

import java.util.List;

import gawe.imb.karya.presenter.er.intro.ErIntroPresenter;
import gawe.imb.karya.presenter.er.intro.ErIntroView;
import gawe.imb.karya.presenter.er.intro2.ERIntro2View;
import karya.imb.gawe.GlideApp;
import karya.imb.gawe.R;
import gawe.imb.karya.model.objects.intro.Intro;
import karya.imb.gawe.view.er.home.ERHomeActivity;
import karya.imb.gawe.view.selectRole.SelectRoleActivity;

/**
 * Created by korneliussendy on 1/25/18.
 */

public class ErIntroActivity extends AppIntro2 implements ErIntroView {

    ErIntroPresenter presenter;
    List<Intro> intros;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ErIntroPresenter(this);

        intros = presenter.extractIntro(getIntent().getExtras().getString(SelectRoleActivity.ER_INTRO, ""));

        for (Intro i : intros) {
            addSlide(BaseFragmentIntro.newInstance(i));
        }

        showStatusBar(true);
        setNavBarColor(R.color.colorPrimary);
        setWizardMode(false);
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        if (newFragment == null)
            return;

        Intro intro = (intros.get(pager.getCurrentItem()));
        View v = newFragment.getView();

        if (v == null)
            return;

        ImageView i = newFragment.getView().findViewById(R.id.image);
        GlideApp.with(newFragment).load(intro.getImageUrl()).into(i);

    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
//        super.onDonePressed(currentFragment);
        Intent i = new Intent(this, ERHomeActivity.class);
        startActivity(i);
        presenter.done();
        finish();
    }

    @Override
    public boolean onCanRequestNextPage() {
        return super.onCanRequestNextPage();
    }


    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
    }

    public void toNextPage() {
        getPager().goToNextSlide();
    }

    @Override
    public void showPager(List<Intro> intros) {

    }

    //INTERFACES

    @Override
    public void log(String log) {

    }

    @Override
    public void toast(String message) {

    }

    @Override
    public void alert(String message) {

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
    public void toEmployerPage() {

    }
}
