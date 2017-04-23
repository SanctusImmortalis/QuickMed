package com.softwaremachine.sanctus.quickmed;

import android.Manifest;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import android.content.pm.PackageManager;
import android.content.Context;
import android.content.ContextWrapper;

import android.location.LocationManager;
import android.location.Location;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Location here = null;
        Context myContext = this.getApplicationContext();
        LocationManager locman = (LocationManager) myContext.getSystemService(Context.LOCATION_SERVICE);

        if(this.getPackageManager().checkPermission(Manifest.permission.ACCESS_FINE_LOCATION, myContext.getPackageName()) == PackageManager.PERMISSION_GRANTED) {
            here = locman.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            LatLng myLoc = new LatLng(here.getLatitude(), here.getLongitude());
            mMap.addMarker(new MarkerOptions().position(myLoc).title("You are here"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(myLoc));
        }
    }
}
