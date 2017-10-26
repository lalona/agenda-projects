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
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.lalo10.agenda.Helpers.SvgHelper;
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
    private DayId dayHoursCopied;

    public AdapterHourFromTo(Fragment fragment,Activity activity, int resoruce, List<DayId> listP) {
        super(activity, resoruce, listP);
        this.activity = activity;
        this.fragment = fragment;
        this.listDays = listP;
        dayHoursCopied = null;
    }

    private class ViewHolderFromTo {
        TextView txtDay;
        EditText fromTime;
        EditText toTime;
        ImageButton btnCopyOrPaste;
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
            holder.btnCopyOrPaste = convertView.findViewById(R.id.btnCopyPaste);
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
        holder.btnCopyOrPaste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dayHoursCopied == null)
                    dayHoursCopied = dayHour;
                else {
                    if(dayHoursCopied == dayHour) {
                        dayHoursCopied = null;
                    } else {
                        dayHour.setFromHour(dayHoursCopied.getFromHour());
                        dayHour.setToHour(dayHoursCopied.getToHour());
                    }
                }
                AdapterHourFromTo.this.notifyDataSetChanged();
            }
        });

        if(dayHoursCopied != null) {
            if(dayHoursCopied == dayHour) {
                SvgHelper.setIcon(activity,R.drawable.ic_cancel,holder.btnCopyOrPaste);
            } else {
                SvgHelper.setIcon(activity,R.drawable.ic_paste,holder.btnCopyOrPaste);
            }
        } else {
            SvgHelper.setIcon(activity,R.drawable.ic_copy,holder.btnCopyOrPaste);
        }

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
