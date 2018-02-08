package gawe.imb.karya.presenter.er.intro;

import java.util.List;

import gawe.imb.karya.model.objects.intro.Intro;
import gawe.imb.karya.presenter.base.BaseView;

/**
 * Created by korneliussendy on 1/25/18.
 */

public interface ErIntroView extends BaseView {

    void toEmployerPage();

    void showPager(List<Intro> intros);
}
