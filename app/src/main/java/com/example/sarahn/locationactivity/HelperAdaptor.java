package com.example.sarahn.locationactivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


public class HelperAdaptor extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "mmhdatabase";
    public static final String TABLE_NAME3 = "timetable";
    public static final int DATABASE_VERSION = 2;
    public static final String NAME1 = "_timeprofilename";
    // public static final String PROFILE = "selectedprofile";
    public static final String TIME_PROFILE = "selecteddtimeprofile";
    public static final String START_HOUR = "starthour";
    public static final String END_HOUR = "endhour";
    public static final String ID="id";
    public static final String START_MINUTE = "startmin";
    public static final String END_MINUTE = "endmin";
    public static final String CREATE_TABLE3 = " CREATE TABLE IF NOT EXISTS" + TABLE_NAME3 + " ("+ID+" INTEGER PRIMARY KEY," + NAME1 + " TEXT, " + START_HOUR + " INTEGER, " + END_HOUR + " INTEGER," + START_MINUTE + " INTEGER, " + END_MINUTE + " INTEGER, " + TIME_PROFILE + " INTEGER) ";
    public static final String DROP_TABLE1 = " DROP TABLE IF EXISTS " + TABLE_NAME3;

//tumhara pc slow hai ya net..?? net hoga ku k 2 din reh gy hain recharge mai aur data b kam hai
    public Context context;

    public HelperAdaptor(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;

    }


    @Override
    public void onCreate(SQLiteDatabase db) {


        try {

            db.execSQL(CREATE_TABLE3);

        } catch (SQLException e) {

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

      /*  try {

            db.execSQL(DROP_TABLE1);


            onCreate(db);


        } catch (SQLException e) {

        }
*/
    }


    public boolean insertdatatime(int id,String nametime, int hrstart, int hrend, int minstart, int minend, int pr) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues1 = new ContentValues();
        contentValues1.put(this.ID,id);
        contentValues1.put(this.NAME1, nametime);
        contentValues1.put(this.START_HOUR, hrstart);
        contentValues1.put(this.END_HOUR, hrend);
        contentValues1.put(this.START_MINUTE, minstart);
        contentValues1.put(this.END_MINUTE, minend);
        contentValues1.put(this.TIME_PROFILE, pr);
        Log.d("Tag","Values:"+contentValues1.toString());
        long result = db.insert(this.TABLE_NAME3, null, contentValues1);
        Log.d("Tag","data insert id is:"+result);
//lets try again and aagian
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }


}
