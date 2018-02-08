package gawe.imb.karya.mainlibs.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by korneliussendy on 1/23/16.
 */
public class MapsGeo {
    private double latitude, longitude;
    private Address address;
    private Context context;
    private boolean shouldShowSettings = false;
    private GPSTracker gps;

    public MapsGeo(Context context) {
        this.context = context;
        gps = new GPSTracker(context);
        if (gps.canGetLocation()) {
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
        } else {
            if (shouldShowSettings)
                gps.showSettingsAlert(context);
        }
    }

    public MapsGeo(Context context, boolean shouldShowSettings) {
        this.context = context;
        this.shouldShowSettings = shouldShowSettings;

        GPSTracker gps = new GPSTracker(context);
        if (gps.canGetLocation()) {
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
        } else {
            if (shouldShowSettings)
                gps.showSettingsAlert(context);
        }
    }

    public void unBind() {
        if (gps != null)
            gps.stopUsingGPS();
    }


    public String getAddress(double latitude, double longitude, Boolean getFullDetail) {
        StringBuilder result = new StringBuilder();
        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses.size() > 0) {
                address = addresses.get(0);
                result.append(address.getLocality()).append("\n");
                result.append(address.getSubAdminArea()).append("\n");
                result.append(address.getAdminArea()).append("\n");
                result.append(address.getCountryName()).append("\n");
                result.append(address.getPostalCode()).append("\n");
            }
        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }
        return result.toString();
    }

    public String getAddress(double latitude, double longitude) {
        StringBuilder result = new StringBuilder();
        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses.size() > 0) {
                address = addresses.get(0);
                if (address.getLocality() != null) {
                    result.append(address.getLocality());
                }
                if (address.getSubAdminArea() != null) {
                    result.append(" - ");
                    result.append(address.getSubAdminArea());
                }
                if ((address.getLocality() == null) && (address.getSubAdminArea() == null)) {
                    result.append("Nowhere");
                }
            }
        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }
        return result.toString();
    }

    public String getAddress() {
        StringBuilder result = new StringBuilder();
        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(this.latitude, this.longitude, 1);
            if (addresses.size() > 0) {
                address = addresses.get(0);
                result.append(address.getLocality()).append("\n");
                result.append(address.getSubAdminArea()).append("\n");
                result.append(address.getAdminArea()).append("\n");
                result.append(address.getCountryName()).append("\n");
                result.append(address.getPostalCode()).append("\n");
            }
        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }
        return result.toString();
    }

    public String getCurrentAddress() {
        StringBuilder result = new StringBuilder();
        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(this.latitude, this.longitude, 1);
            if (addresses.size() > 0) {
                address = addresses.get(0);
                result.append(address.getLocality()).append(" - ");
                result.append(address.getSubAdminArea());
            }
        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }

        return result.toString();
    }

    public String getSomething() {
        StringBuilder result = new StringBuilder();
        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(this.latitude, this.longitude, 1);
            if (addresses.size() > 0) {
                Address address = addresses.get(0);
                result.append(address.getLocality()).append("\n");
                result.append(address.getCountryName());
            }
        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }

        return result.toString();
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public Address getMeAddress() {
        return address;
    }

    public String getCountry() {
        StringBuilder result = new StringBuilder();
        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses.size() > 0) {
                address = addresses.get(0);
                if (address.getCountryName() != null) {
                    result.append(address.getLocality());
                }
            }
        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }
        return result.toString();
    }

    public String getDistrict() {
        StringBuilder result = new StringBuilder();
        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses.size() > 0) {
                address = addresses.get(0);
                if (address.getLocality() != null) {
                    result.append(address.getLocality());
                }
            }
        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }
        return result.toString();
    }

    public String getCity() {
        StringBuilder result = new StringBuilder();
        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses.size() > 0) {
                address = addresses.get(0);
                if (address.getSubAdminArea() != null) {
                    result.append(address.getSubAdminArea());
                }
            }
        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }
        return result.toString();
    }

    public String getSubLocality() {
        StringBuilder result = new StringBuilder();
        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses.size() > 0) {
                address = addresses.get(0);
                if (address.getSubLocality() != null) {
                    result.append(address.getSubAdminArea());
                }
            }
        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }
        return result.toString();
    }

    public String[] getListDistrictCity() {
        String result[] = new String[4];
        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            result[0] = String.valueOf(latitude);
            result[1] = String.valueOf(longitude);
            if (addresses.size() > 0) {
                address = addresses.get(0);
                result[2] = address.getLocality();
                result[3] = address.getSubAdminArea();
            }

        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }
        return result;
    }

    public String getDistrictCity() {
        StringBuilder result = new StringBuilder();
        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses.size() > 0) {
                address = addresses.get(0);
                if (address.getLocality() != null) {
                    result.append(address.getLocality());
                }
                if (address.getSubAdminArea() != null) {
                    result.append(" - ");
                    result.append(address.getSubAdminArea());
                }
                if ((address.getLocality() == null) && (address.getSubAdminArea() == null)) {
                    result.append("Nowhere");
                }
            }
        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }
        return result.toString();
    }

    public String getDistrictCity(double latitude, double longitude) {
        StringBuilder result = new StringBuilder();
        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses.size() > 0) {
                address = addresses.get(0);
                if (address.getLocality() != null) {
                    result.append(address.getLocality());
                }
                if (address.getSubAdminArea() != null) {
                    result.append(" - ");
                    result.append(address.getSubAdminArea());
                }
                if ((address.getLocality() == null) && (address.getSubAdminArea() == null)) {
                    result.append("Nowhere");
                }
            }
        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }
        return result.toString();
    }
}
