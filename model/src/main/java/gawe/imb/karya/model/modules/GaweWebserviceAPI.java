package gawe.imb.karya.model.modules;

import gawe.imb.karya.model.objects.GaweBrowse;
import gawe.imb.karya.model.objects.gaweResponse.SingleResponse;
import io.reactivex.Single;
import gawe.imb.karya.model.objects.gaweResponse.PaginationResponse;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by korneliussendy on 1/24/18.
 */

public interface GaweWebserviceAPI {

    @FormUrlEncoded
    @POST("user/updateRole")
    Single<ResponseBody> updateRole(@Field("id") String id, @Field("role") String role);

    @FormUrlEncoded
    @POST("user/updateLocation")
    Single<ResponseBody> updateLocation(
            @Field("id") String id,
            @Field("lat") Double lat,
            @Field("lng") Double lng,
            @Field("deviceToken") String deviceToken
    );

    @FormUrlEncoded
    @POST("er/browse/home")
    Single<PaginationResponse<GaweBrowse>> loadBrowseHome(
            @Field("page") int page,
            @Field("limit") int limit,
            @Field("lat") Double lat,
            @Field("lng") Double lng,
            @Field("gender") String gender,
            @Field("category") String category,
            @Field("jarakMax") int distance,
            @Field("fullTime") String yesNoFullTime,
            @Field("partTime") String yesNoPartTime,
            @Field("sortBy") String sortBy
    );

    @FormUrlEncoded
    @POST("er/createJobHarian")
    Single<SingleResponse<String>> createJob(
            @Field("erId") String employerId,
            @Field("lat") Double latitude,
            @Field("lng") Double longitude,
            @Field("category") String category,
            @Field("duration") int duration,
            @Field("description") String description,
            @Field("note") String note,
            @Field("wage") Double wage,
            @Field("address") String address,
            @Field("lang") String lang,
            @Field("gender") String gender
    );
}
