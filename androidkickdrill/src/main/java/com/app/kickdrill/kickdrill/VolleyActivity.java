package com.app.kickdrill.kickdrill;

import android.os.Bundle;

import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.app.kickdrill.volley.VolleyNetworkManager;

import org.json.JSONObject;

public abstract class VolleyActivity<T> extends KickDrillActivity implements Response.Listener<T>, Response.ErrorListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResponse(T response) {
        setResponse(response);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        if(error instanceof TimeoutError){
            onTimeoutErrorOccured(error);
        }else{
            onNetworkProblemErrorOccured(error);
        }
    }

    public abstract void setResponse(T response);

    public abstract void onNetworkProblemErrorOccured(VolleyError error);

    public abstract void onTimeoutErrorOccured(VolleyError error);

    public boolean makeJsonWebRequest(String url, JSONObject param, int method) {
        if (detector.isConnectingToInternet()) {
            VolleyNetworkManager jsonrequest = new VolleyNetworkManager(this, this, url, param);
            jsonrequest.makeJsonWebRequest(method);
            return true;
        } else {
            return false;
        }
    }

}
