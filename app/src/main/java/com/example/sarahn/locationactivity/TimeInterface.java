package com.example.sarahn.locationactivity;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import java.util.Calendar;


public class TimeInterface extends ActionBarActivity {

    static final int DIALOG_ID = 0;
    static final int DIALOG_ID2 = 1;
    EditText nametexttime;
    Button btn1;
    Button btn2;
    int hour, hour1;
    int minute, minute1;
    int soundval;
    RadioButton rb1time;
    RadioButton rb2time;
    RadioGroup rg;
    AudioManager audioManager;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                // the callback received when the user "sets" the TimePickerDialog in the dialog
                public void onTimeSet(TimePicker view, int hourOfDay, int min) {
                    hour = hourOfDay;
                    minute = min;

                }
            };
    private TimePickerDialog.OnTimeSetListener mTimeSetListener1 =
            new TimePickerDialog.OnTimeSetListener() {
                // the callback received when the user "sets" the TimePickerDialog in the dialog
                public void onTimeSet(TimePicker view, int hourOfDay, int min) {
                    hour1 = hourOfDay;
                    minute1 = min;

                }


            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeinterface);


        nametexttime = (EditText) findViewById(R.id.editTexttime);

        rb1time = (RadioButton) findViewById(R.id.radioButton3);
        rb2time = (RadioButton) findViewById(R.id.radioButton4);
        rg = (RadioGroup) findViewById(R.id.rgroup);

        showtimepickerdialog();
        showtimepickerdialog1();


    }

    public void showtimepickerdialog() {
        btn1 = (Button) findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_ID);
            }
        });

    }

    public void showtimepickerdialog1() {
        btn2 = (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_ID2);
            }
        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG_ID:
                return new TimePickerDialog(this, mTimeSetListener, hour, minute, false);
            case DIALOG_ID2:
                return new TimePickerDialog(this, mTimeSetListener1, hour1, minute1, false);

        }

        return null;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timeinterface, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void savetime(View view) {

        switch (rg.getCheckedRadioButtonId()) {
            case R.id.radioButton3:
                soundval = 1;
                break;
            case R.id.radioButton4:
                soundval = 2;
                break;

        }


        HelperAdaptor helperAdaptortime = new HelperAdaptor(this);
        String name = nametexttime.getText().toString();
        int curprofiletime = audioManager.getRingerMode();
        boolean isinsertedtime = helperAdaptortime.insertdatatime(name, hour, hour1, minute, minute1, soundval, curprofiletime);

        setstartalarm(hour, minute);
        setendalarm(hour1, minute1);
    }

    public void setstartalarm(int a, int b) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, a);
        calendar.set(Calendar.MINUTE, b);

        Intent myIntent = new Intent(TimeInterface.this, TimeReceiver.class);
        myIntent.putExtra("sounds", soundval);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(TimeInterface.this, 1, myIntent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, calculateMillis(calendar), pendingIntent);

    }

    public void setendalarm(int c, int d) {

        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.HOUR_OF_DAY, c);
        calendar1.set(Calendar.MINUTE, d);

        Intent myIntent1 = new Intent(TimeInterface.this, EndTimeReceiver.class);
        PendingIntent pendingIntent1 = PendingIntent.getBroadcast(TimeInterface.this, 1, myIntent1, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, calculateMillis(calendar1), pendingIntent1);
    }

    private long calculateMillis(Calendar cal) {
        int offset = cal.getTimeZone().getOffset(cal.getTimeInMillis());
        return cal.getTimeInMillis() + offset;
    }
}
