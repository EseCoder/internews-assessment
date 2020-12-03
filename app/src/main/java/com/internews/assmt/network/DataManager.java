package com.internews.assmt.network;

import android.content.Context;


import com.google.gson.Gson;
import com.internews.assmt.model.SuperHero;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * DataManager class offers a data access layer
 * Created by Ese Udom on 10/7/2020.
 */
public class DataManager {

    private static WebServices services;
    private static Map<String, Call<JSONObject>> callMap;

    public DataManager() {
    }

    //initialization block
    static {
        callMap = new HashMap<>();

        Retrofit retrofit = WebServiceFactory.getFactory();
        services = retrofit.create(WebServices.class);
    }


    /**
     * Fetches SuperHeroes
     *
     * @param context
     *
     * @param callback
     */
    public static void getSups(Context context,
                             WebServiceCallback<JSONObject> callback) {

        if (NetworkConnection.isConnected(context)) {
            Call<JSONObject> call = services.getSups();

            callMap.put(callback.getClass().getSimpleName(), call);

            call.enqueue(callback);

            if (call.isExecuted()) {
                callback.onProgress();
            }
        } else {
            callback.onNetworkUnavailable();
        }
    }

    public static List<SuperHero> persistSups(JSONObject model) {
        try {
            List<SuperHero> list = new ArrayList<>();
            SuperHero[] superHeroes = new Gson().fromJson(model.getJSONArray("characters").toString(), SuperHero[].class);
            for (SuperHero superHero : superHeroes) {
                if (SuperHero.find(SuperHero.class, "name = ?", superHero.getName()).isEmpty())
                    superHero.save();
                list.add(superHero);
            }

            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Utility method to cancel a webservice call
     *
     * @param callback callback of calling class
     */
    public static void cancelRequest(WebServiceCallback<JSONObject> callback) {
        Call<JSONObject> call = callMap.get(callback.getClass().getSimpleName());
        if (call != null)
            call.cancel();
        callMap.remove(callback.getClass().getSimpleName());
    }

    /**
     * Utility method to clear call map
     */
    public static void clearCallMap() {
        callMap.clear();
    }
}
