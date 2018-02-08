package karya.imb.gawe.view.er.adsAdd;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Switch;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import butterknife.BindView;
import gawe.imb.karya.model.objects.GaweCategoryNested;
import gawe.imb.karya.presenter.er.adsAdd.ERAdsAddPresenter;
import gawe.imb.karya.presenter.er.adsAdd.ERAdsAddView;
import karya.imb.gawe.R;
import karya.imb.gawe.view.er.ERBaseActivity;
import karya.imb.gawe.view.er.adsMy.ERMyAdsFragment;
import karya.imb.gawe.view.er.pickCategory.ERPickCategoryActivity;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by korneliussendy on 2/3/18.
 */

public class ERAdsAddActivity extends ERBaseActivity<ERAdsAddPresenter> implements ERAdsAddView {

    @BindView(R.id.etCategory) EditText etCategory;
    @BindView(R.id.etDescription) EditText etDescription;
    @BindView(R.id.etAddress) EditText etAddress;
    @BindView(R.id.progressAddress) ProgressBar progressAddress;
    @BindView(R.id.etNote) EditText etNote;
    @BindView(R.id.vDetails) LinearLayout vDetails;
    @BindView(R.id.etWageMin) EditText etWageMin;
    @BindView(R.id.etWageMax) EditText etWageMax;
    @BindView(R.id.swNegotiable) Switch swNegotiable;
    @BindView(R.id.vWage) LinearLayout vWage;
    @BindView(R.id.btnSave) FancyButton btnSave;

    private static final int REQUEST_PICK_CAT_FOR_NEW = 149;
    private int REQUEST_PLACE_PICKER = 115;
//    private LatLngBounds myLatLngBound;

    @Override
    protected ERAdsAddPresenter createPresenter() {
        return new ERAdsAddPresenter(this);
    }

    @Override
    public int setContentView() {
        return R.layout.activity_er_ads_add;
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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("NEWS ADD");
        presenter.extractData(getIntent().getStringExtra(ERMyAdsFragment.KEY_CATEGORY));

    }

    @Override
    public void categoryLoaded(GaweCategoryNested category) {
        if (category != null)
            etCategory.setText(category.getName());
        else
            etCategory.setText("");

        etCategory.setEnabled(true);
        etCategory.setFocusable(false);
        etCategory.setOnClickListener(view -> presenter.onCategoryClick());
    }

    @Override
    public void dataLoaded() {
        toggleView(true);
        etAddress.setFocusable(false);
        etAddress.setOnClickListener(v -> presenter.onAddressClick());
        btnSave.setOnClickListener(view -> presenter.onSubmitClick());

        etDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                presenter.onDescriptionUpdate(editable.toString());
            }
        });

        etWageMin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                presenter.onMinWageUpdate(editable.length() <= 0 ? 0d : Double.parseDouble(editable.toString()));
            }
        });

        etWageMax.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                presenter.onMaxWageUpdate(editable.length() <= 0 ? 0d : Double.parseDouble(editable.toString()));
            }
        });

        swNegotiable.setOnCheckedChangeListener((compoundButton, checked) -> presenter.onNegotiableClick(checked));

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
    public void locationLoaded(String address) {
        etAddress.setText(address);
    }

    @Override
    public void showLoadingLocation() {
        etAddress.setEnabled(false);
        btnSave.setEnabled(false);
        progressAddress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingLocation() {
        etAddress.setEnabled(true);
        btnSave.setEnabled(true);
        progressAddress.setVisibility(View.GONE);
    }

    @Override
    public void failedLoadingLocation(Throwable message) {
        Log.e("FAILED ", message.getMessage(), message);
        alert(message.getMessage());
    }

    @Override
    public void toPickCategoryPage() {
        Intent i = new Intent(this, ERPickCategoryActivity.class);
        startActivityForResult(i, REQUEST_PICK_CAT_FOR_NEW);
    }

    @Override
    public void failedOrIncompleteForm(String errorMessage) {
        alert(errorMessage);
    }

    @Override
    public void showLoadingSaveBursa() {
        toggleView(false);
    }

    @Override
    public void hideLoadingSaveBursa() {
        toggleView(true);
    }

    @Override
    public void failedSaveBursa(String message) {
        alert(message);
    }

    @Override
    public void successSaveBursa() {
        toast("Success create data");
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PICK_CAT_FOR_NEW && resultCode == RESULT_OK) {
            presenter.onCategorySelected(data.getStringExtra(ERPickCategoryActivity.KEY_RESULT));
        } else if (requestCode == REQUEST_PLACE_PICKER && resultCode == RESULT_OK) {
            Place place = PlacePicker.getPlace(this, data);
            log("place picked " + place.getAddress());
            LatLng latLng = place.getLatLng();
//            myLatLngBound = place.getViewport();
            presenter.onLocationSelected(latLng.latitude, latLng.longitude, place.getAddress());
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void toggleView(boolean enable) {
        etCategory.setEnabled(enable);
        etDescription.setEnabled(enable);
        etAddress.setEnabled(enable);
        etNote.setEnabled(enable);
        etWageMin.setEnabled(enable);
        etWageMax.setEnabled(enable);
        swNegotiable.setEnabled(enable);
        btnSave.setEnabled(enable);
    }
}
