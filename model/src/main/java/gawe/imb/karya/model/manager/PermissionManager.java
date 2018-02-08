package gawe.imb.karya.model.manager;

import android.Manifest;

/**
 * Created by korneliussendy on 2/1/18.
 */

public class PermissionManager {

    public static String[] getSplashPermission() {
        return new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
    }
}
