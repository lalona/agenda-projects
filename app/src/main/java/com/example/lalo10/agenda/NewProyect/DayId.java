package com.example.lalo10.agenda.NewProyect;

import android.content.Context;
import android.widget.EditText;

import com.example.lalo10.agenda.NewProyect.Dialogs.ResponseFromDialogPicker;
import com.example.lalo10.agenda.R;

import java.util.Calendar;
import java.util.Comparator;

/**
 * Created by lalo10 on 10/5/17.
 */

public class DayId implements ResponseFromDialogPicker,Comparator<DayId> {
    public String nombre;
    // Algo para guardar a que hora piensa trabajar
    Calendar fromHour;
    Calendar toHour;
    public int id;

    @Override
    public void timePicked(TYPE_HOUR typeHour, int hour, int minutes,EditText changeText) {
        switch (typeHour) {
            case FROM:
                setFromTime(hour,minutes,changeText);
                break;
            case TO:
                setToTime(hour,minutes,changeText);
                break;
        }
    }

    public DayId() {}

    @Override
    public int compare(DayId dayId, DayId t1) {
        if(dayId.id > t1.id)
            return 1;
        else if (dayId.id < t1.id)
            return -1;
        else
            return 0;

    }

    public enum TYPE_HOUR {
        FROM,
        TO
    }

    public DayId(String nombre,int id) {
        this.nombre = nombre;
        this.id = id;
    }

    public static DayId[] getArrayDays(Context context) {
        // 7 porque los dias de la semana nunca van a cambiar y si cambian que perro
        return  new DayId[] {
                new DayId(context.getResources().getString(R.string.DOMINGO),  0),
                new DayId(context.getResources().getString(R.string.LUNES),    1),
                new DayId(context.getResources().getString(R.string.MARTES),   2),
                new DayId(context.getResources().getString(R.string.MIERCOLES),3),
                new DayId(context.getResources().getString(R.string.JUEVES),   4),
                new DayId(context.getResources().getString(R.string.VIERNES),  5),
                new DayId(context.getResources().getString(R.string.SABADO),   6)
        };
    }

    @Override
    public String toString() {
        return nombre;
    }

    public void setToTime(int hours,int minute,EditText changeText) {
        this.toHour = Calendar.getInstance();
        this.toHour.set(Calendar.HOUR_OF_DAY, hours);
        this.toHour.set(Calendar.MINUTE, minute);
        try {
            changeText.setText(getToTimeString());
        } catch (NoTimeException e) {
            e.printStackTrace();
        }
    }

    public void setFromTime(int hours,int minute,EditText changeText) {
        this.fromHour = Calendar.getInstance();
        this.fromHour.set(Calendar.HOUR_OF_DAY, hours);
        this.fromHour.set(Calendar.MINUTE, minute);
        try {
            changeText.setText(getFromTimeString());
        } catch (NoTimeException e) {
            e.printStackTrace();
        }
    }

    public String getToTimeString() throws NoTimeException{
        return getTimeString(toHour);
    }

    public String getFromTimeString() throws NoTimeException{
        return getTimeString(fromHour);
    }

    /**
     * Una alternativa es usar dateFormat.format(calendar.getTime());
     * @param hour
     * @return
     * @throws NoTimeException
     */
    private String getTimeString(Calendar hour) throws NoTimeException {
        if(hour == null)
            throw new NoTimeException();
        int minutes = hour.get(Calendar.MINUTE);
        String minutesString;
        if(minutes < 10) {
            minutesString = "0" + minutes;
        } else {
            minutesString = Integer.toString(minutes);
        }
        return hour.get(Calendar.HOUR_OF_DAY) + ":" + minutesString;
    }

    public Calendar getTime(TYPE_HOUR typeHour) throws NoTimeException {
        switch (typeHour) {
            case FROM:
                if(fromHour != null)
                    return fromHour;
                break;
            case TO:
                if(toHour != null)
                    return toHour;
                break;
        }
        throw new NoTimeException();
    }

    public Calendar getFromTime() throws NoTimeException{
        if(fromHour == null)
            throw new NoTimeException();
        return fromHour;
    }

}
