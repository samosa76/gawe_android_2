package gawe.imb.karya.model.manager;

import com.google.common.base.Strings;
import com.google.gson.Gson;

import gawe.imb.karya.model.MyApplication;
import gawe.imb.karya.model.objects.GaweSettings;
import gawe.imb.karya.model.utils.Helper;
import io.reactivex.Single;
import gawe.imb.karya.model.modules.FirebaseClient;

/**
 * Created by korneliussendy on 2/1/18.
 */

public class SettingsManager {

    private static String KEY_SETTING = "ini_gawe_settings";

    public static void saveSettings(GaweSettings settings) {
        Gson g = new Gson();
        Helper.getPreference(MyApplication.getContext()).edit()
                .putString(KEY_SETTING, g.toJson(settings)).apply();
    }

    public static GaweSettings getSettings() {
        Gson g = new Gson();
        String s = Helper.getPreference(MyApplication.getContext()).getString(KEY_SETTING, "");
        if (Strings.isNullOrEmpty(s))
            return new GaweSettings();
        else
            return g.fromJson(s, GaweSettings.class);
    }

    public static Single<GaweSettings> loadSettings() {
        return FirebaseClient
                .getSettings()
                .cache()
                .onErrorReturnItem(new GaweSettings());
    }
}
