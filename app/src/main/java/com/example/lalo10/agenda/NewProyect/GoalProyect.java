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

    public static boolean createGoalAndActivities(Activity activity, NewProjectData projectData) {
        List<DayId> selectedDays = projectData.getDaysSelected();
        List<ActivityProyect> activitiesProject = new ArrayList<>();
        GregorianCalendar gcal = new GregorianCalendar();
        gcal.setTime(projectData.getStart().getCalendar().getTime());
        DayId dayId = null;
        boolean foundBegin = false;
        Calendar dayInIteration = null;
        DayIdHelper dayIdHelper = new DayIdHelper(selectedDays);
        long idGoal = -1;
        while (!foundBegin || !gcal.getTime().after(projectData.getEnd().getCalendar().getTime())) {
            // mientras no encuentre el primer dia que coincida va a seguir iterando
            dayInIteration = Calendar.getInstance();
            dayInIteration.setTime(gcal.getTime());
            try {
                dayId = dayIdHelper.getDayIfHas(dayInIteration.get(Calendar.DAY_OF_WEEK));
                foundBegin = true;
                break;
            } catch (ItDoesntHasDayException e) {
            }
            gcal.add(Calendar.DATE, 1);
        }

        if(!foundBegin || (dayId == null) || (dayInIteration == null))
            return false;
        else {
            GoalsDBDAO goalsDBDAO = new GoalsDBDAO(activity);
            idGoal = goalsDBDAO.save(new GoalProyect(projectData));
            activitiesProject.add(new ActivityProyect(dayId,dayInIteration,idGoal));
            DayId currentDay = dayId;
            try {
                dayId = dayIdHelper.getNextDay();
            } catch (ItDoesntHasDayException e) {
                return false;
            }
            try {
                gcal.add(Calendar.DATE, dayIdHelper.getNextDayNumber(currentDay));
            } catch (ItDoesntHasDayException e) {
                return false;
            }
        }

        while (!gcal.getTime().after(projectData.getEnd().getCalendar().getTime())) {
            dayInIteration = Calendar.getInstance();
            dayInIteration.setTime(gcal.getTime());
            activitiesProject.add(new ActivityProyect(dayId,dayInIteration,idGoal));
            DayId currentDay = dayId;
            try {
                dayId = dayIdHelper.getNextDay(currentDay);
            } catch (ItDoesntHasDayException e) {
                return false;
            }
            try {
                gcal.add(Calendar.DATE, dayIdHelper.getNextDayNumber(currentDay));
            } catch (ItDoesntHasDayException e) {
                return false;
            }
        }

        for(ActivityProyect activityProyect : activitiesProject) {
            ActivitiesDBDAO activitiesDBDAO = new ActivitiesDBDAO(activity);
            Log.d("GOALPROYECT",activityProyect.getDateString());
            activitiesDBDAO.save(activityProyect);
        }
        return true;

    }



}
