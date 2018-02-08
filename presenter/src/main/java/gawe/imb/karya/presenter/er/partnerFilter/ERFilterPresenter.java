package gawe.imb.karya.presenter.er.partnerFilter;

import android.util.Log;

import com.google.common.base.Strings;

import java.util.List;

import gawe.imb.karya.model.manager.FilterManager;
import gawe.imb.karya.model.objects.BrowseFilter;
import gawe.imb.karya.model.objects.others.Pair;
import gawe.imb.karya.presenter.base.BasePresenter;

/**
 * Created by korneliussendy on 1/29/18.
 */

public class ERFilterPresenter extends BasePresenter<ERFilterView> {

    private BrowseFilter filter;
    private List<Pair<String, String>> listGender, listType, listSort;

    public ERFilterPresenter(ERFilterView view) {
        attachView(view);
    }

    public void loadData() {
        view.showLoadingSavedFilter();
        view.showLoadingGender();
        view.showLoadingType();
        view.showLoadingSort();

        addDisposable(FilterManager.getSavedFilter().subscribe((f, throwable) -> {
            view.hideLoadingSavedFilter();
            if (throwable != null) {
                view.loadSavedFilterFailed(throwable.getMessage());
                return;
            }
            view.filterLoaded(f);
            this.filter = f;
            loadGender(f);
            loadType(f);
            loadSort(f);

        }));
    }

    private void loadSavedFilter() {
        view.showLoadingSavedFilter();
        addDisposable(FilterManager.getSavedFilter().subscribe((f, throwable) -> {
            view.hideLoadingSavedFilter();
            if (throwable != null) {
                view.loadSavedFilterFailed(throwable.getMessage());
                return;
            }
            view.filterLoaded(f);
            filter = f;

        }));
    }

    private void loadGender(BrowseFilter f) {
        view.showLoadingGender();
        addDisposable(FilterManager.getGenderList().subscribe((gender, throwable) -> {
            view.hideLoadingGender();
            if (throwable != null) {
                view.loadGenderError(throwable.getMessage());
                return;
            }
            listGender = gender;
            view.genderLoaded(gender, f.getGender());
        }));
    }

    private void loadType(BrowseFilter f) {
        view.showLoadingType();
        addDisposable(FilterManager.getTypeList().subscribe((types, throwable) -> {
            view.hideLoadingType();
            if (throwable != null) {
                view.loadTypeError(throwable.getMessage());
                return;
            }
            listType = types;
            view.typeLoaded(types, f.getType());
        }));
    }

    private void loadSort(BrowseFilter f) {
        view.showLoadingSort();
        addDisposable(FilterManager.getSortList().subscribe((sorts, throwable) -> {
            view.hideLoadingSort();
            if (throwable != null) {
                view.loadSortError(throwable.getMessage());
                return;
            }
            listSort = sorts;
            view.sortLoaded(sorts, f.getSortBy());

        }));
    }

    public void saveFilter(BrowseFilter filter) {
        view.showLoadingSavingFilter();
        Log.d("SaveFilter", filter.toString());

        addDisposable(FilterManager.saveFilter(filter).subscribe(aBoolean -> {
                    view.hideLoadingSavingFilter();
                    if (aBoolean) {
                        view.saveFilterSuccess();
                    } else {
                        view.saveFilterFailed("Failed to save");
                    }
                }
        ));
    }

    public void onGenderSelected(Pair<String, String> selectedGender) {
        if (selectedGender == null) {
            filter.setGender(FilterManager.GENDER_FEMALE);
        } else {
            Log.d("SelectedGender", selectedGender.getKey());
            filter.setGender(selectedGender.getKey());
        }
    }

    public void onTypeSelected(Pair<String, String> selectedGender) {
        if (selectedGender == null) {
            filter.setType(FilterManager.TYPE_ALL);
        } else {
            filter.setType(selectedGender.getKey());
        }
    }

    public void onCategoryClick() {
        view.toSelectCategoryPage();
    }


    public void onSortSelected(Pair<String, String> selectedGender) {
        if (selectedGender == null)
            filter.setSortBy(FilterManager.SORT_NEARBY);
        else
            filter.setSortBy(selectedGender.getKey());
    }

    public void onDistanceChange(int distance) {
        filter.setDistance(distance);
    }

    public void onCategorySelected() {

    }

    public void onButtonFilterClick() {
        if (Strings.isNullOrEmpty(filter.getType())) {
            filter.setType(FilterManager.TYPE_ALL);
        }

        if (filter.getType().equals(FilterManager.TYPE_ALL)) {
            filter.setFullTime(filter.getType().equals(FilterManager.TYPE_ALL) ||
                    filter.getType().equals(FilterManager.TYPE_FULL_TIME)
            );
            filter.setPartTime(filter.getType().equals(FilterManager.TYPE_ALL) ||
                    filter.getType().equals(FilterManager.TYPE_PART_TIME)
            );
        }

        saveFilter(filter);
    }
}
