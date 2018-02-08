package gawe.imb.karya.model.manager;

import android.support.annotation.NonNull;
import android.util.Log;

import gawe.imb.karya.model.objects.BrowseFilter;
import gawe.imb.karya.model.objects.GaweBrowse;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import gawe.imb.karya.model.objects.gaweResponse.PaginationResponse;
import gawe.imb.karya.model.modules.GaweWebserviceAPI;
import gawe.imb.karya.model.modules.GaweWebserviceClient;

/**
 * Created by korneliussendy on 1/29/18.
 */

public class ERBrowseManager {

    public static Single<PaginationResponse<GaweBrowse>> getBrowseItem
            (int page, int perPage, Double lat, Double lng, @NonNull BrowseFilter filter) {
        Log.d("BrowseFilter", filter.toString());
        GaweWebserviceAPI api = GaweWebserviceClient.getClient().create(GaweWebserviceAPI.class);
        return api.loadBrowseHome(
                page, perPage,
                lat, lng,
                filter.getGender(),
                filter.getCategory(),
                filter.getDistance(),
                (filter.getFullTime() ? "yes" : "no"),
                (filter.getPartTime() ? "yes" : "no"),
                filter.getSortBy())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
