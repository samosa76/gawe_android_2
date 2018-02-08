package gawe.imb.karya.model.objects;

/**
 * Created by korneliussendy on 9/21/17.
 */

public class Employer extends GaweUser {

    private String TYPE = "EMPLOYER";
    private boolean hasPendingPayment = false;
    private double totalPendingPayment = 0d;
    private int missionCreated = 0;

    public Employer() {
    }

    public Employer(User user) {
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

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public boolean isHasPendingPayment() {
        return hasPendingPayment;
    }

    public void setHasPendingPayment(boolean hasPendingPayment) {
        this.hasPendingPayment = hasPendingPayment;
    }

    public double getTotalPendingPayment() {
        return totalPendingPayment;
    }

    public void setTotalPendingPayment(double totalPendingPayment) {
        this.totalPendingPayment = totalPendingPayment;
    }

    public int getMissionCreated() {
        return missionCreated;
    }

    public void setMissionCreated(int missionCreated) {
        this.missionCreated = missionCreated;
    }

}
