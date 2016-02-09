package com.example.sarahn.locationactivity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

//yaha sy shuru hota hai, agr tm screen dekh sko tou bht acha hoga wrna mri profile per screenshot hain app k, purani post
//okey wait


public class MainActivity extends ActionBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public void locationinterface(View view)
    {
        Intent intentlocation = new Intent(getApplicationContext(), Locationinterface1.class);
        startActivity(intentlocation);

    }

    public void timeinterface(View view){

        Intent intentlocation = new Intent(getApplicationContext(), TimeInterface.class);
        startActivity(intentlocation);
    }
}
