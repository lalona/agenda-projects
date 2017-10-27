package com.example.lalo10.agenda.Database.Proyectos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.lalo10.agenda.NewProyect.ActivityProyect;
import com.example.lalo10.agenda.NewProyect.GoalProyect;
import com.example.lalo10.agenda.NewProyect.NoCoincidenceFoundEx;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by lalo10 on 10/24/17.
 */

public class ActivitiesDBDAO extends ProyectManagementDAO {
    private static final String WHERE_ID_EQUALS = DataBaseHelper.ID_ACTS
            + " = ?";

    private static final String WHERE_FK_ID_EQUALS = DataBaseHelper.ID_GOALS
            + " = ?";


    public ActivitiesDBDAO(Context context) {
        super(context);
    }

    public long save(ActivityProyect activity) {
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.DATE_ACTS, activity.getDateString());
        values.put(DataBaseHelper.HORA_INI_ACTS, activity.getFromHourInMin());
        values.put(DataBaseHelper.HORA_FIN_ACTS, activity.getToHourInMin());
        values.put(DataBaseHelper.POR_ACCOMPLISH_ACTS, activity.getPorAccomplish());
        values.put(DataBaseHelper.ID_GOALS, activity.getGoalProyectId());
        values.put(DataBaseHelper.FROM_EXACT_TIME_MIL, activity.getFromHourInMin());
        values.put(DataBaseHelper.TO_EXACT_TIME_MIL,activity.getToHourInMin());
        return database.insert(DataBaseHelper.ACTIVITIES_TABLE, null, values);
    }

    public long update(ActivityProyect activity) {
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.DATE_ACTS, activity.getDateString());
        values.put(DataBaseHelper.HORA_INI_ACTS, activity.getFromHourInMin());
        values.put(DataBaseHelper.HORA_FIN_ACTS, activity.getToHourInMin());
        values.put(DataBaseHelper.ID_GOALS, activity.getGoalProyectId());
        values.put(DataBaseHelper.POR_ACCOMPLISH_ACTS, activity.getPorAccomplish());
        values.put(DataBaseHelper.FROM_EXACT_TIME_MIL, activity.getFromHourInMin());
        values.put(DataBaseHelper.TO_EXACT_TIME_MIL,activity.getToHourInMin());
        long result = database.update(DataBaseHelper.ACTIVITIES_TABLE, values,
                WHERE_ID_EQUALS,
                new String[] { String.valueOf(activity.getId()) });
        return result;
    }

    public int deleteActivity(ActivityProyect activity) {
        return database.delete(DataBaseHelper.ACTIVITIES_TABLE,
                WHERE_ID_EQUALS, new String[] { activity.getId() + "" });
    }

    public List<ActivityProyect> getAllActivities() {
        List<ActivityProyect> activities = new ArrayList<>();
        Cursor cursor = database.query(DataBaseHelper.ACTIVITIES_TABLE,
                new String[] { DataBaseHelper.ID_ACTS,
                        DataBaseHelper.DATE_ACTS,
                        DataBaseHelper.HORA_INI_ACTS,
                        DataBaseHelper.HORA_FIN_ACTS,
                        DataBaseHelper.POR_ACCOMPLISH_ACTS,
                        DataBaseHelper.FROM_EXACT_TIME_MIL,
                        DataBaseHelper.TO_EXACT_TIME_MIL
                        }, null, null, null, null,
                null);

        while (cursor.moveToNext()) {
            ActivityProyect activity = new ActivityProyect();
            activity.setId(cursor.getInt(0));
            activity.setDate(cursor.getString(1));
            activity.setFromHour(cursor.getInt(2));
            activity.setToHour(cursor.getInt(3));
            activity.setPorAccomplish(cursor.getInt(4));
            activities.add(activity);
        }
        cursor.close();
        return activities;
    }

    public int deleteAllActiviedFrom(long idGoal) {
        return database.delete(DataBaseHelper.ACTIVITIES_TABLE,
                WHERE_FK_ID_EQUALS, new String[] { idGoal + "" });

    }

    public List<ActivityProyect> getCoincidenceAct(ActivityProyect activityProyect) throws NoCoincidenceFoundEx {
        List<ActivityProyect> activities = new ArrayList<>();
        String WHERE_INTERVAL_EQUALS = "( " + DataBaseHelper.FROM_EXACT_TIME_MIL + " < ? AND" +
                                              DataBaseHelper.TO_EXACT_TIME_MIL + "   > ? ) OR " +
                                       "( " + DataBaseHelper.FROM_EXACT_TIME_MIL + " < ? AND" +
                                              DataBaseHelper.TO_EXACT_TIME_MIL + "   > ? ) OR" +
                                       "( " + DataBaseHelper.FROM_EXACT_TIME_MIL + " = ? AND" +
                                              DataBaseHelper.TO_EXACT_TIME_MIL + "   = ? ) ";
        String[] whereArgs = new String[] {
                activityProyect.getFromHourInMin() + "",
                activityProyect.getFromHourInMin() + "",
                activityProyect.getToHourInMin()   + "",
                activityProyect.getToHourInMin()   + "",
                activityProyect.getFromHourInMin() + "",
                activityProyect.getToHourInMin()   + ""
        };

        Cursor cursor = database.query(DataBaseHelper.ACTIVITIES_TABLE,
                new String[] { DataBaseHelper.ID_ACTS,
                        DataBaseHelper.DATE_ACTS,
                        DataBaseHelper.HORA_INI_ACTS,
                        DataBaseHelper.HORA_FIN_ACTS,
                        DataBaseHelper.FROM_EXACT_TIME_MIL,
                        DataBaseHelper.TO_EXACT_TIME_MIL
                },
                WHERE_INTERVAL_EQUALS, whereArgs, null, null,
                null);
        if(cursor == null)
            throw new NoCoincidenceFoundEx();
        try {
            while (cursor.moveToNext()) {
                ActivityProyect activity = new ActivityProyect();
                activity.setId(cursor.getInt(0));
                activity.setDate(cursor.getString(1));
                activity.setFromHour(cursor.getInt(2));
                activity.setToHour(cursor.getInt(3));
                activity.setPorAccomplish(cursor.getInt(4));
                activities.add(activity);
            }
        } finally {
            cursor.close();
        }
        if(activities.isEmpty()) {
            throw new NoCoincidenceFoundEx();
        }
        return activities;
    }
}
