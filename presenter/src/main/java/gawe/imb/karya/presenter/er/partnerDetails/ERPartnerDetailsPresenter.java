package gawe.imb.karya.presenter.er.partnerDetails;

import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.List;

import gawe.imb.karya.model.manager.GsonManager;
import gawe.imb.karya.model.objects.Employee;
import gawe.imb.karya.model.objects.GaweBrowse;
import gawe.imb.karya.model.objects.GaweCategory;
import gawe.imb.karya.presenter.base.BasePresenter;
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

public class ERPartnerDetailsPresenter extends BasePresenter<ERPartnerDetailsView> {

    private GaweBrowse browse;
    private String categoryId;

    private List<Friend> listFriend;
    private List<Achievement> listAchievement;
    private List<Rate> listRate;
    private List<PartnerStatistics> listStatistics;
    private List<Review> listReview;
    private List<String> listImageUrl;
    private List<GaweCategory> listCategories;
    private Employee partner;

    private boolean valid = false;

    public ERPartnerDetailsPresenter(ERPartnerDetailsView view) {
        attachView(view);
        listFriend = new ArrayList<>();
        listAchievement = new ArrayList<>();
        listRate = new ArrayList<>();
        listStatistics = new ArrayList<>();
        listReview = new ArrayList<>();
        listImageUrl = new ArrayList<>();
        listCategories = new ArrayList<>();
    }

    public void ExtractData(String dataBrowse, String categoryId) {
        this.browse = GsonManager.extractData(dataBrowse, GaweBrowse.class);
        this.categoryId = categoryId;
        this.valid = (browse != null && !Strings.isNullOrEmpty(categoryId));

        if (browse == null) {
            throw new NullPointerException("null or broken data browse");
        } else if (Strings.isNullOrEmpty(categoryId)) {
            throw new NullPointerException("null or empty category id");
        }
    }

    public void loadData() {
        if (!valid)
            return;
        loadPartner();
        loadOtherCategories();
        loadFriends();
        loadAchievement();
        loadRate();
        loadStatistics();
        loadReviews();
        loadPartnerImages();
        loadRequiredCredit();
    }

    public void onCategorySelected(int position) {
        view.openPartnerDetails(browse, listCategories.get(position).getCat_id());
    }

    public void onHireClicked() {
        view.showLoadingValidateCredit();
        view.hideLoadingValidateCredit();
        view.failedValidateCredit(new Throwable());
        view.showConfirmCredit(1L, 1L);
    }

    public void onConfirmHireClicked() {
        view.showLoadingHire();
        view.hideLoadingHire();
        view.failedLoadHire(new Throwable());
        view.successLoadHire();
    }

    public void onCallClicked() {
        String phoneNumber = "";
        view.openCallPage(phoneNumber);
    }

    public void onMessageClicked() {
        String partnerId = "";
        view.openMessagePage(partnerId);
    }

    public void onAddToContactClicked() {
        String phoneNumber = "";
        view.openAddToContactPage(phoneNumber);
    }

    public void onTopUpClicked() {
        view.openTopUpPage();
    }

    public void onSeeAllPhotoClicked() {
        view.openGalleryPage(listImageUrl);
    }

    public void onSeeAllReviewClicked() {
        view.openReviewListPage(partner.getId(), categoryId);
    }


    public void loadPartner() {
        view.showLoadingPartner();
        view.hideLoadingPartner();
        view.failedLoadPartner(new Throwable());
        view.successLoadPartner(partner);
    }

    public void loadOtherCategories() {
        //TODO also load category

        view.showLoadingCategories();
        view.hideLoadingCategories();
        view.failedLoadCategories(new Throwable());
        view.successLoadCategories(listFriend);
    }

    public void loadFriends() {
        view.showLoadingFriends();
        view.hideLoadingFriends();
        view.failedLoadFriends(new Throwable());
        view.successLoadFriends(listFriend);
    }

    public void loadAchievement() {
        view.showLoadingAchievement();
        view.hideLoadingAchievement();
        view.failedLoadAchievement(new Throwable());
        view.successLoadAchievement(listAchievement);
    }

    public void loadRate() {
        view.showLoadingRate();
        view.hideLoadingRate();
        view.failedLoadRate(new Throwable());
        view.successLoadRate(listRate);
    }

    public void loadStatistics() {
        view.showLoadingStatistics();
        view.hideLoadingStatistics();
        view.failedLoadStatistics(new Throwable());
        view.successLoadStatisctics(listStatistics);
    }

    public void loadReviews() {
        view.showLoadingReviews();
        view.hideLoadingReviews();
        view.failedLoadReviews(new Throwable());
        view.successLoadReviews(listReview);
    }

    public void loadPartnerImages() {
        view.showLoadingImages();
        view.hideLoadingImages();
        view.failedLoadImages(new Throwable());
        view.successLoadImages(listImageUrl);
    }

    public void loadRequiredCredit() {
        //demo only
        Long credit = 1L;
        view.showLoadingCredit();
        view.hideLoadingCredit();
        view.failedLoadCredit(new Throwable());
        view.successLoadCredit(credit);
    }


}
