package karya.imb.gawe.view.er.profile;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.common.base.Strings;
import com.miguelbcr.ui.rx_paparazzo2.RxPaparazzo;
import com.miguelbcr.ui.rx_paparazzo2.entities.Options;
import com.miguelbcr.ui.rx_paparazzo2.entities.size.CustomMaxSize;

import java.util.ArrayList;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import gawe.imb.karya.model.objects.Employee;
import gawe.imb.karya.model.objects.Employer;
import gawe.imb.karya.model.objects.User;
import gawe.imb.karya.presenter.er.profile.ERProfilePresenter;
import gawe.imb.karya.presenter.er.profile.ERProfileView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import karya.imb.gawe.GlideApp;
import karya.imb.gawe.R;
import gawe.imb.karya.model.manager.UserManager;
import gawe.imb.karya.model.utils.Helper;
import karya.imb.gawe.view.er.BaseTabFragment;
import karya.imb.gawe.view.splash.SplashActivity;
import mehdi.sakout.fancybuttons.FancyButton;

import static android.app.Activity.RESULT_OK;

/**
 * Created by korneliussendy on 1/26/18.
 */

public class ERProfileFragment extends BaseTabFragment<ERProfilePresenter> implements ERProfileView {

    @BindView(R.id.swipeRefreshLayout) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.btnLogout) View btnLogout;
    @BindView(R.id.vPicture) LinearLayout vPicture;
    @BindView(R.id.ivImage) CircleImageView ivImage;
    @BindView(R.id.progressProfile) ProgressBar progressProfile;
    @BindView(R.id.etName) EditText etName;
    @BindView(R.id.etEmail) EditText etEmail;
    @BindView(R.id.etPhone) EditText etPhone;
    @BindView(R.id.rbMale) RadioButton rbMale;
    @BindView(R.id.rbFemale) RadioButton rbFemale;
    @BindView(R.id.rgGender) RadioGroup rgGender;
    @BindView(R.id.vDetails) LinearLayout vDetails;
    @BindView(R.id.btnUploadKTP) FancyButton btnUploadKTP;
    @BindView(R.id.progressKTP) ProgressBar progressKTP;
    @BindView(R.id.ivKTP) ImageView ivKTP;
    @BindView(R.id.btnUploadKTM) FancyButton btnUploadKTM;
    @BindView(R.id.progressKTM) ProgressBar progressKTM;
    @BindView(R.id.ivKTM) ImageView ivKTM;
    @BindView(R.id.vIdentity) LinearLayout vIdentity;
    @BindView(R.id.btnSave) FancyButton btnSave;

    @Override
    protected ERProfilePresenter createPresenter() {
        return new ERProfilePresenter(this);
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_er_profile;
    }

    @Override
    protected boolean showToolbar() {
        return true;
    }

    @Override
    protected int resBackgroundToolbar() {
        return R.color.white;
    }

    @Override
    protected void setView(View view) {
        toggleMainView(false);
        presenter.loadUserBulk(UserManager.getUserPreference(getContext()).getId());
        swipeRefreshLayout.setOnRefreshListener(() -> {
            toggleMainView(false);
            swipeRefreshLayout.setRefreshing(true);
            presenter.reloadUserBulk(UserManager.getUserPreference(getContext()).getId());
        });
        btnLogout.setOnClickListener(v -> presenter.onLogoutClick());
    }


    private void toggleMainView(boolean enabled) {
        etName.setEnabled(enabled);
        etEmail.setEnabled(enabled);
        etPhone.setEnabled(enabled);

        rgGender.setEnabled(enabled);
        rbMale.setEnabled(enabled);
        rbFemale.setEnabled(enabled);

        ivImage.setEnabled(enabled);
        btnUploadKTM.setEnabled(enabled);
        ivKTM.setEnabled(enabled);
        btnUploadKTP.setEnabled(enabled);
        ivKTP.setEnabled(enabled);

        btnSave.setEnabled(enabled);
        btnLogout.setEnabled(enabled);
    }

    @Override
    public void userLoaded(User user, Employee employee, Employer employer) {
        toggleMainView(true);
        swipeRefreshLayout.setRefreshing(false);
        Log.d("USER LOADED ", "User : " + user.toString());
        Log.d("USER LOADED ", "Employee : " + employee.toString());
        Log.d("USER LOADED ", "Employer : " + employer.toString());
        rgGender.setOnCheckedChangeListener((radioGroup, i) -> {
            RadioButton rb = radioGroup.findViewById(i);
            presenter.genderChanged(rb.getTag().toString());
        });

        if (user.getGender().equals("M")) {
            rbMale.setChecked(true);
        } else {
            rbFemale.setChecked(true);
        }

        etName.setText(user.getFullName());
        etEmail.setText(user.getEmail());
        etPhone.setText(user.getPhone());

        ivImage.setOnClickListener(v1 -> presenter.onProfilePictureClick());
        btnUploadKTP.setOnClickListener(v2 -> presenter.onButtonKTPClick());
        btnUploadKTM.setOnClickListener(v3 -> presenter.onButtonKTMClick());

        btnSave.setOnClickListener(v4 -> presenter.updateUser(
                UserManager.getUserPreference(getContext()).getId(),
                Helper.get(etName),
                Helper.get(etEmail),
                Helper.get(etPhone)
        ));

        GlideApp.with(this)
                .load(user.getProfile_pic()).
                placeholder(R.drawable.logo_g_yellow_transparent)
                .error(R.drawable.logo_g_yellow_transparent)
                .into(ivImage);

        ivKTP.setVisibility(Strings.isNullOrEmpty(user.getKtp()) ? View.GONE : View.VISIBLE);
        ivKTM.setVisibility(Strings.isNullOrEmpty(user.getKartu_mahasiswa()) ? View.GONE : View.VISIBLE);

        GlideApp.with(this)
                .load(user.getKtp()).
                placeholder(R.drawable.logo_g_yellow_transparent)
                .error(R.drawable.logo_g_yellow_transparent)
                .into(ivKTP);

        GlideApp.with(this)
                .load(user.getKartu_mahasiswa()).
                placeholder(R.drawable.logo_g_yellow_transparent)
                .error(R.drawable.logo_g_yellow_transparent)
                .into(ivKTM);


    }

    @Override
    public void showPickOption(int imageCode, ArrayList<String> pickOptions) {
        if (getContext() == null)
            return;
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.select_dialog_singlechoice);
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
                            UserManager.getUserPreference(getContext()).getId(),
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

                    response.targetUI().presenter.uploadImage(
                            UserManager.getUserPreference(getContext()).getId(),
                            imageCode,
                            response.data().getFile()
                    );
                });
    }

    @Override
    public void showLoadingUpdateProfileImage() {
        ivImage.setEnabled(false);
        progressProfile.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoadingUpdateKTPImage() {
        ivKTP.setEnabled(false);
        progressKTP.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoadingUpdateKTMImage() {
        ivKTM.setEnabled(false);
        progressKTM.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingUpdateProfileImage() {
        ivImage.setEnabled(true);
        progressProfile.setVisibility(View.GONE);
    }

    @Override
    public void hideLoadingUpdateKTPImage() {
        ivKTP.setEnabled(true);
        progressKTP.setVisibility(View.GONE);
    }

    @Override
    public void hideLoadingUpdateKTMImage() {
        ivKTM.setEnabled(true);
        progressKTM.setVisibility(View.GONE);
    }

    @Override
    public void errorUpdateProfileImage(String message) {
        ivImage.setEnabled(true);
    }

    @Override
    public void errorUpdateKTPImage(String message) {
        ivKTP.setEnabled(true);
    }

    @Override
    public void errorUpdateKTMImage(String message) {
        ivKTM.setEnabled(true);
    }

    @Override
    public void finishUpdateProfileImage(String downloadUrl) {
        ivImage.setEnabled(true);
        GlideApp.with(this)
                .load(downloadUrl)
                .placeholder(R.drawable.logo_g_yellow_transparent)
                .error(R.drawable.logo_g_yellow_transparent)
                .into(ivImage);
    }

    @Override
    public void finishUpdateKTPImage(String downloadUrl) {
        ivKTP.setVisibility(View.VISIBLE);
        ivKTP.setEnabled(true);
        GlideApp.with(this)
                .load(downloadUrl)
                .into(ivKTP);
    }

    @Override
    public void finishUpdateKTMImage(String downloadUrl) {
        ivKTM.setVisibility(View.VISIBLE);
        ivKTM.setEnabled(true);
        GlideApp.with(this)
                .load(downloadUrl)
                .into(ivKTM);
    }

    @Override
    public void progressUploadProfile(long progress, long total) {
        log("Profile picture update : " + progress + " from " + total);
    }

    @Override
    public void progressUploadKTP(long progress, long total) {
        log("KTP update : " + progress + " from " + total);
    }

    @Override
    public void progressUploadKTM(long progress, long total) {
        log("KTM update : " + progress + " from " + total);
    }

    @Override
    public void showLoadingUpdateProfile() {
        btnSave.setText("UPDATING PROFILE");
        toggleMainView(false);
    }

    @Override
    public void hideLoadingUpdateProfile() {
        btnSave.setText("SAVE");
        toggleMainView(true);
    }

    @Override
    public void errorUpdateProfile(String message) {
        alert(message);
    }

    @Override
    public void successUpdateProfile() {
        toast("update success");
    }

    @Override
    public void uploadImageSuccess(int code, String imageUrl) {

    }

    @Override
    public void uploadImageFailed(int code, String imageUrl) {

    }

    @Override
    public void toSplashPage() {
        Intent i = new Intent(getContext(), SplashActivity.class);
        startActivity(i);
        if (getActivity() != null)
            getActivity().finishAffinity();
    }

    private Options createOption(float aspectRatioX, float aspectRatioY) {
        Options options = new Options();
        options.setToolbarColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        options.setStatusBarColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
        options.setAspectRatio(aspectRatioX, aspectRatioY);
        return options;
    }
}
