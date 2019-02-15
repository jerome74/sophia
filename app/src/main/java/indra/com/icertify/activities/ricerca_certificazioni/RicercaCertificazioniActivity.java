package indra.com.icertify.activities.ricerca_certificazioni;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import indra.com.icertify.R;
import indra.com.icertify.activities.navigation_drawer.BaseNavigationDrawer;
import indra.com.icertify.services.interfaces.ICallsDelegate;
import indra.com.icertify.services.interfaces.SOServiceRicercaCertificazioni;
import indra.com.icertify.services.models.ricerca_certificazioni.Item;
import indra.com.icertify.services.services.Calls.CallsToRicercaCertificazioni;
import indra.com.icertify.utility.CustomProgressDialog;

public class RicercaCertificazioniActivity extends BaseNavigationDrawer implements ICallsDelegate
{
    //private RecyclerView mRecyclerView;
    private SOServiceRicercaCertificazioni mService;
    List<Item> items;

    private CustomProgressDialog customProgressDialog;
    RicercaCertificazioniActivity ricercaCertificazioniActivity = null;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ricerca_certificazioni);
        ricercaCertificazioniActivity = this;
        manageDrawer();

        RicercaCertificazioni();
    }


    /**
     * Registra il pulsante e sta in ascolto del click
     * Alla pressione del pulsante effettua la ricerca delle certificazioni
     */
    private void RicercaCertificazioni()
    {
        //Start Certification Search
        Button btRicercaCert = (Button)findViewById(R.id.id_button_ricerca_certificazioni);
        btRicercaCert.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if ( customProgressDialog == null )
                {
                    customProgressDialog = new CustomProgressDialog(ricercaCertificazioniActivity);
                }

                CallsToRicercaCertificazioni callsToRicercaCertificazioni = new CallsToRicercaCertificazioni();
                callsToRicercaCertificazioni.load(getApplicationContext(), ricercaCertificazioniActivity);

                customProgressDialog.show();
            }
        });
    }


    /**
     *
     */
    @Override
    public void onBackPressed()
    {
        if( customProgressDialog != null )
            customProgressDialog.dismiss();

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


    /**
     *
     * @param object
     */
    @Override
    public void onSearchSuccessfull(Object object)
    {
        ArrayList<Item> items = (ArrayList<Item>)object;
        customProgressDialog.dismiss();
        Intent intent = new Intent(RicercaCertificazioniActivity.this, RisultatoRicercaCertificazioniActivity.class);
        intent.putExtra("ITEM_CERT", items);
        startActivity(intent);
    }


    /**
     *
     */
    @Override
    public void onSearchFailed()
    {
        Toast.makeText(ricercaCertificazioniActivity, R.string.search_failed, Toast.LENGTH_SHORT).show();
        customProgressDialog.dismiss();
    }


    /**
     *
     */
    @Override
    public void onFailure()
    {
        Toast.makeText(ricercaCertificazioniActivity, R.string.search_failed, Toast.LENGTH_SHORT).show();
        customProgressDialog.dismiss();
    }
}
