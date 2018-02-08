package gawe.imb.karya.presenter.er.partners;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import gawe.imb.karya.model.manager.ERBrowseManager;
import gawe.imb.karya.model.manager.FilterManager;
import gawe.imb.karya.model.objects.BrowseFilter;
import gawe.imb.karya.model.objects.GaweBrowse;
import gawe.imb.karya.presenter.base.BasePresenter;

/**
 * Created by korneliussendy on 1/26/18.
 */

public class ERPartnersPresenter extends BasePresenter<ERPartnersView> {

    private int page = 1, perPage = 5;
    private Long total = 0L;
    private boolean isLoadingBrowse = false, hasMorePage = true;
    private List<GaweBrowse> list;
    private BrowseFilter selectedFilter;

    public ERPartnersPresenter(ERPartnersView view) {
        attachView(view);
        list = new ArrayList<>();
    }

    public void onFilterClick() {
        view.toFilterPage();
    }

    public void loadBrowse(Double lat, Double lng) {
        loadBrowse(lat, lng, getSelectedFilter());
    }

    public void loadBrowse(Double lat, Double lng, BrowseFilter filter) {
        loadBrowse(page, perPage, lat, lng, filter);
    }

    public void loadBrowse(int page, int perPage, double lat, double lng, BrowseFilter filter) {

        view.log("loadBrowse page " + page);
        if (isLoadingBrowse) {
            view.log("still loading " + page);
            view.stillLoadingBrowse(this.page, this.perPage);
            return;
        }

        if (!hasMorePage) {
            view.log("dont has more page " + page);
            view.allBrowseLoaded();
            return;
        }

        this.page = page;
        this.perPage = perPage;

        isLoadingBrowse = true;
        view.showLoadingBrowse();

        Log.d("loadBrowse: ", "" + filter.toString());
        if (filter == null) {
            filter = FilterManager.getPrefSavedFilter();
        }

        addDisposable(ERBrowseManager.getBrowseItem(page, perPage, lat, lng, filter)
                .subscribe((paginationResponse, throwable) -> {
                    isLoadingBrowse = false;
                    view.hideLoadingBrowse();

                    if (throwable != null) {
                        Log.e("Partner", "loadBrowse: ", throwable);
                        view.failedLoadBrowse(throwable.getMessage());
                        return;
                    }

                    if (this.page <= 1 && paginationResponse.getResults().size() <= 0) {
                        view.dataLoadedZeroResult();
                    }

                    this.page++;
                    this.total = paginationResponse.getTotal();
                    Log.d("Partner", "loadBrowse: " + paginationResponse.toString());
                    List<GaweBrowse> list = paginationResponse.getResults();
                    this.list.addAll(list);

                    Log.d("PartnerCek", "current list size: " + this.list.size() + " total : " + total);

                    hasMorePage = this.list.size() < total;
                    view.browseLoaded(list);
                })
        );
    }

    public void reloadBrowse(Double lat, Double lng) {
        page = 1;
        total = 0L;
        isLoadingBrowse = false;
        hasMorePage = true;
        list.clear();
        compositeDisposable.clear();
        view.clearCurrentData();
        selectedFilter = null;

        loadBrowse(lat, lng, getSelectedFilter());
    }

    private BrowseFilter getSelectedFilter() {
        if (selectedFilter == null) {
            selectedFilter = FilterManager.getPrefSavedFilter();
        }
        view.setDistance(selectedFilter.getDistance());
        return selectedFilter;
    }

}
