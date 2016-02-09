package com.example.sarahn.locationactivity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Locationinterface1 extends Activity {


    EditText nametext;
    double lat, lng;
    int profile;
    int radius = 100;
    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_interface);


        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                profile = 0;

            } else {
                profile = extras.getInt("profileselection");

            }
        } else {
            profile = (Integer) savedInstanceState.getSerializable("profileselection");

       }
        nametext = (EditText) findViewById(R.id.et);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_location_interface1, menu);
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
    public void showmap(View view){

        Intent intentmaps = new Intent(this, MapsActivity.class);
        startActivity(intentmaps);
    }
    public void showsoundsettings(View view){

        Intent intentsound = new Intent(this, SoundInterface.class);
        startActivity(intentsound);
    }
    //now what i want to do is to, i have sound vriables and lat long a now i will listen and if that location arrieves i will
    //save previous settings and load the new one if that location freed then i will load back the previous
    
    public void datasave(View view) {

        SharedPreferences sharedPreference = getSharedPreferences("latlngdata",Context.MODE_PRIVATE);
        lat = sharedPreference.getFloat("latitude" , 0);
        lng = sharedPreference.getFloat("longitude", 0);
       // Toast.makeText(this, " lat long data" +lat+ " " +lng, Toast.LENGTH_SHORT).show();




        String name = nametext.getText().toString();
        LocationHelperAdaptor locationhelperAdaptor = new LocationHelperAdaptor(this);

        boolean isinserted = locationhelperAdaptor.insertdata(name, lat, lng, profile);

        if(isinserted){
             Toast.makeText(this, "data inserted" , Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "data not inserted" , Toast.LENGTH_SHORT).show();
        }

        proximity();

        Intent intentlocation1 = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intentlocation1);



    }

    public void proximity(){

        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER , 1000, 1, new mylocationlistner());
        Intent i = new Intent(this, ProximityReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(getApplicationContext(), -1, i, 0);
        lm.addProximityAlert(lat, lng, radius, -1, pi);
        Intent curlocationsave = new Intent(Locationinterface1.this, ProximityReceiver.class);
        curlocationsave.putExtra("sounds1", profile);

    }


    //access lat lng(or may be all values) through primary key and pass it into proximity
    //when proximity reach save current sound settings into database
    //load sound settings for that location
    class mylocationlistner implements LocationListener{

        @Override
        public void onLocationChanged(Location location) {

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }



}
