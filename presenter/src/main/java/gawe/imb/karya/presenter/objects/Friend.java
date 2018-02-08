package gawe.imb.karya.presenter.objects;

/**
 * Created by korneliussendy on 2/9/18,
 * at 12:28 AM.
 * Gawe
 */

public class Friend {

    private String name;
    private String userId;
    private String imageUrl;

    public Friend(String name, String userId, String imageUrl) {
        this.name = name;
        this.userId = userId;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
