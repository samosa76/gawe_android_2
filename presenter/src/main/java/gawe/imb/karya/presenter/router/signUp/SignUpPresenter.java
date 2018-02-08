package gawe.imb.karya.presenter.router.signUp;

import com.google.common.base.Strings;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gawe.imb.karya.model.manager.UserManager;
import gawe.imb.karya.presenter.base.BasePresenter;

/**
 * Created by korneliussendy on 1/24/18.
 */

public class SignUpPresenter extends BasePresenter<SignUpView> {

    private static final int CODE_PROFILE = 0;
    private static final int CODE_KTP = 1;
    private static final int CODE_KTM = 2;

    private String accountId, urlProfile;
    private List<String> pickOptions;
    private static String PICK_CAMERA = "Camera";
    private static String PICK_GALLERY = "Gallery";
    private Map<Integer, File> mapImages;

    public SignUpPresenter(SignUpView view) {
        attachView(view);
        pickOptions = new ArrayList<>();
        pickOptions.add(PICK_CAMERA);
        pickOptions.add(PICK_GALLERY);
        mapImages = new HashMap<>();
    }

    public void extractData(String accountId, String phone) {
        this.accountId = accountId;
        if (Strings.isNullOrEmpty(accountId) || Strings.isNullOrEmpty(accountId)) {
            view.errorFailedExtractData();
        }
        view.dataLoaded(accountId, phone);
    }

    public void onButtonUploadProfileClicked() {
        view.showPickOption(CODE_PROFILE, pickOptions);
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

    public void imageUserCanceled() {
    }

    public void uploadImage(int imageCode, File file) {
        switch (imageCode) {
            case CODE_PROFILE:
                view.showLoadingUpdateProfileImage();
                addDisposable(UserManager.uploadProfileEmployer(accountId, file, CODE_PROFILE)
                        .subscribe(uploadResponse -> {
                            view.progressUploadProfile(uploadResponse.getProgress(), uploadResponse.getTotal());
                            if (uploadResponse.isFinish()) {
                                urlProfile = uploadResponse.getDownloadUrl();
                                view.finishUpdateProfileImage(urlProfile);
                            }
                        }, throwable -> {
                            view.hideLoadingUpdateProfileImage();
                            view.errorUpdateProfileImage(throwable.getMessage());
                        }, () -> view.hideLoadingUpdateProfileImage())
                );
                break;
        }
    }

    public void imageSelected(int imageCode, File file) {
        mapImages.put(imageCode, file);
    }

    public void onButtonRegisterClicked() {
        //TODO perlu image?

        String uplineId = view.retrieveReferrerId();
        File profile = mapImages.get(CODE_PROFILE);
        view.showLoadingCreateUser();
        if (mapImages.get(CODE_PROFILE) != null) {
            addDisposable(
                    UserManager.uploadProfileEmployer(accountId, profile, CODE_PROFILE)
                            .subscribe(
                                    uploadResponse -> createUser(uplineId, uploadResponse.getDownloadUrl()),
                                    throwable -> {
                                        view.hideLoadingCreateUser();
                                        view.failedCreateUser(throwable);
                                    }
                            )
            );
        } else {
            createUser(uplineId, null);
        }

    }

    private void createUser(String uplineId, String downloadUrl) {
        UserManager.createNewUser().subscribe(() -> {
            view.hideLoadingCreateUser();
            view.successCreateUser();
        }, throwable ->
        {
            view.hideLoadingCreateUser();
            view.failedCreateUser(throwable);
        });
    }
}