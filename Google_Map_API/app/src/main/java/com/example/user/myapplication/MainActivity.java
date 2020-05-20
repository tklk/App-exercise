package com.example.user.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1;
    //設定超過一個距離(尺)他就會做更新location的動作
    private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000;
    //設定超過一個時間(毫秒)他就會做更新location的動作

    protected LocationManager locationManager;

    protected Button retrieveLocationButton;
    protected TextView locationPrint;



    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Location loc1;
        int dist=0;

        Location change{
            if(loc1==null)
                loc1=location;
            else {
                dist += loc1.distanceto(location);
                loc1 = location;
                tool(dist);
            }
        }

        retrieveLocationButton = (Button) findViewById(R.id.retrieveLocationButton);
        locationPrint = (TextView) findViewById(R.id.FindLocation);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(
                //Provider: the name of the GPS provider
                LocationManager.GPS_PROVIDER,
                //minTime: 最小時間間隔後更新位置，以毫秒為單位
                MINIMUM_TIME_BETWEEN_UPDATES,
                //minDistance: 最短距離間隔外更新位置，以公尺為單位
                MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
                //Listener:每次地點更新時會呼叫LocationListener中onLocationChanged(Location) 		     方法
                new MyLocationListener()
        );

        retrieveLocationButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                showCurrentLocation();
            }
        });
    }



    protected void showCurrentLocation() {
        //取得最後得知道provider資訊
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        //得知GPS位置時，在TextView上顯示經緯度
        if (location != null) {
            String message = String.format(
                    "Current Location \n Longitude: %1$s \n Latitude: %2$s",
                    location.getLongitude(), location.getLatitude());
            locationPrint.setText(message);
        }
        else
            Toast.makeText(MainActivity.this, "null",Toast.LENGTH_LONG).show();
    }
    private class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
// TODO Auto-generated method stub
            //將想要印出的資料用string.format的方法存入string
            //%1$s，%2$s中，1、2代表後方第幾個參數
            String message = String.format(
                    "New Location \n Longitude: %1$s \n Latitude: %2$s",
                    location.getLongitude(), location.getLatitude());
            locationPrint.setText(message);
        }
        @Override
        public void onProviderDisabled(String provider) {
// TODO Auto-generated method stub
//當device的GPS沒有開啟的時候他會顯示
            Toast.makeText(MainActivity.this,"Provider disabled by the user. GPS turned off",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onProviderEnabled(String provider) {
// TODO Auto-generated method stub
//當device將GPS打開的時候他會顯示
            Toast.makeText(MainActivity.this,"Provider enabled by the user. GPS turned on",
                    Toast.LENGTH_LONG).show();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
// TODO Auto-generated method stub
//當provider change的時候會顯示
            Toast.makeText(MainActivity.this, "Provider status changed",Toast.LENGTH_LONG).show();
        }
    }

}
