package com.example.lalo10.agenda;

import java.util.List;

/**
 * Created by lalo10 on 10/5/17.
 * Design pattern: singleton
 */

public class NewProjectData {

    private static NewProjectData onlyinstance;

    private NewProjectData() {

    }

    public NewProjectData getInstance() {
        if(onlyinstance == null) {
            onlyinstance = new NewProjectData();
        }
        return onlyinstance;
    }

    String goal;
    Fechas start;
    Fechas end;
    List<DayId> daysSelected;

    public void start() {

    }




}
