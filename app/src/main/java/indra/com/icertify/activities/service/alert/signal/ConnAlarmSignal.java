package indra.com.icertify.activities.service.alert.signal;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import android.widget.Toast;

import indra.com.icertify.activities.SophiaMainActivity;
import indra.com.icertify.activities.constants.UTIL_SOPHIA;
import indra.com.icertify.activities.service.NotifyBean;
import indra.com.icertify.activities.service.external.PreferenceCallBack;


public class ConnAlarmSignal extends WakefulBroadcastReceiver implements LocationListener {

	private Context mContext;
	// flag for GPS status
	boolean isGPSEnabled = false;
	// flag for network status
	boolean isNetworkEnabled = false;
	// flag for GPS status
	boolean canGetLocation = false;
	Location location; // location
	double latitude; // latitude
	double longitude; // longitude
	// The minimum distance to change Updates in meters
	private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
	// The minimum time between updates in milliseconds
	private static final long MIN_TIME_BW_UPDATES = 1000; // 1 second
	// Declaring a Location Manager
	protected LocationManager locationManager;

	@Override
	public void onReceive(Context paramContext, Intent paramIntent)
	{
		getLocation(paramContext, paramIntent);
		Log.d(UTIL_SOPHIA.SOPHIA, "ConnAlarmSignal : onReceive()");
	}

	public void getLocation(Context paramContext, Intent paramIntent) {

		if(mContext == null)
			this.mContext = paramContext;

		try {
			locationManager = (LocationManager) mContext.getSystemService(mContext.LOCATION_SERVICE);
			// getting GPS status
			isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
			// getting network status
			isNetworkEnabled = locationManager
					.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
			if (!isGPSEnabled && !isNetworkEnabled) {
				// no network provider is enabled
			} else {
				this.canGetLocation = true;
				// First get location from Network Provider
				if (isNetworkEnabled) {
					//check the network permission
					if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
						ActivityCompat.requestPermissions((Activity) mContext, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
					}
					Log.d(UTIL_SOPHIA.SOPHIA, "ConnAlarmSignal : isNetworkEnabled");
					locationManager.requestLocationUpdates(
							LocationManager.NETWORK_PROVIDER,
							MIN_TIME_BW_UPDATES,
							MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
					Log.d("Network", "Network");
					if (locationManager != null) {
						location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
						Log.d(UTIL_SOPHIA.SOPHIA, "ConnAlarmSignal : location");
					}
				}
				// if GPS Enabled get lat/long using GPS Services
				if (isGPSEnabled) {
					if (location == null) {
						//check the network permission
						if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
							ActivityCompat.requestPermissions((Activity) mContext, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
						}
						Log.d(UTIL_SOPHIA.SOPHIA, "ConnAlarmSignal : isGPSEnabled");
						locationManager.requestLocationUpdates(
								LocationManager.GPS_PROVIDER,
								MIN_TIME_BW_UPDATES,
								MIN_DISTANCE_CHANGE_FOR_UPDATES, (LocationListener) this);
						Log.d("GPS Enabled", "GPS Enabled");
						if (locationManager != null) {
							location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onLocationChanged(Location location) {

		if (location != null) {

			latitude = location.getLatitude();
			longitude = location.getLongitude();

			/*************************************/
			PreferenceCallBack callBack = NotifyBean.getEvent(UTIL_SOPHIA.NB_SophiaMainActivity);
			Message message = new Message();
			message.obj = location;
			callBack.returnServiceResponse(message);
			/*************************************/
		}
	}
	@Override
	public void onProviderDisabled(String provider) {
	}
	@Override
	public void onProviderEnabled(String provider) {
	}
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}
}
