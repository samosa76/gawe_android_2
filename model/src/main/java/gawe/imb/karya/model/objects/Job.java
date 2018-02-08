package gawe.imb.karya.model.objects;

import android.util.Log;

import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.List;

import gawe.imb.karya.mainlibs.utils.Constants;
import gawe.imb.karya.model.utils.Helper;

/**
 * Created by korneliussendy on 2/7/18.
 */

public class Job {
    private Long jobId = 0l;
    private String id = "";
    private String employerId = "";

    private int genderType = -1;
    //    private String genderTypeStr = "";
//    private int qtyMale = 0;
//    private int qtyFemale = 0;
//    private int qtyAny = 0;
    private int totalWorker = 1;

    //DESCRIPTIONS
    private String description = "";

    //LOCATIONS
    private double lat = 0d;
    private double lng = 0d;
    private String address = "";
    private String note = "";

    //DURATION and TIME
    private int duration = 0;

    private String dateCreated = "";
    private long dateCreatedMillis = 0;

    private String dateAccepted = "";
    private long dateAcceptedMillis = 0;

    private String dateArrived = "";
    private long dateArrivedMillis = 0;

    private String dateStarted = "";
    private long dateStartedMillis = 0;

    private String dateFinished = "";
    private long dateFinishedMillis = 0;

    private String datePaid = "";
    private long datePaidMillis = 0;

    private String dateRated = "";
    private long dateRatedMillis = 0;

    private String dateRatedEmployee = "";
    private long dateRatedEmployeeMillis = 0;

    private String dateRatedEmployer = "";
    private long dateRatedEmployerMillis = 0;

    private String dateCanceled = "";
    private long dateCanceledMillis = 0;

    private String dateCompleted = "";
    private long dateCompletedMillis = 0;

    private String dateReported = "";
    private long dateReportedMillis = 0;

    private double wage = 30000d;
    private double total = 0d;

//    private double pointsNeeded = 0;

    private String employeeId = "";

    private int population = 0;
    private int pushedPopulation = 0;
    private int rejectedCount = 0;

    private boolean stCreated = false;
    private boolean stPopulated = false;
    private boolean stPushed = false;
    private boolean stAccepted = false;
    private boolean stArrived = false;
    private boolean stStarted = false;
    private boolean stFinished = false;
    private boolean stPaid = false;
    private boolean stRatedEmployee = false;
    private boolean stRatedEmployer = false;
    private boolean stRated = false;
    private boolean stCompleted = false;
    private boolean stCompletedEmployee = false;
    private boolean stCompletedEmployer = false;
    private boolean stCanceled = false;
    private boolean stReported = false;

    private double rating = 0d;
    private String ratingCode = "";
    private List<String> ratingList = new ArrayList<>();

    private String msg = "";
    private String msgCancelEmployer = "";
    private String msgCancelEmployee = "";
    private String cancelCode = "";

    private String jobCategory = "";
    private boolean widen = false;
    private String formType = "";
    private boolean test = false;

    public Job() {
    }

    public Job(Job j) {
        this.employerId = j.getEmployerId();
    }

    public Job(Job j, boolean isWiden) {
        this.employerId = j.getEmployerId();
        this.genderType = j.getGenderType();
        this.jobCategory = j.getJobCategory();
        this.description = j.getDescription();
        this.duration = j.getDuration();
        this.lat = j.getLat();
        this.lng = j.getLng();
        this.address = j.getAddress();
        this.note = j.getNote();
        this.wage = j.getWage();
        this.total = j.getTotal();
        this.employerId = j.getEmployerId();
        this.widen = isWiden;
    }

    public String getEmployerId() {
        return employerId;
    }

