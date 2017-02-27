package com.app.kickdrill.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.kickdrill.kickdrill.KickDrillActivity;

/**
 * Created by venki on 16/8/16.
 */
public class MasterRecycleViewHolder extends RecyclerView.ViewHolder {

    View view;
    KickDrillActivity frameworkactivity;


    public MasterRecycleViewHolder(View itemView) {
        super(itemView);
        this.view = itemView;
        frameworkactivity = (KickDrillActivity) itemView.getContext();
    }


}
