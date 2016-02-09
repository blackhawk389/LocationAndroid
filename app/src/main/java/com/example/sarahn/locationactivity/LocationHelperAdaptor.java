package com.example.sarahn.locationactivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SarahN on 10/30/2015.
 */
public class LocationHelperAdaptor extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "locationdatabase";
    public static final String TABLE_NAME = "location";
    public static final int DATABASE_VERSION = 2;
    public static final String NAME = "_profilename";
    public static final String LAT = "latitude";
    public static final String LNG = "longitude";
    public static final String PROFILE = "selectedprofile";
    public static final String CREATE_TABLE =  " CREATE TABLE IF NOT EXISTS" +TABLE_NAME+ " ("+NAME+ " STRING PRIMARY KEY, " +LAT+ " REAL, " +LNG+ " REAL, " +PROFILE+ " INTEGER); " ;
    public static final String DROP_TABLE = " DROP TABLE IF EXISTS "
            +TABLE_NAME;


    public Context context;

    public LocationHelperAdaptor(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
        this.context= context;

    }




    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL(CREATE_TABLE);
        }catch(SQLException e){

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {

        try {

            db.execSQL(DROP_TABLE);


            onCreate(db);


        }catch (SQLException e)
        {

        }

    }

    public boolean insertdata(String name, double lt ,  double lg, int p)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(this.NAME, name);
        contentValues.put(this.LAT, lt);
        contentValues.put(this.LNG, lg);
        contentValues.put(this.PROFILE, p);
         long id = db.insert(this.TABLE_NAME, name, contentValues);
        if(id == -1)
        {
            return  false;
        }
        else {
            return true;
        }
        //ye jo code hai ye tumny kaha se ki hai..??
    }
}
