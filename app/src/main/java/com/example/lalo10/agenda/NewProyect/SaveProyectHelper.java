package com.example.lalo10.agenda.NewProyect;

import android.app.Activity;
import android.util.Log;

import com.example.lalo10.agenda.Database.Proyectos.ActivitiesDBDAO;
import com.example.lalo10.agenda.Database.Proyectos.GoalsDBDAO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by lalo10 on 10/27/17.
 */

public class SaveProyectHelper {

    private static long lastId;

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
        lastId = idGoal;
        return true;

    }

    public static void checkConflicts(List<ActivityProyect> preActivities,Activity activity) {
        ActivitiesDBDAO activitiesDBDAO = new ActivitiesDBDAO(activity);
        for(ActivityProyect activityProyect : preActivities) {
            try {
                List<ActivityProyect> activityProyectsConflict = activitiesDBDAO.getCoincidenceAct(activityProyect);

            } catch (NoCoincidenceFoundEx noCoincidenceFoundEx) {
                //noCoincidenceFoundEx.printStackTrace();

            }
        }
    }

    public static boolean saveChanges(Activity activity, NewProjectData projectData) {
        if(lastId == 0)
            return false;
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
            GoalProyect goalProyect = new GoalProyect(projectData);
            goalProyect.setId(lastId);
            idGoal = goalsDBDAO.update(goalProyect);
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


        ActivitiesDBDAO activitiesDBDAO = new ActivitiesDBDAO(activity);
        int num = activitiesDBDAO.deleteAllActiviedFrom(lastId);
        Log.d("GOALPROYECT","Se borraron " + num);

        for(ActivityProyect activityProyect : activitiesProject) {
            Log.d("GOALPROYECT",activityProyect.getDateString());
            activitiesDBDAO.save(activityProyect);
        }
        return true;
    }

}
