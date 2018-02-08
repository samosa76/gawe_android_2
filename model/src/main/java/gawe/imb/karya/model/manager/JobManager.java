package gawe.imb.karya.model.manager;


import com.google.common.base.Strings;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import gawe.imb.karya.model.objects.Job;
import gawe.imb.karya.model.utils.Helper;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by korneliussendy on 2/6/18.
 */

public class JobManager {

    private static String BASE_DB = "db_karya/";
    private static String BASE_JOB_DB = BASE_DB + "db_job_missions/";
    private static String REF_JOB_ACTIVE_LIST = BASE_JOB_DB + "jobs_active";
    private static String REF_JOB_COMPLETED_LIST = BASE_JOB_DB + "jobs_completed";
    private static final String REF_JOB = BASE_JOB_DB + "jobs/";

    private static final String REF_UTILS = BASE_DB + "utils/";
    private static final String REF_LAST_MISSION_ID = REF_UTILS + "jobTransactionCounter";

    public static Observable<Boolean> hasActiveJobs() {
        if (UserManager.getUserPreference() == null || Strings.isNullOrEmpty(UserManager.getUserPreference().getId())) {
            return Observable.just(false);
        }
        Observable<Boolean> observable =
                Observable.create(emitter -> {
                    DatabaseReference pageRef = Helper.getDatabase().getReference(REF_JOB_ACTIVE_LIST).child(UserManager.getUserPreference().getId());
                    ValueEventListener valueEventListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            emitter.onNext(dataSnapshot.exists() && dataSnapshot.hasChildren());
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            emitter.onError(databaseError.toException());
                        }
                    };
                    emitter.setDisposable(new Disposable() {
                        @Override
                        public void dispose() {
                            if (pageRef != null)
                                pageRef.removeEventListener(valueEventListener);
                        }

                        @Override
                        public boolean isDisposed() {
                            return false;
                        }
                    });
                    pageRef.addValueEventListener(valueEventListener);
                });
        return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<List<String>> getActiveJobs() {
        if (UserManager.getUserPreference() == null || Strings.isNullOrEmpty(UserManager.getUserPreference().getId())) {
            return Observable.just(new ArrayList<String>());
        }

        Observable<List<String>> observable = Observable.create(emitter -> {
            DatabaseReference pageRef = Helper.getDatabase().getReference(REF_JOB_ACTIVE_LIST).child(UserManager.getUserPreference().getId());
            ValueEventListener valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    List<String> list = new ArrayList<>();
                    if (!dataSnapshot.exists() || !dataSnapshot.hasChildren()) {
                        emitter.onNext(list);
                        return;
                    }
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                                String s = ds.getValue(String.class);
                        String s = ds.getKey();
                        if (!Strings.isNullOrEmpty(s)) {
                            list.add(s);
                        }
                    }
                    emitter.onNext(list);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    emitter.onError(databaseError.toException());
                }
            };
            emitter.setDisposable(new Disposable() {
                @Override
                public void dispose() {
                    if (pageRef != null)
                        pageRef.removeEventListener(valueEventListener);
                }

                @Override
                public boolean isDisposed() {
                    return false;
                }
            });
            pageRef.addValueEventListener(valueEventListener);
        });
        return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public static Single<Job> CreateJob(Job job) {
        return Single.create(emitter -> Helper.getDatabase()
                .getReference(REF_LAST_MISSION_ID)
                .runTransaction(new Transaction.Handler() {
                    @Override
                    public Transaction.Result doTransaction(MutableData currentData) {
                        if (currentData.getValue() == null) {
                            currentData.setValue(1);
                        } else {
                            currentData.setValue((Long) currentData.getValue() + 1);
                        }
                        return Transaction.success(currentData);
                    }

                    @Override
                    public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {
                        if (databaseError != null) {
                            emitter.onError(databaseError.toException());
                            return;
                        }
                        if (!b) {
                            emitter.onError(new Throwable("not commited"));
                            return;
                        }
                        job.setJobId(dataSnapshot.getValue(Long.class));
                        Helper.getDatabase().getReference(REF_JOB).child(job.getId()).setValue(job);
                        emitter.onSuccess(job);
                    }
                }, false));

    }
}
