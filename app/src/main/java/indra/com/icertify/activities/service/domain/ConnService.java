package indra.com.icertify.activities.service.domain;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import indra.com.icertify.activities.constants.UTIL_SOPHIA;
import indra.com.icertify.activities.service.NotifyBean;
import indra.com.icertify.activities.service.ServiceManager;
import indra.com.icertify.activities.service.alert.ServiceAlarm;
import indra.com.icertify.activities.service.alert.signal.ConnAlarmSignal;
import indra.com.icertify.activities.service.enu.ServiceState;


public class ConnService extends ServiceManager
{
	
	private static Context context;
	private Intent intent;

	
	
	

	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{		
		Log.d(UTIL_SOPHIA.SOPHIA, "ConnService : onStartCommand()");
		return super.onStartCommand(intent, flags, startId);
	}




	@Override
	public indra.com.icertify.activities.service.enu.ServiceState execute()
	{	
		intent = new Intent(this, ConnAlarmSignal.class);
		
		ServiceAlarm.start(this, (long) 2000, intent);
		return ServiceState.STARTED;
	}
	
	
	
	
	/**
	 * @method onDestroy
	 * @args
	 * @return void
	 */
	
	@Override
	public void onDestroy()
	{
		
		Log.d(UTIL_SOPHIA.SOPHIA,  this.getClass().getName() + " : onDestroy()");
		ServiceAlarm.stop(this, new Intent(this, ConnAlarmSignal.class));
		
		try 
		{
			NotifyBean.notifyEvent(UTIL_SOPHIA.NB_ConnetionEvent);
		}
		catch (Exception e)
		{
			Log.w(UTIL_SOPHIA.SOPHIA,  e);
		}
		
		super.onDestroy();
	}




	public static Context getContext() {
		return context;
	}




	public static void setContext(Context context) {
		ConnService.context = context;
	}




	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public indra.com.icertify.activities.service.enu.ServiceState terminate() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
