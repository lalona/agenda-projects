package com.example.lalo10.agenda.Helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by lalo10 on 10/24/17.
 */

public class TimeFormatHelper {

    public static String calendar2String(Calendar calendar) {
        return calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static Calendar string2Calendar(String time) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            cal.setTime(sdf.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cal;
    }
}
