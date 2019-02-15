package indra.com.icertify.services.models.ricerca_certificazioni_scadute;

/**
 * Created by adifrancesco on 20/10/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SOResponseRicercaCertficazioniScadute implements Serializable
{
    @SerializedName("has_more")
    @Expose
    private Boolean hasMore;

    public Boolean getHasMore() {
        return hasMore;
    }

}
