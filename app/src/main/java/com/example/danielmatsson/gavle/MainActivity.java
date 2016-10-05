package com.example.danielmatsson.gavle;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.danielmatsson.gavle.R;

public class Gavle2 extends AppCompatActivity implements MenuItem.OnMenuItemClickListener {

    Button showMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Behöver en locationmanager för att hitta våt
        //GPS provider
        LocationManager locationManager;
        //Hämta enhetens gps
        String svcName = Context.LOCATION_SERVICE;
        //Hämta provider
        locationManager = (LocationManager) getSystemService(svcName);
        String provider = LocationManager.GPS_PROVIDER;
        //här kommer felhantering
        //sätt upp själva positions lyssnaren
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            //För att appen ska fråga telefonen om tillåtelse att använda GPSen när den installerar
            if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.INTERNET }
                    ,10
                );
            }
            return;
        }
        locationManager.requestLocationUpdates(provider, 0, 0, new LocationListener() {
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
        });

        //Hämta senaste longitud och latitud
        Location loc = locationManager.getLastKnownLocation(provider);
        //metod som visar vår nya positoin
        updateWithNewLocation(loc);
    }//här sluat onCreate metoden
    
    private void updateWithNewLocation(Location loc) {
        TextView myLocationText = null;
        String latlongText = "No location found";
        //om vi  fått någon position ska detta skrivas
        if (loc != null) {
            //hämta latitud och longitud
            double lat = loc.getLatitude();
            double lng = loc.getLongitude();
            latlongText = "Lat: "+ lat + " Long: "+ lng;
            
        }//här slutar if-satsen
        //sätt texten för textview i layouten
        myLocationText.setText("Your current position is: \n"+ latlongText);
                

        showMenu = (Button) findViewById(R.id.show_dropdown_menu);
        showMenu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                PopupMenu dropDownMenu = new PopupMenu(getApplicationContext(), showMenu);
                dropDownMenu.getMenuInflater().inflate(R.menu.menu, dropDownMenu.getMenu());
                showMenu.setText("DropDown Menu");
                dropDownMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Toast.makeText(getApplicationContext(), "You have clicked " + menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        return true;
                    }
                });
                dropDownMenu.show();
            }
        });

    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        return false;
    }

}
