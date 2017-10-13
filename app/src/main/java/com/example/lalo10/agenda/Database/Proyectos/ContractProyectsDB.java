package com.example.lalo10.agenda.Database.Proyectos;

import android.provider.BaseColumns;

import com.example.lalo10.agenda.Database.Guide.ContractGuide;

/**
 * Created by lalo10 on 10/6/17.
 */

public class ContractProyectsDB {
    // make the constructor private.
    private ContractProyectsDB() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "proyectos";
        public static final String C_N_GOAL   = "goal";
        public static final String C_N_START  = "date_start";
        public static final String C_N_END    = "date_end";
        public static final String C_N_DAYS_SELECTED = "days_week_selected";
    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ContractGuide.FeedEntry.TABLE_NAME + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.C_N_GOAL + " TEXT," +
                    FeedEntry.C_N_START + " TEXT " +
                    FeedEntry.C_N_END + " TEXT " +
                    FeedEntry.C_N_DAYS_SELECTED + " TEXT " +
                    ")";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ContractGuide.FeedEntry.TABLE_NAME;

}
