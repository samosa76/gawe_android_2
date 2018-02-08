package karya.imb.gawe.view.signUp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.miguelbcr.ui.rx_paparazzo2.RxPaparazzo;
import com.miguelbcr.ui.rx_paparazzo2.entities.Options;
import com.miguelbcr.ui.rx_paparazzo2.entities.size.CustomMaxSize;

import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import gawe.imb.karya.model.modules.DynamicLinkService;
import gawe.imb.karya.presenter.router.signUp.SignUpPresenter;
import gawe.imb.karya.presenter.router.signUp.SignUpView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import karya.imb.gawe.GlideApp;
import karya.imb.gawe.R;
import karya.imb.gawe.mvp.MvpActivity;
import gawe.imb.karya.mainlibs.utils.Constants;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by korneliussendy on 1/24/18.
 */

public class SignUpActivity extends MvpActivity<SignUpPresenter> implements SignUpView {

    @BindView(R.id.ivBackgroundProfile) ImageView ivBackgroundProfile;
    @BindView(R.id.ivImage) CircleImageView ivImage;
    @BindView(R.id.btnUploadProfile) FancyButton btnUploadProfile;
    @BindView(R.id.progressProfile) ProgressBar progressProfile;
    @BindView(R.id.etName) EditText etName;
    @BindView(R.id.etPhone) EditText etPhone;
    @BindView(R.id.cbTOS) CheckBox cbTOS;
    @BindView(R.id.btnRegister) FancyButton btnRegister;

    @Override
    protected int layoutRes() {
        return R.layout.activity_sign_up;
    }

    @Override
    protected SignUpPresenter createPresenter() {
        return new SignUpPresenter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.extractData(
                getIntent().getStringExtra(Constants.ACCOUNT_ID),
                getIntent().getStringExtra(Constants.PHONE_NUMBER)
        );
    }

    @Override
    public void dataLoaded(String accountId, String phone) {
        etPhone.setText(phone);
        btnUploadProfile.setOnClickListener(view -> presenter.onButtonUploadProfileClicked());
        btnRegister.setOnClickListener(view -> presenter.onButtonRegisterClicked());
    }

    @Override
    public void errorFailedExtractData() {

    }

    @Override
    public void showPickOption(int imageCode, List<String> pickOptions) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice);
        adapter.addAll(pickOptions);
        builder.setAdapter(adapter, (dialogInterface, i) -> {
            presenter.pickOptionSelected(imageCode, i);
            dialogInterface.dismiss();
        });
        builder.create().show();
    }

    @Override
    public void openCamera(int imageCode, int maxSize, float aspectRatioX, float aspectRatioY) {
        RxPaparazzo.single(this)
                .size(new CustomMaxSize(maxSize))
                .crop(createOption(aspectRatioX, aspectRatioY))
                .usingCamera()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if (response.resultCode() != RESULT_OK) {
                        response.targetUI().presenter.imageUserCanceled();
                        return;
                    }

                    response.targetUI().presenter.uploadImage(
                            imageCode,
                            response.data().getFile()
                    );
                });
    }

    @Override
    public void openGallery(int imageCode, int maxSize, float aspectRatioX, float aspectRatioY) {
        RxPaparazzo.single(this)
                .size(new CustomMaxSize(maxSize))
                .crop(createOption(aspectRatioX, aspectRatioY))
                .usingGallery()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if (response.resultCode() != RESULT_OK) {
                        response.targetUI().presenter.imageUserCanceled();
                        return;
                    }

                    response.targetUI().presenter.imageSelected(
                            imageCode,
                            response.data().getFile()
                    );
                });
    }

    @Override
    public void showLoadingUpdateProfileImage() {
        btnUploadProfile.setEnabled(false);
        btnRegister.setEnabled(false);
        ivBackgroundProfile.setVisibility(View.INVISIBLE);
        progressProfile.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingUpdateProfileImage() {
        btnUploadProfile.setEnabled(true);
        btnRegister.setEnabled(true);
        progressProfile.setVisibility(View.GONE);
    }

    @Override
    public void progressUploadProfile(long progress, long total) {

    }

    @Override
    public void finishUpdateProfileImage(String urlProfile) {
        ivBackgroundProfile.setVisibility(View.GONE);
        GlideApp.with(this)
                .load(urlProfile)
                .placeholder(R.drawable.logo_g_yellow_transparent)
                .error(R.drawable.logo_g_yellow_transparent)
                .into(ivImage);
    }

    @Override
    public void errorUpdateProfileImage(String message) {
        ivBackgroundProfile.setVisibility(View.VISIBLE);
    }

    @Override
    public String retrieveReferrerId() {
        DynamicLinkService service = new DynamicLinkService();
        return service.getCode(this);
    }

    @Override
    public void showLoadingCreateUser() {
        toggleView(false);
    }

    @Override
    public void hideLoadingCreateUser() {
        toggleView(true);
    }

    @Override
    public void failedCreateUser(Throwable throwable) {
        toggleView(true);
    }

    @Override
    public void successCreateUser() {
        toggleView(false);
    }

    private void toggleView(boolean enabled) {
        ivBackgroundProfile.setEnabled(enabled);
        ivImage.setEnabled(enabled);
        btnUploadProfile.setEnabled(enabled);
        progressProfile.setEnabled(enabled);
        etName.setEnabled(enabled);
        etPhone.setEnabled(enabled);
        cbTOS.setEnabled(enabled);
        btnRegister.setEnabled(enabled);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    private Options createOption(float aspectRatioX, float aspectRatioY) {
        Options options = new Options();
        options.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        options.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        options.setAspectRatio(aspectRatioX, aspectRatioY);
        return options;
    }

}
