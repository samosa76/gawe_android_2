package gawe.imb.karya.model.objects;

/**
 * Created by korneliussendy on 11/2/17.
 */

public class GaweSettings {
    private Long averageWage = 0L;
    private Long bonuscredit_employee_giver = 0L;
    private Long bonuscredit_employee_taker = 0L;
    private Long bonuscredit_employer_giver = 0L;
    private Long bonuscredit_employer_taker = 0L;
    private String bursa_tnc = "";
    private int bursa_expired_days = 0;
    private Long cancel_banned_duration = 0L;
    private Long cancel_banned_duration_new = 0L;
    private String coupon_image = "";
    private String coupon_previous_result = "";
    private String coupon_tnc = "";
    private Long creditPerBursa = 1L;
    private Long creditPerMission = 1L;
    private Long defaultRadius = 0L;
    private Long defaultServiceCharge = 0L;
    private String defaultServiceChargeType = "";
    private Long defaultcredit_employee = 0L;
    private Long defaultcredit_employer = 0L;
    private Long ee_max_selected_category = 1L;
    private String home_welcome_text = "";
    private String iklan_gawesocial_image = "";
    private String iklan_gawesocial_type = "";
    private String iklan_gawesocial_url = "";
    private String iklanhome_employee_image = "";
    private String iklanhome_employee_type = "";
    private String iklanhome_employee_url = "";
    private String iklanhome_employer_image = "";
    private String iklanhome_employer_type = "";
    private String iklanhome_employer_url = "";
    private String link_employer_help = "";
    private String link_employee_help = "";
    private Long min_distance_to_arrive = 0L;
    private String popup_afterlogin_action_employee = "";
    private String popup_afterlogin_action_employer = "";
    private String popup_afterlogin_image_employee = "";
    private String popup_afterlogin_image_employer = "";
    private String popup_afterlogin_url_employee = "";
    private String popup_afterlogin_url_employer = "";
    private String popup_afterreview_action_employee = "";
    private String popup_afterreview_action_employer = "";
    private String popup_afterreview_image_employee = "";
    private String popup_afterreview_image_employer = "";
    private String popup_afterreview_url_employee = "";
    private String popup_afterreview_url_employer = "";
    private String popup_aftersignup_action_employee = "";
    private String popup_aftersignup_action_employer = "";
    private String popup_aftersignup_image_employee = "";
    private String popup_aftersignup_image_employer = "";
    private String popup_aftersignup_url_employee = "";
    private String popup_aftersignup_url_employer = "";
    private Long promoServiceCharge = 0L;
    //    private Boolean promoServiceChargeActivated = false;
    private String promoServiceChargeEnded = "";
    private String promoServiceChargeType = "";
    private String radiusIncrement = "";
    private String signup_tos_link = "";
    private Long suspend_employee_brpkali = 0L;
    private Long suspend_employee_lebihkecildrbrp = 0L;
    private Long suspend_employer_brpkali = 0L;
    private Long suspend_employer_lebihkecildrbrp = 0L;
    private Long time_search_job_timeout_minutes = 3l;
    private String url_help_employee_help_page = "";
    private String url_help_employee_home = "";
    private String version_android_gawe = "";
    private String version_android_partner = "";
    private String share_code_invite_employer = "";

    private Long banned_cancel_job_berapa_kali = 0L;

    public GaweSettings() {
    }

    public Long getAverageWage() {
        return averageWage;
    }

    public void setAverageWage(Long averageWage) {
        this.averageWage = averageWage;
    }

    public Long getBonuscredit_employee_giver() {
        return bonuscredit_employee_giver;
    }

    public void setBonuscredit_employee_giver(Long bonuscredit_employee_giver) {
        this.bonuscredit_employee_giver = bonuscredit_employee_giver;
    }

    public Long getBonuscredit_employee_taker() {
        return bonuscredit_employee_taker;
    }

    public void setBonuscredit_employee_taker(Long bonuscredit_employee_taker) {
        this.bonuscredit_employee_taker = bonuscredit_employee_taker;
    }

    public Long getBonuscredit_employer_giver() {
        return bonuscredit_employer_giver;
    }

