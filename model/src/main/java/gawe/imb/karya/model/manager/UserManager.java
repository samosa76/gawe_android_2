package gawe.imb.karya.model.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import com.facebook.accountkit.AccountKit;
import com.google.common.base.Strings;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import org.joda.time.DateTime;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gawe.imb.karya.mainlibs.utils.MapsGeo;
import gawe.imb.karya.model.BuildConfig;
import gawe.imb.karya.model.MyApplication;
import gawe.imb.karya.model.objects.User;
import gawe.imb.karya.model.objects.UserBulk;
import gawe.imb.karya.model.utils.Helper;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import gawe.imb.karya.model.objects.others.UploadResponse;
import gawe.imb.karya.model.modules.FirebaseClient;
import gawe.imb.karya.model.modules.FirebaseCloudFunctions;
import gawe.imb.karya.model.modules.GaweWebserviceAPI;
import gawe.imb.karya.model.modules.GaweWebserviceClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by korneliussendy on 1/24/18.
 */

public class UserManager {

    public static final String ROLE_EMPLOYEE = "EMPLOYEE";
    public static final String ROLE_EMPLOYER = "EMPLOYER";
    private static final String USER_ROLE = "SUER_ROLE";
    private static final String SHOW_ER_INTRO = "should show er intro";
    private static final String SHOW_EE_INTRO = "should show intro for employee";

    private static Context getContext() {
        return MyApplication.getContext();
    }

