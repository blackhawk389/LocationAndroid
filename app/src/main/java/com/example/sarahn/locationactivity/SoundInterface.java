package com.example.sarahn.locationactivity;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;


public class SoundInterface extends Activity {

    private RadioButton rb;
    private RadioButton rb1;
    int value1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        rb = (RadioButton) findViewById(R.id.radioButton);
        rb1 = (RadioButton) findViewById(R.id.radioButton2);


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

//save button
    public void savesound(View view){

        if(rb.isChecked())
        {
            value1 = 1;
        }else if(rb1.isChecked())
        {
            value1 = 2;
        }


        Intent intent = new Intent(SoundInterface.this, Locationinterface1.class);
        intent.putExtra("profileselection", value1);
        startActivity(intent);


        Toast.makeText(this, "Sound data has been saved successfully" + value1, Toast.LENGTH_SHORT).show();
    }

}


