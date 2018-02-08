package gawe.imb.karya.presenter.er.adsMy;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

import gawe.imb.karya.model.manager.BursaManager;
import gawe.imb.karya.model.objects.GaweBursa;
import gawe.imb.karya.model.objects.GaweCategoryNested;
import gawe.imb.karya.presenter.base.BasePresenter;

/**
 * Created by korneliussendy on 1/26/18.
 */

public class ERMyAdsPresenter extends BasePresenter<ERMyAdsView> {

    private int page = 0, perPage = 10, dataSize = 0;
    private DocumentSnapshot lastDocument;
    private boolean isLoadingBursa = false, hasMorePage = true;
    private List<GaweBursa> list;

    public ERMyAdsPresenter(ERMyAdsView view) {
        attachView(view);
        list = new ArrayList<>();
    }

    public void loadBursa(String userId) {
        loadBursa(userId, perPage);
    }

    public void loadBursa(String userId, int perPage) {
        if (isLoadingBursa) {
            view.stillLoadingBursa(page, dataSize);
            return;
        }

        if (!hasMorePage) {
            view.allDataLoaded();
            return;
        }

        isLoadingBursa = true;
        view.showLoadingBursa();

        addDisposable(BursaManager.getMyAds(userId, perPage, lastDocument)
                .subscribe((snapshots, throwable) -> {

                    isLoadingBursa = false;
                    view.hideLoadingBursa();

                    if (throwable != null) {
                        view.errorLoadingBursa(throwable.getMessage());
                        return;
                    }

                    page++;
                    dataSize += snapshots.size();
                    hasMorePage = snapshots.size() > 0 && snapshots.size() == perPage;
                    lastDocument = snapshots.size() > 0 ? snapshots.get(snapshots.size() - 1) : null;
                    for (DocumentSnapshot s : snapshots) {
                        list.add(s.toObject(GaweBursa.class));
                    }
                    view.bursaLoaded(list);
                })
        );
    }

    public void reloadBursa(String userId, int perPage) {
        page = 0;
        dataSize = 0;
        lastDocument = null;
        hasMorePage = true;
        list.clear();
        view.clearCurrentBursa();
        compositeDisposable.clear();
        isLoadingBursa = false;
        loadBursa(userId, perPage);
    }

    public void reloadBursa(String userId) {
        reloadBursa(userId, perPage);
    }

    public void viewAdsDetails(int position, GaweBursa bursa) {
        view.toAdsDetailsPage(bursa);
    }

    public void onButtonAddClicked() {
//        view.toAddAdsPage();
        view.pickCategoryPage();
    }

    public void onCategorySelected(GaweCategoryNested category) {
        view.toAddAdsPage(category);
    }
}
