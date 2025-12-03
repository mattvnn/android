package com.example.locationapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private TextView txtLatitude, txtLongitude, txtProvider, txtAccuracy, txtAltitude, txtBearing, txtSpeed;

    private static final int PERMISSION_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLatitude = findViewById(R.id.txtLatitude);
        txtLongitude = findViewById(R.id.txtLongitude);
        txtProvider = findViewById(R.id.txtProvider);
        txtAccuracy = findViewById(R.id.txtAccuracy);
        txtAltitude = findViewById(R.id.txtAltitude);
        txtBearing = findViewById(R.id.txtBearing);
        txtSpeed = findViewById(R.id.txtSpeed);

        txtLatitude.setText("Latitude: Aguardando...");
        txtLongitude.setText("Longitude: Aguardando...");
        txtProvider.setText("Provedor: Aguardando...");
        txtAccuracy.setText("Precisão: Aguardando...");
        txtAltitude.setText("Altitude: Aguardando...");
        txtBearing.setText("Direção: Aguardando...");
        txtSpeed.setText("Velocidade: Aguardando...");

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSION_REQUEST_CODE);
        } else {
            setupLocation();
        }
    }

    private void setupLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permissão de localização não concedida.", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            LocationManager locationManager =
                    (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    5000,
                    10,
                    this
            );

            txtProvider.setText("Provedor: GPS (Real)");
            Toast.makeText(this, "Solicitando localização real...", Toast.LENGTH_SHORT).show();

        } catch (SecurityException e) {
            Toast.makeText(this, "Erro de segurança (Permissão): " + e.getMessage(), Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "Erro: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocationManager locationManager =
                (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationManager.removeUpdates(this);
            Toast.makeText(this, "Monitoramento de localização pausado.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        txtLatitude.setText(String.format("Latitude: %.6f", location.getLatitude()));
        txtLongitude.setText(String.format("Longitude: %.6f", location.getLongitude()));

        if (location.hasAccuracy()) {
            txtAccuracy.setText(String.format("Precisão: %.2f metros", location.getAccuracy()));
        } else {
            txtAccuracy.setText("Precisão: Indisponível");
        }

        if (location.hasAltitude()) {
            txtAltitude.setText(String.format("Altitude: %.2f metros", location.getAltitude()));
        } else {
            txtAltitude.setText("Altitude: Indisponível");
        }

        if (location.hasBearing()) {
            txtBearing.setText(String.format("Direção: %.2f graus", location.getBearing()));
        } else {
            txtBearing.setText("Direção: Indisponível");
        }

        if (location.hasSpeed()) {
            txtSpeed.setText(String.format("Velocidade: %.2f m/s", location.getSpeed()));
        } else {
            txtSpeed.setText("Velocidade: Indisponível");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE && grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            setupLocation();
        } else {
            Toast.makeText(this, "Permissão de localização negada.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override public void onProviderEnabled(@NonNull String provider) {}
    @Override public void onProviderDisabled(@NonNull String provider) {}
    @Override public void onStatusChanged(String provider, int status, Bundle extras) {}
}