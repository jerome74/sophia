package indra.com.icertify.activities.service;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import indra.com.icertify.activities.constants.ServiceState;
import indra.com.icertify.activities.constants.UTIL_SOPHIA;
import indra.com.icertify.activities.handler.ServiceHandler;

public abstract  class AccessibilityServiceManager extends AccessibilityService {


	private WakeLock sWakeLock;
	protected Intent intent;
	protected Activity activity;
	protected ServiceHandler serviceHandler;


	
	/**
	 * @method onCreate
	 * @args
	 * @return void
	 */
	
	@Override
	public void onCreate()
	{
		super.onCreate();
	}
	
	/**
	 * @method acquireWakeLock
	 * @args ctx, lock
	 * @return void
	 */
	
	private void acquireWakeLock(Context ctx, String lock)
    {
        if (sWakeLock == null) 
        {
            PowerManager pMgr = (PowerManager) ctx.getSystemService(POWER_SERVICE);
            sWakeLock = pMgr.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, lock);
        }
        
        sWakeLock.acquire();
    }
    
	/**
	 * @method releaseWakeLock
	 * @args ctx
	 * @return void
	 */
	
    protected void releaseWakeLock(Context ctx)
    {
        sWakeLock.release();
    }

	/**
	 * @method onStart
	 * @args intent, startId
	 * @return void
	 */
	
	@Override
	public int onStartCommand(Intent intent, int flags , int startId)
	{
		super.onStartCommand(intent, flags, startId);
		Log.d(UTIL_SOPHIA.SOPHIA, "AccessibilityServiceManager : onStartCommand()");
		this.intent = intent;
		
		/*************/
		doExecute()
		/*************/;
		
		return START_STICKY ;
	}
	
	/**
	 * @method doExecute
	 * @return void
	 * @param void
	 * @throws none
	 */
	
	public ServiceState doExecute()
	{		
			acquireWakeLock(this, "LOCK");
			return execute();
	}
	
	/**
	 * @method doExecute
	 * @return void
	 * @param void
	 * @throws none
	 */
	
	public abstract ServiceState execute();


	@Override
	public void onInterrupt() 
	{
		Log.d(UTIL_SOPHIA.SOPHIA, "on method: onInterrupt()");
	}

	 @Override
     protected void onServiceConnected() 
	 {		 
	        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
	        // We are interested in all types of accessibility events.
	        info.eventTypes = AccessibilityEvent.TYPES_ALL_MASK;
	        // We want to receive events in a certain interval.
	        info.notificationTimeout = 100;
	        setServiceInfo(info);
     }
	 
	 @Override
		public void onAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
		{
			Log.d(UTIL_SOPHIA.SOPHIA, "on method: paramAccessibilityEvent()");
			Log.d(UTIL_SOPHIA.SOPHIA, "event: " + paramAccessibilityEvent.getEventType() );
			
	        if(paramAccessibilityEvent.getEventType() == AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED)
	        {
	        	updateActivity();
	        }
	        else
	        {
	        	Log.w(UTIL_SOPHIA.SOPHIA, "not correct event");
	        }
	        
	        Log.d(UTIL_SOPHIA.SOPHIA, "exit method: paramAccessibilityEvent()");
	    }
	 
/**
 * @method: updateActivity
 * @args:
 * @return:void
 */
	protected abstract void updateActivity();
	 
}
