package indra.com.icertify.utility;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by adifrancesco on 24/10/2017.
 */

public class InternetConnection
{
    public static boolean isInternetOn(Context context)
    {
        // get Connectivity Manager object to check connection
        ConnectivityManager connec = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);

        // Check for network connections
        if ( connec.getNetworkInfo( ConnectivityManager.TYPE_MOBILE).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == android.net.NetworkInfo.State.CONNECTED
                )
        {
            return true;
        }
        else if ( connec.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                connec.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == android.net.NetworkInfo.State.DISCONNECTED )
        {
            return false;
        }

        return false;
    }
}
