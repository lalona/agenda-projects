package com.example.lalo10.agenda.NewProyect;

import com.example.lalo10.agenda.Helpers.TimeFormatHelper;

import java.util.Calendar;

/**
 * Created by lalo10 on 10/24/17.
 */

public class ActivityProyect {

    private int idActivity;

    private Calendar fromHour;

    private Calendar toHour;
    private Calendar date;

    private int porAccomplish;
    private GoalProyect goalProyect;

    private long fromTimeInMil;

    public long getFromTimeInMil() {
        return fromTimeInMil;
    }

    public void setFromTimeInMil(long fromTimeInMil) {
        this.fromTimeInMil = fromTimeInMil;
    }

    public long getToTimeInMil() {
        return toTimeInMil;
    }

    public void setToTimeInMil(long toTimeInMil) {
        this.toTimeInMil = toTimeInMil;
    }

    private long toTimeInMil;

    public ActivityProyect() {}

    public ActivityProyect(DayId dayId, Calendar date, long idGoalProyect) {
        this.fromHour = dayId.fromHour;
        this.toHour = dayId.toHour;
        this.date = date;
        this.fromTimeInMil = createTimeInMil(this.date,this.fromHour);
        this.fromTimeInMil = createTimeInMil(this.date,this.toHour);
        this.porAccomplish = 0;
        this.goalProyect = new GoalProyect(idGoalProyect);
    }

    private long createTimeInMil(Calendar date, Calendar hour) {
        Calendar dateHourExact = date;
        dateHourExact.set(Calendar.MINUTE,hour.get(Calendar.MINUTE));
        dateHourExact.set(Calendar.HOUR_OF_DAY,hour.get(Calendar.HOUR_OF_DAY));
        dateHourExact.set(Calendar.SECOND,0);
        return dateHourExact.getTimeInMillis();
    }

    public int getId() {
        return idActivity;
    }

    public void setId(int idActivity) {
        this.idActivity = idActivity;
    }

    public Calendar getFromHour() {
        return fromHour;
    }

    public int getFromHourInMin() {
        return convertTimeToMin(fromHour);
    }

    public void setFromHour(Calendar fromHour) {
        this.fromHour = fromHour;
    }

    public void setFromHour(int fromHour) {
        this.fromHour = convertMinToTime(fromHour);
    }

    public Calendar getToHour() {
        return toHour;
    }

    public int getToHourInMin() {
        return convertTimeToMin(toHour);
    }

    public void setToHour(Calendar toHour) {
        this.toHour = toHour;
    }

    public void setToHour(int toHour) {
        this.fromHour = convertMinToTime(toHour);
    }

    public GoalProyect getGoalProyect() {
        return goalProyect;
    }

    public long getGoalProyectId() {
        return goalProyect.getId();
    }

    public void setGoalProyect(GoalProyect goalProyect) {
        this.goalProyect = goalProyect;
    }

    public int getPorAccomplish() {
        return porAccomplish;
    }

    public void setPorAccomplish(int porAccomplish) {
        this.porAccomplish = porAccomplish;
    }

    public Calendar getDate() {
        return date;
    }

    public String getDateString() {
        return TimeFormatHelper.calendar2String(date);
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public void setDate(String date) {
        this.date = TimeFormatHelper.string2Calendar(date);
    }

    private int convertTimeToMin(Calendar time) {
        int hours = time.get(Calendar.HOUR_OF_DAY);
        int min = time.get(Calendar.MINUTE);
        return hours * 60 + min;
    }

    private Calendar convertMinToTime(int minDay) {
        Calendar c = Calendar.getInstance();
        int hours = minDay / 60;
        float min = (minDay / 60 - hours) * 60;
        c.set(Calendar.HOUR_OF_DAY,hours);
        c.set(Calendar.MINUTE,(int) min);
        return c;
    }

}
