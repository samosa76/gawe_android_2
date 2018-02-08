package gawe.imb.karya.model.objects;

/**
 * Created by korneliussendy on 9/18/17.
 */

public class User {

    protected String id = "";
    protected String fullName = "";
    protected String email = "";
    protected String phone = "";
    protected String gender = "";
    protected String deviceToken = "";
    protected String referralCode = "";
    protected String myReferralCode = "";
    protected String ktp = "";
    protected String idNumber = "";
    protected String profile_pic = "";
    protected boolean hasProfilePicture = false;
    protected boolean hasIDPicture = false;
    protected boolean hasRegistered = false;
    protected boolean isPhoneVerified = false;
    protected boolean cs_verified = false;
    protected Long lastLogin = 0l;
    protected Long dateCreated = 0l;
    protected Long dateCreatedAsli = 0l;
    protected Double lat = 0d, lng = 0d;

    protected boolean banned = false;
    protected String bannedUntil = "";
    protected String bannedReason = "";
    protected Long bannedUntilMillis = 0l;

    protected Long myVersionCode = 0l;
    protected String myVersionName = "";
    protected Long point = 0l;
    protected Long coupons = 0l;

    protected String kartu_mahasiswa = "";
    protected boolean hasKartuMahasiswa = false;
    protected Long yearOfBirth = 0l;

    protected String appType = "GAWE";

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public String getMyReferralCode() {
        return myReferralCode;
    }

    public void setMyReferralCode(String myReferralCode) {
        this.myReferralCode = myReferralCode;
    }

    public String getKtp() {
        return ktp;
    }

    public void setKtp(String ktp) {
        this.ktp = ktp;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public boolean isHasProfilePicture() {
        return hasProfilePicture;
    }

    public void setHasProfilePicture(boolean hasProfilePicture) {
        this.hasProfilePicture = hasProfilePicture;
    }

    public boolean isHasIDPicture() {
        return hasIDPicture;
    }

    public void setHasIDPicture(boolean hasIDPicture) {
        this.hasIDPicture = hasIDPicture;
    }

    public boolean isHasRegistered() {
        return hasRegistered;
    }

    public void setHasRegistered(boolean hasRegistered) {
        this.hasRegistered = hasRegistered;
    }

    public boolean isPhoneVerified() {
        return isPhoneVerified;
    }

    public void setPhoneVerified(boolean phoneVerified) {
        isPhoneVerified = phoneVerified;
    }

    public boolean isCs_verified() {
        return cs_verified;
    }

    public void setCs_verified(boolean cs_verified) {
        this.cs_verified = cs_verified;
    }

    public Long getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Long lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Long getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Long dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Long getDateCreatedAsli() {
        return dateCreatedAsli;
    }

    public void setDateCreatedAsli(Long dateCreatedAsli) {
        this.dateCreatedAsli = dateCreatedAsli;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public String getBannedUntil() {
        return bannedUntil;
    }

    public void setBannedUntil(String bannedUntil) {
        this.bannedUntil = bannedUntil;
    }

    public String getBannedReason() {
        return bannedReason;
    }

    public void setBannedReason(String bannedReason) {
        this.bannedReason = bannedReason;
    }

    public Long getBannedUntilMillis() {
        return bannedUntilMillis;
    }

    public void setBannedUntilMillis(Long bannedUntilMillis) {
        this.bannedUntilMillis = bannedUntilMillis;
    }

    public Long getMyVersionCode() {
        return myVersionCode;
    }

    public void setMyVersionCode(Long myVersionCode) {
        this.myVersionCode = myVersionCode;
    }

    public String getMyVersionName() {
        return myVersionName;
    }

    public void setMyVersionName(String myVersionName) {
        this.myVersionName = myVersionName;
    }

    public Long getPoint() {
        return point;
    }

    public void setPoint(Long point) {
        this.point = point;
    }

    public Long getCoupons() {
        return coupons;
    }

    public void setCoupons(Long coupons) {
        this.coupons = coupons;
    }

    public String getKartu_mahasiswa() {
        return kartu_mahasiswa;
    }

    public void setKartu_mahasiswa(String kartu_mahasiswa) {
        this.kartu_mahasiswa = kartu_mahasiswa;
    }

    public boolean isHasKartuMahasiswa() {
        return hasKartuMahasiswa;
    }

    public void setHasKartuMahasiswa(boolean hasKartuMahasiswa) {
        this.hasKartuMahasiswa = hasKartuMahasiswa;
    }

    public Long getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(Long yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", gender='" + gender + '\'' +
                ", deviceToken='" + deviceToken + '\'' +
                ", referralCode='" + referralCode + '\'' +
                ", myReferralCode='" + myReferralCode + '\'' +
                ", ktp='" + ktp + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", profile_pic='" + profile_pic + '\'' +
                ", hasProfilePicture=" + hasProfilePicture +
                ", hasIDPicture=" + hasIDPicture +
                ", hasRegistered=" + hasRegistered +
                ", isPhoneVerified=" + isPhoneVerified +
                ", cs_verified=" + cs_verified +
                ", lastLogin=" + lastLogin +
                ", dateCreated=" + dateCreated +
                ", dateCreatedAsli=" + dateCreatedAsli +
                ", lat=" + lat +
                ", lng=" + lng +
                ", banned=" + banned +
                ", bannedUntil='" + bannedUntil + '\'' +
                ", bannedReason='" + bannedReason + '\'' +
                ", bannedUntilMillis=" + bannedUntilMillis +
                ", myVersionCode=" + myVersionCode +
                ", myVersionName='" + myVersionName + '\'' +
                ", point=" + point +
                ", coupons=" + coupons +
                ", kartu_mahasiswa='" + kartu_mahasiswa + '\'' +
                ", hasKartuMahasiswa=" + hasKartuMahasiswa +
                ", yearOfBirth=" + yearOfBirth +
                ", appType='" + appType + '\'' +
                '}';
    }
}

