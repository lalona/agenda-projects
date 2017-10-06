package com.example.lalo10.agenda.NewProyect;

import java.util.Calendar;

/**
 * Created by andel on 8/13/17.
 */

public class Fechas {

    int year;

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public Calendar getCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year,month,day);
        return calendar;
    }

    public Calendar getMinCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year,month,day);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return calendar;
    }

    int month;
    int day;

    public Fechas(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public static Fechas getInstCurrentDate() {
        Calendar calander = Calendar.getInstance();
        int cDay = calander.get(Calendar.DAY_OF_MONTH);
        int cMonth = calander.get(Calendar.MONTH) + 1;
        int cYear = calander.get(Calendar.YEAR);
        return new Fechas(cYear,cMonth,cDay);
    }

    @Override
    public String toString() {
        return day + "/" + month + "/" + year;
    }

    public Calendar getMaxCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year,month,day);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return calendar;
    }
}
