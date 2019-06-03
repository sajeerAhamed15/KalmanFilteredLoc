package com.example.getkalmanfilteredloc;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import mad.location.manager.lib.Commons.Utils;
import mad.location.manager.lib.Interfaces.LocationServiceInterface;
import mad.location.manager.lib.Services.KalmanLocationService;
import mad.location.manager.lib.Services.ServicesHelper;

public class MainActivity extends AppCompatActivity implements LocationServiceInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();
        ServicesHelper.addLocationServiceInterface(this);

        ServicesHelper.getLocationService(this, value -> {
            if (value.IsRunning()) {
                return;
            }
            value.stop();
            KalmanLocationService.Settings settings = KalmanLocationService.defaultSettings;
            value.reset(settings); //TODO!! here you can adjust your filter behavior by changing settings object
            value.start();
        });
    }

    @Override
    public void locationChanged(Location location) {
        Toast.makeText(this, "Lat: "+location.getLatitude()+" Long: "+location.getLongitude(), Toast.LENGTH_SHORT).show();
    }
}
