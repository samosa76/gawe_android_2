package gawe.imb.karya.presenter.base;

/**
 * Created by korneliussendy on 1/22/18.
 *
 */

public interface BaseView {

    void log(String log);

    void toast(String message);

    void alert(String message);

    void savePreference(String key, String value);

    void showLoading();

    void hideLoading();
}
