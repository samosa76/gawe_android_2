package gawe.imb.karya.presenter.router.signUp;

import java.util.List;

import gawe.imb.karya.presenter.base.BaseView;

/**
 * Created by korneliussendy on 1/24/18.
 */

public interface SignUpView extends BaseView {

    void dataLoaded(String accountId, String phone);

    void errorFailedExtractData();

    void showPickOption(int codeProfile, List<String> pickOptions);

    void openCamera(int imageCode, int maxSize, float aspectRatioX, float aspectRatioY);

    void openGallery(int imageCode, int maxSize, float aspectRatioX, float aspectRatioY);

    void showLoadingUpdateProfileImage();

    void hideLoadingUpdateProfileImage();

    void progressUploadProfile(long progress, long total);

    void finishUpdateProfileImage(String urlProfile);

    void errorUpdateProfileImage(String message);

    String retrieveReferrerId();

    void showLoadingCreateUser();

    void hideLoadingCreateUser();

    void failedCreateUser(Throwable throwable);

    void successCreateUser();
}
