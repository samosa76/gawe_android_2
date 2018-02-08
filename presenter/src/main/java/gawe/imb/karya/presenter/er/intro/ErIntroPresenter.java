package gawe.imb.karya.presenter.er.intro;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import gawe.imb.karya.model.manager.IntroManager;
import gawe.imb.karya.model.objects.intro.Intro;
import gawe.imb.karya.presenter.base.BasePresenter;

/**
 * Created by korneliussendy on 1/25/18.
 */

public class ErIntroPresenter extends BasePresenter<ErIntroView> {

    public ErIntroPresenter(ErIntroView view) {
        attachView(view);
    }

    public List<Intro> extractIntro(String string) {
        if (Strings.isNullOrEmpty(string)) {
            return IntroManager.getDefaultERIntro();
        } else {
            Gson g = new Gson();
            return g.fromJson(string, new TypeToken<List<Intro>>() {
            }.getType());
        }
    }

}
