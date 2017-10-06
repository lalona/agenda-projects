package com.example.lalo10.agenda.NewProyect;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lalo10 on 10/5/17.
 * Design pattern: singleton
 */

public class NewProjectData {

    private static NewProjectData onlyinstance;
    private Context context;


    private NewProjectData() {

    }

    public static NewProjectData getInstance(Context context) {
        if(onlyinstance == null) {
            onlyinstance = new NewProjectData();
        }
        onlyinstance.context = context;
        return onlyinstance;
    }

    String goal;
    Fechas start;
    Fechas end;
    List<DayId> daysSelected;

    public void start() {

    }

    public void setDaysSelected(List<DayId> daysSelected) {
        this.daysSelected = daysSelected;

    }

    public List<DayId> getDaysSelected() {
        if(daysSelected == null) {
            //Dummy data
            DayId[] days = DayId.getArrayDays(context);
            daysSelected = new ArrayList<>();

            daysSelected.add( new DayId(days[0].nombre,days[0].id) );
            daysSelected.add( new DayId(days[3].nombre,days[3].id) );
        }
        return daysSelected;
    }




}
