package gawe.imb.karya.presenter.er.urgentForm;

import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.List;

import gawe.imb.karya.mainlibs.utils.Constants;
import gawe.imb.karya.model.manager.CreditManager;
import gawe.imb.karya.model.manager.GeoCodeManager;
import gawe.imb.karya.model.manager.GsonManager;
import gawe.imb.karya.model.manager.JobManager;
import gawe.imb.karya.model.manager.MyLocationManager;
import gawe.imb.karya.model.manager.SettingsManager;
import gawe.imb.karya.model.manager.UserManager;
import gawe.imb.karya.model.manager.WageManager;
import gawe.imb.karya.model.objects.GaweCategoryNested;
import gawe.imb.karya.model.objects.GaweSettings;
import gawe.imb.karya.model.objects.Job;
import gawe.imb.karya.model.objects.googleGeoCode.AddressComponent;
import gawe.imb.karya.model.objects.googleGeoCode.Result;
import gawe.imb.karya.presenter.base.BasePresenter;
import io.reactivex.Single;

/**
 * Created by korneliussendy on 2/6/18.
 */

public class ERUrgentFormPresenter extends BasePresenter<ERUrgentFormView> {

    private GaweCategoryNested selectedCategory;

    private String selectedGender = "";
    private int selectedDuration = -1;
    private double selectedWage = 0d;

    private double selectedLat = 0d, selectedLng = 0d;
    private String selectedAddress = "";
    private String selectedCity = "";
    private String selectedRegion = "";
    private String selectedCountry = "";

    private String selectedCityName = "";
    private String selectedRegionName = "";
    private String selectedCountryName = "";

    private String selectedDescription = "";
    private String selectedNote = "";

    private Job job;

    private List<String> genders = new ArrayList<>();
    private List<String> genderStrings = new ArrayList<>();

    private List<Integer> durations = new ArrayList<>();
    private List<String> durationStrings = new ArrayList<>();

    public ERUrgentFormPresenter(ERUrgentFormView view) {
        attachView(view);
        genders.add("M");
        genders.add("F");

        genderStrings.add("Male");
        genderStrings.add("Female");

        for (int i = 1; i <= 12; i++) {
            durations.add(i);
            durationStrings.add("" + i + " Hour" + (i > 1 ? "s" : ""));
        }

        job = new Job();
    }

    public void extractData(String data) {
        if (Strings.isNullOrEmpty(data)) {
            view.categoryLoaded(null);
        } else {
            selectedCategory = GsonManager.extractData(data, GaweCategoryNested.class);
            if (selectedCategory == null) {
                view.categoryLoaded(null);
                return;
            }

            job.setJobCategory(selectedCategory.getId());
            view.categoryLoaded(selectedCategory);
            loadWage();
            loadMyLocation();
        }
        view.dataLoaded();
        loadOtherThings();
    }

    private void loadMyLocation() {
        view.showLoadingLocation();
        addDisposable(
                MyLocationManager.getMyLocation().subscribe((latLng, throwable) -> {
                    view.hideLoadingLocation();
                    if (throwable != null)
                        return;
                    onLocationSelected(latLng.getLatitude(), latLng.getLongitude(), "");
                })
        );
    }

    private void loadOtherThings() {
        int defaultGender = 1;
        view.genderLoaded(genderStrings, defaultGender);
        selectedGender = genders.get(defaultGender);

        job.setGenderType(genders.get(defaultGender).equals(Constants.GENDER_FEMALE) ?
                Constants.GENDER_CODE_FEMALE_ONLY : Constants.GENDER_CODE_MALE_ONLY
        );

        int defaultDuration = 4;
        view.durationLoaded(durationStrings, defaultDuration);
        selectedDuration = durations.get(defaultDuration);

        job.setDuration(defaultDuration);
    }

    public void onGenderChanged(int position) {
        this.selectedGender = genders.get(position);
        job.setGenderType(genders.get(position).equals(Constants.GENDER_FEMALE) ?
                Constants.GENDER_CODE_FEMALE_ONLY : Constants.GENDER_CODE_MALE_ONLY
        );
    }

    public void onDurationChanged(int position) {
        selectedDuration = durations.get(position);
        job.setDuration(durations.get(position));
        loadWage();
    }

    public void onDescriptionUpdated(String description) {
        this.selectedDescription = description;
        job.setDescription(description);
    }

