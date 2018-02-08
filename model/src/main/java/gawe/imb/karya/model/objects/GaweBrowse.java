package gawe.imb.karya.model.objects;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GaweBrowse{

	@SerializedName("availableBrowse")
	private boolean availableBrowse;

	@SerializedName("jobCategory")
	private List<String> jobCategory;

	@SerializedName("cs_verified")
	private boolean csVerified;

	@SerializedName("role")
	private String role;

	@SerializedName("gender")
	private String gender;

	@SerializedName("ktm")
	private String ktm;

	@SerializedName("level")
	private int level;

	@SerializedName("ktp")
	private String ktp;

	@SerializedName("queueing")
	private boolean queueing;

	@SerializedName("profile_pic")
	private String profilePic;

	@SerializedName("fullName")
	private String fullName;

	@SerializedName("fullTime")
	private boolean fullTime;

	@SerializedName("deviceToken")
	private String deviceToken;

	@SerializedName("duration")
	private String duration;

	@SerializedName("partTime")
	private boolean partTime;

	@SerializedName("phone")
	private String phone;

	@SerializedName("ready")
	private boolean ready;

	@SerializedName("working")
	private boolean working;

	@SerializedName("location")
	private String location;

	@SerializedName("id")
	private String id;

	@SerializedName("banned")
	private boolean banned;

	@SerializedName("accepting")
	private boolean accepting;

	public void setAvailableBrowse(boolean availableBrowse){
		this.availableBrowse = availableBrowse;
	}

	public boolean isAvailableBrowse(){
		return availableBrowse;
	}

	public void setJobCategory(List<String> jobCategory){
		this.jobCategory = jobCategory;
	}

	public List<String> getJobCategory(){
		return jobCategory;
	}

	public void setCsVerified(boolean csVerified){
		this.csVerified = csVerified;
	}

	public boolean isCsVerified(){
		return csVerified;
	}

	public void setRole(String role){
		this.role = role;
	}

	public String getRole(){
		return role;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return gender;
	}

	public void setKtm(String ktm){
		this.ktm = ktm;
	}

	public String getKtm(){
		return ktm;
	}

	public void setLevel(int level){
		this.level = level;
	}

	public int getLevel(){
		return level;
	}

	public void setKtp(String ktp){
		this.ktp = ktp;
	}

	public String getKtp(){
		return ktp;
	}

	public void setQueueing(boolean queueing){
		this.queueing = queueing;
	}

	public boolean isQueueing(){
		return queueing;
	}

	public void setProfilePic(String profilePic){
		this.profilePic = profilePic;
	}

	public String getProfilePic(){
		return profilePic;
	}

	public void setFullName(String fullName){
		this.fullName = fullName;
	}

	public String getFullName(){
		return fullName;
	}

	public void setFullTime(boolean fullTime){
		this.fullTime = fullTime;
	}

	public boolean isFullTime(){
		return fullTime;
	}

	public void setDeviceToken(String deviceToken){
		this.deviceToken = deviceToken;
	}

	public String getDeviceToken(){
		return deviceToken;
	}

	public void setDuration(String duration){
		this.duration = duration;
	}

	public String getDuration(){
		return duration;
	}

	public void setPartTime(boolean partTime){
		this.partTime = partTime;
	}

	public boolean isPartTime(){
		return partTime;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setReady(boolean ready){
		this.ready = ready;
	}

	public boolean isReady(){
		return ready;
	}

	public void setWorking(boolean working){
		this.working = working;
	}

	public boolean isWorking(){
		return working;
	}

	public void setLocation(String location){
		this.location = location;
	}

	public String getLocation(){
		return location;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setBanned(boolean banned){
		this.banned = banned;
	}

	public boolean isBanned(){
		return banned;
	}

	public void setAccepting(boolean accepting){
		this.accepting = accepting;
	}

	public boolean isAccepting(){
		return accepting;
	}

	@Override
 	public String toString(){
		return 
			"GaweBrowse{" + 
			"availableBrowse = '" + availableBrowse + '\'' + 
			",jobCategory = '" + jobCategory + '\'' + 
			",cs_verified = '" + csVerified + '\'' + 
			",role = '" + role + '\'' + 
			",gender = '" + gender + '\'' + 
			",ktm = '" + ktm + '\'' + 
			",level = '" + level + '\'' + 
			",ktp = '" + ktp + '\'' + 
			",queueing = '" + queueing + '\'' + 
			",profile_pic = '" + profilePic + '\'' + 
			",fullName = '" + fullName + '\'' + 
			",fullTime = '" + fullTime + '\'' + 
			",deviceToken = '" + deviceToken + '\'' + 
			",duration = '" + duration + '\'' + 
			",partTime = '" + partTime + '\'' + 
			",phone = '" + phone + '\'' + 
			",ready = '" + ready + '\'' + 
			",working = '" + working + '\'' + 
			",location = '" + location + '\'' + 
			",id = '" + id + '\'' + 
			",banned = '" + banned + '\'' + 
			",accepting = '" + accepting + '\'' + 
			"}";
		}
}