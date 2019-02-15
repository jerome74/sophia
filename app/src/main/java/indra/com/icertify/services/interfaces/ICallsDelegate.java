package indra.com.icertify.services.interfaces;

/**
 * Created by adifrancesco on 25/10/2017.
 */

public interface ICallsDelegate
{
    void onSearchSuccessfull(Object object);


    void onSearchFailed();
    void onFailure();
}
