package com.example.getkalmanfilteredloc;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import mad.location.manager.lib.Interfaces.LocationServiceInterface;
import mad.location.manager.lib.Services.KalmanLocationService;
import mad.location.manager.lib.Services.ServicesHelper;

public class MainActivity extends AppCompatActivity implements LocationServiceInterface {

    private static final String TAG = "MAIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        init();
        ServicesHelper.addLocationServiceInterface(this);
    }

    private void init() {
        ServicesHelper.getLocationService(this, value -> {
            if (value.IsRunning()) {
                return;
            }
            value.stop();
            KalmanLocationService.Settings settings =KalmanLocationService.defaultSettings;
            value.reset(settings);
            value.start();
        });
    }

    @Override
    public void locationChanged(Location location) {
        Toast.makeText(this, "Lat: "+location.getLatitude()+" Long: "+location.getLongitude(), Toast.LENGTH_SHORT).show();
        Log.e(TAG, "locationChanged" );
    }
}
