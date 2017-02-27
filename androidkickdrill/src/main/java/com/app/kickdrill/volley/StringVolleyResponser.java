package com.app.kickdrill.volley;

import com.android.volley.Response;

/**
 * Created by vyanki on 25/5/16.
 */
public interface StringVolleyResponser extends Response.Listener<String>,Response.ErrorListener {

     void setStringResponse(String response);

}
