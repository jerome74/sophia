package indra.com.icertify.services.interfaces;


import indra.com.icertify.services.models.ricerca_certificazioni.SOResponseRicercaCertficazioni;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by adifrancesco on 20/10/2017.
 */

public interface SOServiceRicercaCertificazioni
{
    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Call<SOResponseRicercaCertficazioni> getAnswers();

    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Call<SOResponseRicercaCertficazioni> getAnswers(@Query( "tagged" ) String tags);
}
