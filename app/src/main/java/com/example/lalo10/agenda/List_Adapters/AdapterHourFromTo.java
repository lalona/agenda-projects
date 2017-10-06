package com.example.lalo10.agenda.List_Adapters;

import android.app.Activity;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lalo10.agenda.NewProyect.DayId;
import com.example.lalo10.agenda.NewProyect.Dialogs.DialogTimePicker;
import com.example.lalo10.agenda.NewProyect.NoTimeException;
import com.example.lalo10.agenda.R;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Created by lalo10 on 10/6/17.
 */

public class AdapterHourFromTo extends ArrayAdapter<DayId> {

    Activity activity;
    Fragment fragment;
    List<DayId> listDays;

    public AdapterHourFromTo(Fragment fragment,Activity activity, int resoruce, List<DayId> listP) {
        super(activity, resoruce, listP);
        this.activity = activity;
        this.fragment = fragment;
        this.listDays = listP;
    }

    private class ViewHolderFromTo {
        TextView txtDay;
        EditText fromTime;
        EditText toTime;
    }

    public void updateList(List<DayId> listP) {
        this.listDays = listP;
        this.notifyDataSetChanged();

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolderFromTo holder;
        LayoutInflater inflater = activity.getLayoutInflater();
        final DayId dayHour = listDays.get(position);
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.item_day_hour, null);
            holder = new ViewHolderFromTo();
            holder.txtDay = (TextView) convertView.findViewById(R.id.txtDay);
            holder.fromTime = (EditText) convertView.findViewById(R.id.timeFrom);
            holder.toTime = (EditText) convertView.findViewById(R.id.timeTo);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolderFromTo) convertView.getTag();
        }
        holder.txtDay.setText(dayHour.nombre);
        holder.toTime.setClickable(true);
        holder.fromTime.setClickable(true);
        holder.fromTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogTimePicker newFragment = new DialogTimePicker();
                newFragment.setOptions(dayHour,DayId.TYPE_HOUR.FROM,holder.fromTime);
                newFragment.show(fragment.getFragmentManager(), "timePicker");
            }
        });
        holder.toTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Calendar fromTime = dayHour.getFromTime();
                    DialogTimePicker newFragment = new DialogTimePicker();
                    newFragment.setOptions(dayHour,DayId.TYPE_HOUR.TO,holder.toTime);
                    newFragment.setMinTime(fromTime);
                    newFragment.show(fragment.getFragmentManager(), "timePicker");
                } catch (NoTimeException e) {
                    // Mostras dialogo de que debe escoger primero la hora de inicio
                    holder.toTime.setHint(activity.getResources().getString(R.string.first_choose_from_hour));
                }
            }
        });

        try {
            String to = dayHour.getToTimeString();
            String from = dayHour.getFromTimeString();
            holder.toTime.setText(to);
            holder.fromTime.setText(from);
        } catch (NoTimeException e) {
            e.printStackTrace();
            holder.toTime.setHint(activity.getResources().getString(R.string.to));
            holder.fromTime.setHint(activity.getResources().getString(R.string.from));
            holder.toTime.setText("");
            holder.fromTime.setText("");
        }
        return convertView;

    }




}
