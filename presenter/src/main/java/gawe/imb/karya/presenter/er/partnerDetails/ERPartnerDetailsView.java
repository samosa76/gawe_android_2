package gawe.imb.karya.presenter.er.partnerDetails;

import java.util.List;

import gawe.imb.karya.model.objects.Employee;
import gawe.imb.karya.model.objects.GaweBrowse;
import gawe.imb.karya.model.objects.GaweCategory;
import gawe.imb.karya.presenter.base.BaseView;
import gawe.imb.karya.presenter.objects.Achievement;
import gawe.imb.karya.presenter.objects.Friend;
import gawe.imb.karya.presenter.objects.PartnerStatistics;
import gawe.imb.karya.presenter.objects.Rate;
import gawe.imb.karya.presenter.objects.Review;

/**
 * Created by korneliussendy on 2/9/18,
 * at 12:22 AM.
 * Gawe
 */

public interface ERPartnerDetailsView extends BaseView {

    void showLoadingPartner();

    void hideLoadingPartner();

    void failedLoadPartner(Throwable throwable);

    void successLoadPartner(Employee partner);

    void showLoadingCategories();

    void hideLoadingCategories();

    void failedLoadCategories(Throwable throwable);

    void successLoadCategories(List<Friend> listFriend);

    void showLoadingFriends();

    void hideLoadingFriends();

    void failedLoadFriends(Throwable throwable);

    void successLoadFriends(List<Friend> listFriend);

    void showLoadingAchievement();

    void hideLoadingAchievement();

    void failedLoadAchievement(Throwable throwable);

    void successLoadAchievement(List<Achievement> listAchievement);

    void showLoadingRate();

    void hideLoadingRate();

    void failedLoadRate(Throwable throwable);

    void successLoadRate(List<Rate> listRate);

    void showLoadingStatistics();

    void hideLoadingStatistics();

    void failedLoadStatistics(Throwable throwable);

    void successLoadStatisctics(List<PartnerStatistics> listStatistics);

    void showLoadingReviews();

    void hideLoadingReviews();

    void failedLoadReviews(Throwable throwable);

    void successLoadReviews(List<Review> listReview);

    void showLoadingImages();

    void hideLoadingImages();

    void failedLoadImages(Throwable throwable);

    void successLoadImages(List<String> listImageUrl);

    void showLoadingCredit();

    void hideLoadingCredit();

    void failedLoadCredit(Throwable throwable);

    void successLoadCredit(Long credit);

    void openPartnerDetails(GaweBrowse browse, String gaweCategory);

    void showConfirmCredit(Long myCredit, Long requiredCredit);

    void showCreditNotEnough(Long myCredit, Long requiredCredit);

    void showLoadingValidateCredit();

    void hideLoadingValidateCredit();

    void failedValidateCredit(Throwable throwable);

    void showLoadingHire();

    void hideLoadingHire();

    void failedLoadHire(Throwable throwable);

    void successLoadHire();

    void openTopUpPage();

    void openGalleryPage(List<String> listImageUrl);

    void openReviewListPage(String id, String categoryId);

    void openCallPage(String phoneNumber);

    void openMessagePage(String partnerId);

    void openAddToContactPage(String phoneNumber);
}
