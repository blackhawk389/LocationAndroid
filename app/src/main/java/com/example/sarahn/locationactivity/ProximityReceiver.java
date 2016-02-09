package com.example.sarahn.locationactivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.media.AudioManager;
import android.widget.Toast;

/**
 * Created by SarahN on 10/16/2015.
 */


    public class ProximityReceiver extends BroadcastReceiver {


        AudioManager audioManager;
        Context context;
        int pr;



                    @Override
        public void onReceive(Context arg0, Intent intent) {


                        audioManager= (AudioManager)arg0.getSystemService(Context.AUDIO_SERVICE);
                        pr = audioManager.getRingerMode();
                        SharedPreferences sharedPreference1 = arg0.getSharedPreferences("cur", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreference1.edit();
                        editor.putInt("pr1", pr);
                        editor.commit();

            String k = LocationManager.KEY_PROXIMITY_ENTERING;


            boolean state = intent.getBooleanExtra(k, false);




            if (state) {

                int i = intent.getIntExtra("sounds1",0);

                if(i == 2)
                {
                    audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                    audioManager.setStreamVolume(AudioManager.STREAM_ALARM, 0, 0);
                }
                else if(i==1)
                {
                    audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                }


                Toast.makeText(arg0, "Your phone will go silent", Toast.LENGTH_SHORT).show();


            } else{

                SharedPreferences sharedPreference = arg0.getSharedPreferences("cur",Context.MODE_PRIVATE);
                int getprofile = sharedPreference.getInt("pr1" , 0);

                if(getprofile == 2)


                    audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                    Toast.makeText(arg0, "Exiting, back to previous settings", Toast.LENGTH_SHORT).show();

            }
        }

    }




