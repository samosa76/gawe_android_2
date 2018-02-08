package karya.imb.gawe.view.er.urgentForm;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.List;

import butterknife.BindView;
import gawe.imb.karya.model.objects.GaweCategoryNested;
import gawe.imb.karya.model.objects.Job;
import gawe.imb.karya.presenter.er.urgentForm.ERUrgentFormPresenter;
import gawe.imb.karya.presenter.er.urgentForm.ERUrgentFormView;
import karya.imb.gawe.R;
import gawe.imb.karya.model.utils.Helper;
import karya.imb.gawe.view.er.ERBaseActivity;
import karya.imb.gawe.view.er.pickCategory.ERPickCategoryActivity;
import mehdi.sakout.fancybuttons.FancyButton;

import static karya.imb.gawe.view.er.pickCategory.ERPickCategoryActivity.KEY_RESULT;
import static karya.imb.gawe.view.er.urgent.ERUrgentFragment.NEW_URGENT_CATEGORY;

/**
 * Created by korneliussendy on 2/6/18.
 */

public class ERUrgentFormActivity extends ERBaseActivity<ERUrgentFormPresenter> implements ERUrgentFormView {

    private static int REQUEST_CHANGE_CATEGORY = 122;
    private static int REQUEST_PLACE_PICKER = 121;

    @BindView(R.id.etCategory) EditText etCategory;
    @BindView(R.id.spinGender) Spinner spinGender;
    @BindView(R.id.spinDuration) Spinner spinDuration;
    @BindView(R.id.etDescription) EditText etDescription;
    @BindView(R.id.vDetails) LinearLayout vDetails;
    @BindView(R.id.etAddress) EditText etAddress;
    @BindView(R.id.progressAddress) ProgressBar progressAddress;
    @BindView(R.id.ivAddNote) ImageView ivAddNote;
    @BindView(R.id.etNote) EditText etNote;
    @BindView(R.id.tvWage) TextView tvWage;
    @BindView(R.id.tvHourlyWage) TextView tvHourlyWage;
    @BindView(R.id.vWage) LinearLayout vWage;
    @BindView(R.id.progressWage) ProgressBar progressWage;
    @BindView(R.id.vLocationAndWage) LinearLayout vLocationAndWage;
    @BindView(R.id.btnSave) FancyButton btnSave;

    @Override
    protected ERUrgentFormPresenter createPresenter() {
        return new ERUrgentFormPresenter(this);
    }

    @Override
    protected boolean showToolbar() {
        return true;
    }

    @Override
    protected boolean showTitle() {
        return true;
    }

    @Override
    protected boolean showLeftIcon() {
        return true;
    }

    @Override
    public int setLeftIconRes() {
        return R.drawable.ic_left_arrow;
    }

    @Override
    public View.OnClickListener onLeftIconClick() {
        return view -> onBackPressed();
    }

    @Override
    public int setContentView() {
        return R.layout.activity_er_urgent_form;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("URGENT");
        presenter.extractData(getIntent().getStringExtra(NEW_URGENT_CATEGORY));
    }

