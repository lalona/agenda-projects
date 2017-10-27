package com.example.lalo10.agenda.NewProyect;

import android.app.Activity;
import android.util.Log;

import com.example.lalo10.agenda.Database.Proyectos.ActivitiesDBDAO;
import com.example.lalo10.agenda.Database.Proyectos.GoalsDBDAO;
import com.example.lalo10.agenda.Helpers.TimeFormatHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by lalo10 on 10/24/17.
 */

public class GoalProyect {

    private long idGoal;
    private String goal;
    private Calendar start;
    private Calendar end;



    public GoalProyect(NewProjectData newProjectData) {
        this.goal = newProjectData.goal;
        this.end = newProjectData.getEnd().getCalendar();
        this.start = newProjectData.getStart().getCalendar();
    }

    public GoalProyect(long id) {
        this.idGoal = id;
    }

    public void setId(long idGoal) {
        this.idGoal = idGoal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public void setStart(String start) {
        this.start = TimeFormatHelper.string2Calendar(start);
    }

    public void setEnd(String end) {
        this.end = TimeFormatHelper.string2Calendar(end);
    }

    public long getId() {
        return idGoal;
    }

    public String getGoal() {
        return goal;
    }

    public Calendar getStart() {
        return start;
    }

    public Calendar getEnd() {
        return end;
    }

    public String getStartString() {
        return TimeFormatHelper.calendar2String(start);
    }

    public String getEndString() {
        return TimeFormatHelper.calendar2String(end);
    }





}
