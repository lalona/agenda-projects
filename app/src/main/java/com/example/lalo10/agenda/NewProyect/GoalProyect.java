package com.example.lalo10.agenda.NewProyect;

import android.app.Activity;

import com.example.lalo10.agenda.Database.Proyectos.GoalsDBDAO;
import com.example.lalo10.agenda.Helpers.TimeFormatHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by lalo10 on 10/24/17.
 */

public class GoalProyect {

    public GoalProyect(NewProjectData newProjectData) {
        this.goal = newProjectData.goal;
        this.end = newProjectData.getEnd().getCalendar();
        this.start = newProjectData.getStart().getCalendar();
    }

    public void setId(int idGoal) {
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

    private int idGoal;
    private String goal;
    private Calendar start;
    private Calendar end;

    public int getId() {
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

    public static boolean createGoalAndActivities(Activity activity, NewProjectData projectData) {
        GoalsDBDAO goalsDBDAO = new GoalsDBDAO(activity);
        long id = goalsDBDAO.save(new GoalProyect(projectData));
        List<DayId> selectedDays = projectData.getDaysSelected();
        GregorianCalendar gcal = new GregorianCalendar();
        gcal.setTime(projectData.getStart().getCalendar().getTime());

        while (!gcal.getTime().after(projectData.getStart().getCalendar().getTime())) {
            Calendar d = Calendar.getInstance();
            d.setTime(gcal.getTime());
            gcal.add(Calendar.DATE, 1);
            for(DayId dayS : )
        }
        for(DayId daySelected : selectedDays) {

        }

    }



}
