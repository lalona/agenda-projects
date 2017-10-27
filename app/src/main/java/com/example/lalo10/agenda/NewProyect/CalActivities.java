package com.example.lalo10.agenda.NewProyect;

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

    public String getGoal() {
        return goal;
    }

}
