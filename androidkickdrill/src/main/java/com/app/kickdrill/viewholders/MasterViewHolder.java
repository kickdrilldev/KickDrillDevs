package com.app.kickdrill.viewholders;

import android.app.Activity;
import android.content.ContextWrapper;
import android.view.View;

import com.app.kickdrill.kickdrill.KickDrillActivity;

/**
 * Created by vyanki on 25/5/16.
 */
public class MasterViewHolder {

    public View view;
    public KickDrillActivity frameworkactivity;

    public MasterViewHolder(View view){
        this.view = view;
        if (view.getContext() instanceof Activity) {
            frameworkactivity = (KickDrillActivity) view.getContext();
        }else {
            frameworkactivity = (KickDrillActivity) ((ContextWrapper) view.getContext())
                    .getBaseContext();
        }
    }
}
