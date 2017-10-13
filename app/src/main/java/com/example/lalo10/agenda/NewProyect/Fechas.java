package com.example.lalo10.agenda.NewProyect;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by andel on 8/13/17.
 */

public class Fechas {

    int year;
    int month;
    int day;
    int minStartInDay;
    int minStartEndDay;

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

    public Fechas(Calendar date, Calendar fromHour, Calendar toHour) {

    }

    public String getStringFormat() {
        Calendar c = this.getCalendar();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String formatted = format1.format(c.getTime());
        return formatted;
    }

    public static void iterateThrougDates(Calendar init, Calendar end, DayIdHelper daysHelper) {

        for(Calendar iDate = init; iDate.after(end); iDate.add(Calendar.DAY_OF_YEAR,1)) {
            try {
                DayId dayHours = daysHelper.getDayIfHas(iDate.get(Calendar.DAY_OF_WEEK));

            } catch (ItDoesntHasDayException e) {
                //No tenia el dia
            }
        }
    }


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
