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
public class StartAlarmServices extends Service {

    TimeInterface time;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        SharedPreferences sharedPreferencetime = getSharedPreferences("startalarm",Context.MODE_PRIVATE);
        SharedPreferences.Editor editortime = sharedPreferencetime.edit();
        editortime.putInt("currentvalue", audioManager.getRingerMode());
        editortime.commit();
        Toast.makeText(this, "Alaram fired", Toast.LENGTH_SHORT).show();

        if(time.soundval == 2) {
            audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
            audioManager.setStreamVolume(AudioManager.STREAM_ALARM, 0, 0);
        }else
        {
            audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
        }

        Toast.makeText(StartAlarmServices.this, "Now in your defined sound settings", Toast.LENGTH_LONG).show();


    }
}