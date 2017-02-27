package com.app.kickdrill.kickdrill;

import android.app.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.kickdrill.adapters.KickDrillBaseAdapter;

import com.app.kickdrill.adapters.KickDrillBaseArrayAdapter;
import com.app.kickdrill.adapters.KickDrillRecycleViewAdapter;
import com.app.kickdrill.db.MasterPojo;

import java.util.ArrayList;



/**
 * Created by vyanki on 24/5/16.
 */


public abstract class KickDrillFragment<T> extends Fragment {


    public View v;
    public int layoutid;



    KickDrillActivity activity;







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(layoutid, container, false);
        return v;
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (KickDrillActivity) activity;

    }


    public<V extends RecyclerView.ViewHolder,M extends MasterPojo> KickDrillRecycleViewAdapter<V,M>
    getRecycleViewAdapter(ArrayList<?> pojos, int layoutid){

        return activity.getRecycleViewAdapter(pojos,layoutid);
    }


    /**
     *
     *
     * */
    public KickDrillBaseAdapter getListAdapter(int layoutid, ArrayList<?> pojos) {
        return activity.getListAdapter(layoutid,pojos);
    }

    public KickDrillBaseArrayAdapter getListArrayAdapter(int layoutid, int resourceid, ArrayList<?> pojos) {
       return activity.getListArrayAdapter(layoutid,resourceid,pojos);
    }


    public void callFragmentWithReplaceBackStack(Fragment fillDetail, int containerid, String tag) {

        activity.callFragmentWithReplaceBackStack(fillDetail,containerid,tag);
    }


    public void callFragmentWithAddBackStack(Fragment fillDetail,int containerid,String tag) {

        activity.callFragmentWithAddBackStack(fillDetail,containerid,tag);
    }

    public void callFragmentReplaceWithOutBackStack(Fragment fillDetail,int containerid,String tag) {

        activity.callFragmentReplaceWithOutBackStack(fillDetail,containerid,tag);
    }



    public void callFragmentAddWithOutBackStack(Fragment fillDetail,int containerid,String tag) {
        activity.callFragmentAddWithOutBackStack(fillDetail,containerid,tag);
    }






}
