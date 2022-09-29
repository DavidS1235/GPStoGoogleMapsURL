package com.dsc.gpstogooglemapsurl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tvLatitud, tvLongitud, tvUrl;

    private LocationManager locationManager;

    private String userLatitude, userLongitude, userURL;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gpstest);

        tvLatitud = findViewById(R.id.latitude);
        tvLongitud = findViewById(R.id.longitude);
        tvUrl = findViewById(R.id.url);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED ){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10,1, new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {
                userLongitude = String.valueOf(location.getLongitude());
                tvLongitud.setText(userLongitude);
                userLatitude = String.valueOf(location.getLatitude());
                tvLatitud.setText(userLatitude);
                userURL = "https://maps.google.com/?q=" + userLatitude+","+userLongitude;
                tvUrl.setText(userURL);
                locationManager.removeUpdates(this);
                locationManager = null;

            }


        });

        TextView t2 = (TextView) findViewById(R.id.url);
        t2.setMovementMethod(LinkMovementMethod.getInstance());

    }

    public String mandaUbicacion(){
        return "userURL";
    }


}
