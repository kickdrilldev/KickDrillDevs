package com.app.kickdrill.volley;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.app.kickdrill.kickdrill.VolleyApplication;


import org.json.JSONObject;

/**
 * Created by vyanki on 25/5/16.
 */
public class VolleyNetworkManager {

    final JSONObject param;
    Response.Listener<?> responser;
    Response.ErrorListener errorlistener;
    final String url;


    public VolleyNetworkManager(Response.Listener<?> responser, Response.ErrorListener errorlistener, String url, JSONObject param) {
        this.responser = responser;
        this.errorlistener = errorlistener;
        this.url = url;
        this.param = param;
    }


    public void makeJsonWebRequest(int method) {
        JsonObjectRequest jsonwebservice =
                new JsonObjectRequest(method, url, null, (Response.Listener<JSONObject>) responser, errorlistener);
        jsonwebservice.setRetryPolicy(new DefaultRetryPolicy(500 * 1000, 200, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        addToRequestQueue(jsonwebservice);
    }

    private <T> void addToRequestQueue(Request<T> webservice) {
        ((VolleyApplication) VolleyApplication.getInstance()).addToRequestQueue(webservice);
    }

    public void makeStringWebRequest(int method) {
        StringRequest stringwebservice = new StringRequest(method, url, (Response.Listener<String>) responser, errorlistener);
        stringwebservice.setRetryPolicy(new DefaultRetryPolicy(500 * 1000, 200, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        addToRequestQueue(stringwebservice);
    }
}
