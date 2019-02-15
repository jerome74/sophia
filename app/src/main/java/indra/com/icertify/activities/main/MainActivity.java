package indra.com.icertify.activities.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.Button;

import indra.com.icertify.R;
import indra.com.icertify.activities.navigation_drawer.BaseNavigationDrawer;
import indra.com.icertify.activities.ricerca_certificazioni.RicercaCertificazioniActivity;
import indra.com.icertify.activities.ricerca_certificazioni_scadute.RicercaCertificazioniScaduteActivity;

public class MainActivity extends BaseNavigationDrawer
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manageDrawer();

        Button btRicercaCertificazioni = (Button)findViewById(R.id.id_button_main_ricerca_certificazioni);
        btRicercaCertificazioni.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(MainActivity.this, RicercaCertificazioniActivity.class);
                startActivity(i);
            }
        });

        Button btRicercaCertificazioniScadute = (Button)findViewById(R.id.id_button_main_ricerca_certificazioni_scadute);
        btRicercaCertificazioniScadute.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(MainActivity.this, RicercaCertificazioniScaduteActivity.class);
                startActivity(i);
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
            moveTaskToBack(true);
//            super.onBackPressed();
        }
    }
}
