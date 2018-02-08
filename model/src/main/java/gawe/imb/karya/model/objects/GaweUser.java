package gawe.imb.karya.model.objects;

/**
 * Created by korneliussendy on 11/14/17.
 */

public class GaweUser extends User{

    protected Long totalJobDone = 0l;
    protected Long totalDuration = 0l;
    protected Long totalRating = 0l;

    public GaweUser() {
    }

    public Long getTotalJobDone() {
        return totalJobDone;
    }

    public void setTotalJobDone(Long totalJobDone) {
        this.totalJobDone = totalJobDone;
    }

    public Long getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(Long totalDuration) {
        this.totalDuration = totalDuration;
    }

    public Long getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(Long totalRating) {
        this.totalRating = totalRating;
    }
}