    public void setBonuscredit_employer_giver(Long bonuscredit_employer_giver) {
        this.bonuscredit_employer_giver = bonuscredit_employer_giver;
    }

    public Long getBonuscredit_employer_taker() {
        return bonuscredit_employer_taker;
    }

    public void setBonuscredit_employer_taker(Long bonuscredit_employer_taker) {
        this.bonuscredit_employer_taker = bonuscredit_employer_taker;
    }

    public String getBursa_tnc() {
        return bursa_tnc;
    }

    public void setBursa_tnc(String bursa_tnc) {
        this.bursa_tnc = bursa_tnc;
    }

    public int getBursa_expired_days() {
        return bursa_expired_days;
    }

    public void setBursa_expired_days(int bursa_expired_days) {
        this.bursa_expired_days = bursa_expired_days;
    }

    public Long getCancel_banned_duration() {
        return cancel_banned_duration;
    }

    public void setCancel_banned_duration(Long cancel_banned_duration) {
        this.cancel_banned_duration = cancel_banned_duration;
    }

    public Long getCancel_banned_duration_new() {
        return cancel_banned_duration_new;
    }

    public void setCancel_banned_duration_new(Long cancel_banned_duration_new) {
        this.cancel_banned_duration_new = cancel_banned_duration_new;
    }

    public String getCoupon_image() {
        return coupon_image;
    }

    public void setCoupon_image(String coupon_image) {
        this.coupon_image = coupon_image;
    }

    public String getCoupon_previous_result() {
        return coupon_previous_result;
    }

    public void setCoupon_previous_result(String coupon_previous_result) {
        this.coupon_previous_result = coupon_previous_result;
    }

    public String getCoupon_tnc() {
        return coupon_tnc;
    }

    public void setCoupon_tnc(String coupon_tnc) {
        this.coupon_tnc = coupon_tnc;
    }

    public Long getCreditPerBursa() {
        return creditPerBursa;
    }

    public void setCreditPerBursa(Long creditPerBursa) {
        this.creditPerBursa = creditPerBursa;
    }

    public Long getCreditPerMission() {
        return creditPerMission;
    }

    public void setCreditPerMission(Long creditPerMission) {
        this.creditPerMission = creditPerMission;
    }

    public Long getDefaultRadius() {
        return defaultRadius;
    }

    public void setDefaultRadius(Long defaultRadius) {
        this.defaultRadius = defaultRadius;
    }

    public Long getDefaultServiceCharge() {
        return defaultServiceCharge;
    }

    public void setDefaultServiceCharge(Long defaultServiceCharge) {
        this.defaultServiceCharge = defaultServiceCharge;
    }

    public String getDefaultServiceChargeType() {
        return defaultServiceChargeType;
    }

    public void setDefaultServiceChargeType(String defaultServiceChargeType) {
        this.defaultServiceChargeType = defaultServiceChargeType;
    }

    public Long getDefaultcredit_employee() {
        return defaultcredit_employee;
    }

    public void setDefaultcredit_employee(Long defaultcredit_employee) {
        this.defaultcredit_employee = defaultcredit_employee;
    }

    public Long getDefaultcredit_employer() {
        return defaultcredit_employer;
    }

    public void setDefaultcredit_employer(Long defaultcredit_employer) {
        this.defaultcredit_employer = defaultcredit_employer;
    }

    public Long getEe_max_selected_category() {
        return ee_max_selected_category;
    }

    public void setEe_max_selected_category(Long ee_max_selected_category) {
        this.ee_max_selected_category = ee_max_selected_category;
    }

    public String getHome_welcome_text() {
        return home_welcome_text;
    }

    public void setHome_welcome_text(String home_welcome_text) {
        this.home_welcome_text = home_welcome_text;
    }

    public String getIklan_gawesocial_image() {
        return iklan_gawesocial_image;
    }

    public void setIklan_gawesocial_image(String iklan_gawesocial_image) {
        this.iklan_gawesocial_image = iklan_gawesocial_image;
    }

    public String getIklan_gawesocial_type() {
        return iklan_gawesocial_type;
    }

    public void setIklan_gawesocial_type(String iklan_gawesocial_type) {
        this.iklan_gawesocial_type = iklan_gawesocial_type;
    }

    public String getIklan_gawesocial_url() {
        return iklan_gawesocial_url;
    }

