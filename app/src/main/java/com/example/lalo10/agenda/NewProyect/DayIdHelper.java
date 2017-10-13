package com.example.lalo10.agenda.NewProyect;

import java.util.List;

/**
 * Created by lalo10 on 10/9/17.
 */

public class DayIdHelper {

    DayId dayId;
    int[] days;
    List<DayId> dayIdList;

    public DayIdHelper(List<DayId> dayIdList) {
        this.dayIdList = dayIdList;
        days = new int[dayIdList.size()];
        for(int i = 0; i < dayIdList.size(); i++)
            days[i] = dayIdList.get(i).id + 1;

    }

    public DayId getDayIfHas(int dayOfWeek) throws ItDoesntHasDayException{
        for(int i = 0; i < days.length; i++) {
            if(dayOfWeek == days[i]) {
                return this.dayIdList.get(i);
            }
        }
        throw new ItDoesntHasDayException();
    }

}
