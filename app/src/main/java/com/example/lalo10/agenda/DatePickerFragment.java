package com.example.lalo10.agenda;

/**
 * Created by andel on 8/21/17.
 */

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;


public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    private Calendar calendar;
    private Calendar maxCalendar;
    private Calendar minCalendar;
    private DatePickerDialog.OnDateSetListener onDateSetListener;

    private static final String TAG = DatePickerFragment.class.getSimpleName();

    public void setup(Calendar calendar, DatePickerDialog.OnDateSetListener onDateSetListener){
        this.calendar = calendar;
        this.onDateSetListener = onDateSetListener;
        this.minCalendar = calendar;
    }

    public void setup(Calendar calendar, Calendar minCalendar, DatePickerDialog.OnDateSetListener onDateSetListener){
        this.calendar = calendar;
        this.minCalendar = minCalendar;
        this.onDateSetListener = onDateSetListener;
    }

    public void setup(Calendar calendar, Calendar maxCalendar, Calendar minCalendar){
        this.calendar = calendar;
        this.maxCalendar = maxCalendar;
        this.minCalendar = minCalendar;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(getActivity(),android.R.style.Theme_Holo_Light_Dialog_MinWidth, this, year, month, day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        if(minCalendar!=null){
            dialog.getDatePicker().setMinDate(minCalendar.getTimeInMillis());
        }

        if(maxCalendar != null){
            dialog.getDatePicker().setMaxDate(maxCalendar.getTimeInMillis());
        }

        return dialog;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        if(onDateSetListener != null){
            onDateSetListener.onDateSet(view, year, month, day);
        }else{
            //LogUtils.w(TAG, "onDateSetListener callback is not initialized.");
        }
    }
}