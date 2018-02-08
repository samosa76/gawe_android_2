package gawe.imb.karya.presenter.er.intro2;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import gawe.imb.karya.model.manager.IntroManager;
import gawe.imb.karya.model.objects.intro.Intro;
import gawe.imb.karya.presenter.base.BasePresenter;

/**
 * Created by korneliussendy on 1/30/18.
 */

class ERIntroPresenter extends BasePresenter<ERIntro2View> {

    private List<Intro> intros;

    ERIntroPresenter(ERIntro2View view) {
        attachView(view);
    }

    void extractIntro(String string) {
        if (Strings.isNullOrEmpty(string)) {
            this.intros = IntroManager.getDefaultERIntro();
        } else {
            Gson g = new Gson();
            this.intros = g.fromJson(string, new TypeToken<List<Intro>>() {
            }.getType());
        }

        if (this.intros == null || this.intros.size() <= 0)
            view.toEmployerPage();
        else
            view.showPager(intros);
    }
}
