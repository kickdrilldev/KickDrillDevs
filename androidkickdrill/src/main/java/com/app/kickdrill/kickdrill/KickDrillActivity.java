package com.app.kickdrill.kickdrill;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;


import com.app.kickdrill.adapters.KickDrillBaseAdapter;

import com.app.kickdrill.adapters.KickDrillBaseArrayAdapter;
import com.app.kickdrill.adapters.KickDrillRecycleViewAdapter;
import com.app.kickdrill.db.MasterPojo;
import com.app.kickdrill.networkandconnectionmanager.ConnectionDetector;
import com.app.kickdrill.viewholders.MasterViewHolder;

import java.util.ArrayList;


/**
 * Created by vyanki on 24/5/16.
 */


public abstract class KickDrillActivity extends   AppCompatActivity{


    public ConnectionDetector detector;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detector = new ConnectionDetector(this);
    }


    @Override
    protected void onResume() {
        super.onResume();

    }



    public<V extends RecyclerView.ViewHolder,M extends MasterPojo> KickDrillRecycleViewAdapter<V,M>
        getRecycleViewAdapter(ArrayList<?> pojos, int layoutid){
        KickDrillRecycleViewAdapter<V,M> frameRecycleViewAdapter = new KickDrillRecycleViewAdapter<>(this,pojos,layoutid);
        return frameRecycleViewAdapter;
    }


    /**
     *
     *
     * */
    public<V extends MasterViewHolder,M extends MasterPojo> KickDrillBaseAdapter<V,M> getListAdapter(int layoutid, ArrayList<?> pojos) {
        KickDrillBaseAdapter<V,M> adapter = new KickDrillBaseAdapter<>(layoutid, pojos, this);
        return adapter;
    }

    public KickDrillBaseArrayAdapter getListArrayAdapter(int layoutid, int resourceid, ArrayList<?> pojos) {
        KickDrillBaseArrayAdapter adapter = new KickDrillBaseArrayAdapter(layoutid,resourceid,pojos,this);
        return adapter;
    }


    public void callFragmentWithReplaceBackStack(Fragment fillDetail, int containerid, String tag) {

        getSupportFragmentManager().beginTransaction()
                .replace(containerid, fillDetail, tag)
                .addToBackStack(tag)
                .commit();
    }


    public void callFragmentWithAddBackStack(Fragment fillDetail,int containerid,String tag) {

        getSupportFragmentManager().beginTransaction().
                add(containerid, fillDetail,tag)
                .addToBackStack(fillDetail.getClass().getName())
                .commit();
    }

    public void callFragmentReplaceWithOutBackStack(Fragment fillDetail,int containerid,String tag) {

        getSupportFragmentManager().beginTransaction()
                .replace(containerid, fillDetail, tag)
                .addToBackStack(tag)
                .commit();
    }


    public void callFragmentAddWithOutBackStack(Fragment fillDetail,int containerid,String tag) {

        getSupportFragmentManager().beginTransaction().
                add(containerid, fillDetail,tag)
                .addToBackStack(fillDetail.getClass().getName())
                .commit();
    }




}
