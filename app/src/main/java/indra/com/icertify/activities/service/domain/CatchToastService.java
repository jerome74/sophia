package indra.com.icertify.activities.service.domain;

import android.content.Intent;
import android.util.Log;

import indra.com.icertify.activities.constants.ServiceState;
import indra.com.icertify.activities.constants.UTIL_SOPHIA;
import indra.com.icertify.activities.service.AccessibilityServiceManager;
import indra.com.icertify.activities.service.NotifyBean;

public class CatchToastService extends AccessibilityServiceManager
{
	
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{		
		Log.d(UTIL_SOPHIA.SOPHIA, "CatchToastService : onStartCommand()");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public ServiceState execute()
	{		
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
		super.onDestroy();
	}
	

/**
 * @method: updateActivity
 * @args:
 * @return:void
 */

	@Override
	protected void updateActivity() 
	{
		if(intent.getBooleanExtra(UTIL_SOPHIA.IS_CONN_SERV, false))
		{
			Log.d(UTIL_SOPHIA.SOPHIA, "CatchToastService : updateActivity()");

			try 
			{
				NotifyBean.notifyEvent(UTIL_SOPHIA.NB_ConnetionEvent);
			}
			catch (Exception e)
			{
				Log.w(UTIL_SOPHIA.SOPHIA,  e);
			}
		}
	}
}
