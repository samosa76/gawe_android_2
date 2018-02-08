package gawe.imb.karya.model.modules;

import android.content.res.Resources;
import android.util.Log;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.Random;

import gawe.imb.karya.model.BuildConfig;
import gawe.imb.karya.model.utils.Helper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by korneliussendy on 1/24/18.
 */

public class GaweWebserviceClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_GAWE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(createOkHttpClient())
                    .build();
        }
        return retrofit;
    }

    private static OkHttpClient createOkHttpClient() {
        final OkHttpClient.Builder httpClient =
                new OkHttpClient.Builder();
        httpClient.addInterceptor(chain -> {
            final Request original = chain.request();
            final HttpUrl originalHttpUrl = original.url();
            final HttpUrl url = originalHttpUrl.newBuilder()
                    .addQueryParameter("lang", Resources.getSystem().getConfiguration().locale.getLanguage())
                    .build();

            // Request customization: add request headers

            final Request.Builder requestBuilder = original
                    .newBuilder()
                    .url(url)
                    .addHeader("token", generateToken())
                    .addHeader("lang", Resources.getSystem().getConfiguration().locale.getLanguage());

            if (BuildConfig.DEBUG) {
                Log.d("generatedToken", generateToken());
                Log.d("url", "" + url.toString());
            }
            final Request request = requestBuilder.build();
            return chain.proceed(request);
        });

        //LOGGING

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
            httpClient.addInterceptor(loggingInterceptor);
        }
        return httpClient.build();
    }

    private static final String DATE_YMD = "yyyy-MM-dd";

    private static String generateToken() {
        String[] arrName = {"acel", "jhomponk", "budi", "cikun", "abui"};
        int randIndex = new Random().nextInt(5);
        String date = DateTime.now().toDateTime(DateTimeZone.UTC).toString(DATE_YMD);
        Log.d("tokenDate", date);
        String token = arrName[randIndex] + date;
        Log.d("before", token);
        token = Helper.encryptMd5(token);
//        token = Helper.sha256(token);
        return token;
    }
}

