package com.app.kickdrill.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.app.kickdrill.kickdrill.KickDrillActivity;
import com.app.kickdrill.db.MasterPojo;
import com.app.kickdrill.adaptermaker.GetViewMaker;
import com.app.kickdrill.viewholders.MasterViewHolder;

import java.util.ArrayList;

/**
 * Created by vyanki on 25/5/16.
 */
public class KickDrillBaseAdapter<V extends MasterViewHolder, M extends MasterPojo> extends BaseAdapter {

    public ArrayList<?> pojos;
    int layoutid;
    KickDrillActivity context;
    LayoutInflater inflater;
    GetViewMaker<V, M> getviewmaker;


    public KickDrillBaseAdapter(int layoutid, ArrayList<?> pojos, KickDrillActivity context) {
        this.pojos = pojos;
        this.layoutid = layoutid;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (getviewmaker instanceof KickDrillActivity) {
            getviewmaker = (GetViewMaker) context;
        }
    }


    public void addGetViewMaker(GetViewMaker getviewmaker) {
        this.getviewmaker = getviewmaker;
    }


    @Override
    public int getCount() {
        return pojos.size();
    }

    @Override
    public Object getItem(int position) {
        return pojos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

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
                getviewmaker.AtViewNotNull(viewHolder, position, masterpojo);
            }
            getviewmaker.AtInitView(viewHolder, masterpojo, position);
        }
        return convertView;
    }


}
