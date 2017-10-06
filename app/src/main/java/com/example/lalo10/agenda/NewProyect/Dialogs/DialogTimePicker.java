package com.example.lalo10.agenda.NewProyect.Dialogs;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.lalo10.agenda.NewProyect.DayId;
import com.example.lalo10.agenda.NewProyect.NoTimeException;

import java.util.Calendar;

/**
 * Created by lalo10 on 10/6/17.
 */

public class DialogTimePicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener {


    DayId day;
    DayId.TYPE_HOUR typeHour;
    private EditText changeText;
    private Calendar minTime;


    public void setOptions(DayId d, DayId.TYPE_HOUR typeHour,EditText changeText) {
        this.day = d;
        this.typeHour = typeHour;
        this.changeText = changeText;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        try {
            Calendar currentTime = this.day.getTime(typeHour);
            hour = currentTime.get(Calendar.HOUR_OF_DAY);
            minute = currentTime.get(Calendar.MINUTE);

        } catch (NoTimeException e) {
            e.printStackTrace();
            if(minTime != null) {
                hour = minTime.get(Calendar.HOUR_OF_DAY);
                minute = minTime.get(Calendar.MINUTE);
            }
        }

        TimePickerDialogMinMax time = new TimePickerDialogMinMax(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
        if(minTime != null) {
            int minHour = minTime.get(Calendar.HOUR_OF_DAY);
            int minMinute = minTime.get(Calendar.MINUTE);
            time.setMin(minHour,minMinute);
        }


        // Create a new instance of TimePickerDialog and return it
        return time;
    }


    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minutes) {
        // Do something with the time picked by user
        this.day.timePicked(typeHour,hour,minutes,changeText);
    }

    public void setMinTime(Calendar minTime) {
        this.minTime = minTime;
    }
}
