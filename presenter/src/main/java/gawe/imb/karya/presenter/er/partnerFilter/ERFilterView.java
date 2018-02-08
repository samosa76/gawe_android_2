package gawe.imb.karya.presenter.er.partnerFilter;

import java.util.List;

import gawe.imb.karya.model.objects.BrowseFilter;
import gawe.imb.karya.model.objects.others.Pair;
import gawe.imb.karya.presenter.base.BaseView;

/**
 * Created by korneliussendy on 1/29/18.
 */

public interface ERFilterView extends BaseView {

    void filterLoaded(BrowseFilter savedFilter);

    void showLoadingSavedFilter();

    void hideLoadingSavedFilter();

    void loadSavedFilterFailed(String message);


    void saveFilterSuccess();

    void saveFilterFailed(String s);


    void showLoadingGender();

    void hideLoadingGender();

    void genderLoaded(List<Pair<String, String>> genders, String gender);

    void loadGenderError(String message);


    void showLoadingType();

    void loadTypeError(String message);

    void hideLoadingType();

    void typeLoaded(List<Pair<String, String>> types, String type);

    void toSelectCategoryPage();


    void showLoadingSort();

    void hideLoadingSort();

    void loadSortError(String message);

    void sortLoaded(List<Pair<String, String>> sorts, String sortBy);


    void showLoadingSavingFilter();

    void hideLoadingSavingFilter();

}
