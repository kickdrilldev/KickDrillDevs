package com.app.kickdrill.adaptermaker;

import android.view.View;

import com.app.kickdrill.db.MasterPojo;
import com.app.kickdrill.viewholders.MasterViewHolder;

/**
 * Created by vyanki on 25/5/16.
 */
public interface GetViewMaker<V extends MasterViewHolder,M extends MasterPojo>
{
    V AtViewNull(View v);

    void AtViewNotNull(V viewholder, int position, M masterpojo);

    void AtInitView(V viewholder, M masterpojo, int position);
}
