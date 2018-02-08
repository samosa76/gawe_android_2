package gawe.imb.karya.presenter.er.intro2;

import java.util.List;

import gawe.imb.karya.model.objects.intro.Intro;
import gawe.imb.karya.presenter.base.BaseView;

/**
 * Created by korneliussendy on 1/30/18.
 */

public interface ERIntro2View extends BaseView {
    void toEmployerPage();

    void showPager(List<Intro> intros);
}
