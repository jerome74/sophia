package indra.com.icertify.activities.ricerca_certificazioni_scadute;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

import indra.com.icertify.R;
import indra.com.icertify.activities.navigation_drawer.BaseNavigationDrawer;
import indra.com.icertify.services.interfaces.ICallsDelegate;

public class RisultatoRicercaCertificazioniScaduteActivity extends BaseNavigationDrawer implements ICallsDelegate
{

    private RisultatoRicercaCertificazioniScaduteActivity risultatoRicercaCertificazioniScaduteActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risultato_ricerca_certificazioni_scadute);

        risultatoRicercaCertificazioniScaduteActivity = this;

        manageDrawer();
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if ( drawer.isDrawerOpen(GravityCompat.START) )
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public void onSearchSuccessfull(Object object)
    {

    }

    @Override
    public void onSearchFailed()
    {

    }

    @Override
    public void onFailure()
    {

    }
}
