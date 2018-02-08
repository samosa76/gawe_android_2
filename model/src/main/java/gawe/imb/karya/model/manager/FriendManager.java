package gawe.imb.karya.model.manager;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

/**
 * Created by korneliussendy on 2/8/18,
 * at 11:12 PM.
 * Gawe
 */

public class FriendManager {

    public static Single<List<Object>> getFriend() {
        return Single.just(new ArrayList<>());
    }

}