    public void setIklan_gawesocial_url(String iklan_gawesocial_url) {
        this.iklan_gawesocial_url = iklan_gawesocial_url;
    }

    public String getIklanhome_employee_image() {
        return iklanhome_employee_image;
    }

    public void setIklanhome_employee_image(String iklanhome_employee_image) {
        this.iklanhome_employee_image = iklanhome_employee_image;
    }

    public String getIklanhome_employee_type() {
        return iklanhome_employee_type;
    }

    public void setIklanhome_employee_type(String iklanhome_employee_type) {
        this.iklanhome_employee_type = iklanhome_employee_type;
    }

    public String getIklanhome_employee_url() {
        return iklanhome_employee_url;
    }

    public void setIklanhome_employee_url(String iklanhome_employee_url) {
        this.iklanhome_employee_url = iklanhome_employee_url;
    }

    public String getIklanhome_employer_image() {
        return iklanhome_employer_image;
    }

    public void setIklanhome_employer_image(String iklanhome_employer_image) {
        this.iklanhome_employer_image = iklanhome_employer_image;
    }

    public String getIklanhome_employer_type() {
        return iklanhome_employer_type;
    }

    public void setIklanhome_employer_type(String iklanhome_employer_type) {
        this.iklanhome_employer_type = iklanhome_employer_type;
    }

    public String getIklanhome_employer_url() {
        return iklanhome_employer_url;
    }

    public void setIklanhome_employer_url(String iklanhome_employer_url) {
        this.iklanhome_employer_url = iklanhome_employer_url;
    }

    public String getLink_employer_help() {
        return link_employer_help;
    }

    public void setLink_employer_help(String link_employer_help) {
        this.link_employer_help = link_employer_help;
    }

    public String getLink_employee_help() {
        return link_employee_help;
    }

    public void setLink_employee_help(String link_employee_help) {
        this.link_employee_help = link_employee_help;
    }

    public Long getMin_distance_to_arrive() {
        return min_distance_to_arrive;
    }

    public void setMin_distance_to_arrive(Long min_distance_to_arrive) {
        this.min_distance_to_arrive = min_distance_to_arrive;
    }

    public String getPopup_afterlogin_action_employee() {
        return popup_afterlogin_action_employee;
    }

    public void setPopup_afterlogin_action_employee(String popup_afterlogin_action_employee) {
        this.popup_afterlogin_action_employee = popup_afterlogin_action_employee;
    }

    public String getPopup_afterlogin_action_employer() {
        return popup_afterlogin_action_employer;
    }

    public void setPopup_afterlogin_action_employer(String popup_afterlogin_action_employer) {
        this.popup_afterlogin_action_employer = popup_afterlogin_action_employer;
    }

    public String getPopup_afterlogin_image_employee() {
        return popup_afterlogin_image_employee;
    }

    public void setPopup_afterlogin_image_employee(String popup_afterlogin_image_employee) {
        this.popup_afterlogin_image_employee = popup_afterlogin_image_employee;
    }

    public String getPopup_afterlogin_image_employer() {
        return popup_afterlogin_image_employer;
    }

    public void setPopup_afterlogin_image_employer(String popup_afterlogin_image_employer) {
        this.popup_afterlogin_image_employer = popup_afterlogin_image_employer;
    }

    public String getPopup_afterlogin_url_employee() {
        return popup_afterlogin_url_employee;
    }

    public void setPopup_afterlogin_url_employee(String popup_afterlogin_url_employee) {
        this.popup_afterlogin_url_employee = popup_afterlogin_url_employee;
    }

    public String getPopup_afterlogin_url_employer() {
        return popup_afterlogin_url_employer;
    }

    public void setPopup_afterlogin_url_employer(String popup_afterlogin_url_employer) {
        this.popup_afterlogin_url_employer = popup_afterlogin_url_employer;
    }

    public String getPopup_afterreview_action_employee() {
        return popup_afterreview_action_employee;
    }

    public void setPopup_afterreview_action_employee(String popup_afterreview_action_employee) {
        this.popup_afterreview_action_employee = popup_afterreview_action_employee;
    }

    public String getPopup_afterreview_action_employer() {
        return popup_afterreview_action_employer;
    }

