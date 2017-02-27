package com.app.kickdrill.kickdrill;

import android.os.Bundle;



import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vyankatesh.jadhav on 2/6/2017.
 */

public abstract class RetrofitFragment<R> extends KickDrillFragment implements Callback<R> {
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
        retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }


    /**
     * it's method used for calling Web Services using Retrofit api with Taking Response.
     */
    protected void callRetrofitServices() {
        if (activity.detector.isConnectingToInternet()) {
            Call<R> call = callWebService();
            call.enqueue(this);
        } else {
            atDeviceOffline();
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
