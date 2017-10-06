package com.example.lalo10.agenda.Database.Guide;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by lalo10 on 10/6/17.
 */

public class AccessDatabase {

    FeedReaderDBHelper mDbHelper;

    public AccessDatabase(Context context) {
        FeedReaderDBHelper mDbHelper = new FeedReaderDBHelper(context);
    }

    public void insertInto() {
        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(ContractGuide.FeedEntry.COLUMN_NAME_TITLE, "New title");
        values.put(ContractGuide.FeedEntry.COLUMN_NAME_SUBTITLE, "New subtitle");

// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(ContractGuide.FeedEntry.TABLE_NAME, null, values);
    }

    public void readTable() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                ContractGuide.FeedEntry._ID,
                ContractGuide.FeedEntry.COLUMN_NAME_TITLE,
                ContractGuide.FeedEntry.COLUMN_NAME_SUBTITLE
        };

// Filter results WHERE "title" = 'My Title'
        String selection = ContractGuide.FeedEntry.COLUMN_NAME_TITLE + " = ?";
        String[] selectionArgs = { "My Title" };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                ContractGuide.FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

        Cursor cursor = db.query(
                ContractGuide.FeedEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        List itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(ContractGuide.FeedEntry._ID));
            itemIds.add(itemId);
        }
        cursor.close();
    }

    public void deleteRow() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define 'where' part of query.
        String selection = ContractGuide.FeedEntry.COLUMN_NAME_TITLE + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { "MyTitle" };
        // Issue SQL statement.
        db.delete(ContractGuide.FeedEntry.TABLE_NAME, selection, selectionArgs);
    }

    public void updateTable() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(ContractGuide.FeedEntry.COLUMN_NAME_TITLE, "New title");

// Which row to update, based on the title
        String selection = ContractGuide.FeedEntry.COLUMN_NAME_TITLE + " LIKE ?";
        String[] selectionArgs = { "MyTitle" };

        int count = db.update(
                ContractGuide.FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);

    }

    /**
     * Call in onDestroy in activity
     */
    protected void close() {
        mDbHelper.close();
    }



}
