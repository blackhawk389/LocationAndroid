package com.example.sarahn.locationactivity;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.IBinder;
import android.widget.Toast;

/**
 * Created by SarahN on 10/18/2015.
 */
public class EndAlarmServices extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);

        Toast.makeText(this, "end Alarm fired", Toast.LENGTH_SHORT).show();
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        SharedPreferences sharedPreferencetime = getSharedPreferences("startalarm",Context.MODE_PRIVATE);
        int currentsound = sharedPreferencetime.getInt("currentvalue",0);

        if(currentsound == 2) {
            audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        }


        Toast.makeText(EndAlarmServices.this, "back to normal mode", Toast.LENGTH_SHORT).show();


    }


}