    public void onNoteUpdated(String note) {
        this.selectedNote = note;
        job.setNote(note);
    }

    public void onCategoryClick() {
        view.toPickCategoryPage();
    }

    public void onCategorySelected(String categoryData) {
        selectedCategory = GsonManager.extractData(categoryData, GaweCategoryNested.class);
        view.categoryUpdated(selectedCategory);
        loadWage();
    }

    public void loadWage() {
        if (selectedCategory == null) {
            return;
        }
        view.showLoadingWage();
        addDisposable(WageManager.getWage(selectedCity, selectedRegion, selectedCountry)
                .subscribe(wage -> {
                    view.hideLoadingWage();

                    Double pengali = (selectedCategory.getPengali() == null || selectedCategory.getPengali() <= 0) ?
                            1 : selectedCategory.getPengali();
                    double dailyWage = wage / 21;
                    double hourlyWage = (dailyWage / 8) * pengali;
                    double calculatedWage = ((int) (hourlyWage * selectedDuration) / 1000) * 1000;

                    selectedWage = calculatedWage;
                    job.setWage(calculatedWage);

                    view.wageLoaded(dailyWage, hourlyWage, calculatedWage);
                }, throwable -> {
                    view.hideLoadingWage();
                    view.failedLoadingWage(throwable);
                })
        );

    }

    public void onLocationClick() {
        view.openMapSelectPage(selectedLat, selectedLng);
    }

    public void onLocationSelected(double latitude, double longitude, CharSequence address) {
        selectedLat = latitude;
        selectedLng = longitude;
        selectedAddress = address.toString();

        job.setLat(latitude);
        job.setLng(longitude);
        job.setAddress(address.toString());

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

                        if (Strings.isNullOrEmpty(selectedAddress)) {
                            selectedAddress = result.getFormattedAddress();

                            job.setAddress(result.getFormattedAddress());
                        }

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
                    loadWage();
                })
        );

    }

    public void onSubmitSelected() {
        if (!isValid()) {
            view.failedOrIncompleteForm(errorMessage);
        } else {
            createJob();
        }
    }

    private void createJob() {
        if (job == null)
            job = new Job();

        errorMessage = "";

        job.setEmployerId(UserManager.getUserPreference().getId());
        job.setNote(selectedNote);

        view.showLoadingLoadSettingsAndUser();
        addDisposable(Single.zip(
                SettingsManager.loadSettings().onErrorReturn(throwable -> new GaweSettings()),
                UserManager.loadUserBulk(UserManager.getUserPreference().getId()),
                (gaweSettings, userBulk) -> {
                    view.hideLoadingLoadSettingsAndUser();
                    if (gaweSettings.getCreditPerMission() > userBulk.getUser().getPoint()) {
                        view.showNotEnoughCredit(gaweSettings.getCreditPerMission(), userBulk.getUser().getPoint());
                    } else {
                        view.showConfirm(gaweSettings.getCreditPerMission(), userBulk.getUser().getPoint());
                    }
                    return true;
                }
                ).subscribe(
                aBoolean -> view.log("loaded " + aBoolean),
                throwable -> {
                })
        );
    }

    public void onCreateJobConfirmed() {
        view.showLoadingCreateJob();
        SettingsManager.loadSettings()
                .flatMap(gaweSettings ->
                        CreditManager.consumeCredits(gaweSettings.getCreditPerMission())
                                .toSingle(() -> true))
                .flatMap(success -> {
                    if (success) {
                        return JobManager.createJob(job);
                    } else {
                        return Single.error(new Exception());
                    }
                })
                .flatMap(JobManager::notifyServer)
                .subscribe((job2, throwable) -> {
                    if (throwable != null) {
                        view.hideLoadingCreateJob();
                        view.errorCreateJob(throwable);
                    } else {
                        view.successCreateJob(job2);
                    }
                });
    }

    public void onConfirmTopUp() {
        view.toTopUpPage();
    }

    public void onSuccessTopUp() {

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
        if (Strings.isNullOrEmpty(selectedDescription)) {
            errorMessage += "\nempty description";
            valid = false;
        }
        if (Strings.isNullOrEmpty(selectedGender)) {
            errorMessage += "\nempty Gender";
            valid = false;
        }
        if (selectedWage <= 0) {
            errorMessage += "\nempty wage";
            valid = false;
        }
        if (selectedDuration <= 0) {
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
