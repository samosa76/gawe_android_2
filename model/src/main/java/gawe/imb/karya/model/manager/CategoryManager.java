package gawe.imb.karya.model.manager;

import android.util.Log;

import com.google.common.base.Strings;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import gawe.imb.karya.model.objects.GaweCategory;
import gawe.imb.karya.model.objects.GaweCategoryNested;
import gawe.imb.karya.model.utils.Helper;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by korneliussendy on 1/27/18.
 */

public class CategoryManager {

    private static String REF_GET_JOB_CATEGORY = "published/GaweCategory/";
    private static String REF_GET_JOB_CATEGORY_NESTED = "published/GaweCategoryNested/";

    public static Single<List<GaweCategory>> getAllCategory() {
        Single<List<GaweCategory>> single = Single.create(emitter -> {
            DatabaseReference ref = Helper.getDatabase().getReference().child(REF_GET_JOB_CATEGORY);
            ValueEventListener eventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (ref != null)
                        ref.removeEventListener(this);
                    List<GaweCategory> list = new ArrayList<>();
                    if (!dataSnapshot.exists()) {
                        emitter.onSuccess(list);
                    } else {
                        for (DataSnapshot d : dataSnapshot.getChildren()) {
                            GaweCategory g = d.getValue(GaweCategory.class);
                            if (g == null)
                                continue;
                            list.add(g);
                        }
                        emitter.onSuccess(list);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    emitter.onError(databaseError.toException());
                }
            };
            ref.addListenerForSingleValueEvent(eventListener);
        });
        single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return single;
    }

    public static Single<GaweCategory> getCategory(String categoryId) {
        if (Strings.isNullOrEmpty(categoryId)) {
            return Single.error(new Throwable("null category"));
        }

        Single<GaweCategory> single = Single.create(emitter -> {
            DatabaseReference ref = Helper.getDatabase().getReference().child(REF_GET_JOB_CATEGORY).child(categoryId);
            ValueEventListener eventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    GaweCategory category;
                    if (!dataSnapshot.exists()) {
                        emitter.onError(new Throwable("Category Not Exist"));
                        return;
                    }
                    category = dataSnapshot.getValue(GaweCategory.class);
                    emitter.onSuccess(category);
                    if (ref != null)
                        ref.removeEventListener(this);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    emitter.onError(databaseError.toException());
                }
            };
            ref.addListenerForSingleValueEvent(eventListener);
        });
        single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return single;

    }

    public static Single<List<GaweCategoryNested>> getAllCategoryNested(String path) {
        Log.d("getAllCategoryNested", "PATH " + path);
        Single<List<GaweCategoryNested>> single = Single.create(emitter -> {
                    DatabaseReference db = Strings.isNullOrEmpty(path) ?
                            Helper.getDatabase().getReference().child(REF_GET_JOB_CATEGORY_NESTED).child(path) :
                            Helper.getDatabase().getReferenceFromUrl(path);
                    db.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            List<GaweCategoryNested> list = new ArrayList<>();
                            if (Strings.isNullOrEmpty(path)) {
                                if (!dataSnapshot.exists() && dataSnapshot.getChildrenCount() <= 0) {
                                    emitter.onSuccess(list);
                                    return;
                                }

                                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                    list.add(extractCategory(ds));
                                }
                                emitter.onSuccess(list);
                            } else {
                                if (!dataSnapshot.child("children").exists() && dataSnapshot.child("children").getChildrenCount() <= 0) {
                                    emitter.onSuccess(list);
                                    return;
                                }
                                list.add(extractCategory(dataSnapshot));
//                                for (DataSnapshot ds : dataSnapshot.child("children").getChildren()) {
//                                    list.add(extractCategory(ds));
//                                }
                                emitter.onSuccess(list);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            emitter.onError(databaseError.toException());
                        }
                    });
                }
        );

        single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return single;
    }

    private static GaweCategoryNested extractCategory(DataSnapshot d) {
        GaweCategoryNested category = new GaweCategoryNested();
//        category.setId(d.child("id").getValue(String.class));
//        category.setId(d.getKey());
        String myPath = d.getRef().toString();
        category.setId(myPath);
        Log.d("getCat", "extracting " + d.getKey());
        category.setPengali(d.child("pengali").getValue(Double.class));
        category.setRangeKm(d.child("rangeKm").getValue(Integer.class));
        category.setIcon(d.child("icon").getValue(String.class));
        category.setName(d.child("name").getValue(String.class));
        category.setShow(d.child("show").getValue(String.class));
        category.setDescription(d.child("description").getValue(String.class));
        category.setCatType(d.child("type").getValue(String.class));
        category.setTags(d.child("tags").getValue(String.class));
        List<GaweCategoryNested> list = new ArrayList<>();
        if (d.child("children").exists()) {
            for (DataSnapshot ds : d.child("children").getChildren()) {
                list.add(extractCategory(ds));
            }
        }
        category.setChildren(list);
        return category;
    }


}
