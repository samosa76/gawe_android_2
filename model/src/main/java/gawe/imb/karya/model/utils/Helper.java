package gawe.imb.karya.model.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.common.base.Strings;
import com.google.common.hash.Hashing;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.TimeZone;

import gawe.imb.karya.mainlibs.utils.Constants;
import gawe.imb.karya.model.BuildConfig;
import gawe.imb.karya.model.objects.Employee;
import gawe.imb.karya.model.objects.Employer;
import gawe.imb.karya.model.objects.User;

/**
 * Created by korneliussendy on 1/24/18.
 */

public class Helper {

    //PREFERENCES

    public static SharedPreferences getPreference(Context context) {
        return context.getSharedPreferences(Constants.PREF, Context.MODE_PRIVATE);
    }

    public static SharedPreferences getPreference(Context context, String key) {
        return context.getSharedPreferences(key, Context.MODE_PRIVATE);
    }

    public static void saveBooleanPref(Context context, String key, boolean value) {
        getPreference(context).edit().putBoolean(key, value).commit();
    }

    public static void saveStringPref(Context context, String key, String value) {
        getPreference(context).edit().putString(key, value).commit();
    }

    public static boolean getBooleanPref(Context context, String key) {
        return getPreference(context).getBoolean(key, false);
    }

    public static boolean getBooleanPref(Context context, String key, boolean defaultValue) {
        return getPreference(context).getBoolean(key, defaultValue);
    }

    public static String getStringPref(Context context, String key) {
        return getPreference(context).getString(key, "");
    }

    public static void clearStringPref(Context context, String key) {
        saveStringPref(context, key, "");
    }

//    EMPLOYEE

    public static DatabaseReference getEmployeeRef() {
        return getDatabase().getReference(Constants.REF_USER_EMPLOYEE);
    }

    public static DatabaseReference getEmployeeRef(String uid) {
        return getDatabase().getReference(Constants.REF_USER_EMPLOYEE).child(uid);
    }

    public static void clearEmployee(Context context) {
        getPreference(context)
                .edit()
                .remove(Constants.REF_USER_EMPLOYEE)
                .commit();
    }

    public static void saveEmployee(Context context, Employee employee, boolean immediatelyUploadData) {
        //TRYFIX ACCORDING TO CRASHLYTICS
        if (context == null)
            return;

        Gson g = new Gson();
        getPreference(context)
                .edit()
                .putString(Constants.REF_USER_EMPLOYEE, g.toJson(employee))
                .commit();

        employee.setDeviceToken(FirebaseInstanceId.getInstance().getToken());

        if (immediatelyUploadData)
            getEmployeeRef(employee.getId()).setValue(employee);
    }

    public static Employee getEmployee(Context context) {
        Gson g = new Gson();
        Employee e = g.fromJson(getPreference(context).getString(Constants.REF_USER_EMPLOYEE, ""), Employee.class);
        return e;
    }

//    FIREBASE

    public static FirebaseDatabase getDatabase() {
        return FirebaseDatabase.getInstance(BuildConfig.FIREBASE_DB);
    }

    public static FirebaseFirestore getFirestore() {
        return FirebaseFirestore.getInstance();
    }

//    EMPLOYER

    public static DatabaseReference getEmployerRef() {
        return getDatabase().getReference(Constants.REF_USER_EMPLOYER);
    }

    public static DatabaseReference getEmployerRef(String uid) {
        return getDatabase().getReference(Constants.REF_USER_EMPLOYER).child(uid);
    }

    public static void clearEmployer(Context context) {
        getPreference(context)
                .edit()
                .remove(Constants.REF_USER_EMPLOYER)
                .commit();
    }

    public static void saveEmployer(Context context, Employer employer) {
        if (context == null)
            return;
        Gson g = new Gson();

        getPreference(context)
                .edit()
                .putString(Constants.REF_USER_EMPLOYER, g.toJson(employer))
                .commit();

        employer.setDeviceToken(FirebaseInstanceId.getInstance().getToken());
        getEmployerRef(employer.getId()).setValue(employer);
    }

    public static void saveEmployer(Context context, Employer employer, boolean immediatelyUploadData) {
        Gson g = new Gson();

        getPreference(context)
                .edit()
                .putString(Constants.REF_USER_EMPLOYER, g.toJson(employer))
                .commit();


        employer.setDeviceToken(FirebaseInstanceId.getInstance().getToken());

        if (immediatelyUploadData)
            getEmployerRef(employer.getId()).setValue(employer);
    }

