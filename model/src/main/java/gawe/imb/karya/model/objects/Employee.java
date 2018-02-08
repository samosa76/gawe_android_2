package gawe.imb.karya.model.objects;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by korneliussendy on 9/18/17.
 */

public class Employee extends GaweUser {

    private String TYPE = "EMPLOYEE";
    private boolean ready = false;
    private boolean accepting = false;
    private boolean working = false;
    private int totalMissionCancel = 0;
    private String activeMission = "";
    private int bullets = 0;
    private String typeAccount = "";
    private String jobCategory = "";
    private String duration = "";
    private Long lastCancelMillis = 0L;
    private int cancelCounter = 0;

    public Employee() {
    }

    public Employee(User user) {
        this.id = user.id;
        this.fullName = user.fullName;
        this.email = user.email;
        this.phone = user.phone;
        this.gender = user.gender;
        this.deviceToken = user.deviceToken;
        this.referralCode = user.referralCode;
        this.myReferralCode = user.myReferralCode;
        this.ktp = user.ktp;
        this.idNumber = user.idNumber;
        this.profile_pic = user.profile_pic;
        this.hasProfilePicture = user.hasProfilePicture;
        this.hasIDPicture = user.hasIDPicture;
        this.hasRegistered = user.hasRegistered;
        this.isPhoneVerified = user.isPhoneVerified;
        this.cs_verified = user.cs_verified;
        this.lastLogin = user.lastLogin;
        this.dateCreated = user.dateCreated;
        this.dateCreatedAsli = user.dateCreatedAsli;
        this.lat = user.lat;

        this.banned = user.banned;
        this.bannedUntil = user.bannedUntil;
        this.bannedReason = user.bannedReason;

        this.myVersionCode = user.myVersionCode;
        this.point = user.point;
        this.coupons = user.coupons;

        this.kartu_mahasiswa = user.kartu_mahasiswa;
        this.hasKartuMahasiswa = user.hasKartuMahasiswa;
    }

    public Employee(FirebaseUser user) {
        this.id = user.getUid();
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public boolean isAccepting() {
        return accepting;
    }

    public void setAccepting(boolean accepting) {
        this.accepting = accepting;
    }

    public boolean isWorking() {
        return working;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }

    public int getTotalMissionCancel() {
        return totalMissionCancel;
    }

    public void setTotalMissionCancel(int totalMissionCancel) {
        this.totalMissionCancel = totalMissionCancel;
    }

    public String getActiveMission() {
        return activeMission;
    }

    public void setActiveMission(String activeMission) {
        this.activeMission = activeMission;
    }

    public int getBullets() {
        return bullets;
    }

    public void setBullets(int bullets) {
        this.bullets = bullets;
    }

    public String getTypeAccount() {
        return typeAccount;
    }

    public void setTypeAccount(String typeAccount) {
        this.typeAccount = typeAccount;
    }

    public String getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(String jobCategory) {
        this.jobCategory = jobCategory;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public Long getLastCancelMillis() {
        return lastCancelMillis;
    }

    public void setLastCancelMillis(Long lastCancelMillis) {
        this.lastCancelMillis = lastCancelMillis;
    }

    public int getCancelCounter() {
        return cancelCounter;
    }

    public void setCancelCounter(int cancelCounter) {
        this.cancelCounter = cancelCounter;
    }
}