    @Override
    public void dataLoaded() {
        btnSave.setEnabled(true);
        btnSave.setOnClickListener(view -> presenter.onSubmitSelected());
        etAddress.setEnabled(true);
        etAddress.setFocusable(false);
        etAddress.setOnClickListener(view -> presenter.onLocationClick());

        etDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                presenter.onDescriptionUpdated(editable.toString());
            }
        });
        etNote.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                presenter.onNoteUpdated(editable.toString());
            }
        });
    }

    @Override
    public void categoryLoaded(GaweCategoryNested selectedCategory) {
        if (selectedCategory != null)
            etCategory.setText(selectedCategory.getName());
        etCategory.setEnabled(true);
        etCategory.setFocusable(false);
        etCategory.setOnClickListener(view -> presenter.onCategoryClick());
    }

    @Override
    public void genderLoaded(List<String> genders, int selected) {
        spinGender.setEnabled(true);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.custom_spinner_item, genders);
        spinGender.setAdapter(adapter);
        spinGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                presenter.onGenderChanged(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinGender.setSelection(selected);
    }

    @Override
    public void durationLoaded(List<String> durations, int selected) {
        spinDuration.setEnabled(true);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.custom_spinner_item, durations);
        spinDuration.setAdapter(adapter);
        spinDuration.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                presenter.onDurationChanged(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinDuration.setSelection(selected);
    }

    @Override
    public void toPickCategoryPage() {
        Intent i = new Intent(this, ERPickCategoryActivity.class);
        startActivityForResult(i, REQUEST_CHANGE_CATEGORY);
    }

    @Override
    public void openMapSelectPage(double selectedLat, double selectedLng) {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        if (selectedLat != 0 && selectedLng != 0) {
            builder.setLatLngBounds(LatLngBounds.builder().include(new LatLng(selectedLat, selectedLng)).build());
        }
        try {
            startActivityForResult(builder.build(this), REQUEST_PLACE_PICKER);
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void categoryUpdated(GaweCategoryNested selectedCategory) {
        if (selectedCategory != null)
            etCategory.setText(selectedCategory.getName());
    }

    @Override
    public void showLoadingLocation() {
        btnSave.setEnabled(false);
        etAddress.setEnabled(false);
        progressAddress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingLocation() {
        btnSave.setEnabled(true);
        etAddress.setEnabled(true);
        progressAddress.setVisibility(View.GONE);
    }

    @Override
    public void failedLoadingLocation(Throwable throwable) {
        Log.e("ERR", "ERROR", throwable);
    }

    @Override
    public void locationLoaded(String selectedAddress) {
        etAddress.setText(selectedAddress);
    }

    @Override
    public void showLoadingWage() {
        progressWage.setVisibility(View.VISIBLE);
        vWage.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideLoadingWage() {
        progressWage.setVisibility(View.GONE);
        vWage.setVisibility(View.VISIBLE);
    }

    @Override
    public void wageLoaded(double dailyWage, double hourlyWage, double calculatedWage) {
        tvWage.setText(Helper.formatRupiah(calculatedWage));
        String perHour = Helper.formatRupiah(hourlyWage) + " / Hour(s)";
        tvHourlyWage.setText(perHour);
    }

    @Override
    public void failedLoadingWage(Throwable throwable) {
        Log.d("FAILED", "LOADING WAGE", throwable);
    }

    @Override
    public void failedOrIncompleteForm(String errorMessage) {
        alert(errorMessage);
    }

    @Override
    public void showLoadingCreateJob() {
        toggleView(false);
    }

    @Override
    public void hideLoadingCreateJob() {
        toggleView(true);
    }

    @Override
    public void showLoadingLoadSettingsAndUser() {

    }

    @Override
    public void hideLoadingLoadSettingsAndUser() {

    }

    @Override
    public void showConfirm(Long creditPerMission, Long point) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
    }

    @Override
    public void showNotEnoughCredit(Long creditPerMission, Long point) {

    }

    @Override
    public void toTopUpPage() {

    }

    @Override
    public void errorCreateJob(Throwable throwable) {
        toast("failed create job");
        Log.e(TAG, "failed create job", throwable);
    }

    @Override
    public void successCreateJob(Job job) {
        toast("Success create job with id " + job.getId());
    }

    private void toggleView(boolean enabled) {
        etCategory.setEnabled(enabled);
        spinGender.setEnabled(enabled);
        spinDuration.setEnabled(enabled);
        etDescription.setEnabled(enabled);
        etAddress.setEnabled(enabled);
        etNote.setEnabled(enabled);
        tvWage.setEnabled(enabled);
        tvHourlyWage.setEnabled(enabled);
        btnSave.setEnabled(enabled);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CHANGE_CATEGORY && resultCode == RESULT_OK) {
            presenter.onCategorySelected(data.getStringExtra(KEY_RESULT));
        } else if (requestCode == REQUEST_PLACE_PICKER && resultCode == RESULT_OK) {
            Place place = PlacePicker.getPlace(this, data);
            log("place picked " + place.getAddress());
            LatLng latLng = place.getLatLng();
            presenter.onLocationSelected(latLng.latitude, latLng.longitude, place.getAddress());
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
