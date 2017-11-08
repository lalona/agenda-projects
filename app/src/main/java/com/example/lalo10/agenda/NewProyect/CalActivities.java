package com.example.lalo10.agenda.NewProyect;

import android.app.Activity;

import java.util.Calendar;

/**
 * Created by lalo10 on 10/27/17.
 */

public class CalActivities {

    String goal;
    Calendar fromHour;
    Calendar toHour;
    Calendar date;
    int day; // it can be Calendar.SUNDAY,...Calendar.SATURDAY

    public CalActivities() {
        fromHour = Calendar.getInstance();
        fromHour.set(Calendar.MINUTE,0);
        fromHour.set(Calendar.HOUR_OF_DAY,0);
        toHour = Calendar.getInstance();
        toHour.set(Calendar.MINUTE,0);
        toHour.set(Calendar.HOUR_OF_DAY,0);
    }

    public String getSchedule() {
        DayId hours = new DayId();
        hours.setFromHour(fromHour);
        hours.setToHour(toHour);
        try {
            return hours.getFromTimeString() + " - " + hours.getToTimeString();
        } catch (NoTimeException e) {
            return "00:00";
        }
    }

    public int getMinDedicated() {
        ActivityProyect hours = new ActivityProyect();
        hours.setFromHour(fromHour);
        hours.setToHour(toHour);
        return hours.getToHourInMin() - hours.getFromHourInMin();
    }

    public String getGoal() {
        return goal;
    }

    public void addMinutesToFromHour(int min) {
        fromHour.add(Calendar.MINUTE,min);

    }

    public void addMinutesToToHour(int min) {
        toHour.add(Calendar.MINUTE,min);
    }

}
