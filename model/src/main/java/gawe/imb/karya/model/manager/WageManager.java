package gawe.imb.karya.model.manager;

import android.util.Log;

import com.google.common.base.Strings;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import gawe.imb.karya.model.utils.Helper;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by korneliussendy on 2/6/18.
 */

public class WageManager {
    private static String BASE_ROY_DB = "published/";
    private static String REF_CITY_WAGE = BASE_ROY_DB + "GaweUMK";
    private static String REF_REGION_WAGE = BASE_ROY_DB + "GaweUMP";
    private static String REF_COUNTRY_WAGE = BASE_ROY_DB + "GaweUMX";

    public static Single<Double> getCityWage(String cityId) {
        if (Strings.isNullOrEmpty(cityId))
            return Single.just(0d);

        Single<Double> single = Single.create(emitter ->
                Helper.getDatabase().getReference(REF_CITY_WAGE).child(cityId)
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Double wage = dataSnapshot.getValue(Double.class);
                                if (!dataSnapshot.exists() || wage == null) {
                                    Log.d("getCityWage", cityId + " NULL WAGE");
                                    emitter.onSuccess(0d);
                                } else {
                                    Log.d("getCityWage", cityId + " WAGE : " + wage);
                                    emitter.onSuccess(wage);
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                emitter.onError(databaseError.toException());
                            }
                        })
        );

        return single.cache().observeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread());
    }

    public static Single<Double> getRegionWage(String regionId) {
        if (Strings.isNullOrEmpty(regionId))
            return Single.just(0d);

        Single<Double> single = Single.create(emitter ->
                Helper.getDatabase().getReference(REF_REGION_WAGE).child(regionId)
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Double wage = dataSnapshot.getValue(Double.class);
                                if (!dataSnapshot.exists() || wage == null) {
                                    Log.d("getRegionWage", regionId + " NULL WAGE");
                                    emitter.onSuccess(0d);
                                } else {
                                    Log.d("getRegionWage", regionId + " WAGE : " + wage);
                                    emitter.onSuccess(wage);
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                emitter.onError(databaseError.toException());
                            }
                        })
        );
        return single.cache().observeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread());
    }

    public static Single<Double> getCountryWage(String countryId) {
        if (Strings.isNullOrEmpty(countryId))
            return Single.just(0d);
        Single<Double> single = Single.create(emitter ->
                Helper.getDatabase().getReference(REF_COUNTRY_WAGE).child(countryId)
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Double wage = dataSnapshot.getValue(Double.class);
                                if (!dataSnapshot.exists() || wage == null) {
                                    Log.d("getCountryWage", countryId + " NULL WAGE");
                                    emitter.onSuccess(0d);
                                } else {
                                    Log.d("getCountryWage", countryId + " WAGE : " + wage);
                                    emitter.onSuccess(wage);
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                emitter.onError(databaseError.toException());
                            }
                        })
        );
        return single.cache().observeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread());
    }

    public static Single<Double> getWage(String cityId, String regionId, String countryId) {
        Single<Double> single = Single.zip(
                getCityWage(cityId)
                        .onErrorReturnItem(0d)
                        .doOnError(throwable -> Log.e("getWage", "getCityWage", throwable)),
                getRegionWage(regionId)
                        .onErrorReturnItem(0d)
                        .doOnError(throwable -> Log.e("getWage", "getRegionWage", throwable)),
                getCountryWage(countryId)
                        .onErrorReturnItem(0d)
                        .doOnError(throwable -> Log.e("getWage", "getCountryWage", throwable)),
                (cityWage, regionWage, countryWage) -> {
                    Log.d("getWage", "cityWage " + cityWage + " regionWage " + regionWage + " countryWage " + countryWage);
                    if (cityWage != null && cityWage > 0) {
                        return cityWage;
                    } else if (regionWage != null && regionWage > 0) {
                        return regionWage;
                    } else if (countryWage != null && countryWage > 0) {
                        return countryWage;
                    } else {
                        return 0d;
                    }
                }).cache();

        return single.observeOn(AndroidSchedulers.mainThread()).subscribeOn(AndroidSchedulers.mainThread());
    }

}
