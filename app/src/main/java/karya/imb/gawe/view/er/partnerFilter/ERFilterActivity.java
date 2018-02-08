package karya.imb.gawe.view.er.partnerFilter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Spinner;

import com.google.common.base.Strings;

import java.util.List;

import butterknife.BindView;
import gawe.imb.karya.model.objects.BrowseFilter;
import gawe.imb.karya.presenter.er.partnerFilter.ERFilterPresenter;
import gawe.imb.karya.presenter.er.partnerFilter.ERFilterView;
import karya.imb.gawe.R;
import gawe.imb.karya.model.objects.others.Pair;
import karya.imb.gawe.view.er.ERBaseActivity;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by korneliussendy on 1/29/18.
 */

public class ERFilterActivity extends ERBaseActivity<ERFilterPresenter> implements ERFilterView {

    //    @BindView(R.id.etGender) EditText etGender;
    @BindView(R.id.spinGender) Spinner spinGender;
    @BindView(R.id.progressBarGender) ProgressBar progressBarGender;
    @BindView(R.id.vGender) LinearLayout vGender;
    //    @BindView(R.id.etType) EditText etType;
    @BindView(R.id.spinType) Spinner spinType;
    @BindView(R.id.progressBarType) ProgressBar progressBarType;
    @BindView(R.id.vType) LinearLayout vType;
    @BindView(R.id.etCategory) EditText etCategory;
    @BindView(R.id.progressBarCategory) ProgressBar progressBarCategory;
    @BindView(R.id.vCategory) LinearLayout vCategory;
    @BindView(R.id.parentDetails) LinearLayout parentDetails;
    //    @BindView(R.id.etSort) EditText etSort;
    @BindView(R.id.spinSort) Spinner spinSort;
    @BindView(R.id.progressBarSort) ProgressBar progressBarSort;
    @BindView(R.id.vSort) LinearLayout vSort;
    @BindView(R.id.parentSort) LinearLayout parentSort;
    @BindView(R.id.etDistance) EditText etDistance;
    @BindView(R.id.seekBarDistance) AppCompatSeekBar seekBarDistance;
    @BindView(R.id.parentDistance) LinearLayout parentDistance;
    @BindView(R.id.btnSave) FancyButton btnSave;

    private int MIN_DISTANCE = 5, MAX_DISTANCE = 1000;

    @Override
    protected ERFilterPresenter createPresenter() {
        return new ERFilterPresenter(this);
    }

    @Override
    public int setContentView() {
        return R.layout.activity_er_partner_filter;
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
    public View.OnClickListener onLeftIconClick() {
        return view -> finish();
    }

    @Override
    public int setLeftIconRes() {
        return R.drawable.ic_left_arrow;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("FILTER");

        seekBarDistance.setMax(MAX_DISTANCE - MIN_DISTANCE);
        seekBarDistance.setEnabled(false);
        seekBarDistance.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int distance = i + MIN_DISTANCE;
                etDistance.setText("" + distance + " km");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                presenter.onDistanceChange(seekBar.getProgress() + MIN_DISTANCE);
            }
        });

        presenter.loadData();
    }

    @Override
    public void filterLoaded(BrowseFilter savedFilter) {
        seekBarDistance.setEnabled(true);
        seekBarDistance.setProgress(savedFilter.getDistance() - MIN_DISTANCE);
        etDistance.setText("" + savedFilter.getDistance() + " km");

        btnSave.setOnClickListener(view -> presenter.onButtonFilterClick());
    }

    @Override
    public void showLoadingSavedFilter() {

    }

    @Override
    public void hideLoadingSavedFilter() {

    }

    @Override
    public void loadSavedFilterFailed(String message) {

    }

    @Override
    public void saveFilterSuccess() {
        toast("Success");
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void saveFilterFailed(String s) {
        alert(s);
    }

    @Override
    public void showLoadingGender() {
        progressBarGender.setVisibility(View.VISIBLE);
        spinGender.setVisibility(View.GONE);
    }

    @Override
    public void hideLoadingGender() {
        progressBarGender.setVisibility(View.GONE);
        spinGender.setVisibility(View.VISIBLE);
    }

    @Override
    public void genderLoaded(List<Pair<String, String>> genders, String selectedGender) {
        setupAdapter(spinGender, CODE_GENDER, genders, selectedGender);
        vGender.setOnClickListener(view -> spinGender.performClick());
    }

    @Override
    public void loadGenderError(String message) {
        alert(message);
    }

    @Override
    public void showLoadingType() {
        progressBarType.setVisibility(View.VISIBLE);
        spinType.setVisibility(View.GONE);
    }

    @Override
    public void loadTypeError(String message) {

    }

    @Override
    public void hideLoadingType() {
        progressBarType.setVisibility(View.GONE);
        spinType.setVisibility(View.VISIBLE);
    }

    @Override
    public void typeLoaded(List<Pair<String, String>> types, String selectedType) {
        setupAdapter(spinType, CODE_TYPE, types, selectedType);
        vType.setOnClickListener(view -> spinType.performClick());
    }

    @Override
    public void toSelectCategoryPage() {

    }

    @Override
    public void showLoadingSort() {
        progressBarSort.setVisibility(View.VISIBLE);
        spinSort.setVisibility(View.GONE);
    }

    @Override
    public void hideLoadingSort() {
        progressBarSort.setVisibility(View.GONE);
        spinSort.setVisibility(View.VISIBLE);
    }

    @Override
    public void loadSortError(String message) {

    }

    @Override
    public void sortLoaded(List<Pair<String, String>> sorts, String sortBy) {
        setupAdapter(spinSort, CODE_SORT, sorts, sortBy);
        vSort.setOnClickListener(view -> spinSort.performClick());
    }

    @Override
    public void showLoadingSavingFilter() {

    }

    @Override
    public void hideLoadingSavingFilter() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    private final int CODE_GENDER = 11;
    private final int CODE_TYPE = 12;
    private final int CODE_SORT = 13;

    private void setupAdapter(Spinner spin, int code, List<Pair<String, String>> list, String selectedKey) {
        ArrayAdapter<Pair<String, String>> adapter = new ArrayAdapter<>(this, R.layout.custom_spinner_item);
        adapter.addAll(list);
        spin.setAdapter(adapter);
        spin.setSelection(getIndexOf(list, selectedKey));
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = adapterView.getAdapter().getItem(i);
                if (obj instanceof Pair) {
                    Pair<String, String> pair = (Pair<String, String>) obj;
                    if (code == CODE_GENDER)
                        presenter.onGenderSelected(pair);
                    else if (code == CODE_TYPE)
                        presenter.onTypeSelected(pair);
                    else if (code == CODE_SORT)
                        presenter.onSortSelected(pair);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                if (code == CODE_GENDER)
                    presenter.onGenderSelected(null);
                else if (code == CODE_TYPE)
                    presenter.onTypeSelected(null);
                else if (code == CODE_SORT)
                    presenter.onSortSelected(null);
            }
        });
    }

    private int getIndexOf(@NonNull List<Pair<String, String>> list, String key) {
        if (Strings.isNullOrEmpty(key))
            return 0;

        for (int i = 0; i < list.size(); i++) {
            Pair<String, String> pair = list.get(i);
            if (key.equals(pair.getKey()))
                return i;
        }

        return 0;
    }
}