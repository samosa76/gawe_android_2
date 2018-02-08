package gawe.imb.karya.model.modules;

import android.net.Uri;
import android.util.Log;

import com.google.common.base.Strings;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

import gawe.imb.karya.model.BuildConfig;
import gawe.imb.karya.model.objects.Employee;
import gawe.imb.karya.model.objects.Employer;
import gawe.imb.karya.model.objects.GaweSettings;
import gawe.imb.karya.model.objects.User;
import gawe.imb.karya.model.utils.Helper;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import gawe.imb.karya.model.objects.others.UploadResponse;


/**
 * Created by korneliussendy on 1/22/18.
 */

public class FirebaseClient {

    private static final String BASE_ROY_DB = "published/";
    private static final String REF_GAWE_SETTINGS = BASE_ROY_DB + "Gawe_Setting";

    private static final String BASE_DB = "db_karya/";
    private static final String BASE_EMPLOYEE_DB = BASE_DB + "db_employee/";
    private static final String BASE_EMPLOYER_DB = BASE_DB + "db_employer/";
    private static final String BASE_UNIVERSAL_DB = BASE_DB + "user_all/";
    public static final String REF_USER_EMPLOYEE = BASE_EMPLOYEE_DB + "user";
    public static final String REF_USER_EMPLOYER = BASE_EMPLOYER_DB + "user";
    private static final String REF_USER_UNIVERSAL = BASE_UNIVERSAL_DB;

    public static Single<GaweSettings> getSettings() {
        Log.d("FirebaseClient", "loading settings");

        Single<GaweSettings> single = Single.create(subscriber -> {
            DatabaseReference ref = FirebaseDatabase.getInstance(BuildConfig.FIREBASE_DB).getReference().child(REF_GAWE_SETTINGS);
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    GaweSettings gws = dataSnapshot.getValue(GaweSettings.class);
                    if (gws == null) {
                        subscriber.onError(new NullPointerException("Empty Settings"));
                    } else {
                        subscriber.onSuccess(gws);
                    }
                    ref.removeEventListener(this);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    subscriber.onError(databaseError.toException());
                }
            });
        });
        return single
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Single<User> getUser(String userId) {
        Single<User> single = Single.create(emitter ->
                Helper.getDatabase().getReference(REF_USER_UNIVERSAL).child(userId)
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                User u = dataSnapshot.getValue(User.class);
                                if (u != null) {
                                    Log.d("getUser", u.toString());
                                    emitter.onSuccess(u);
                                } else {
                                    emitter.onError(new NullPointerException("null user"));
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                emitter.onError(databaseError.toException());
                            }
                        }));
        return single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public static Single<Employee> getEmployee(String userId) {
        Single<Employee> single = Single.create(emitter ->
                Helper.getDatabase().getReference(REF_USER_EMPLOYEE).child(userId)
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Employee u = dataSnapshot.getValue(Employee.class);
                                if (u != null) {
                                    Log.d("getEmployee", u.toString());
                                    emitter.onSuccess(u);
                                } else {
                                    emitter.onError(new NullPointerException("null user"));
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                emitter.onError(databaseError.toException());
                            }
                        }));
        return single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Single<Employer> getEmployer(String userId) {
        Single<Employer> single = Single.create(emitter ->
                Helper.getDatabase().getReference(REF_USER_EMPLOYER).child(userId)
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Employer u = dataSnapshot.getValue(Employer.class);
                                if (u != null) {
                                    Log.d("getEmployer", u.toString());
                                    emitter.onSuccess(u);
                                } else {
                                    emitter.onError(new NullPointerException("null user"));
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                emitter.onError(databaseError.toException());
                            }
                        }));
        return single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public static Observable<String> getReferralParentNameFromCode(String referralCode) {
        if (Strings.isNullOrEmpty(referralCode))
            return Observable.just("");
        Single<String> single = Single.create(emitter -> {
            //TODO TODO
        });
        return single.toObservable().concatMap(FirebaseClient::getUserName);
    }

    public static Observable<String> getUserName(String userId) {
        if (Strings.isNullOrEmpty(userId))
            return Observable.just("");
        Single<String> single = Single.create(emitter -> {
            //TODO TODO
        });
        return single.toObservable();
    }

    public static Observable<String> getReferralParentId(String referralCode) {
        if (Strings.isNullOrEmpty(referralCode)) {
            return Observable.just("");
        } else {
            Single<String> single = Single.create(emitter -> {
                //TODO firebase referral id
            });
            return single.toObservable();
        }

    }

    public static Observable<UploadResponse> uploadFile(String path, File file, int requestCode) {
        Observable<UploadResponse> observable = Observable.create(emitter -> {
            StorageReference ref = FirebaseStorage
                    .getInstance()
                    .getReference()
                    .child(path);

            Uri uri = Uri.fromFile(file);

            UploadTask uploadTask = ref.putFile(uri);
            uploadTask.addOnProgressListener(taskSnapshot -> {
                Log.d("Upload", "Upload : " + taskSnapshot.getBytesTransferred() + " / " + taskSnapshot.getTotalByteCount());
                emitter.onNext(new UploadResponse(requestCode).updateProgress(taskSnapshot.getBytesTransferred(), taskSnapshot.getTotalByteCount()));
            });
            uploadTask.addOnFailureListener(emitter::onError);
            uploadTask.addOnSuccessListener(taskSnapshot -> {
                Uri dUrl = taskSnapshot.getDownloadUrl();

                if (dUrl == null) {
                    emitter.onError(new Throwable("no Download Url"));
                    return;
                }
                UploadResponse response = new UploadResponse(requestCode);
                response.setDownloadUrl(dUrl.toString());
                response.setTotal(taskSnapshot.getTotalByteCount());
                response.setProgress(taskSnapshot.getBytesTransferred());
                response.setFinish(true);
                emitter.onNext(response);
                emitter.onComplete();
            });

        });
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    public static Completable loginToFirebase(String token) {
        return Completable.create(emitter -> {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signInWithCustomToken(token)
                    .addOnCompleteListener(task -> {
                                Log.d("loginToFirebase", " complete");
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in employer's information
                                    Log.d("loginToFirebase", "signInWithCustomToken:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    emitter.onComplete();
                                } else {
                                    // If sign in fails, display a message to the employer.
                                    Log.w("loginToFirebase", "signInWithCustomToken:failure", task.getException());
                                    emitter.onError(task.getException());
                                }

                            }
                    );
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
