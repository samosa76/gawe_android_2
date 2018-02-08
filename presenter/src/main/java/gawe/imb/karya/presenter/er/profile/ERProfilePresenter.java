package gawe.imb.karya.presenter.er.profile;

import android.util.Patterns;

import com.google.common.base.Strings;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import gawe.imb.karya.model.manager.UserManager;
import gawe.imb.karya.presenter.base.BasePresenter;

/**
 * Created by korneliussendy on 1/26/18.
 */

public class ERProfilePresenter extends BasePresenter<ERProfileView> {

    private static final int CODE_PROFILE = 0;
    private static final int CODE_KTP = 1;
    private static final int CODE_KTM = 2;

    private static String PICK_CAMERA = "Camera";
    private static String PICK_GALLERY = "Gallery";

    private boolean
            newProfilePic = false,
            newKTP = false,
            newKTM = false;

    private String
            urlProfile,
            urlKTP,
            urlKTM;

    private String gender;

    private ArrayList<String> pickOptions;

    public ERProfilePresenter(ERProfileView view) {
        attachView(view);
        pickOptions = new ArrayList<>();
        pickOptions.add(PICK_CAMERA);
        pickOptions.add(PICK_GALLERY);
    }

    public void loadUserBulk(String userId) {
        addDisposable(UserManager.loadUserBulk(userId, true).subscribe(userBulk -> {
                    gender = userBulk.getUser().getGender();
                    view.userLoaded(userBulk.getUser(), userBulk.getEmployee(), userBulk.getEmployer());
                })
        );
    }

    public void reloadUserBulk(String userId) {
        clearData();
        loadUserBulk(userId);
    }

    public void genderChanged(String gender) {
        this.gender = gender;
    }

    public void onProfilePictureClick() {
        view.showPickOption(CODE_PROFILE, pickOptions);
    }

    public void onButtonKTPClick() {
        int maxSize;
        float aspectRatioX, aspectRatioY;
        maxSize = 1200;
        aspectRatioX = 8.6f;
        aspectRatioY = 5.4f;

        view.openCamera(CODE_KTP, maxSize, aspectRatioX, aspectRatioY);
    }

    public void onButtonKTMClick() {
        int maxSize;
        float aspectRatioX, aspectRatioY;
        maxSize = 1200;
        aspectRatioX = 8.6f;
        aspectRatioY = 5.4f;

        view.openCamera(CODE_KTM, maxSize, aspectRatioX, aspectRatioY);

//        view.showPickOption(CODE_KTM, pickOptions);
    }

    public void pickOptionSelected(int imageCode, int optionPosition) {
        int maxSize;
        float aspectRatioX, aspectRatioY;

        switch (imageCode) {
            case CODE_PROFILE:
                maxSize = 800;
                aspectRatioX = 1f;
                aspectRatioY = 1f;
                break;
            case CODE_KTP:
                maxSize = 1200;
                aspectRatioX = 8.6f;
                aspectRatioY = 5.4f;
                break;
            case CODE_KTM:
                maxSize = 1200;
                aspectRatioX = 8.6f;
                aspectRatioY = 5.4f;
                break;
            default:
                view.alert("invalid image code");
                return;
        }

        String option = pickOptions.get(optionPosition);
        if (option.equals(PICK_CAMERA)) {
            view.openCamera(imageCode, maxSize, aspectRatioX, aspectRatioY);
        } else if (option.equals(PICK_GALLERY)) {
            view.openGallery(imageCode, maxSize, aspectRatioX, aspectRatioY);
        }
    }

