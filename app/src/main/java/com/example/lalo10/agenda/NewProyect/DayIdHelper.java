package com.example.lalo10.agenda.NewProyect;

import java.util.List;

/**
 * Created by lalo10 on 10/9/17.
 */


public class DayIdHelper {

    DayId dayId;
    int[] days;
    List<DayId> dayIdList;
    DayId nextDay;

    public DayIdHelper(List<DayId> dayIdList) {
        this.dayIdList = dayIdList;
        days = new int[dayIdList.size()];
        for(int i = 0; i < dayIdList.size(); i++)
            days[i] = dayIdList.get(i).id;

    }

    public DayId getDayIfHas(int dayOfWeek) throws ItDoesntHasDayException{
        for(int i = 0; i < days.length; i++) {
            if(dayOfWeek == days[i]) {
                if( (i + 2) > this.dayIdList.size() ) // si sobre pasa el limite del tamaño entonces sigue el primero
                    this.nextDay = this.dayIdList.get(0);
                else
                    this.nextDay = this.dayIdList.get(i + 1);
                return this.dayIdList.get(i);
            }
        }
        throw new ItDoesntHasDayException();
    }

    public DayId getNextDay(DayId currentDay) throws ItDoesntHasDayException {
        int dayOfWeek = currentDay.id;
        if(this.dayIdList.size() == 1)
            return dayIdList.get(0);
        for(int i = 0; i < days.length; i++) {
            if(dayOfWeek == days[i]) {
                if( (i + 2) > this.dayIdList.size() ) // si sobre pasa el limite del tamaño entonces sigue el primero
                    nextDay = this.dayIdList.get(0);
                else
                    nextDay = this.dayIdList.get(i + 1);
                return nextDay;
            }
        }
        throw new ItDoesntHasDayException();
    }

    public DayId getNextDay() throws ItDoesntHasDayException {
        if(nextDay != null)
            return nextDay;
        throw new ItDoesntHasDayException();
    }

    public int getNextDayNumber(DayId currentDay) throws ItDoesntHasDayException {
        if(dayIdList.size() == 1)
            return 7;
        if(nextDay != null) {
            if(nextDay.id < currentDay.id) {
                return (7 - currentDay.id) + nextDay.id;
            } else {
                return nextDay.id - currentDay.id;
            }
        }
        throw new ItDoesntHasDayException();
    }

}
