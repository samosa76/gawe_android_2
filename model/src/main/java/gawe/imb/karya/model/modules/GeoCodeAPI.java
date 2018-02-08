package gawe.imb.karya.model.modules;

import io.reactivex.Single;
import gawe.imb.karya.model.objects.googleGeoCode.GeoCodeResults;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by korneliussendy on 2/5/18.
 */

public interface GeoCodeAPI {

    @GET("/maps/api/geocode/json")
    Single<GeoCodeResults> getLocationDetails(@Query("latlng") String latlng, @Query("language") String language, @Query("key") String key, @Query("result_type") String result_type);
}
