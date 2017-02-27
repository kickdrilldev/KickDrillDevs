package com.app.kickdrill.kickdrill;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.app.kickdrill.volley.VolleyNetworkManager;

import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class VolleyFragment<T> extends KickDrillFragment implements Response.Listener<T>, Response.ErrorListener {


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




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       return  super.onCreateView(inflater,container,savedInstanceState);
    }

    public boolean makeJsonWebRequest(String url, JSONObject param, int method) {
        if (activity.detector.isConnectingToInternet()) {
            VolleyNetworkManager jsonrequest = new VolleyNetworkManager(this,this, url, param);
            jsonrequest.makeJsonWebRequest(method);
            return true;
        } else {
            return false;
        }
    }

}
