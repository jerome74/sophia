package indra.com.icertify.services.interfaces;

import java.util.ArrayList;

import indra.com.icertify.services.models.ricerca_certificazioni.Item;


/**
 * Created by adifrancesco on 25/10/2017.
 */

public interface CallsDelegate
{
    void onCertificationSearchSuccessfull(ArrayList<Item> items);


    void onSearchFailed();
    void onFailure();
}
