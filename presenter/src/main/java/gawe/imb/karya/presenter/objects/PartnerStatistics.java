package gawe.imb.karya.presenter.objects;

/**
 * Created by korneliussendy on 2/9/18,
 * at 12:54 AM.
 * Gawe
 */

public class PartnerStatistics {
    private String title;
    private Boolean showStar;
    private Double value;

    public PartnerStatistics(String title, Boolean showStar, Double value) {
        this.title = title;
        this.showStar = showStar;
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
