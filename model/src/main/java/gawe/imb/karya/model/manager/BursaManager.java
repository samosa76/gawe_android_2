package gawe.imb.karya.model.manager;

import com.google.common.base.Strings;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.SetOptions;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gawe.imb.karya.model.objects.BursaJob;
import gawe.imb.karya.model.utils.Helper;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by korneliussendy on 1/28/18.
 *
 */

public class BursaManager {

    private static String REF_BURSA_JOB = "bursa_jobs";

    public static Single<List<DocumentSnapshot>> getMyAds(String id, long perPage, DocumentSnapshot lastDocument) {

        Single<List<DocumentSnapshot>> single = Single.create(emitter -> {
            if (Strings.isNullOrEmpty(id)) {
                emitter.onError(new NullPointerException("No Id found"));
                return;
            }
            CollectionReference jobRef = Helper.getFirestore().collection(REF_BURSA_JOB);
            Query first = jobRef
                    .whereEqualTo("employerId", id)
                    .whereEqualTo("stCreated", true)
                    .whereEqualTo("stCancelled", false)
                    .whereGreaterThan("millisCreated", 0)
                    .orderBy("millisCreated", Query.Direction.DESCENDING);

            if (lastDocument != null) {
                first.startAfter(lastDocument);
            }

            first = first.limit(perPage);
            first.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    List<DocumentSnapshot> list = new ArrayList<>();
                    if (task.getResult().isEmpty()) {
                        emitter.onSuccess(list);
                    } else {
                        for (DocumentSnapshot document : task.getResult()) {
                            if (document.exists()) {
                                list.add(document);
                            }
                        }
                        emitter.onSuccess(list);
                    }
                } else {
                    emitter.onError(task.getException());
                }
            });
        });

        single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return single;
    }

    public static Completable saveAds(String jobId, BursaJob bursaJob) {
        return Completable.create(emitter -> {
            DocumentReference jobRef = Helper.getFirestore().collection(REF_BURSA_JOB).document(jobId);
            jobRef.set(bursaJob, SetOptions.merge()).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    emitter.onComplete();
                } else {
                    emitter.onError(task.getException());
                }
            });

        });
    }

    public static Completable updateAds(String jobId, Map<String, Object> dataMap) {
        return Completable.create(emitter -> {
            DocumentReference jobRef = Helper.getFirestore().collection(REF_BURSA_JOB).document(jobId);
            jobRef.set(dataMap, SetOptions.merge()).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    emitter.onComplete();
                } else {
                    emitter.onError(task.getException());
                }
            });

        });
    }

    public static Completable cancelAds(String jobId) {
        if (Strings.isNullOrEmpty(jobId)) {
            return Completable.error(new NullPointerException("null job id"));
        }
        Map<String, Object> map = new HashMap<>();
        map.put("stCancelled", true);
        map.put("millisCancelled", DateTime.now().getMillis());
        map.put("dateCancelled", Helper.formatDateTime(DateTime.now()));
        map.put("millisUpdated", DateTime.now().getMillis());
        map.put("dateUpdated", Helper.formatDateTime(DateTime.now()));

        return updateAds(jobId, map);
    }

}