    public static Employer getEmployer(Context context) {
        Gson g = new Gson();
        Employer e = g.fromJson(getPreference(context).getString(Constants.REF_USER_EMPLOYER, ""), Employer.class);
        if (e == null) {
            Log.d("Helper", "employer null");
        }
        return e;
    }

    //    USER
    public static DatabaseReference getUserRef() {
        return getDatabase().getReference(Constants.REF_USER_UNIVERSAL);
    }

    public static DatabaseReference getUserRef(String uid) {
        return getDatabase().getReference(Constants.REF_USER_UNIVERSAL).child(uid);
    }

    public static User getUser(Context context) {
        Gson g = new Gson();
        User e = g.fromJson(getPreference(context).getString(Constants.REF_USER_UNIVERSAL, ""), User.class);
        if (e == null) {
            Log.d("Helper", "employer null");
        }
        return e;
    }

    public static void clearUser(Context context) {
        getPreference(context)
                .edit()
                .remove(Constants.REF_USER_UNIVERSAL)
                .commit();
        clearEmployee(context);
        clearEmployer(context);
    }

    public static void saveUser(Context context, User user) {
        Gson g = new Gson();

        getPreference(context)
                .edit()
                .putString(Constants.REF_USER_UNIVERSAL, g.toJson(user))
                .commit();

        user.setDeviceToken(FirebaseInstanceId.getInstance().getToken());
        getEmployerRef(user.getId()).setValue(user);
    }

    public static void saveUser(Context context, User user, boolean immediatelyUploadData) {
        Gson g = new Gson();

        getPreference(context)
                .edit()
                .putString(Constants.REF_USER_UNIVERSAL, g.toJson(user))
                .commit();

        user.setDeviceToken(FirebaseInstanceId.getInstance().getToken());

        if (immediatelyUploadData)
            getUserRef(user.getId()).setValue(user);
    }

    //BANNED


