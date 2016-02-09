package com.example.sarahn.locationactivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    private EditText text;
    public double latii; //here latitude will save and its public so that everyone use it
    public double lgii;   //here longitude will save
    Circle circle;
    Marker marker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
        mMap.setMyLocationEnabled(true);
        mMap.setOnMapClickListener(this);


    }


    @Override
    protected void onStart() {
        super.onStart();
        setUpMapIfNeeded();
        retreivedata();
        Toast.makeText(this, "You are good to go",Toast.LENGTH_SHORT).show();

    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    public void search(View view) throws IOException {
        searchLocation();
    }
    public void gotolocation(double lat, double lg )       //here is the function which specify location based on coords
    {
        LatLng ll = new LatLng(lat, lg);   //initializing LatLng with longitude and latitude
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(ll, 20);//here updating camera to latlong
        mMap.animateCamera(cameraUpdate);   //update map
    }

    //search location method
    public void searchLocation() throws IOException {

        Geocoder geocode = new Geocoder(this);
        EditText editText = (EditText) findViewById(R.id.search);
        String getlocation = editText.getText().toString();
        List<Address> list = geocode.getFromLocationName(getlocation, 1);
        Address add = list.get(0);
        double lat = add.getLatitude();
        double lg = add.getLongitude();
        gotolocation(lat, lg);
    }
    private void setUpMap() {}
    private Circle drawCircle(LatLng ll) {

        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(ll);
        circleOptions.radius(20);
        circleOptions.fillColor(Color.RED);
        circleOptions.strokeColor(Color.BLACK);
        circleOptions.strokeWidth(3);
        return mMap.addCircle(circleOptions);
    }


    @Override
    public void onMapClick(LatLng latLng) {

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        if (marker != null){
            removeEveryThing();
        }

        marker = mMap.addMarker(markerOptions);
        circle = drawCircle(latLng);

        latii = latLng.latitude;
        lgii = latLng.longitude;

        Toast.makeText(this,
                latLng.latitude + ", " + latLng.longitude,
                Toast.LENGTH_SHORT).show();


    }
    private void removeEveryThing() {
        mMap.clear();
        marker = null;

    }
    public void retreivedata()
    {

        SharedPreferences sharedPreferences = getSharedPreferences("Maydata", Context.MODE_PRIVATE);
        float lat = sharedPreferences.getFloat("longitude", 0);
        float lng = sharedPreferences.getFloat("latitude", 0);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 10));

    }


    public void save(View view)
    {
        SharedPreferences sharedPreference = getSharedPreferences("latlngdata",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putFloat("latitude", (float) latii);
        editor.putFloat("longitude", (float) lgii);
        editor.commit();

        Intent intent = new Intent(MapsActivity.this, Locationinterface1.class);
        startActivity(intent);
        Toast.makeText(this, "LatLng saved " + latii +" " + lgii,Toast.LENGTH_SHORT).show();
    }


}