    public static Single<String> getCustomToken(String facebookToken) {
        if (Strings.isNullOrEmpty(facebookToken)) {
            return Single.just("");
        }
        Single<String> single = Single.create(emitter -> {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.CLOUD_FUNCTIONS_ENDPOINT)
                    .build();
            FirebaseCloudFunctions mCloudFunction = retrofit.create(FirebaseCloudFunctions.class);
            mCloudFunction.getCustomToken(facebookToken).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        if (response.isSuccessful()) {
                            final String customToken = response.body().string();
                            emitter.onSuccess(customToken);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable e) {
                    emitter.onError(e);
                }
            });
        });
        return single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public static User getUserPreference() {
        return Helper.getUser(getContext());
    }

    public static User getUserPreference(Context context) {
        return Helper.getUser(context);
    }

    public static String getUserRole() {
        String role = Helper.getStringPref(getContext(), USER_ROLE);
        return Strings.isNullOrEmpty(role) ? "" : role;
    }

    public static void clearUserRole() {
        Helper.getPreference(getContext()).edit().remove(USER_ROLE).apply();
    }

    public static void saveUserRole(@NonNull String userId, @NonNull String role) {
        Log.d("saveUserRole", "UserId " + userId + " role " + role);
        Helper.saveStringPref(getContext(), USER_ROLE, role);
        GaweWebserviceAPI api = GaweWebserviceClient.getClient().create(GaweWebserviceAPI.class);
        api.updateRole(userId, role)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribe(
                        responseBody -> Log.d("saveUser", responseBody.string()),
                        throwable -> Log.d("saveUser", throwable.getLocalizedMessage(), throwable)
                );
    }

    public static boolean isStillBanned(User user, boolean releaseIfPass) {
        if (user.isBanned() && user.getBannedUntilMillis() != 0) {
            DateTime limitBanned = new DateTime(user.getBannedUntilMillis());
            if (DateTime.now().isAfter(limitBanned)) {
                if (releaseIfPass) {
                    getUserRef(user.getId()).child("banned").setValue(false);
                    getUserRef(user.getId()).child("bannedUntilMillis").setValue(0);
                    getEmployeeRef(user.getId()).child("banned").setValue(false);
                    getEmployeeRef(user.getId()).child("bannedUntilMillis").setValue(0);
                    getEmployerRef(user.getId()).child("banned").setValue(false);
                    getEmployerRef(user.getId()).child("bannedUntilMillis").setValue(0);
                }
                return false;
            }
            //berarti belum lewat masa tahanan
            return true;
        } else if (user.isBanned() && user.getBannedUntilMillis() == 0) {
            //ditahan seumur hidup
            return true;
        } else {
            //engga di banned ignore banned untill millis
            return false;
        }
    }

    private static DatabaseReference getUserRef(String uid) {
        return Helper.getDatabase().getReference(REF_USER_UNIVERSAL).child(uid);
    }

    private static DatabaseReference getEmployerRef(String uid) {
        return Helper.getDatabase().getReference(REF_USER_EMPLOYER).child(uid);
    }

    private static DatabaseReference getEmployeeRef(String uid) {
        return Helper.getDatabase().getReference(REF_USER_EMPLOYEE).child(uid);
    }


    public static boolean shouldShowERIntro() {
        return Helper.getBooleanPref(getContext(), SHOW_ER_INTRO, true);
    }

    public static boolean shouldShowEEIntro() {
        return Helper.getBooleanPref(MyApplication.getContext(), SHOW_EE_INTRO, true);
    }

    public static void setERIntroDone() {
        Helper.saveBooleanPref(MyApplication.getContext(), SHOW_ER_INTRO, true);
    }

    public static void setEEIntroDone() {
        Helper.saveBooleanPref(MyApplication.getContext(), SHOW_EE_INTRO, true);
    }

    public static void setERIntroUndone() {
        Helper.saveBooleanPref(MyApplication.getContext(), SHOW_ER_INTRO, false);
    }

    public static void setEEIntroUndone() {
        Helper.saveBooleanPref(MyApplication.getContext(), SHOW_EE_INTRO, false);
    }

    public static void saveUserLocationAndDeviceToken(@NonNull String userId) {
        if (Strings.isNullOrEmpty(userId)) {
            return;
        }

        GaweWebserviceAPI api = GaweWebserviceClient.getClient().create(GaweWebserviceAPI.class);
        MapsGeo mg = new MapsGeo(getContext());
        api.updateLocation(userId, mg.getLatitude(), mg.getLongitude(), FirebaseInstanceId.getInstance().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribe(
                        responseBody -> Log.d("saveLocation", "success" + responseBody.string()),
                        throwable -> Log.d("saveLocation", "failed", throwable)
                );
        Log.d("saveLocation", "UserId " + userId + " role " + mg.getLatitude() + "-" + mg.getLongitude());
        mg.unBind();
    }

    public static boolean isFirebaseLoggedIn() {
        return FirebaseAuth.getInstance().getCurrentUser() != null;
    }

    public static boolean isFacebookLoggedId() {
        return AccountKit.getCurrentAccessToken() != null;
    }

    public static boolean isUserLoggedIn() {
        return getUserPreference() != null && isFacebookLoggedId() && isFirebaseLoggedIn();
    }

    public static void logout() {
        getLoginPref(getContext()).edit().remove(KEY_USER_LOGIN).apply();
        clearUserRole();

        Helper.clearUser(MyApplication.getContext());
        Helper.clearEmployee(MyApplication.getContext());
        Helper.clearEmployer(MyApplication.getContext());
        FirebaseAuth.getInstance().signOut();
        AccountKit.logOut();
    }

    private static String PREF_LOGIN = "karya_login_1221";
    private static String KEY_USER_LOGIN = "user_semi";

    ///SEMI LOGIN START

    private static SharedPreferences getLoginPref(Context context) {
        return Helper.getPreference(getContext(), PREF_LOGIN);
    }

    public static void semiLogout() {
        clearUserRole();
        getLoginPref(getContext()).edit().putBoolean(KEY_USER_LOGIN, true).apply();
    }

    public static void semiLogin() {
        getLoginPref(getContext()).edit().putBoolean(KEY_USER_LOGIN, false).apply();
    }

    public static boolean isSemiLogin() {
        return getLoginPref(getContext()).getBoolean(KEY_USER_LOGIN, false);
    }

    ///SEMI LOGIN END

    public static Single<UserBulk> loadUserBulk(String userId, boolean save) {
        if (Strings.isNullOrEmpty(userId)) {
            return Single.error(new Throwable("Null or empty user"));
        }

        return Single.zip(
                FirebaseClient.getUser(userId).doAfterSuccess(user -> {
                    if (save) Helper.saveUser(getContext(), user);
                }),
                FirebaseClient.getEmployee(userId).doAfterSuccess(employee -> {
                    if (save)
                        Helper.saveEmployee(getContext(), employee, false);
                }),
                FirebaseClient.getEmployer(userId).doAfterSuccess(employer -> {
                    if (save)
                        Helper.saveEmployer(getContext(), employer, false);
                }),
                UserBulk::new
        );
    }

    public static Single<UserBulk> loadUserBulk(String userId) {
        return loadUserBulk(userId, false);
    }

    private static final String REF_IMAGE_USER = "karya_user_image";
    private static final String REF_IMAGE_KTP_USER = "karya_user_ktp";
    private static final String REF_IMAGE_KTM_USER = "karya_user_ktm";

    private static final String REF_IMAGE_EMPLOYER = REF_IMAGE_USER;
    private static final String REF_IMAGE_KTP_EMPLOYER = REF_IMAGE_KTP_USER;
    private static final String REF_IMAGE_KTM_EMPLOYER = REF_IMAGE_KTM_USER;

    private static final String REF_IMAGE_EMPLOYEE = REF_IMAGE_USER;
    private static final String REF_IMAGE_KTP_EMPLOYEE = REF_IMAGE_KTP_USER;
    private static final String REF_IMAGE_KTM_EMPLOYEE = REF_IMAGE_KTM_USER;

    public static Observable<UploadResponse> uploadProfileEmployer(String userId, File file, int requestCode) {
        if (Strings.isNullOrEmpty(userId)) {
            return Observable.error(new Throwable("Empty User"));
        }
        String path = REF_IMAGE_EMPLOYER + "/" + userId;
        return uploadImage(path, file, requestCode);
    }

    public static Observable<UploadResponse> uploadKTPEmployer(String userId, File file, int requestCode) {
        if (Strings.isNullOrEmpty(userId)) {
            return Observable.error(new Throwable("Empty User"));
        }
        String path = REF_IMAGE_KTP_EMPLOYER + "/" + userId;
        return uploadImage(path, file, requestCode);
    }

    public static Observable<UploadResponse> uploadKTMEmployer(String userId, File file, int requestCode) {
        if (Strings.isNullOrEmpty(userId)) {
            return Observable.error(new Throwable("Empty User"));
        }
        String path = REF_IMAGE_KTM_EMPLOYER + "/" + userId;
        return uploadImage(path, file, requestCode);
    }

    private static Observable<UploadResponse> uploadImage(String path, File file, int requestCode) {
        return FirebaseClient.uploadFile(path, file, requestCode);
    }

    private static final String BASE_DB = "db_karya/";
    private static final String BASE_EMPLOYEE_DB = BASE_DB + "db_employee/";
    private static final String BASE_EMPLOYER_DB = BASE_DB + "db_employer/";
    private static final String BASE_UNIVERSAL_DB = BASE_DB + "user_all/";

    private static final String REF_USER_EMPLOYEE = BASE_EMPLOYEE_DB + "user";
    private static final String REF_USER_EMPLOYER = BASE_EMPLOYER_DB + "user";
    private static final String REF_USER_UNIVERSAL = BASE_UNIVERSAL_DB;

    public static final String FULL_NAME = "fullName";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    public static final String GENDER = "gender";
    public static final String PROFILE_PIC = "profile_pic";
    public static final String KTP = "ktp";
    public static final String KARTU_MAHASISWA = "kartu_mahasiswa";
    public static final String DATE_CREATED = "dateCreated";
    public static final String LAST_UPDATE = "lastUpdate";

    public static Completable saveUser(Map<String, Object> mapUser, String userId) {
        if (mapUser == null || mapUser.size() <= 0) {
            Log.d("saveUser", "null or empty data");
            return Completable.complete();
        }

        mapUser.put(DATE_CREATED, DateTime.now().getMillis());
        mapUser.put(LAST_UPDATE, DateTime.now().getMillis());

        if (Strings.isNullOrEmpty(userId)) {
            Log.d("saveUser", "null or empty data");
            return Completable.error(new Throwable("null user id"));
        }

        List<Completable> list = new ArrayList<>();
        Completable completableUpdateUser = Completable.create(emitter ->
                Helper.getDatabase().getReference(REF_USER_UNIVERSAL).child(userId).updateChildren(mapUser,
                        (databaseError, databaseReference) -> {
                            if (databaseError != null)
                                emitter.onError(databaseError.toException());
                            else
                                emitter.onComplete();
                        }))
                .subscribeOn(Schedulers.io()
                );
        list.add(completableUpdateUser);

        Completable completableUpdateEmployer = Completable.create(emitter ->
                Helper.getDatabase().getReference(REF_USER_EMPLOYER).child(userId).updateChildren(mapUser,
                        (databaseError, databaseReference) -> {
                            if (databaseError != null)
                                emitter.onError(databaseError.toException());
                            else
                                emitter.onComplete();
                        }))
                .subscribeOn(Schedulers.io());
        list.add(completableUpdateEmployer);

        Completable completableUpdateEmployee = Completable.create(emitter ->
                Helper.getDatabase().getReference(REF_USER_EMPLOYEE).child(userId).updateChildren(mapUser,
                        (databaseError, databaseReference) -> {
                            if (databaseError != null)
                                emitter.onError(databaseError.toException());
                            else
                                emitter.onComplete();
                        }))
                .subscribeOn(Schedulers.io());
        list.add(completableUpdateEmployee);

        return Completable.concat(list);

    }

    public static void saveUserOnly(Map<String, Object> mapUser, String userId) {
        if (mapUser == null || mapUser.size() <= 0) {
            return;
        }
        if (Strings.isNullOrEmpty(userId)) {
            Log.d("saveUser", "null or empty data");
            return;
        }

        saveUser(mapUser, userId).subscribe(() -> Log.d("saveUserOnly", "success"),
                throwable -> Log.d("saveUserOnly", "Failed", throwable));
    }

    public static void saveUserOnly(String key, Object value, String userId) {
        if (Strings.isNullOrEmpty(key))
            return;
        Map<String, Object> mapUser = new HashMap<>();
        mapUser.put(key, value);
        saveUserOnly(mapUser, userId);
    }

    private static final String BASE_REFERRAL_CODE = BASE_DB + "referral_code/";
    public static final String BASE_EMPLOYEE_REF_CODE = BASE_REFERRAL_CODE + "employee/";
    public static final String BASE_EMPLOYER_REF_CODE = BASE_REFERRAL_CODE + "employer/";


    public static Single<User> getUserByReferral(String referralCode) {
        Single<String> single = Single.create(emitter -> {
            DatabaseReference ref = Helper.getDatabase().getReference(BASE_EMPLOYER_REF_CODE).child(referralCode);
            ValueEventListener eventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String userID = dataSnapshot.getValue(String.class);
                    emitter.onSuccess(userID == null ? "" : userID);
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
        return single.flatMap(s -> {
            if (Strings.isNullOrEmpty(s)) {
                return Single.just(new User());
            } else {
                return FirebaseClient.getUser(s);
            }
        });
    }

}