    public static boolean isStillBanned(Employee user, boolean releaseIfPass) {
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

    public static boolean isStillBanned(Employer user, boolean releaseIfPass) {
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

    //HISTORY

    public static DatabaseReference getEmployeeHistoryRegister(DateTime dateTime, String employeeId) {
        return getDatabase()
                .getReference(Constants.REF_HISTORY_EMPLOYEE_REGISTER)
                .child(String.valueOf(dateTime.getYear()))
                .child(String.valueOf(dateTime.getMonthOfYear()))
                .child(String.valueOf(dateTime.getDayOfMonth()))
                .child(employeeId);
    }

    public static DatabaseReference getEmployeeHistoryLogin(DateTime dateTime, String employeeId) {
        return getDatabase()
                .getReference(Constants.REF_HISTORY_EMPLOYEE_LOGIN)
                .child(String.valueOf(dateTime.getYear()))
                .child(String.valueOf(dateTime.getMonthOfYear()))
                .child(String.valueOf(dateTime.getDayOfMonth()))
                .child(employeeId);
    }

    public static DatabaseReference getEmployeeHistoryActive(DateTime dateTime, String employeeId) {
        return getDatabase()
                .getReference(Constants.REF_HISTORY_EMPLOYEE_ACTIVE)
                .child(String.valueOf(dateTime.getYear()))
                .child(String.valueOf(dateTime.getMonthOfYear()))
                .child(String.valueOf(dateTime.getDayOfMonth()))
                .child(employeeId);
    }

    public static DatabaseReference getEmployeeHistoryCategory(DateTime dateTime, String employeeId, String categoryId) {
        return getDatabase()
                .getReference(Constants.REF_HISTORY_EMPLOYEE_CATEGORY)
                .child(String.valueOf(dateTime.getYear()))
                .child(String.valueOf(dateTime.getMonthOfYear()))
                .child(String.valueOf(dateTime.getDayOfMonth()))
                .child(categoryId)
                .child(employeeId);
    }

    public static DatabaseReference getEmployerHistoryLogin(DateTime dateTime, String employerId) {
        return getDatabase()
                .getReference(Constants.REF_HISTORY_EMPLOYER_LOGIN)
                .child(String.valueOf(dateTime.getYear()))
                .child(String.valueOf(dateTime.getMonthOfYear()))
                .child(String.valueOf(dateTime.getDayOfMonth()))
                .child(employerId);
    }

    public static DatabaseReference getEmployerHistoryRegister(DateTime dateTime, String employerId) {
        return getDatabase()
                .getReference(Constants.REF_HISTORY_EMPLOYER_REGISTER)
                .child(String.valueOf(dateTime.getYear()))
                .child(String.valueOf(dateTime.getMonthOfYear()))
                .child(String.valueOf(dateTime.getDayOfMonth()))
                .child(employerId);
    }

    public static DatabaseReference getUserHistoryLogin(DateTime dateTime, String userId) {
        return getDatabase()
                .getReference(Constants.REF_HISTORY_USER_LOGIN)
                .child(String.valueOf(dateTime.getYear()))
                .child(String.valueOf(dateTime.getMonthOfYear()))
                .child(String.valueOf(dateTime.getDayOfMonth()))
                .child(userId);
    }

    public static DatabaseReference getUserHistoryRegister(DateTime dateTime, String userId) {
        return getDatabase()
                .getReference(Constants.REF_HISTORY_USER_REGISTER)
                .child(String.valueOf(dateTime.getYear()))
                .child(String.valueOf(dateTime.getMonthOfYear()))
                .child(String.valueOf(dateTime.getDayOfMonth()))
                .child(userId);
    }

    //    OTHERS

    public static String toGawe36(Long number) {
        long n = Constants.BASE_ID + (number);
        return Long.toString(n, 36).toUpperCase();
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static String getStringResourceByName(String aString, Context context) {
        String packageName = context.getPackageName();
        int resId = context.getResources().getIdentifier(aString, "string", packageName);
        if (resId == 0) {
            return aString;
        } else {
            return context.getString(resId);
        }
    }

    public static String encryptMd5(String text) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest
                    .getInstance(MD5);
            digest.update(text.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String sha256(String text) {
        return Hashing.sha256()
                .hashString(text, StandardCharsets.UTF_8)
                .toString();
    }

    public static String get(EditText editText) {
        if (editText == null)
            return "";
        return editText.getText().toString();
    }

    public static void intentCall(Context context, String number) {
        if (Strings.isNullOrEmpty(number)) {
            return;
        }

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + number.replace(" ", "")));
        context.startActivity(intent);
    }

    public static void intentSMS(Context context, String number) {
        if (Strings.isNullOrEmpty(number)) {
            return;
        }
        Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("smsto:" + number));
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address", number);
        smsIntent.setData(Uri.parse("sms:" + number));
        try {
            context.startActivity(smsIntent);
        } catch (ActivityNotFoundException e) {
            // Display some sort of error message here.
            Log.e("SMS", "intentSMS: ", e);
        }
    }

    public static String formatRupiah(double d) {
        // Use this weird formatter, because IDR & $ are having different ,. behavior
        DecimalFormat formatter = new DecimalFormat("'Rp. '#,###");
        return formatter.format(d).replace(',', '.').replace('_', ',');
    }

    public static String formatDateTime(DateTime date, String format) {
        DateTime localDate = date.toDateTime(DateTimeZone.forTimeZone(TimeZone.getDefault()));
        return DateTimeFormat.forPattern(format).withLocale(Locale.US).print(localDate);
    }

    public static String formatDateTime(DateTime date) {
        String DATE_JSON_FULL = "yyyy-MM-dd HH:mm:ss";
        DateTime localDate = date.toDateTime(DateTimeZone.forTimeZone(TimeZone.getDefault()));
        return DateTimeFormat.forPattern(DATE_JSON_FULL).withLocale(Locale.US).print(localDate);
    }

    public static LinearLayoutManager setupRecyclerView(RecyclerView rv, RecyclerView.Adapter adapter) {
        LinearLayoutManager manager = new LinearLayoutManager(rv.getContext());
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);

        return manager;
    }

    public static LinearLayoutManager setupHorizontalRecyclerView(RecyclerView rv, RecyclerView.Adapter adapter) {
        LinearLayoutManager manager = new LinearLayoutManager(rv.getContext(), LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);

        return manager;
    }

    public static GridLayoutManager setupGridLayoutRecyclerView(RecyclerView rv, RecyclerView.Adapter adapter, int column) {
        GridLayoutManager manager = new GridLayoutManager(rv.getContext(), column);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);

        return manager;
    }

    public static boolean isGooglePlayServicesAvailable(Activity activity) {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int status = googleApiAvailability.isGooglePlayServicesAvailable(activity);
        if (status != ConnectionResult.SUCCESS) {
            if (googleApiAvailability.isUserResolvableError(status)) {
                googleApiAvailability.getErrorDialog(activity, status, 2404).show();
            }
            return false;
        }
        return true;
    }
}
