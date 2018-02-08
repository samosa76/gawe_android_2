package gawe.imb.karya.presenter.er.adsAdd;

import com.google.common.base.Strings;
import com.google.firebase.firestore.GeoPoint;
import com.google.gson.Gson;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gawe.imb.karya.model.manager.BursaManager;
import gawe.imb.karya.model.manager.GeoCodeManager;
import gawe.imb.karya.model.manager.SettingsManager;
import gawe.imb.karya.model.manager.UserManager;
import gawe.imb.karya.model.objects.BursaJob;
import gawe.imb.karya.model.objects.GaweCategoryNested;
import gawe.imb.karya.model.objects.googleGeoCode.AddressComponent;
import gawe.imb.karya.model.objects.googleGeoCode.Result;
import gawe.imb.karya.model.utils.Helper;
import gawe.imb.karya.presenter.base.BasePresenter;

/**
 * Created by korneliussendy on 2/3/18.
 */

public class ERAdsAddPresenter extends BasePresenter<ERAdsAddView> {

    private GaweCategoryNested selectedCategory;
    private String jobId = "";
    private double selectedLat = 0, selectedLng = 0;
    private String selectedAddress = "";
    private String selectedCity = "";
    private String selectedRegion = "";
    private String selectedCountry = "";
    private String selectedCityName = "";
    private String selectedRegionName = "";
    private String selectedCountryName = "";
    private String note = "";
    private String description = "";
    private boolean negotiable = false;
    private Double minWage = 0d, maxWage = 0d;

    public ERAdsAddPresenter(ERAdsAddView view) {
        attachView(view);
    }

    public void extractData(String stringJsonCategory) {
        if (Strings.isNullOrEmpty(stringJsonCategory)) {
            view.categoryLoaded(null);
            return;
        }

        selectedCategory = extractCategory(stringJsonCategory);
        view.categoryLoaded(selectedCategory);
        view.dataLoaded();
    }

    public void onCategoryClick() {
        view.toPickCategoryPage();
    }

    public void onCategorySelected(String stringJsonCategory) {
        selectedCategory = extractCategory(stringJsonCategory);
        view.categoryLoaded(selectedCategory);
    }

    private GaweCategoryNested extractCategory(String stringJsonCategory) {
        Gson g = new Gson();
        return g.fromJson(stringJsonCategory, GaweCategoryNested.class);
    }

    public void onAddressClick() {
        view.openMapSelectPage(selectedLat, selectedLng);
    }

    public void onLocationSelected(double latitude, double longitude, CharSequence address) {
        selectedLat = latitude;
        selectedLng = longitude;
        selectedAddress = address.toString();
        view.showLoadingLocation();
        addDisposable(GeoCodeManager.getLocationDetails(selectedLat, selectedLng)
                .subscribe((geoCode, throwable) -> {
                    view.hideLoadingLocation();
                    if (throwable != null) {
                        view.log("FAILED " + throwable.getMessage());
                        view.failedLoadingLocation(throwable);
                        clearLocationData();
                        return;
                    }

                    List<Result> listResult = new ArrayList<>();
                    listResult.addAll(geoCode.getResults());
                    view.log("list size : " + listResult.size());
                    if (listResult.size() > 0) {
                        Result result = listResult.get(0);
                        List<AddressComponent> listComponent = new ArrayList<>();
                        listComponent.addAll(result.getAddressComponents());
                        view.log("component size : " + listComponent.size());
                        for (AddressComponent ac : listComponent) {
                            if (ac.getTypes().contains("administrative_area_level_2")) {
                                selectedCity = ac.getLongName().toLowerCase().replace(" ", "_");
                                selectedCityName = Strings.isNullOrEmpty(ac.getShortName()) ? ac.getLongName() : ac.getShortName();
                            } else if (ac.getTypes().contains("administrative_area_level_1")) {
                                selectedRegion = ac.getLongName().toLowerCase().replace(" ", "_");
                                selectedRegionName = Strings.isNullOrEmpty(ac.getShortName()) ? ac.getLongName() : ac.getShortName();
                            } else if (ac.getTypes().contains("country")) {
                                selectedCountry = ac.getLongName().toLowerCase().replace(" ", "_");
                                selectedCountryName = Strings.isNullOrEmpty(ac.getShortName()) ? ac.getLongName() : ac.getShortName();
                            }
                        }
                        view.log("city : " + selectedCity + " region : " + selectedRegion + " country : " + selectedCountry);

                    }
                    view.locationLoaded(selectedAddress);
                })
        );

    }

    public void onDescriptionUpdate(String description) {
        this.description = description;
    }

    public void onNoteUpdate(String note) {
        this.note = note;
    }

    public void onMinWageUpdate(double minWage) {
        this.minWage = minWage;
    }

    public void onMaxWageUpdate(double maxWage) {
        this.maxWage = maxWage;
    }

    public void onNegotiableClick(boolean checked) {
        this.negotiable = checked;
    }

    public void onSubmitClick() {
        if (!isValid()) {
            view.failedOrIncompleteForm(errorMessage);
        } else {
            saveBursa();
        }
    }

