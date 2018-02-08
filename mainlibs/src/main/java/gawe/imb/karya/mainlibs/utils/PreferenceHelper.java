package gawe.imb.karya.mainlibs.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by korneliussendy on 1/24/18.
 */

public class PreferenceHelper {

    private static final String PREFERENCE = "gawePreference";
    private static final String KEY_USER_ID = "userID";

    private static SharedPreferences getPreference(Context context) {
        return context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
    }

    public static SharedPreferences.Editor getPreferenceEditor(Context context) {
        return getPreference(context).edit();
    }

    public static String getUserId(Context context) {
        return getPreference(context).getString(KEY_USER_ID, "");
    }
}
