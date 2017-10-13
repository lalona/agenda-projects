package com.example.lalo10.agenda.Database.Proyectos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lalo10.agenda.Database.Proyectos.ContractProyectsDB;
import com.example.lalo10.agenda.NewProyect.NewProjectData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lalo10 on 10/13/17.
 */

public class ProyectAccessDatabase {
    ProyectDBHelper mDbHelper;

    public ProyectAccessDatabase(Context context) {
        mDbHelper = new ProyectDBHelper(context);
    }

    public void insertInto(NewProjectData data) {
        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(ContractProyectsDB.FeedEntry.C_N_DAYS_SELECTED, data.getDaysSelectedBits());
        values.put(ContractProyectsDB.FeedEntry.C_N_END, data.getEndString());
        values.put(ContractProyectsDB.FeedEntry.C_N_START, data.getStartString());
        values.put(ContractProyectsDB.FeedEntry.C_N_GOAL, data.getGoal());

// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(ContractProyectsDB.FeedEntry.TABLE_NAME, null, values);
    }

    public void readTable() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                ContractProyectsDB.FeedEntry._ID,
                ContractProyectsDB.FeedEntry.C_N_DAYS_SELECTED,
                ContractProyectsDB.FeedEntry.C_N_END,
                ContractProyectsDB.FeedEntry.C_N_START,
                ContractProyectsDB.FeedEntry.C_N_GOAL
        };

// Filter results WHERE "title" = 'My Title'
        String selection = ContractProyectsDB.FeedEntry.C_N_DAYS_SELECTED + " = ?";
        String[] selectionArgs = { "ABS" };

        Cursor cursor = db.query(
                ContractProyectsDB.FeedEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                          // The sort order
        );

        List itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(ContractProyectsDB.FeedEntry._ID));
            itemIds.add(itemId);
        }
        cursor.close();
    }

    /*
    public void deleteRow() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define 'where' part of query.
        String selection = ContractProyectsDB.FeedEntry.COLUMN_NAME_TITLE + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { "MyTitle" };
        // Issue SQL statement.
        db.delete(ContractProyectsDB.FeedEntry.TABLE_NAME, selection, selectionArgs);
    }

    public void updateTable() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(ContractProyectsDB.FeedEntry.COLUMN_NAME_TITLE, "New title");

// Which row to update, based on the title
        String selection = ContractProyectsDB.FeedEntry.COLUMN_NAME_TITLE + " LIKE ?";
        String[] selectionArgs = { "MyTitle" };

        int count = db.update(
                ContractProyectsDB.FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);

    }*/

    /**
     * Call in onDestroy in activity
     */
    protected void close() {
        mDbHelper.close();
    }

}
