package com.example.lalo10.agenda.Database.Proyectos;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by lalo10 on 10/24/17.
 */

public class ProyectManagementDAO  {
    protected SQLiteDatabase database;
    private DataBaseHelper dbHelper;
    private Context mContext;

    public ProyectManagementDAO(Context context) {
        this.mContext = context;
        dbHelper = DataBaseHelper.getHelper(mContext);
        open();

    }

    public void open() throws SQLException {
        if(dbHelper == null)
            dbHelper = DataBaseHelper.getHelper(mContext);
        database = dbHelper.getWritableDatabase();
    }

    /*public void close() {
        dbHelper.close();
        database = null;
    }*/

}