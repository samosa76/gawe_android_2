package gawe.imb.karya.presenter.er.urgentForm;

import android.support.annotation.Nullable;

import java.util.List;

import gawe.imb.karya.model.objects.GaweCategoryNested;
import gawe.imb.karya.model.objects.Job;
import gawe.imb.karya.presenter.base.BaseView;

/**
 * Created by korneliussendy on 2/6/18.
 *
 */

public interface ERUrgentFormView extends BaseView {

    void dataLoaded();

    void categoryLoaded(@Nullable GaweCategoryNested selectedCategory);

    void toPickCategoryPage();

    void categoryUpdated(GaweCategoryNested selectedCategory);

    void genderLoaded(List<String> genders, int selected);

    void durationLoaded(List<String> durations, int selected);

    void openMapSelectPage(double selectedLat, double selectedLng);

    void showLoadingLocation();

    void hideLoadingLocation();

    void failedLoadingLocation(Throwable throwable);

    void locationLoaded(String selectedAddress);

    void showLoadingWage();

    void hideLoadingWage();

    void wageLoaded(double dailyWage, double hourlyWage, double calculatedWage);

    void failedLoadingWage(Throwable throwable);

    void failedOrIncompleteForm(String errorMessage);

    void errorCreateJob(Throwable throwable);

    void successCreateJob(Job job);

    void showLoadingCreateJob();

    void hideLoadingCreateJob();

    void showLoadingLoadSettingsAndUser();

    void hideLoadingLoadSettingsAndUser();

    void showConfirm(Long creditPerMission, Long point);

    void showNotEnoughCredit(Long creditPerMission, Long point);

    void toTopUpPage();
}
