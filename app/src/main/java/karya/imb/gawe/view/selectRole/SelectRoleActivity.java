package karya.imb.gawe.view.selectRole;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import gawe.imb.karya.presenter.router.selectRole.SelectRolePresenter;
import gawe.imb.karya.presenter.router.selectRole.SelectRoleView;
import karya.imb.gawe.R;
import gawe.imb.karya.model.manager.UserManager;
import gawe.imb.karya.model.objects.intro.Intro;
import karya.imb.gawe.mvp.MvpActivity;
import karya.imb.gawe.view.er.home.ERHomeActivity;
import karya.imb.gawe.view.er.intro.ErIntroActivity;

/**
 * Created by korneliussendy on 1/24/18.
 */

public class SelectRoleActivity extends MvpActivity<SelectRolePresenter> implements SelectRoleView {


    @BindView(R.id.ivEmployee) ImageView ivEmployee;
    @BindView(R.id.ivEmployer) ImageView ivEmployer;
    @BindView(R.id.vLoader) View vLoader;
    public static String ER_INTRO = "erIntro";

    @Override
    protected int layoutRes() {
        return R.layout.activity_select_role;
    }

    @Override
    protected SelectRolePresenter createPresenter() {
        return new SelectRolePresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ivEmployee.setOnClickListener(view ->
                presenter.onEmployeeSelected(UserManager.getUserPreference(this).getId())
        );

        ivEmployer.setOnClickListener(view ->
                presenter.onEmployerSelected(UserManager.getUserPreference(this).getId())
        );

    }

    @Override
    public void showLoading() {
        vLoader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        vLoader.setVisibility(View.VISIBLE);
    }

    @Override
    public void toEmployeePage() {
    }

    @Override
    public void toEmployeeIntro(List<Intro> intros) {

    }

    @Override
    public void toEmployerPage() {
        Intent i = new Intent(this, ERHomeActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void toEmployerIntro(List<Intro> intros) {
        Gson g = new Gson();

        Intent i = new Intent(this, ErIntroActivity.class);
        i.putExtra(ER_INTRO, g.toJson(intros));
        startActivity(i);
        finish();
    }

    @Override
    public void errorInvalidRole() {
        alert("invalid role");
    }

}
