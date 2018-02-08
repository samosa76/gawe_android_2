package karya.imb.gawe.view.er.adsDetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.common.base.Strings;

import butterknife.BindView;
import gawe.imb.karya.model.objects.Employee;
import gawe.imb.karya.model.objects.Employer;
import gawe.imb.karya.model.objects.GaweBursa;
import gawe.imb.karya.model.objects.User;
import gawe.imb.karya.presenter.er.adsDetails.ERAdsDetailsPresenter;
import gawe.imb.karya.presenter.er.adsDetails.ERAdsDetailsView;
import karya.imb.gawe.R;
import gawe.imb.karya.model.manager.UserManager;
import gawe.imb.karya.model.utils.Helper;
import karya.imb.gawe.view.er.ERBaseActivity;
import karya.imb.gawe.view.er.adsMy.ERMyAdsFragment;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by korneliussendy on 1/30/18.
 */

public class ERAdsDetailsActivity extends ERBaseActivity<ERAdsDetailsPresenter> implements ERAdsDetailsView {

    @BindView(R.id.vJobStatus) View vJobStatus;
    @BindView(R.id.tvStatus) TextView tvStatus;

    @BindView(R.id.tvCategory) TextView tvCategory;
    @BindView(R.id.progressCategory) ProgressBar progressCategory;
    @BindView(R.id.tvWage) TextView tvWage;
    @BindView(R.id.tvNegotiable) TextView tvNegotiable;
    @BindView(R.id.tvDescription) TextView tvDescription;
    @BindView(R.id.tvAddress) TextView tvAddress;
    @BindView(R.id.ivNote) ImageView ivNote;
    @BindView(R.id.tvNote) TextView tvNote;
    @BindView(R.id.vNote) RelativeLayout vNote;
    @BindView(R.id.vJobDetails) LinearLayout vJobDetails;
    @BindView(R.id.vButtons) View vButtons;
    @BindView(R.id.btnShowEmployer) Button btnShowEmployer;
    @BindView(R.id.progressBarEmployer) ProgressBar progressBarEmployer;
    @BindView(R.id.tvEmployerName) TextView tvEmployerName;
    @BindView(R.id.vPhone) View vPhone;
    @BindView(R.id.ivCellphone) ImageView ivCellphone;
    @BindView(R.id.tvEmployerPhone) TextView tvEmployerPhone;
    @BindView(R.id.vPhoneAction) View vPhoneAction;
    @BindView(R.id.ivCall) ImageView ivCall;
    @BindView(R.id.ivMessage) ImageView ivMessage;
    @BindView(R.id.ivAddContact) ImageView ivAddContact;
    @BindView(R.id.vEmployerDetails) LinearLayout vEmployerDetails;
    @BindView(R.id.vEmployer) LinearLayout vEmployer;
    @BindView(R.id.btnApply) FancyButton btnApply;
    @BindView(R.id.btnCancel) Button btnCancel;

    @Override
    protected ERAdsDetailsPresenter createPresenter() {
        return new ERAdsDetailsPresenter(this);
    }

