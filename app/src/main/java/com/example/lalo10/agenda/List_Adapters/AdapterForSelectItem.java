package com.example.lalo10.agenda.List_Adapters;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.lalo10.agenda.DayId;
import com.example.lalo10.agenda.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by sebastian on 12/1/16.
 */
public class AdapterForSelectItem extends ArrayAdapter<DayId> {

    /*Esto es nuevo*/private List<String> coloredItems;
    Activity activity;
    final static String TAG = AdapterForSelectItem.class.getSimpleName();

    public AdapterForSelectItem(Activity activity, int resoruce, List<DayId> listP) {
        super(activity, resoruce, listP);
        this.activity = activity;
    }

    public AdapterForSelectItem(Activity activity, int resoruce, DayId[] arrayP) {

        super(activity, resoruce, Arrays.asList(arrayP));
        this.activity = activity;
    }

    public void setColoredItems (/*Esto es nuevo*/List<String> p) {
        this.coloredItems = p;
    }

    public List<String> getColoredItems() {
        return coloredItems;
    }

    public void setAllAsMark () {  }

    public void clearColoredItems () {this.coloredItems.clear();}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);
        if(coloredItems == null)
            return v;
        String currentName = getItem(position).nombre;
        Log.d(TAG,"Current name: " + currentName);
        if (coloredItems.contains(currentName)) {
            v.setBackgroundColor(activity.getResources().getColor(R.color.list_selected));
        } else {
            v.setBackgroundColor(activity.getResources().getColor(R.color.list_normal)); //or whatever was original
        }

        return v;
    }



}
