package indra.com.icertify.activities;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class SophiaService extends Service {
    public SophiaService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
