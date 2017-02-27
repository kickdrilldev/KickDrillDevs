package com.app.kickdrill.kickdrill;


import android.os.Bundle;
import android.util.Log;


import com.app.kickdrill.db.MasterPojo;

import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class RetrofitActivity<R extends MasterPojo> extends KickDrillActivity implements Callback<R> {

    /**
     * base_url is main server domain url where actual server app is hosted;
     */
    protected String base_url;

    /**
     * it's used create web-client for typical webservice.
     */
    protected Retrofit retrofit;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }


    /**
     * it's method used for calling Web Services using Retrofit api with Taking Response.
     */
    protected boolean callRetrofitWebService() {
        if (detector.isConnectingToInternet()) {
            Call<R> call = callWebService();
            call.enqueue(this);
            return true;
        } else {
            atDeviceOffline();
            return false;
        }
    }

    /**
     * it's method is for when device is at offline or state.
     */
    protected abstract void atDeviceOffline();

    /**
     * it's method used calling webseevices and return webservice call object;
     *
     * @return
     */
    public abstract Call<R> callWebService();

    @Override
    public void onResponse(Call<R> call, Response<R> response) {
        Log.e("request url : ", call.request().url().toString());
        setResponse(call, response);
    }

    /**
     * it's method used for to set the response of any web service
     *
     * @param call
     * @param response
     */
    protected abstract void setResponse(Call<R> call, Response<R> response);


    @Override
    public void onFailure(Call<R> call, Throwable t) {
        Log.e("request url : ", call.request().url().toString());
        if (t instanceof SocketTimeoutException) {
            onTimeout(call, t);
        } else {
            onNetworkError(call, t);
        }
    }

    /**
     * it's method is used for when any webservice fail at any erro occured , except time out of web service
     *
     * @param call
     * @param t
     */

    protected abstract void onNetworkError(Call<R> call, Throwable t);

    /**
     * it's method is used for when any webservice is exceeds particular time
     *
     * @param call
     * @param t
     */
    protected abstract void onTimeout(Call<R> call, Throwable t);
}
