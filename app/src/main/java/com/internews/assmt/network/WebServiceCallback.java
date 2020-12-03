package com.internews.assmt.network;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class WebServiceCallback<T> implements Callback<JSONObject> {

    @Override
    public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
        onDataReturned(response.body(), response.code(), response.errorBody());
    }

    @Override
    public void onFailure(Call<JSONObject> call, Throwable t) {
        onError(t);
    }

    public abstract void onDataReturned(JSONObject model, int resCode, ResponseBody errorBody);

    public abstract void onError(Throwable t);

    public abstract void onNetworkUnavailable();

    public abstract void onProgress();
}
