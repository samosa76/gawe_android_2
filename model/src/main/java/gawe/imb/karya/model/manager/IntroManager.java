package gawe.imb.karya.model.manager;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import gawe.imb.karya.model.utils.Helper;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import gawe.imb.karya.model.objects.intro.Intro;

/**
 * Created by korneliussendy on 1/26/18.
 */

public class IntroManager {

    public static Single<List<Intro>> getERIntro() {
        Single<List<Intro>> single = Single.create(emitter ->
                Helper.getDatabase()
                        .getReference("intro/er")
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                List<Intro> intros = new ArrayList<>();
                                if (!dataSnapshot.exists()) {
                                    Log.d("getERIntro", "Data not exist");
                                    emitter.onSuccess(intros);
                                    return;
                                }
                                for (DataSnapshot d : dataSnapshot.getChildren()) {
                                    Log.d("getERIntro", d.toString());
                                    intros.add(d.getValue(Intro.class));
                                }
                                Log.d("getERIntro", "Data exist");
                                emitter.onSuccess(intros);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                emitter.onError(databaseError.toException());
                            }
                        })

        );
        return single.observeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread());
    }

    public static List<Intro> getDefaultERIntro() {
        return new ArrayList<>();
    }

    public static Single<List<Intro>> getEEIntro() {
        Single<List<Intro>> single = Single.create(emitter ->
                Helper.getDatabase()
                        .getReference("intro/ee")
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                List<Intro> intros = new ArrayList<>();
                                if (!dataSnapshot.exists()) {
                                    Log.d("getEEIntro", "Data not exist");
                                    emitter.onSuccess(intros);
                                    return;
                                }
                                for (DataSnapshot d : dataSnapshot.getChildren()) {
                                    Log.d("getEEIntro", d.toString());
                                    intros.add(d.getValue(Intro.class));
                                }
                                Log.d("getEEIntro", "Data exist");
                                emitter.onSuccess(intros);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                emitter.onError(databaseError.toException());
                            }
                        })

        );
        return single.observeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread());
    }

    public static List<Intro> getDefaultEEIntro() {
        return new ArrayList<>();
    }

}
