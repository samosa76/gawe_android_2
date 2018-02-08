package gawe.imb.karya.model.modules;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by korneliussendy on 10/21/17.
 */

public interface FirebaseCloudFunctions {

    @GET("getCustomToken")
    Call<ResponseBody> getCustomToken(@Query("access_token") String accessToken);
}
