package indra.com.icertify.services.services.Clients;

/**
 * Created by adifrancesco on 20/10/2017.
 */

import android.content.Context;

import java.util.concurrent.TimeUnit;

import indra.com.icertify.utility.InternetConnection;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient
{

    private static Retrofit retrofit = null;

    public static Retrofit getClient(Context context, String baseUrl)
    {
        if( InternetConnection.isInternetOn(context) )
        {
            if ( retrofit == null )
            {
                final OkHttpClient okHttpClient = new OkHttpClient.Builder().
                        readTimeout(2, TimeUnit.SECONDS).
                        connectTimeout(2, TimeUnit.SECONDS).
                        build();

                retrofit = new Retrofit.Builder().
                        baseUrl(baseUrl).
                        client(okHttpClient).
                        addConverterFactory(GsonConverterFactory.create()).
                        build();
            }
        }
        else
        {

        }

        return retrofit;
    }
}