    public void uploadImage(String userId, int imageCode, File file) {

        switch (imageCode) {
            case CODE_PROFILE:
                view.showLoadingUpdateProfileImage();
                addDisposable(UserManager.uploadProfileEmployer(userId, file, CODE_PROFILE)
                        .subscribe(uploadResponse -> {
                            view.progressUploadProfile(uploadResponse.getProgress(), uploadResponse.getTotal());
                            if (uploadResponse.isFinish()) {
                                urlProfile = uploadResponse.getDownloadUrl();
                                view.finishUpdateProfileImage(urlProfile);
                                UserManager.saveUserOnly(UserManager.PROFILE_PIC, urlProfile, userId);
                            }
                        }, throwable -> {
                            view.hideLoadingUpdateProfileImage();
                            view.errorUpdateProfileImage(throwable.getMessage());
                        }, () -> {
                            newProfilePic = true;
                            view.hideLoadingUpdateProfileImage();
                        })
                );
                break;
            case CODE_KTP:
                view.showLoadingUpdateKTPImage();
                addDisposable(UserManager.uploadKTPEmployer(userId, file, CODE_PROFILE)
                        .subscribe(uploadResponse -> {
                            view.progressUploadKTP(uploadResponse.getProgress(), uploadResponse.getTotal());
                            if (uploadResponse.isFinish()) {
                                urlKTP = uploadResponse.getDownloadUrl();
                                view.finishUpdateKTPImage(urlKTP);
                                UserManager.saveUserOnly(UserManager.KTP, urlKTP, userId);
                            }
                        }, throwable -> {
                            view.hideLoadingUpdateKTPImage();
                            view.errorUpdateKTPImage(throwable.getMessage());
                        }, () -> {
                            newKTP = true;
                            view.hideLoadingUpdateKTPImage();
                        })
                );
                break;
            case CODE_KTM:
                view.showLoadingUpdateKTMImage();
                addDisposable(UserManager.uploadKTMEmployer(userId, file, CODE_PROFILE)
                        .subscribe(uploadResponse -> {
                            view.progressUploadKTM(uploadResponse.getProgress(), uploadResponse.getTotal());
                            if (uploadResponse.isFinish()) {
                                urlKTM = uploadResponse.getDownloadUrl();
                                view.finishUpdateKTMImage(urlKTM);
                                UserManager.saveUserOnly(UserManager.KARTU_MAHASISWA, urlKTM, userId);
                            }
                        }, throwable -> {
                            view.hideLoadingUpdateKTMImage();
                            view.errorUpdateKTMImage(throwable.getMessage());
                        }, () -> {
                            newKTM = true;
                            view.hideLoadingUpdateKTMImage();
                        })
                );
                break;
            default:
                view.alert("invalid image code");
                break;
        }

    }

    public void onLogoutClick() {
        UserManager.semiLogout();
        //TODO update user hilangkan device token supaya tidak kena push
        view.toSplashPage();
    }

    public void updateUser(String userId, String name, String email, String phone) {
        boolean valid = true;
        String message = "";
        //VALIDASI
        if (Strings.isNullOrEmpty(name)) {
            valid = false;
            message = "Empty Name";
        } else if (name.length() > 30) {
            valid = false;
            message = "Name Too Long";
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            valid = false;
            message += "\nInvalid Email";
        }
        if (!Patterns.PHONE.matcher(phone).matches()) {
            valid = false;
            message += "\nInvalid Phone";
        }

        if (Strings.isNullOrEmpty(gender)) {
            valid = false;
            message += "\nInvalid Gender";
        }

        if (!valid) {
            view.errorUpdateProfile(message);
            return;
        }

        Map<String, Object> mapUser = new HashMap<>();
        mapUser.put(UserManager.FULL_NAME, name);
        mapUser.put(UserManager.EMAIL, email);
        mapUser.put(UserManager.PHONE, phone);
        mapUser.put(UserManager.GENDER, gender);


        if (newProfilePic)
            mapUser.put(UserManager.PROFILE_PIC, urlProfile);
        if (newKTP)
            mapUser.put(UserManager.KTP, urlKTP);
        if (newKTM)
            mapUser.put(UserManager.KARTU_MAHASISWA, urlKTM);

        view.log(mapUser.toString());

        view.showLoadingUpdateProfile();
        compositeDisposable.add(UserManager.saveUser(mapUser, userId).subscribe(() -> {
                    view.hideLoadingUpdateProfile();
                    view.successUpdateProfile();
                    clearData();
                }, throwable -> {
                    view.hideLoadingUpdateProfile();
                    view.errorUpdateProfile(throwable.getMessage());
                })
        );
    }

    private void clearData() {
        urlProfile = null;
        urlKTP = null;
        urlKTM = null;

        newProfilePic = false;
        newKTP = false;
        newKTM = false;
    }

    public void imageUserCanceled() {

    }
}
