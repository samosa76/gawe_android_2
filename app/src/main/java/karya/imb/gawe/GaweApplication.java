package karya.imb.gawe;

import com.miguelbcr.ui.rx_paparazzo2.RxPaparazzo;

import net.danlew.android.joda.JodaTimeAndroid;

import org.joda.time.DateTimeZone;

import gawe.imb.karya.model.MyApplication;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by korneliussendy on 1/22/18.
 */

public class GaweApplication extends MyApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/SF UI Text Regular.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        JodaTimeAndroid.init(this);
        DateTimeZone.setDefault(DateTimeZone.UTC);
        RxPaparazzo.register(this);
    }
}
