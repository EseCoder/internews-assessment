package com.internews.assmt.network;

import com.internews.assmt.util.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Retrofit factory class for building the Retrofit object
 * Created by Ese Udom on 10/7/2020.
 */
public class WebServiceFactory {

    private static Retrofit retrofit = null;

    private WebServiceFactory() {
        //class shouldn't be initialized
    }

    public static Retrofit getFactory() {
        if (retrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(0, TimeUnit.MINUTES)
                    .readTimeout(0, TimeUnit.SECONDS)
                    .writeTimeout(0, TimeUnit.SECONDS)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(JSONConverterFactory.create()) /*GsonConverterFactory.create()*/
                    .build();
        }
        return retrofit;
    }
}
