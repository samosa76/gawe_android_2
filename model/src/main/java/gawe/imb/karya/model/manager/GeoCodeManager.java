package gawe.imb.karya.model.manager;

import gawe.imb.karya.model.utils.Helper;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import gawe.imb.karya.model.objects.googleGeoCode.GeoCodeResults;
import gawe.imb.karya.model.modules.GeoCodeAPI;
import gawe.imb.karya.model.modules.GeoCodeClient;

/**
 * Created by korneliussendy on 2/5/18.
 */

public class GeoCodeManager {

    private static String BASE_URL = "https://maps.googleapis.com";
    private static String KEY = "AIzaSyB2aRLsMU_qlQsiswR9QFF_lnCH3qkIpwY";

    public static Single<GeoCodeResults> getLocationDetails(double lat, double lng) {
        String latLng = String.valueOf(Helper.round(lat, 8)) + "," + String.valueOf(Helper.round(lng, 8));
        GeoCodeAPI geoCodeAPI = GeoCodeClient.getClient(BASE_URL).create(GeoCodeAPI.class);
        Single<GeoCodeResults> single = geoCodeAPI.getLocationDetails(latLng, "en", KEY, "administrative_area_level_2");
        return single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
