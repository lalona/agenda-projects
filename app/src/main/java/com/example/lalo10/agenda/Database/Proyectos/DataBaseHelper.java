package com.example.lalo10.agenda.Database.Proyectos;

/**
 * Created by lalo10 on 10/24/17.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "proyect_management";
    private static final int DATABASE_VERSION = 1;

    public static final String GOALS_TABLE = "goals";
    public static final String ACTIVITIES_TABLE = "activities";

    /* [NAME OF FIELD]_[NAME OF TABLE] */

    // TABLE GOALS
    public static final String ID_GOALS = "idgoals";
    public static final String FECHA_INI_GOALS = "fecha_ini";
    public static final String FECHA_FIN_GOALS = "fecha_fin";
    public static final String GOAL_GOALS = "goal";

    // TABLE ACTIVITIES
    public static final String ID_ACTS = "idactivities";
    public static final String DATE_ACTS = "date_activities"; // it ends in activities just because can be ambiguous
    public static final String HORA_INI_ACTS = "hora_ini_min";
    public static final String HORA_FIN_ACTS = "hora_ini_fin";
    public static final String POR_ACCOMPLISH_ACTS = "por_accomp_act";
    //FK ID_GOALS = ID_GOALS

    public static final String CREATE_GOALS_TABLE = "CREATE TABLE "
            + GOALS_TABLE + "(" + ID_GOALS + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + FECHA_INI_GOALS + " TEXT, " + FECHA_FIN_GOALS + " TEXT, "
            + GOAL_GOALS + " INT )";

    public static final String CREATE_ACTIVITIES_TABLE = "CREATE TABLE "
            + ACTIVITIES_TABLE + "(" + ID_ACTS + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + DATE_ACTS +" TEXT, "
            + HORA_INI_ACTS + " INT,"+ HORA_FIN_ACTS +" INT, "
            + POR_ACCOMPLISH_ACTS + " INT,"
            + ID_GOALS + " INT,"
            + "FOREIGN KEY ("+ID_GOALS+") REFERENCES "  + GOALS_TABLE
            + "( " + ID_GOALS +  ") )";

    private static DataBaseHelper instance;

    public static synchronized DataBaseHelper getHelper(Context context) {
        if (instance == null)
            instance = new DataBaseHelper(context);
        return instance;
    }

    private DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_GOALS_TABLE);
        db.execSQL(CREATE_ACTIVITIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}