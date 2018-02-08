package gawe.imb.karya.model.manager;

import android.content.res.Resources;

import io.reactivex.Single;

/**
 * Created by korneliussendy on 2/8/18.
 */

public class LangManager {

    public Single<String> getLanguage() {
        return Single.just(
                Resources.getSystem().getConfiguration().locale.getLanguage()
        );
    }

    public Single<String> getCountry() {
        return Single.just(
                Resources.getSystem().getConfiguration().locale.getCountry()
        );
    }

    public Single<String> getISO3Language() {
        return Single.just(
                Resources.getSystem().getConfiguration().locale.getISO3Language()
        );
    }

    public Single<String> getISO3Country() {
        return Single.just(
                Resources.getSystem().getConfiguration().locale.getISO3Country()
        );
    }
}
