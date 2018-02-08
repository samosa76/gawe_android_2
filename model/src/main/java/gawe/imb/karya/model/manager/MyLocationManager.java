package gawe.imb.karya.model.manager;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.location.LocationRequest;
import com.patloew.rxlocation.RxLocation;

import gawe.imb.karya.model.MyApplication;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by korneliussendy on 2/6/18.
 */

public class MyLocationManager {

    public static Single<LatLng> getMyLocation() {
//        Single<LatLng> single = Single.create(emitter -> {
//
//            MapsGeo mg = new MapsGeo(MyApplication.getContext());
//            emitter.onSuccess(new LatLng(mg.getLatitude(), mg.getLongitude()));
//            mg.unBind();
//        });
//
//        return single.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread());//.subscribeOn(AndroidSchedulers.mainThread());


        RxLocation rxLocation = new RxLocation(MyApplication.getContext());
        LocationRequest locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(5000);

        if (ActivityCompat.checkSelfPermission(
                getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return Single.just(new LatLng());
        }
        Single<LatLng> single = rxLocation
                .location()
                .lastLocation()
                .flatMapSingle(location -> Single.just(new LatLng(location.getLatitude(), location.getLongitude())
                ));

//        Single<LatLng> single = rxLocation.location().updates(locationRequest)
//                .flatMap(location -> rxLocation.geocoding().fromLocation(location).toObservable())
//                .singleOrError()
//                .flatMap(address -> {
//                    String result = address.getLocality() + "\n" +
//                            address.getSubAdminArea() + "\n" +
//                            address.getAdminArea() + "\n" +
//                            address.getCountryName() + "\n" +
//                            address.getPostalCode() + "\n";
//                    Single<LatLng> s = Single.just(new LatLng(address.getLatitude(), address.getLongitude(), result));
//                    return s;
//                });

        return single.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread());
    }

    private static Context getContext() {
        return MyApplication.getContext();
    }


    public static class LatLng {
        private double latitude = 0, longitude = 0;
        private String address = "";

        LatLng() {
        }

        LatLng(double latitude, double longitude, String address) {
            this.latitude = latitude;
            this.longitude = longitude;
            this.address = address;
        }

        LatLng(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }
    }
}
