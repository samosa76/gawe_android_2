package gawe.imb.karya.presenter.router.selectRole;

import java.util.List;

import gawe.imb.karya.model.objects.intro.Intro;
import gawe.imb.karya.presenter.base.BaseView;

/**
 * Created by korneliussendy on 1/24/18.
 */

public interface SelectRoleView extends BaseView {

    void toEmployeePage();

    void toEmployeeIntro(List<Intro> intros);

    void toEmployerPage();

    void toEmployerIntro(List<Intro> listIntro);

    void errorInvalidRole();
}
