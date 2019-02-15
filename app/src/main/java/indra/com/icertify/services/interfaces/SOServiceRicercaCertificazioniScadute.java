package indra.com.icertify.services.interfaces;


import indra.com.icertify.services.models.ricerca_certificazioni_scadute.SOResponseRicercaCertficazioniScadute;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by adifrancesco on 20/10/2017.
 */

public interface SOServiceRicercaCertificazioniScadute
{
    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Call<SOResponseRicercaCertficazioniScadute> getAnswers();

    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Call<SOResponseRicercaCertficazioniScadute> getAnswers(@Query( "tagged" ) String tags);
}
