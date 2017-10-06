package com.example.lalo10.agenda.NewProyect.Dialogs;

import android.widget.EditText;

import com.example.lalo10.agenda.NewProyect.DayId;

/**
 * Created by lalo10 on 10/6/17.
 */

public interface ResponseFromDialogPicker {
    void timePicked(DayId.TYPE_HOUR typeHour, int hour, int minutes, EditText changeText);
}
