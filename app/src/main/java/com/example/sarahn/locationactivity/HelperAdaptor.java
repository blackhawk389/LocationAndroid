package com.example.sarahn.locationactivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


    public class HelperAdaptor extends SQLiteOpenHelper{
        public static final String DATABASE_NAME = "maindatabase";
        public static final String TABLE_NAME = "locationtable";
        public static final String TABLE_NAME2 = "timebasedtable";
        public static final int DATABASE_VERSION = 1;
        public static final String NAME = "_profilename";
        public static final String NAME1 = "_timeprofilename";
        public static final String LAT = "latitude";
        public static final String LNG = "longitude";
        public static final String PROFILE = "selectedprofile";
        public static final String CUR_PROFILE= "currentprofile";
        public static final String TIME_PROFILE = "selecteddtimeprofile";
        public static final String START_HOUR = "starthour";
        public static final String END_HOUR = "endhour";
        public static final String START_MINUTE = "startmin";
        public static final String END_MINUTE = "endmin";
        public static final String CUR_PROFILE_TIME= "currentprofiletime";
        public static final String CREATE_TABLE =  " CREATE TABLE " +TABLE_NAME+ " ("+NAME+ " TEXT PRIMARY KEY, " +LAT+ " INTEGER, " +LNG+ " INTEGER, " +PROFILE+ " INTEGER, "+CUR_PROFILE+" INTEGER) " ;
        public static final String CREATE_TABLE2 = " CREATE TABLE " +TABLE_NAME2+ " ("+NAME1+ " TEXT PRIMARY KEY, " +CUR_PROFILE_TIME+ "INTEGER, " +START_HOUR+ " INTEGER, " +END_HOUR+ "INTEGER," +START_MINUTE+ " INTEGER, " +END_MINUTE+ "INTEGER, " +TIME_PROFILE+ " INTEGER) "  ;


        public Context context;

        public HelperAdaptor (Context context) {
            super(context,DATABASE_NAME, null, DATABASE_VERSION);
            this.context= context;

        }


        @Override
        public void onCreate(SQLiteDatabase db) {


            try {
                Toast.makeText(context, "Oncreate called", Toast.LENGTH_LONG).show();
                db.execSQL(CREATE_TABLE);
                db.execSQL(CREATE_TABLE2);

            }catch (SQLException e)
            {

            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            try {
//
//                Toast.makeText(context, "Onupgrade called", Toast.LENGTH_LONG).show();
//                db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
              //  onCreate(db);

            }catch (SQLException e)
            {

            }

        }

        public boolean insertdata(String name, double lt ,  double lg, int p, int cp) {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(this.NAME, name);
            contentValues.put(this.LAT, lt);
            contentValues.put(this.LNG, lg);
            contentValues.put(this.PROFILE, p);
            contentValues.put(this.CUR_PROFILE, cp);
            long id = db.insert(this.TABLE_NAME, null, contentValues);
            if(id == -1)
            {
                return  false;
            }
            else {
                return true;
            }
        }

        public boolean insertdatatime(String nametime, int hrstart , int hrend , int minstart, int minend, int pr, int cpt) {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(this.NAME1, nametime);
            contentValues.put(this.START_HOUR, hrstart);
            contentValues.put(this.END_HOUR, hrend);
            contentValues.put(this.START_MINUTE, minstart);
            contentValues.put(this.END_MINUTE, minend);
            contentValues.put(this.TIME_PROFILE, pr);
            contentValues.put(this.CUR_PROFILE_TIME, cpt);

            long id = db.insert(this.TABLE_NAME2, null, contentValues);
            if(id == -1)
            {
                return false;
            }
            else {
                return true;
            }

        }

        }



