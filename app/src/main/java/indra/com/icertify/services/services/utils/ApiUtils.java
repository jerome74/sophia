package indra.com.icertify.services.services.utils;

import android.content.Context;

import indra.com.icertify.services.interfaces.SOServiceRicercaCertificazioni;
import indra.com.icertify.services.interfaces.SOServiceRicercaCertificazioniScadute;
import indra.com.icertify.services.services.Clients.RetrofitClient;

/**
 * Created by adifrancesco on 20/10/2017.
 */

public class ApiUtils
{

    public static final String BASE_URL = "https://api.stackexchange.com/2.2/";

    public static SOServiceRicercaCertificazioni getSOServiceRicercaCertificazioni(Context context)
    {
        return RetrofitClient.getClient(context, BASE_URL).create(SOServiceRicercaCertificazioni.class);
    }

    public static SOServiceRicercaCertificazioniScadute getSOServiceRicercaCertificazioniScadute(Context context)
    {
        return RetrofitClient.getClient(context, BASE_URL).create(SOServiceRicercaCertificazioniScadute.class);
    }
}
