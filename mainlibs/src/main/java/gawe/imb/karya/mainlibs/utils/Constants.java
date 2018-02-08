package gawe.imb.karya.mainlibs.utils;

/**
 * Created by korneliussendy on 1/24/18.
 */

public class Constants {

    public static final String PREF = "karya_employee_1092";
    public static final String PREF_MY_ACTIVE_ROLE = "My_active_role";

    public static final String BASE_DB = "db_karya/";
    public static final String BASE_EMPLOYEE_DB = BASE_DB + "db_employee/";
    public static final String BASE_EMPLOYER_DB = BASE_DB + "db_employer/";
    public static final String BASE_UNIVERSAL_DB = BASE_DB + "user_all/";

    public static final String UPLINE_ID = "upline_id";
    public static final String UPLINE_NAME = "upline_id";

    public static final String USER_LAT = "lat";
    public static final String USER_LNG = "lng";
    public static final String TAG_PROFILE = "Tag profile";
    public static final String TAG_SIGN_UP = "Tag sign uo";

    public static final String PHONE_NUMBER = "phone_number";
    public static final String ACCOUNT_ID = "account_id";

    public static final Long BASE_ID = 1000000000L;
    public static final String CONNECTOR = "__AMAN__";

    public static final String GENDER_MALE = "M";
    public static final String GENDER_FEMALE = "F";

    public static final int GENDER_CODE_ANY_GENDER = 0;
    public static final int GENDER_CODE_FEMALE_ONLY = 1;
    public static final int GENDER_CODE_MALE_ONLY = 2;
    public static final int GENDER_CODE_MALE_AND_FEMALE = 3;

    public static final String REF_USER_EMPLOYEE = BASE_EMPLOYEE_DB + "user";
    public static final String REF_USER_EMPLOYER = BASE_EMPLOYER_DB + "user";
    public static final String REF_USER_UNIVERSAL = BASE_UNIVERSAL_DB;

    public static final String REF_HISTORY_EMPLOYEE_LOGIN = BASE_DB + "history/employee/login/";
    public static final String REF_HISTORY_EMPLOYEE_REGISTER = BASE_DB + "history/employee/register/";
    public static final String REF_HISTORY_EMPLOYEE_ACTIVE = BASE_DB + "history/employee/active/";
    public static final String REF_HISTORY_EMPLOYEE_CATEGORY = BASE_DB + "history/category/";

    public static final String REF_HISTORY_EMPLOYER_LOGIN = BASE_DB + "history/employer/login/";
    public static final String REF_HISTORY_EMPLOYER_REGISTER = BASE_DB + "history/employer/register/";

    public static final String REF_HISTORY_USER_LOGIN = BASE_DB + "history/user/login/";
    public static final String REF_HISTORY_USER_REGISTER = BASE_DB + "history/user/register/";
    public static final String REF_HISTORY_USER_LOCATION = BASE_DB + "history/bursa/user_to_location/";
    public static final String REF_HISTORY_USER_CURRENT_LOCATION = BASE_DB + "history/bursa/user_to_current_location/";
    public static final String REF_HISTORY_LOCATION_USER = BASE_DB + "history/bursa/location_to_user/";
    public static final String REF_HISTORY_BURSA_JOB_CREATED = BASE_DB + "history/bursa/job_created/";
    public static final String REF_HISTORY_BURSA_JOB_INTEREST = BASE_DB + "history/bursa/job_interest/";
    public static final String REF_HISTORY_BURSA_JOB_VIEWED = BASE_DB + "history/bursa/job_viewed/";


    public static final String FLAG_ORIGIN = "tag_original_pagiyo";
    public static final String FLAG_FROM_SIGN_UP_EMPLOYER = "im_from_signUP_baBy_EMPLOYER";
    public static final String FLAG_FROM_SIGN_UP_EMPLOYEE = "im_from_signUP_baBy_EMPLOYEE";
    public static final String FLAG_FROM_SIGN_UP_USER = "im_from_signUP_baBy_USER";
    public static final String FLAG_FROM_LOGIN_EMPLOYER = "im_just__logged_in_baBy_EMPLOYER";
    public static final String FLAG_FROM_LOGIN_EMPLOYEE = "im_just_logged_in_baBy_EMPLOYEE";
    public static final String FLAG_FROM_LOGIN_USER = "im_just_logged_in_baBy_USER";
    public static final String FLAG_AFTER_REVIEW_EMPLOYER = "im_just___review_baBy_EMPLOYER";
    public static final String FLAG_AFTER_REVIEW_EMPLOYEE = "im_just_review_in_baBy_EMPLOYEE";
    public static final String FLAG_AFTER_REVIEW_USER = "im_just_review_in_baBy_USER";
    public static final String FLAG_FROM_NOTIF_CLICK = "im_from_notif_click";
}
