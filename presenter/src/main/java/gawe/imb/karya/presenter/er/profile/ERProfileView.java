package gawe.imb.karya.presenter.er.profile;

import java.util.ArrayList;

import gawe.imb.karya.model.objects.Employee;
import gawe.imb.karya.model.objects.Employer;
import gawe.imb.karya.model.objects.User;
import gawe.imb.karya.presenter.base.BaseView;

/**
 * Created by korneliussendy on 1/26/18.
 */

public interface ERProfileView extends BaseView {
    void userLoaded(User user, Employee employee, Employer employer);

    void showPickOption(int imageCode, ArrayList<String> pickOptions);

    void showLoadingUpdateProfileImage();

    void showLoadingUpdateKTPImage();

    void showLoadingUpdateKTMImage();

    void hideLoadingUpdateProfileImage();

    void hideLoadingUpdateKTPImage();

    void hideLoadingUpdateKTMImage();

    void errorUpdateProfileImage(String message);

    void errorUpdateKTPImage(String message);

    void errorUpdateKTMImage(String message);

    void finishUpdateProfileImage(String downloadUrl);

    void finishUpdateKTPImage(String downloadUrl);

    void finishUpdateKTMImage(String downloadUrl);

    void showLoadingUpdateProfile();

    void hideLoadingUpdateProfile();

    void errorUpdateProfile(String message);

    void successUpdateProfile();

    void uploadImageSuccess(int code, String imageUrl);

    void uploadImageFailed(int code, String imageUrl);

    void openCamera(int imageCode, int maxSize, float aspectRatioX, float aspectRatioY);

    void openGallery(int imageCode, int maxSize, float aspectRatioX, float aspectRatioY);

    void progressUploadProfile(long progress, long total);

    void progressUploadKTP(long progress, long total);

    void progressUploadKTM(long progress, long total);

    void toSplashPage();
}
