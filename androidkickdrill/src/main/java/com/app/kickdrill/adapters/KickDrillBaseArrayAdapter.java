package com.app.kickdrill.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.app.kickdrill.adaptermaker.GetViewMaker;
import com.app.kickdrill.kickdrill.KickDrillActivity;
import com.app.kickdrill.db.MasterPojo;
import com.app.kickdrill.viewholders.MasterViewHolder;

import java.util.ArrayList;

/**
 * Created by venki on 9/7/16.
 */
public class KickDrillBaseArrayAdapter<T,V extends MasterViewHolder,M extends MasterPojo> extends ArrayAdapter<T> {

    private LayoutInflater inflater;
    private int layoutid,resourceid;
    private ArrayList<?> pojos;

    public GetViewMaker getGetViewMaker() {
        return getviewmaker;
    }


    public KickDrillBaseArrayAdapter(int layoutid,int resourceid,ArrayList<?> pojos,KickDrillActivity kickDrillActivity)
    {
        super(kickDrillActivity,resourceid);
        this.resourceid = resourceid;
        this.layoutid = layoutid;
        this.pojos = pojos;
        inflater = (LayoutInflater) kickDrillActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public int getCount() {
        return pojos.size();
    }

    @Override
    public T getItem(int position) {
        return (T) pojos.get(position);
    }

    public void setGetViewMaker(GetViewMaker getviewmaker)
    {
        this.getviewmaker = getviewmaker;

    }

    GetViewMaker<V,M> getviewmaker;



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        V viewHolder = null;
        M masterpojo = (M) pojos.get(position);
        if (getviewmaker == null) {
            try {
                throw new Exception("Must provide AddViewMaker Interface");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if (convertView == null) {
                convertView = inflater.inflate(layoutid, parent, false);
                viewHolder = getviewmaker.AtViewNull(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (V) convertView.getTag();
                getviewmaker.AtViewNotNull(viewHolder, position,masterpojo);
            }
            getviewmaker.AtInitView(viewHolder, masterpojo, position);
        }
        return convertView;
    }


}
