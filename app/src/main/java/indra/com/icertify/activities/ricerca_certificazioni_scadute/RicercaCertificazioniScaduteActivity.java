package indra.com.icertify.activities.ricerca_certificazioni_scadute;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import indra.com.icertify.R;
import indra.com.icertify.activities.navigation_drawer.BaseNavigationDrawer;
import indra.com.icertify.services.interfaces.ICallsDelegate;
import indra.com.icertify.services.models.ricerca_certificazioni_scadute.SOResponseRicercaCertficazioniScadute;
import indra.com.icertify.services.services.Calls.CallsToRicercaCertificazioniScadute;
import indra.com.icertify.utility.CustomProgressDialog;

public class RicercaCertificazioniScaduteActivity extends BaseNavigationDrawer implements ICallsDelegate
{
    private CustomProgressDialog customProgressDialog;
    RicercaCertificazioniScaduteActivity ricercaCertificazioniScaduteActivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ricerca_certificazioni_scadute);
        ricercaCertificazioniScaduteActivity = this;
        manageDrawer();

        RicercaCertificazioniScadute();
    }


    /**
     * Registra il pulsante e sta in ascolto del click
     * Alla pressione del pulsante effettua la ricerca delle certificazioni scadute
     */
    private void RicercaCertificazioniScadute()
    {
        //Start Certification Search
        Button btRicercaCertScadute = (Button)findViewById(R.id.id_button_ricerca_certificazioni_scadute);
        btRicercaCertScadute.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if ( customProgressDialog == null )
                {
                    customProgressDialog = new CustomProgressDialog(ricercaCertificazioniScaduteActivity);
                }

                CallsToRicercaCertificazioniScadute callsToRicercaCertificazioniScadute = new CallsToRicercaCertificazioniScadute();
                callsToRicercaCertificazioniScadute.load(getApplicationContext(), ricercaCertificazioniScaduteActivity);

                customProgressDialog.show();
            }
        });
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
        SOResponseRicercaCertficazioniScadute soResponseRicercaCertficazioniScadute = (SOResponseRicercaCertficazioniScadute) object;
        Intent intent = new Intent(RicercaCertificazioniScaduteActivity.this, RisultatoRicercaCertificazioniScaduteActivity.class);
        startActivity(intent);

        customProgressDialog.dismiss();
    }

    @Override
    public void onSearchFailed()
    {
        customProgressDialog.dismiss();
        Toast.makeText(ricercaCertificazioniScaduteActivity, R.string.search_failed, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure()
    {
        customProgressDialog.dismiss();
        Toast.makeText(ricercaCertificazioniScaduteActivity, R.string.search_failed, Toast.LENGTH_SHORT).show();
    }
}