    @Override
    public int setContentView() {
        return R.layout.activity_ads_details;
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
    protected boolean showLogo() {
        return false;
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
        setTitle("ADS DETAILS");

        toggleView(false);
        btnShowEmployer.setOnClickListener(view -> presenter.onButtonContactUserClicked());

        Bundle b = getIntent().getExtras();
        if (b == null)
            b = new Bundle();
        presenter.loadBursaPage(b.getString(ERMyAdsFragment.KEY_VIEW_DETAILS, ""));
    }

    private void toggleView(boolean enabled) {
        btnShowEmployer.setEnabled(enabled);
        btnApply.setEnabled(enabled);
        btnCancel.setEnabled(enabled);
    }

    @Override
    public void dataLoaded(GaweBursa bursa) {
        if (bursa.getEmployerId().equals(UserManager.getUserPreference(this).getId())) {
            btnApply.setVisibility(View.GONE);
            btnApply.setEnabled(false);
        } else {
            btnApply.setVisibility(View.VISIBLE);
            btnApply.setEnabled(false);
        }

        tvCategory.setText("need category");
        String wage = (bursa.getSalaryMax() == bursa.getSalaryMin()) ?
                Helper.formatRupiah(bursa.getSalaryMax()) :
                Helper.formatRupiah(bursa.getSalaryMin()) + " - " + Helper.formatRupiah(bursa.getSalaryMax());
        tvWage.setText(wage);
        tvDescription.setText(bursa.getDescription());
        tvAddress.setText(bursa.getAddress());
        tvNote.setText(bursa.getLocationNote());
        tvNegotiable.setText(bursa.getNegotiable() ? "Negotiable" : "Not Negotiable");

        btnCancel.setVisibility(View.VISIBLE);
        btnCancel.setOnClickListener(view -> presenter.onAdsCancelClick());
    }

    @Override
    public void showPromptCancel() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Cancel ads?");
        builder.setPositiveButton("yes", (dialogInterface, i) -> presenter.cancelAds());
        builder.setNegativeButton("no", (dialogInterface, i) -> dialogInterface.dismiss());
        builder.create().show();
    }

    @Override
    public void showLoadingCancelAds() {

    }

    @Override
    public void hideLoadingCancelAds() {

    }

    @Override
    public void successCancelAds() {

    }

    @Override
    public void failedCancelAds(Throwable throwable) {

    }

    @Override
    public void showBursaCanceled() {

    }

    @Override
    public void showBursaExpired() {

    }

    @Override
    public void showBursaCompleted() {

    }

    @Override
    public void showBursaApproved() {

    }

    @Override
    public void showBursaPending() {
        vJobStatus.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoadingUser() {
        btnShowEmployer.setEnabled(false);
        btnShowEmployer.setVisibility(View.INVISIBLE);
        progressBarEmployer.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingUser() {
        btnShowEmployer.setEnabled(true);
        btnShowEmployer.setVisibility(View.VISIBLE);
        progressBarEmployer.setVisibility(View.GONE);
    }

    @Override
    public void failedLoadingUser() {
        toast("failed to load user");
    }

    @Override
    public void userLoaded(User user, Employer employer, Employee employee) {
        tvEmployerName.setText(user.getFullName());
        if (Strings.isNullOrEmpty(user.getPhone())) {
            vPhone.setVisibility(View.GONE);
            vPhoneAction.setVisibility(View.GONE);
        } else {
            tvEmployerPhone.setText(user.getPhone());
            ivCall.setOnClickListener(view -> presenter.onCallClick(user.getPhone()));
            ivMessage.setOnClickListener(view -> presenter.onMessageClick(user.getPhone()));
            ivAddContact.setOnClickListener(view -> presenter.onAddContactClick(user.getPhone()));
            vPhone.setVisibility(View.VISIBLE);
            vPhoneAction.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showLoadingCategory() {
        tvCategory.setVisibility(View.INVISIBLE);
        progressCategory.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingCategory() {
        tvCategory.setVisibility(View.VISIBLE);
        progressCategory.setVisibility(View.GONE);
    }

    @Override
    public void failedLoadingCategory(String message) {
        log(message);
    }

    @Override
    public void categoryLoaded(String categoryName) {
        tvCategory.setText(categoryName);
    }

    @Override
    public void displayUser() {
        vEmployerDetails.setVisibility(View.VISIBLE);
        vButtons.setVisibility(View.GONE);

    }

    @Override
    public void openCall(String phone) {
        Helper.intentCall(this, phone);
    }

    @Override
    public void sendMessage(String phone) {
        Helper.intentSMS(this, phone);
    }

    @Override
    public void addToContact(String employerName, String phone) {
        toast("Under Construction");
    }

    @Override
    public void fatalError(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error");
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.ok, (dialogInterface, i) -> finish());
        builder.create().show();
    }

}
