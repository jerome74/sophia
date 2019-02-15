package indra.com.icertify.services.services.Calls;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import indra.com.icertify.services.interfaces.ICalls;
import indra.com.icertify.services.interfaces.ICallsDelegate;
import indra.com.icertify.services.interfaces.SOServiceRicercaCertificazioniScadute;
import indra.com.icertify.services.models.ricerca_certificazioni_scadute.SOResponseRicercaCertficazioniScadute;
import indra.com.icertify.services.services.utils.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by adifrancesco on 25/10/2017.
 */

public class CallsToRicercaCertificazioniScadute implements ICalls
{
    /**
     *
     */
    @Override
    public void load(Context context, ICallsDelegate callsDelegate)
    {
        final SOServiceRicercaCertificazioniScadute mService = ApiUtils.getSOServiceRicercaCertificazioniScadute(context);
        final ICallsDelegate mCallsDelegate = callsDelegate;
        final Context mContext = context;

        mService.getAnswers().enqueue(new Callback<SOResponseRicercaCertficazioniScadute>()
        {
            @Override
            public void onResponse(Call<SOResponseRicercaCertficazioniScadute> call, Response<SOResponseRicercaCertficazioniScadute> response)
            {
                if ( response.isSuccessful() )
                {
                    Toast.makeText(mContext, "Respone successfull", Toast.LENGTH_LONG).show();
                    SOResponseRicercaCertficazioniScadute soResponseRicercaCertficazioniScadute = response.body();
                    mCallsDelegate.onSearchSuccessfull(soResponseRicercaCertficazioniScadute);
                }
                else
                {
                    int statusCode = response.code();
                    // handle request errors depending on status code
                    Toast.makeText(mContext, "Respone is not successfull", Toast.LENGTH_LONG).show();
                    mCallsDelegate.onSearchFailed();
                }
            }

            @Override
            public void onFailure(Call<SOResponseRicercaCertficazioniScadute> call, Throwable t)
            {
                Toast.makeText(mContext, "Failed to receive data", Toast.LENGTH_LONG).show();
                Log.d("ICertify", "error loading from API");
                mCallsDelegate.onFailure();
            }
        });
    }
}