    public void setPopup_afterreview_action_employer(String popup_afterreview_action_employer) {
        this.popup_afterreview_action_employer = popup_afterreview_action_employer;
    }

    public String getPopup_afterreview_image_employee() {
        return popup_afterreview_image_employee;
    }

    public void setPopup_afterreview_image_employee(String popup_afterreview_image_employee) {
        this.popup_afterreview_image_employee = popup_afterreview_image_employee;
    }

    public String getPopup_afterreview_image_employer() {
        return popup_afterreview_image_employer;
    }

    public void setPopup_afterreview_image_employer(String popup_afterreview_image_employer) {
        this.popup_afterreview_image_employer = popup_afterreview_image_employer;
    }

    public String getPopup_afterreview_url_employee() {
        return popup_afterreview_url_employee;
    }

    public void setPopup_afterreview_url_employee(String popup_afterreview_url_employee) {
        this.popup_afterreview_url_employee = popup_afterreview_url_employee;
    }

    public String getPopup_afterreview_url_employer() {
        return popup_afterreview_url_employer;
    }

    public void setPopup_afterreview_url_employer(String popup_afterreview_url_employer) {
        this.popup_afterreview_url_employer = popup_afterreview_url_employer;
    }

    public String getPopup_aftersignup_action_employee() {
        return popup_aftersignup_action_employee;
    }

    public void setPopup_aftersignup_action_employee(String popup_aftersignup_action_employee) {
        this.popup_aftersignup_action_employee = popup_aftersignup_action_employee;
    }

    public String getPopup_aftersignup_action_employer() {
        return popup_aftersignup_action_employer;
    }

    public void setPopup_aftersignup_action_employer(String popup_aftersignup_action_employer) {
        this.popup_aftersignup_action_employer = popup_aftersignup_action_employer;
    }

    public String getPopup_aftersignup_image_employee() {
        return popup_aftersignup_image_employee;
    }

    public void setPopup_aftersignup_image_employee(String popup_aftersignup_image_employee) {
        this.popup_aftersignup_image_employee = popup_aftersignup_image_employee;
    }

    public String getPopup_aftersignup_image_employer() {
        return popup_aftersignup_image_employer;
    }

    public void setPopup_aftersignup_image_employer(String popup_aftersignup_image_employer) {
        this.popup_aftersignup_image_employer = popup_aftersignup_image_employer;
    }

    public String getPopup_aftersignup_url_employee() {
        return popup_aftersignup_url_employee;
    }

    public void setPopup_aftersignup_url_employee(String popup_aftersignup_url_employee) {
        this.popup_aftersignup_url_employee = popup_aftersignup_url_employee;
    }

    public String getPopup_aftersignup_url_employer() {
        return popup_aftersignup_url_employer;
    }

    public void setPopup_aftersignup_url_employer(String popup_aftersignup_url_employer) {
        this.popup_aftersignup_url_employer = popup_aftersignup_url_employer;
    }

    public Long getPromoServiceCharge() {
        return promoServiceCharge;
    }

    public void setPromoServiceCharge(Long promoServiceCharge) {
        this.promoServiceCharge = promoServiceCharge;
    }

//    public Boolean getPromoServiceChargeActivated() {
//        return promoServiceChargeActivated;
//    }
//
//    public void setPromoServiceChargeActivated(Boolean promoServiceChargeActivated) {
//        this.promoServiceChargeActivated = promoServiceChargeActivated;
//    }

    public String getPromoServiceChargeEnded() {
        return promoServiceChargeEnded;
    }

    public void setPromoServiceChargeEnded(String promoServiceChargeEnded) {
        this.promoServiceChargeEnded = promoServiceChargeEnded;
    }

    public String getPromoServiceChargeType() {
        return promoServiceChargeType;
    }

    public void setPromoServiceChargeType(String promoServiceChargeType) {
        this.promoServiceChargeType = promoServiceChargeType;
    }

    public String getRadiusIncrement() {
        return radiusIncrement;
    }

    public void setRadiusIncrement(String radiusIncrement) {
        this.radiusIncrement = radiusIncrement;
    }

    public String getSignup_tos_link() {
        return signup_tos_link;
    }

