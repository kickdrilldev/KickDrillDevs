package com.app.kickdrill.adaptermaker;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.kickdrill.db.MasterPojo;

/**
 * Created by venki on 16/8/16.
 */
public interface RecycleViewMaker<V,M extends MasterPojo>
{

    <V extends RecyclerView.ViewHolder> V onCreateViewHolder(int parent, View view);

    void onBindViewHolder(V holder, M pojo, int position);

    int getItemViewType(int position);
}