    private void saveBursa() {

        String shortAddress;
        if (!Strings.isNullOrEmpty(selectedCityName) && !Strings.isNullOrEmpty(selectedRegionName)) {
            shortAddress = selectedCityName + ", " + selectedRegionName;
        } else if (Strings.isNullOrEmpty(selectedCityName) && !Strings.isNullOrEmpty(selectedRegionName)) {
            shortAddress = selectedRegionName;
        } else if (!Strings.isNullOrEmpty(selectedCityName) && Strings.isNullOrEmpty(selectedRegionName)) {
            shortAddress = selectedCityName;
        } else {
            shortAddress = "";
        }

        if (Strings.isNullOrEmpty(jobId))
            jobId = "" + DateTime.now().getMillis() + "__" + UserManager.getUserPreference().getId();

        SettingsManager.loadSettings().subscribe(gaweSettings -> {
            DateTime dtExpired = DateTime.now().plusDays(gaweSettings != null ? gaweSettings.getBursa_expired_days() : 15);

            Map<String, Object> map = new HashMap<>();
            map.put("description", description);
            map.put("salaryMax", maxWage);
            map.put("salaryMin", minWage);
            map.put("lat", selectedLat);
            map.put("lng", selectedLng);
            map.put("address", selectedAddress);
            map.put("locationNote", Strings.isNullOrEmpty(note) ? "" : note);
            map.put("city", selectedCity);
            map.put("region", selectedRegion);
            map.put("country", selectedCountry);
            map.put("negotiable", negotiable);
            map.put("employerId", UserManager.getUserPreference().getId());
            map.put("id", jobId);
            map.put("categoryId", selectedCategory.getId());
            map.put("geoPoint", new GeoPoint(selectedLat, selectedLng));
            map.put("dateUpdated", Helper.formatDateTime(DateTime.now()));
            map.put("millisUpdated", DateTime.now().getMillis());
            map.put("stApproved", false);
            if (!Strings.isNullOrEmpty(shortAddress))
                map.put("shortAddress", shortAddress);
            map.put("hasBeenReviewed", false);


            BursaJob bursaJob = new BursaJob();
            bursaJob.setDescription(description);
            bursaJob.setSalaryMax(maxWage);
            bursaJob.setSalaryMin(minWage);
            bursaJob.setLat(selectedLat);
            bursaJob.setLng(selectedLng);
            bursaJob.setAddress(selectedAddress);
            bursaJob.setLocationNote(Strings.isNullOrEmpty(note) ? "" : note);
            bursaJob.setCity(selectedCity);
            bursaJob.setRegion(selectedRegion);
            bursaJob.setCountry(selectedCountry);
            bursaJob.setNegotiable(negotiable);
            bursaJob.setEmployerId(UserManager.getUserPreference().getId());
            bursaJob.setId(jobId);
            bursaJob.setCategoryId(selectedCategory.getId());
            bursaJob.setStCreated(true);
            bursaJob.setDateCreated(Helper.formatDateTime(DateTime.now()));
            bursaJob.setMillisCreated(DateTime.now().getMillis());
            bursaJob.setGeoPoint(new GeoPoint(selectedLat, selectedLng));
            bursaJob.setShortAddress(shortAddress);
            bursaJob.setDateExpired(Helper.formatDateTime(dtExpired));
            bursaJob.setMillisExpired(dtExpired.getMillis());
            //JUST IN CASE
            bursaJob.setStApproved(false);
            bursaJob.setStCancelled(false);
            bursaJob.setStCompleted(false);
            bursaJob.setStExpired(false);
            bursaJob.setStRejected(false);
            bursaJob.setHasBeenReviewed(false);

            view.showLoadingSaveBursa();
            addDisposable(BursaManager.saveAds(jobId, bursaJob)
                    .subscribe(() -> {
                        clearLocationData();
                        view.hideLoadingSaveBursa();
                        view.successSaveBursa();
                    }, throwable -> {
                        view.hideLoadingSaveBursa();
                        view.failedSaveBursa(throwable.getMessage());
                    })
            );
        });
    }

    private String errorMessage = "";

    private boolean isValid() {
        errorMessage = "";
        boolean valid = true;
        if (selectedCategory == null || Strings.isNullOrEmpty(selectedCategory.getId())) {
            errorMessage += "\nempty category";
            valid = false;
        }
        if (selectedLat == 0 || selectedLng == 0) {
            errorMessage += "\nempty location";
            valid = false;
        }
        if (Strings.isNullOrEmpty(selectedAddress)) {
            errorMessage += "\nempty address";
            valid = false;
        }
        if (Strings.isNullOrEmpty(selectedCity)) {
            errorMessage += "\nempty city";
            valid = false;
        }
        if (Strings.isNullOrEmpty(selectedRegion)) {
            errorMessage += "\nempty Region";
            valid = false;
        }
        if (Strings.isNullOrEmpty(selectedCountry)) {
            errorMessage += "\nempty Country";
            valid = false;
        }
        if (Strings.isNullOrEmpty(description)) {
            errorMessage += "\nempty description";
            valid = false;
        }
        if (minWage <= 0) {
            errorMessage += "\nempty min wage";
            valid = false;
        }
        if (maxWage <= 0) {
            errorMessage += "\nempty max wage";
            valid = false;
        }

        return valid;
    }

    private void clearLocationData() {
        selectedLat = 0;
        selectedLng = 0;
        selectedAddress = "";
        selectedCity = "";
        selectedRegion = "";
        selectedCountry = "";
        selectedCityName = "";
        selectedRegionName = "";
        selectedCountryName = "";

    }
}
