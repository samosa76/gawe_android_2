package gawe.imb.karya.presenter.objects;

/**
 * Created by korneliussendy on 2/9/18,
 * at 1:06 AM.
 * Gawe
 */

public class Review {
    private String name;
    private String userImage;
    private String content;
    private Double rate;
    private Long rateDateTime;

    public Review(String name, String userImage, String content, Double rate, Long rateDateTime) {
        this.name = name;
        this.userImage = userImage;
        this.content = content;
        this.rate = rate;
        this.rateDateTime = rateDateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Long getRateDateTime() {
        return rateDateTime;
    }

    public void setRateDateTime(Long rateDateTime) {
        this.rateDateTime = rateDateTime;
    }
}