    public void setEmployerId(String employerId) {
        Log.d("JOB", "Set ER ID");
        this.employerId = employerId;
    }



    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
        Log.d("JOB", "previous JobId = " + id);
//        this.id = String.valueOf(jobId) + Constants.CONNECTOR + employerId;
        if (!Strings.isNullOrEmpty(employerId))
            this.id = Helper.toGawe36(jobId) + Constants.CONNECTOR + employerId;
        Log.d("JOB", "set JobId = " + id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getGenderType() {
        return genderType;
    }

    public void setGenderType(int genderType) {
        this.genderType = genderType;
    }

    //    public String getGenderTypeStr() {
//        return genderTypeStr;
//    }
//
//    public void setGenderTypeStr(String genderTypeStr) {
//        this.genderTypeStr = genderTypeStr;
//    }
//
//    public int getQtyMale() {
//        return qtyMale;
//    }
//
//    public void setQtyMale(int qtyMale) {
//        this.qtyMale = qtyMale;
//    }
//
//    public int getQtyFemale() {
//        return qtyFemale;
//    }
//
//    public void setQtyFemale(int qtyFemale) {
//        this.qtyFemale = qtyFemale;
//    }
//
//    public int getQtyAny() {
//        return qtyAny;
//    }
//
//    public void setQtyAny(int qtyAny) {
//        this.qtyAny = qtyAny;
//    }

    public int getTotalWorker() {
        return totalWorker;
    }

    public void setTotalWorker(int totalWorker) {
        this.totalWorker = totalWorker;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDateReported() {
        return dateReported;
    }

    public void setDateReported(String dateReported) {
        this.dateReported = dateReported;
    }

    public long getDateReportedMillis() {
        return dateReportedMillis;
    }

    public void setDateReportedMillis(long dateReportedMillis) {
        this.dateReportedMillis = dateReportedMillis;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public long getDateCreatedMillis() {
        return dateCreatedMillis;
    }

    public void setDateCreatedMillis(long dateCreatedMillis) {
        this.dateCreatedMillis = dateCreatedMillis;
    }

    public String getDateAccepted() {
        return dateAccepted;
    }

    public void setDateAccepted(String dateAccepted) {
        this.dateAccepted = dateAccepted;
    }

    public long getDateAcceptedMillis() {
        return dateAcceptedMillis;
    }

    public void setDateAcceptedMillis(long dateAcceptedMillis) {
        this.dateAcceptedMillis = dateAcceptedMillis;
    }

    public String getDateArrived() {
        return dateArrived;
    }

    public void setDateArrived(String dateArrived) {
        this.dateArrived = dateArrived;
    }

    public long getDateArrivedMillis() {
        return dateArrivedMillis;
    }

    public void setDateArrivedMillis(long dateArrivedMillis) {
        this.dateArrivedMillis = dateArrivedMillis;
    }

    public String getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(String dateStarted) {
        this.dateStarted = dateStarted;
    }

    public long getDateStartedMillis() {
        return dateStartedMillis;
    }

    public void setDateStartedMillis(long dateStartedMillis) {
        this.dateStartedMillis = dateStartedMillis;
    }

    public String getDateFinished() {
        return dateFinished;
    }

    public void setDateFinished(String dateFinished) {
        this.dateFinished = dateFinished;
    }

    public long getDateFinishedMillis() {
        return dateFinishedMillis;
    }

    public void setDateFinishedMillis(long dateFinishedMillis) {
        this.dateFinishedMillis = dateFinishedMillis;
    }

    public String getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(String datePaid) {
        this.datePaid = datePaid;
    }

    public long getDatePaidMillis() {
        return datePaidMillis;
    }

    public void setDatePaidMillis(long datePaidMillis) {
        this.datePaidMillis = datePaidMillis;
    }

    public String getDateRated() {
        return dateRated;
    }

    public void setDateRated(String dateRated) {
        this.dateRated = dateRated;
    }

    public long getDateRatedMillis() {
        return dateRatedMillis;
    }

    public void setDateRatedMillis(long dateRatedMillis) {
        this.dateRatedMillis = dateRatedMillis;
    }

    public String getDateRatedEmployee() {
        return dateRatedEmployee;
    }

    public void setDateRatedEmployee(String dateRatedEmployee) {
        this.dateRatedEmployee = dateRatedEmployee;
    }

    public long getDateRatedEmployeeMillis() {
        return dateRatedEmployeeMillis;
    }

    public void setDateRatedEmployeeMillis(long dateRatedEmployeeMillis) {
        this.dateRatedEmployeeMillis = dateRatedEmployeeMillis;
    }

    public String getDateRatedEmployer() {
        return dateRatedEmployer;
    }

    public void setDateRatedEmployer(String dateRatedEmployer) {
        this.dateRatedEmployer = dateRatedEmployer;
    }

    public long getDateRatedEmployerMillis() {
        return dateRatedEmployerMillis;
    }

    public void setDateRatedEmployerMillis(long dateRatedEmployerMillis) {
        this.dateRatedEmployerMillis = dateRatedEmployerMillis;
    }

    public boolean isStRatedEmployee() {
        return stRatedEmployee;
    }

    public void setStRatedEmployee(boolean stRatedEmployee) {
        this.stRatedEmployee = stRatedEmployee;
    }

    public boolean isStRatedEmployer() {
        return stRatedEmployer;
    }

    public void setStRatedEmployer(boolean stRatedEmployer) {
        this.stRatedEmployer = stRatedEmployer;
    }

    public String getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(String dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public long getDateCompletedMillis() {
        return dateCompletedMillis;
    }

    public void setDateCompletedMillis(long dateCompletedMillis) {
        this.dateCompletedMillis = dateCompletedMillis;
    }

    public String getDateCanceled() {
        return dateCanceled;
    }

    public void setDateCanceled(String dateCanceled) {
        this.dateCanceled = dateCanceled;
    }

    public long getDateCanceledMillis() {
        return dateCanceledMillis;
    }

    public void setDateCanceledMillis(long dateCanceledMillis) {
        this.dateCanceledMillis = dateCanceledMillis;
    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public boolean isStReported() {
        return stReported;
    }

    public void setStReported(boolean stReported) {
        this.stReported = stReported;
    }

    public boolean isStCreated() {
        return stCreated;
    }

    public void setStCreated(boolean stCreated) {
        this.stCreated = stCreated;
    }

    public boolean isStPopulated() {
        return stPopulated;
    }

    public void setStPopulated(boolean stPopulated) {
        this.stPopulated = stPopulated;
    }

    public boolean isStPushed() {
        return stPushed;
    }

    public void setStPushed(boolean stPushed) {
        this.stPushed = stPushed;
    }

    public boolean isStAccepted() {
        return stAccepted;
    }

    public void setStAccepted(boolean stAccepted) {
        this.stAccepted = stAccepted;
    }

    public boolean isStArrived() {
        return stArrived;
    }

    public void setStArrived(boolean stArrived) {
        this.stArrived = stArrived;
    }

    public boolean isStStarted() {
        return stStarted;
    }

    public void setStStarted(boolean stStarted) {
        this.stStarted = stStarted;
    }

    public boolean isStFinished() {
        return stFinished;
    }

    public void setStFinished(boolean stFinished) {
        this.stFinished = stFinished;
    }

    public boolean isStPaid() {
        return stPaid;
    }

    public void setStPaid(boolean stPaid) {
        this.stPaid = stPaid;
    }

    public boolean isStRated() {
        return stRated;
    }

    public void setStRated(boolean stRated) {
        this.stRated = stRated;
    }

    public boolean isStCompleted() {
        return stCompleted;
    }

    public void setStCompleted(boolean stCompleted) {
        this.stCompleted = stCompleted;
    }

    public boolean isStCompletedEmployee() {
        return stCompletedEmployee;
    }

    public void setStCompletedEmployee(boolean stCompletedEmployee) {
        this.stCompletedEmployee = stCompletedEmployee;
    }

    public boolean isStCompletedEmployer() {
        return stCompletedEmployer;
    }

    public void setStCompletedEmployer(boolean stCompletedEmployer) {
        this.stCompletedEmployer = stCompletedEmployer;
    }

    public boolean isStCanceled() {
        return stCanceled;
    }

    public void setStCanceled(boolean stCanceled) {
        this.stCanceled = stCanceled;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

//    public double getPointsNeeded() {
//        return pointsNeeded;
//    }
//
//    public void setPointsNeeded(double pointsNeeded) {
//        this.pointsNeeded = pointsNeeded;
//    }

    public int getPushedPopulation() {
        return pushedPopulation;
    }

    public void setPushedPopulation(int pushedPopulation) {
        this.pushedPopulation = pushedPopulation;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsgCancelEmployer() {
        return msgCancelEmployer;
    }

    public void setMsgCancelEmployer(String msgCancelEmployer) {
        this.msgCancelEmployer = msgCancelEmployer;
    }

    public String getMsgCancelEmployee() {
        return msgCancelEmployee;
    }

    public void setMsgCancelEmployee(String msgCancelEmployee) {
        this.msgCancelEmployee = msgCancelEmployee;
    }

    public int getRejectedCount() {
        return rejectedCount;
    }

    public void setRejectedCount(int rejectedCount) {
        this.rejectedCount = rejectedCount;
    }

    public String getRatingCode() {
        return ratingCode;
    }

    public void setRatingCode(String ratingCode) {
        this.ratingCode = ratingCode;
    }

    public List<String> getRatingList() {
        return ratingList;
    }

    public void setRatingList(List<String> ratingList) {
        this.ratingList = ratingList;
    }

    public String getCancelCode() {
        return cancelCode;
    }

    public void setCancelCode(String cancelCode) {
        this.cancelCode = cancelCode;
    }

    public String getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(String jobCategory) {
        this.jobCategory = jobCategory;
    }

    public boolean isWiden() {
        return widen;
    }

    public void setWiden(boolean widen) {
        this.widen = widen;
    }

    public boolean isTest() {
        return test;
    }

    public void setTest(boolean test) {
        this.test = test;
    }

    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Job))
            return false;
        return this.id.equals(((Job) obj).getId());
//        return super.equals(obj);
    }
}
