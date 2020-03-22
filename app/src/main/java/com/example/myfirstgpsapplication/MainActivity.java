package com.example.myfirstgpsapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FusedLocationProviderClient fusedLocationClient;
    //private TextView text;
    double longitude;
    double latitude;
    Geocoder geocoder=new Geocoder(this, Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        final String loc;
        setContentView(R.layout.activity_main);
        //this.text =(TextView)findViewById(R.id.locationField);


        findViewById(R.id.nappi).setOnClickListener(this);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {

                    @Override
                    public void onSuccess (Location location){
                        TextView text = findViewById(R.id.locationField);
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                           text.setText(String.valueOf(location.getLatitude())+" "+String.valueOf(location.getLongitude()));
                           latitude=location.getLatitude();
                           longitude=location.getLongitude();
                            try {
                                List<Address> myList=geocoder.getFromLocation(latitude,longitude,1);
                                TextView text2=findViewById(R.id.placeField);

                                text2.setText(myList.get(0).getLocality()+" "+myList.get(0).getCountryName());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        else
                          text.setText("ei onnistunu");
                    }
                });


    }


    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.nappi)
            Log.d("nappi", "painettu");
                fusedLocationClient.getLastLocation();
    }

}
