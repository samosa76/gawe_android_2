package gawe.imb.karya.model.objects;

/**
 * Created by korneliussendy on 1/29/18.
 */

public class BrowseFilter {

    private String category = "";
    private int distance = 5;
    private String type = "ALL";
    private String gender = "M";//or "F"
    private String sortBy = "nearby";//or "rating"
    private Boolean fullTime = true;
    private Boolean partTime = true;


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public Boolean getFullTime() {
        return fullTime;
    }

    public void setFullTime(Boolean fullTime) {
        this.fullTime = fullTime;
    }

    public Boolean getPartTime() {
        return partTime;
    }

    public void setPartTime(Boolean partTime) {
        this.partTime = partTime;
    }

    @Override
    public String toString() {
        return "BrowseFilter{" +
                "category='" + category + '\'' +
                ", distance=" + distance +
                ", type='" + type + '\'' +
                ", gender='" + gender + '\'' +
                ", sortBy='" + sortBy + '\'' +
                ", fullTime=" + fullTime +
                ", partTime=" + partTime +
                '}';
    }
}
