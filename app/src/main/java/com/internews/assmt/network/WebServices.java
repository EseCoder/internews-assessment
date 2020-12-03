package com.internews.assmt.network;


import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Web service interface class. Contains all web service calls
 * Created by Ese Udom on 10/7/2020.
 */
public interface WebServices {

    @GET("AniketSK/d4d9e03d5d2fdfb83199dbb2605e8cf6/raw/49983e4225cf2f53ab9d29e3a3b6ed35805518c8/SampleResponse.json")
    Call<JSONObject> getSups();

}