    public void setSignup_tos_link(String signup_tos_link) {
        this.signup_tos_link = signup_tos_link;
    }

    public Long getSuspend_employee_brpkali() {
        return suspend_employee_brpkali;
    }

    public void setSuspend_employee_brpkali(Long suspend_employee_brpkali) {
        this.suspend_employee_brpkali = suspend_employee_brpkali;
    }

    public Long getSuspend_employee_lebihkecildrbrp() {
        return suspend_employee_lebihkecildrbrp;
    }

    public void setSuspend_employee_lebihkecildrbrp(Long suspend_employee_lebihkecildrbrp) {
        this.suspend_employee_lebihkecildrbrp = suspend_employee_lebihkecildrbrp;
    }

    public Long getSuspend_employer_brpkali() {
        return suspend_employer_brpkali;
    }

    public void setSuspend_employer_brpkali(Long suspend_employer_brpkali) {
        this.suspend_employer_brpkali = suspend_employer_brpkali;
    }

    public Long getSuspend_employer_lebihkecildrbrp() {
        return suspend_employer_lebihkecildrbrp;
    }

    public void setSuspend_employer_lebihkecildrbrp(Long suspend_employer_lebihkecildrbrp) {
        this.suspend_employer_lebihkecildrbrp = suspend_employer_lebihkecildrbrp;
    }

    public Long getTime_search_job_timeout_minutes() {
        return time_search_job_timeout_minutes;
    }

    public void setTime_search_job_timeout_minutes(Long time_search_job_timeout_minutes) {
        this.time_search_job_timeout_minutes = time_search_job_timeout_minutes;
    }

    public String getUrl_help_employee_help_page() {
        return url_help_employee_help_page;
    }

    public void setUrl_help_employee_help_page(String url_help_employee_help_page) {
        this.url_help_employee_help_page = url_help_employee_help_page;
    }

    public String getUrl_help_employee_home() {
        return url_help_employee_home;
    }

    public void setUrl_help_employee_home(String url_help_employee_home) {
        this.url_help_employee_home = url_help_employee_home;
    }

    public String getVersion_android_gawe() {

        return version_android_gawe;
    }

    public void setVersion_android_gawe(String version_android_gawe) {
        this.version_android_gawe = version_android_gawe;
    }

    public String getVersion_android_partner() {
        return version_android_partner;
    }

    public void setVersion_android_partner(String version_android_partner) {
        this.version_android_partner = version_android_partner;
    }

    public String getShare_code_invite_employer() {
        return share_code_invite_employer;
    }

    public void setShare_code_invite_employer(String share_code_invite_employer) {
        this.share_code_invite_employer = share_code_invite_employer;
    }

    public Long getBanned_cancel_job_berapa_kali() {
        return banned_cancel_job_berapa_kali;
    }

    public void setBanned_cancel_job_berapa_kali(Long banned_cancel_job_berapa_kali) {
        this.banned_cancel_job_berapa_kali = banned_cancel_job_berapa_kali;
    }

