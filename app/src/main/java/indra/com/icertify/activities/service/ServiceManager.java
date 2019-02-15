package indra.com.icertify.activities.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

import indra.com.icertify.activities.service.enu.ServiceState;

public abstract class ServiceManager extends Service {
    private WakeLock sWakeLock;
    protected boolean acquire = true;

    /**
     * @return void
     * @method onCreate
     * @args
     */

    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * @return void
     * @method acquireWakeLock
     * @args ctx, lock
     */

    private void acquireWakeLock(Context ctx, String lock) {
        if (sWakeLock == null) {
            PowerManager pMgr = (PowerManager) ctx.getSystemService(POWER_SERVICE);
            sWakeLock = pMgr.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, lock);
        }

        sWakeLock.acquire();
    }

    /**
     * @return void
     * @method releaseWakeLock
     * @args ctx
     */

    protected void releaseWakeLock(Context ctx) {
        sWakeLock.release();
    }

    /**
     * @return void
     * @method onStart
     * @args intent, startId
     */

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        /*************/
        doExecute()
        /*************/;

        return START_STICKY;
    }


    /**
     * @param void
     * @return void
     * @throws none
     * @method doExecute
     */

    public ServiceState doExecute() {
        if (acquire)
            acquireWakeLock(this, "LOCK");

        return execute();
    }

    /**
     * @param void
     * @return void
     * @throws none
     * @method doExecute
     */

    public abstract ServiceState execute();

    public abstract ServiceState terminate();
}
