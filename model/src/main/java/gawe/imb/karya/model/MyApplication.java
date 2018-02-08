package gawe.imb.karya.model;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

/**
 * Created by korneliussendy on 1/22/18.
 */

public class MyApplication extends MultiDexApplication {
    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
