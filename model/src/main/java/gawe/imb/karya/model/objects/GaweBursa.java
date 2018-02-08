package gawe.imb.karya.model.objects;

import com.google.firebase.firestore.GeoPoint;


public class GaweBursa {
    private String address = "";
    private String city = "";
    private String country = "";
    private String dateApproved = "";
    private String dateCancelled = "";
    private String dateCompleted = "";
    private String dateCreated = "";
    private String dateExpired = "";
    private String dateRejected = "";
    private String dateUpdated = "";
    private String description = "";
    private String employerId = "";
    private String employerName = "";
    private String employerPhone = "";
    private String employerImage = "";
    private String id = "";
    private Double lat = 0d;
    private Double lng = 0d;
    private String locationNote = "";
    private Long millisApproved = 0L;
    private Long millisCancelled = 0L;
    private Long millisCompleted = 0L;
    private Long millisCreated = 0L;
    private Long millisExpired = 0L;
    private Long millisRejected = 0L;
    private Long millisUpdated = 0L;
    private Boolean negotiable = false;
    private int pushedCount = 0;
    private String region = "";
    private Double salaryMax = 0d;
    private Double salaryMin = 0d;
    private String shortAddress = "";
    private Boolean stApproved = false;
    private Boolean stCancelled = false;
    private Boolean stCompleted = false;
    private Boolean stCreated = false;
    private Boolean stExpired = false;
    private Boolean stRejected = false;
    private Boolean hasBeenReviewed = false;
    private int viewCount = 0;
    private GeoPoint geoPoint;
    private String category = "";

    public GaweBursa() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDateApproved() {
        return dateApproved;
    }

    public void setDateApproved(String dateApproved) {
        this.dateApproved = dateApproved;
    }

    public String getDateCancelled() {
        return dateCancelled;
    }

    public void setDateCancelled(String dateCancelled) {
        this.dateCancelled = dateCancelled;
    }

    public String getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(String dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateExpired() {
        return dateExpired;
    }

    public void setDateExpired(String dateExpired) {
        this.dateExpired = dateExpired;
    }

    public String getDateRejected() {
        return dateRejected;
    }

    public void setDateRejected(String dateRejected) {
        this.dateRejected = dateRejected;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmployerId() {
        return employerId;
    }

    public void setEmployerId(String employerId) {
        this.employerId = employerId;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public String getEmployerPhone() {
        return employerPhone;
    }

    public void setEmployerPhone(String employerPhone) {
        this.employerPhone = employerPhone;
    }

    public String getEmployerImage() {
        return employerImage;
    }

    public void setEmployerImage(String employerImage) {
        this.employerImage = employerImage;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getLocationNote() {
        return locationNote;
    }

    public void setLocationNote(String locationNote) {
        this.locationNote = locationNote;
    }

    public Long getMillisApproved() {
        return millisApproved;
    }

    public void setMillisApproved(Long millisApproved) {
        this.millisApproved = millisApproved;
    }

    public Long getMillisCancelled() {
        return millisCancelled;
    }

    public void setMillisCancelled(Long millisCancelled) {
        this.millisCancelled = millisCancelled;
    }

    public Long getMillisCompleted() {
        return millisCompleted;
    }

    public void setMillisCompleted(Long millisCompleted) {
        this.millisCompleted = millisCompleted;
    }

    public Long getMillisCreated() {
        return millisCreated;
    }

    public void setMillisCreated(Long millisCreated) {
        this.millisCreated = millisCreated;
    }

    public Long getMillisExpired() {
        return millisExpired;
    }

    public void setMillisExpired(Long millisExpired) {
        this.millisExpired = millisExpired;
    }

    public Long getMillisRejected() {
        return millisRejected;
    }

    public void setMillisRejected(Long millisRejected) {
        this.millisRejected = millisRejected;
    }

    public Long getMillisUpdated() {
        return millisUpdated;
    }

    public void setMillisUpdated(Long millisUpdated) {
        this.millisUpdated = millisUpdated;
    }

    public Boolean getNegotiable() {
        return negotiable;
    }

    public void setNegotiable(Boolean negotiable) {
        this.negotiable = negotiable;
    }

    public int getPushedCount() {
        return pushedCount;
    }

    public void setPushedCount(int pushedCount) {
        this.pushedCount = pushedCount;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public double getSalaryMax() {
        return salaryMax;
    }

    public void setSalaryMax(double salaryMax) {
        this.salaryMax = salaryMax;
    }

    public double getSalaryMin() {
        return salaryMin;
    }

    public void setSalaryMin(double salaryMin) {
        this.salaryMin = salaryMin;
    }

    public String getShortAddress() {
        return shortAddress;
    }

    public void setShortAddress(String shortAddress) {
        this.shortAddress = shortAddress;
    }

    public Boolean getStApproved() {
        return stApproved;
    }

    public void setStApproved(Boolean stApproved) {
        this.stApproved = stApproved;
    }

    public Boolean getStCancelled() {
        return stCancelled;
    }

    public void setStCancelled(Boolean stCancelled) {
        this.stCancelled = stCancelled;
    }

    public Boolean getStCompleted() {
        return stCompleted;
    }

    public void setStCompleted(Boolean stCompleted) {
        this.stCompleted = stCompleted;
    }

    public Boolean getStCreated() {
        return stCreated;
    }

    public void setStCreated(Boolean stCreated) {
        this.stCreated = stCreated;
    }

    public Boolean getStExpired() {
        return stExpired;
    }

    public void setStExpired(Boolean stExpired) {
        this.stExpired = stExpired;
    }

    public Boolean getStRejected() {
        return stRejected;
    }

    public void setStRejected(Boolean stRejected) {
        this.stRejected = stRejected;
    }

    public Boolean getHasBeenReviewed() {
        return hasBeenReviewed;
    }

    public void setHasBeenReviewed(Boolean hasBeenReviewed) {
        this.hasBeenReviewed = hasBeenReviewed;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public GeoPoint getGeoPoint() {
        return geoPoint;
    }

    public void setGeoPoint(GeoPoint geoPoint) {
        this.geoPoint = geoPoint;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}