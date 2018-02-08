package gawe.imb.karya.model.manager;

import com.google.common.base.Strings;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import gawe.imb.karya.model.MyApplication;
import gawe.imb.karya.model.objects.BrowseFilter;
import gawe.imb.karya.model.utils.Helper;
import io.reactivex.Single;
import gawe.imb.karya.model.objects.others.Pair;

/**
 * Created by korneliussendy on 1/29/18.
 */

public class FilterManager {

    private static String KEY_FILTER = "key_filters";

    public static String SORT_NEARBY = "nearby";
    public static String SORT_RATING = "rating";

    public static String TYPE_ALL = "all";
    public static String TYPE_FULL_TIME = "full_time";
    public static String TYPE_PART_TIME = "part_time";

    public static String GENDER_ALL = "all";
    public static String GENDER_MALE = "M";
    public static String GENDER_FEMALE = "F";

    public static Single<BrowseFilter> getSavedFilter() {
        String s = Helper.getPreference(MyApplication.getContext()).getString(KEY_FILTER, "");
        if (Strings.isNullOrEmpty(s)) {
            return Single.just(new BrowseFilter());
        }
        try {
            Gson g = new Gson();
            return Single.just(g.fromJson(s, BrowseFilter.class));
        } catch (com.google.gson.JsonSyntaxException ex) {
            return Single.just(new BrowseFilter());
        }
    }

    public static BrowseFilter getPrefSavedFilter() {
        String s = Helper.getPreference(MyApplication.getContext()).getString(KEY_FILTER, "");
        if (Strings.isNullOrEmpty(s)) {
            return new BrowseFilter();
        }
        try {
            Gson g = new Gson();
            return g.fromJson(s, BrowseFilter.class);
        } catch (com.google.gson.JsonSyntaxException ex) {
            return new BrowseFilter();
        }
    }

    public static Single<Boolean> saveFilter(BrowseFilter filter) {
        Gson g = new Gson();
        try {
            String s = g.toJson(filter);
            Helper.saveStringPref(MyApplication.getContext(), KEY_FILTER, s);
            return Single.just(true);
        } catch (Exception e) {
            return Single.just(false);
        }
    }

    public static Single<List<Pair<String, String>>> getGenderList() {
        List<Pair<String, String>> genders = new ArrayList<>();
        genders.add(new Pair<>(GENDER_ALL, "All"));
        genders.add(new Pair<>(GENDER_FEMALE, "Female"));
        genders.add(new Pair<>(GENDER_MALE, "Male"));
        return Single.just(genders);
    }

    public static Single<List<Pair<String, String>>> getTypeList() {
        List<Pair<String, String>> types = new ArrayList<>();
        types.add(new Pair<>(TYPE_ALL, "All"));
        types.add(new Pair<>(TYPE_FULL_TIME, "Full Time"));
        types.add(new Pair<>(TYPE_PART_TIME, "Part Time"));
        return Single.just(types);
    }

    public static Single<List<Pair<String, String>>> getSortList() {
        List<Pair<String, String>> sorts = new ArrayList<>();
        sorts.add(new Pair<>(SORT_NEARBY, "Nearby"));
        sorts.add(new Pair<>(SORT_RATING, "Rating"));
        return Single.just(sorts);
    }

}
