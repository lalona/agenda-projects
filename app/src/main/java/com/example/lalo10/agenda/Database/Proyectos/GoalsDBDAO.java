package com.example.lalo10.agenda.Database.Proyectos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.lalo10.agenda.NewProyect.GoalProyect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lalo10 on 10/24/17.
 */

public class GoalsDBDAO extends ProyectManagementDAO {
    private static final String WHERE_ID_EQUALS = DataBaseHelper.ID_GOALS
            + " = ?";

    public GoalsDBDAO(Context context) {
        super(context);
    }

    public long save(GoalProyect goal) {
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.FECHA_INI_GOALS, goal.getStartString());
        values.put(DataBaseHelper.FECHA_FIN_GOALS, goal.getEndString());
        values.put(DataBaseHelper.GOAL_GOALS, goal.getGoal());

        return database.insert(DataBaseHelper.GOALS_TABLE, null, values);
    }

    public long update(GoalProyect goal) {
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.FECHA_INI_GOALS, goal.getStartString());
        values.put(DataBaseHelper.FECHA_FIN_GOALS, goal.getEndString());
        values.put(DataBaseHelper.GOAL_GOALS, goal.getGoal());

        long result = database.update(DataBaseHelper.GOALS_TABLE, values,
                WHERE_ID_EQUALS,
                new String[] { String.valueOf(goal.getId()) });

        return result;

    }

    public int deleteGoal(GoalProyect goal) {
        return database.delete(DataBaseHelper.GOALS_TABLE,
                WHERE_ID_EQUALS, new String[] { goal.getId() + "" });
    }

    public List<GoalProyect> getAllGoals() {
        List<GoalProyect> goals = new ArrayList<>();
        Cursor cursor = database.query(DataBaseHelper.GOALS_TABLE,
                new String[] { DataBaseHelper.ID_GOALS,
                        DataBaseHelper.FECHA_INI_GOALS,
                        DataBaseHelper.FECHA_FIN_GOALS,
                        DataBaseHelper.GOAL_GOALS}, null, null, null, null,
                null);

        while (cursor.moveToNext()) {
            GoalProyect goal = new GoalProyect();
            goal.setId(cursor.getInt(0));
            goal.setStart(cursor.getString(1));
            goal.setEnd(cursor.getString(2));
            goals.add(goal);
        }
        return goals;
    }
}
