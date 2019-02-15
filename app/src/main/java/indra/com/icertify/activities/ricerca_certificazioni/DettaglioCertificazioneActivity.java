package indra.com.icertify.activities.ricerca_certificazioni;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

import indra.com.icertify.R;
import indra.com.icertify.activities.navigation_drawer.BaseNavigationDrawer;
import indra.com.icertify.services.models.ricerca_certificazioni.Item;

public class DettaglioCertificazioneActivity extends BaseNavigationDrawer
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettaglio_certificazione);
        manageDrawer();

        //Recupera i dati
        Intent i = getIntent();
        Item item = (Item) i.getSerializableExtra("ITEM_CERT_DETT");
        if(item != null )
        {

        }
        else
        {
            //Gestione dell'errore
        }
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
}
