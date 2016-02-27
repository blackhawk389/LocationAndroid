package com.example.sarahn.locationactivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.widget.Toast;

import java.sql.Time;

/**
 * Created by SarahN on 10/17/2015.
 */
public class TimeReceiver extends BroadcastReceiver {

    AudioManager audioManager;
    @Override
    public void onReceive(Context context, Intent intent) {


        Toast.makeText(context, " Receiver class fired..!!", Toast.LENGTH_SHORT).show();

       audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int i = intent.getIntExtra("sounds", 0);

        if(i == 2)
        {
            audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
            audioManager.setStreamVolume(AudioManager.STREAM_ALARM, 0, 0);
        }
        else if(i==1)
        {
            audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
        }


    }
}