    @Override
    public String toString() {
        return "GaweSettings{" +
                "averageWage=" + averageWage +
                ", bonuscredit_employee_giver=" + bonuscredit_employee_giver +
                ", bonuscredit_employee_taker=" + bonuscredit_employee_taker +
                ", bonuscredit_employer_giver=" + bonuscredit_employer_giver +
                ", bonuscredit_employer_taker=" + bonuscredit_employer_taker +
                ", bursa_tnc='" + bursa_tnc + '\'' +
                ", bursa_expired_days=" + bursa_expired_days +
                ", cancel_banned_duration=" + cancel_banned_duration +
                ", cancel_banned_duration_new=" + cancel_banned_duration_new +
                ", coupon_image='" + coupon_image + '\'' +
                ", coupon_previous_result='" + coupon_previous_result + '\'' +
                ", coupon_tnc='" + coupon_tnc + '\'' +
                ", creditPerBursa=" + creditPerBursa +
                ", creditPerMission=" + creditPerMission +
                ", defaultRadius=" + defaultRadius +
                ", defaultServiceCharge=" + defaultServiceCharge +
                ", defaultServiceChargeType='" + defaultServiceChargeType + '\'' +
                ", defaultcredit_employee=" + defaultcredit_employee +
                ", defaultcredit_employer=" + defaultcredit_employer +
                ", ee_max_selected_category=" + ee_max_selected_category +
                ", home_welcome_text='" + home_welcome_text + '\'' +
                ", iklan_gawesocial_image='" + iklan_gawesocial_image + '\'' +
                ", iklan_gawesocial_type='" + iklan_gawesocial_type + '\'' +
                ", iklan_gawesocial_url='" + iklan_gawesocial_url + '\'' +
                ", iklanhome_employee_image='" + iklanhome_employee_image + '\'' +
                ", iklanhome_employee_type='" + iklanhome_employee_type + '\'' +
                ", iklanhome_employee_url='" + iklanhome_employee_url + '\'' +
                ", iklanhome_employer_image='" + iklanhome_employer_image + '\'' +
                ", iklanhome_employer_type='" + iklanhome_employer_type + '\'' +
                ", iklanhome_employer_url='" + iklanhome_employer_url + '\'' +
                ", link_employer_help='" + link_employer_help + '\'' +
                ", link_employee_help='" + link_employee_help + '\'' +
                ", min_distance_to_arrive=" + min_distance_to_arrive +
                ", popup_afterlogin_action_employee='" + popup_afterlogin_action_employee + '\'' +
                ", popup_afterlogin_action_employer='" + popup_afterlogin_action_employer + '\'' +
                ", popup_afterlogin_image_employee='" + popup_afterlogin_image_employee + '\'' +
                ", popup_afterlogin_image_employer='" + popup_afterlogin_image_employer + '\'' +
                ", popup_afterlogin_url_employee='" + popup_afterlogin_url_employee + '\'' +
                ", popup_afterlogin_url_employer='" + popup_afterlogin_url_employer + '\'' +
                ", popup_afterreview_action_employee='" + popup_afterreview_action_employee + '\'' +
                ", popup_afterreview_action_employer='" + popup_afterreview_action_employer + '\'' +
                ", popup_afterreview_image_employee='" + popup_afterreview_image_employee + '\'' +
                ", popup_afterreview_image_employer='" + popup_afterreview_image_employer + '\'' +
                ", popup_afterreview_url_employee='" + popup_afterreview_url_employee + '\'' +
                ", popup_afterreview_url_employer='" + popup_afterreview_url_employer + '\'' +
                ", popup_aftersignup_action_employee='" + popup_aftersignup_action_employee + '\'' +
                ", popup_aftersignup_action_employer='" + popup_aftersignup_action_employer + '\'' +
                ", popup_aftersignup_image_employee='" + popup_aftersignup_image_employee + '\'' +
                ", popup_aftersignup_image_employer='" + popup_aftersignup_image_employer + '\'' +
                ", popup_aftersignup_url_employee='" + popup_aftersignup_url_employee + '\'' +
                ", popup_aftersignup_url_employer='" + popup_aftersignup_url_employer + '\'' +
                ", promoServiceCharge=" + promoServiceCharge +
                ", promoServiceChargeEnded='" + promoServiceChargeEnded + '\'' +
                ", promoServiceChargeType='" + promoServiceChargeType + '\'' +
                ", radiusIncrement='" + radiusIncrement + '\'' +
                ", signup_tos_link='" + signup_tos_link + '\'' +
                ", suspend_employee_brpkali=" + suspend_employee_brpkali +
                ", suspend_employee_lebihkecildrbrp=" + suspend_employee_lebihkecildrbrp +
                ", suspend_employer_brpkali=" + suspend_employer_brpkali +
                ", suspend_employer_lebihkecildrbrp=" + suspend_employer_lebihkecildrbrp +
                ", time_search_job_timeout_minutes=" + time_search_job_timeout_minutes +
                ", url_help_employee_help_page='" + url_help_employee_help_page + '\'' +
                ", url_help_employee_home='" + url_help_employee_home + '\'' +
                ", version_android_gawe='" + version_android_gawe + '\'' +
                ", version_android_partner='" + version_android_partner + '\'' +
                ", share_code_invite_employer='" + share_code_invite_employer + '\'' +
                ", banned_cancel_job_berapa_kali=" + banned_cancel_job_berapa_kali +
                '}';
    }
}
