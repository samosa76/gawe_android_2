package gawe.imb.karya.model.modules;

import gawe.imb.karya.model.R;

/**
 * Created by korneliussendy on 1/23/18.
 */

public class LocalClient {

    private static int[] resBackground = {
            R.drawable.splash_1,
            R.drawable.splash_2,
            R.drawable.splash_3,
            R.drawable.splash_4,
            R.drawable.splash_5
    };

    public static int getRandomBackground() {
//        Random random = new Random();
//        return resBackground[random.nextInt(resBackground.length - 1)];
        return R.drawable.background_splash;
    }

    public static int getLoginBackground() {
        return R.drawable.background_login;
    }
}
