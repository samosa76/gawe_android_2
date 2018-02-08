package gawe.imb.karya.model.manager;

import android.util.Log;

import com.google.common.base.Strings;
import com.google.gson.Gson;

/**
 * Created by korneliussendy on 2/6/18.
 */

public class GsonManager {

    public static  <T> T extractData(String data, Class<T> classOfT) {
        if (Strings.isNullOrEmpty(data))
            return null;
        try {
            Gson g = new Gson();
            return g.fromJson(data, classOfT);
        } catch (Exception e) {
            Log.e("GSON extract", "FAILED", e);
            return null;
        }
    }
}
