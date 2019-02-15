package indra.com.icertify.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;

import indra.com.icertify.R;
import indra.com.icertify.activities.constants.SERVICES;
import indra.com.icertify.activities.constants.UTIL_SOPHIA;
import indra.com.icertify.activities.handler.ServiceHandler;
import indra.com.icertify.activities.service.GPSTracker;
import indra.com.icertify.activities.service.NotifyBean;
import indra.com.icertify.activities.service.domain.ConnService;
import indra.com.icertify.activities.service.external.PreferenceCallBack;

public class SophiaMainActivity extends AppCompatActivity implements PreferenceCallBack {

    TextView tvLatitude;
    TextView tvLongitude;
    TextView tvImei;
    Intent connService;
    ServiceHandler serviceHandler;

    @Override
    public void returnServiceResponse()
    {
        handler.sendEmptyMessage(0);
    }

    @Override
    public void returnServiceResponse(final Message message)
    {
        handler.sendMessage(message);
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        public void handleMessage(final Message msg) {

            boolean toClose = true;
            boolean toExit = false;

            try {
                runOnUiThread(new Runnable() {
                    public void run() {

                        getLocation(((Location)msg.obj).getLatitude(),((Location)msg.obj).getLongitude());
                    }
                });

            } catch (Exception e) {
                Log.e(UTIL_SOPHIA.SOPHIA, "ERROR :", e);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sophia_main);


        tvImei = (TextView)findViewById(R.id.vlImei);
        tvLongitude = (TextView)findViewById(R.id.vlLongitudine);
        tvLatitude = (TextView)findViewById(R.id.vlLatitudine);

        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(this.TELEPHONY_SERVICE);

        try {
            getImei();
            tvImei.setText(String.valueOf(telephonyManager.getDeviceId()));
        } catch (SecurityException e) {
            Log.d(UTIL_SOPHIA.SOPHIA, "SecurityException : " + e.getLocalizedMessage());
            tvImei.setText("dummy-86679003268376");
        }



        NotifyBean.createEvent(UTIL_SOPHIA.NB_SophiaMainActivity, this);

        try
        {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101); }

            startServiceGps();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getLocation(double latitude,  double longitude)
    {
            tvLatitude.setText(String.valueOf(latitude));
            tvLongitude.setText(String.valueOf(longitude));
    }

    private void startServiceGps()
    {
        connService = new Intent(this, ConnService.class);
        serviceHandler = new ServiceHandler(this, this);
        Message startMessage = new Message();
        startMessage.arg1 = SERVICES.START;
        serviceHandler.handleMessage(startMessage, connService);
    }

    private void getImei()
    {
        int MY_READ_PHONE_STATE = 1234;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED)
        {
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_PHONE_STATE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_PHONE_STATE},
                        MY_READ_PHONE_STATE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }
}
