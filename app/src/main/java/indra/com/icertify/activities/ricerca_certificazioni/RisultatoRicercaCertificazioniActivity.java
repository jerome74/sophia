package indra.com.icertify.activities.ricerca_certificazioni;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import indra.com.icertify.R;
import indra.com.icertify.activities.navigation_drawer.BaseNavigationDrawer;
import indra.com.icertify.services.adapters.ListCertificazioniAdapter;
import indra.com.icertify.services.interfaces.ICallsDelegate;
import indra.com.icertify.services.models.ricerca_certificazioni.Item;
import indra.com.icertify.services.services.Calls.CallsToRicercaCertificazioni;

public class RisultatoRicercaCertificazioniActivity extends BaseNavigationDrawer implements ICallsDelegate
{
    private ListView mListView;
    private ListCertificazioniAdapter mAdapter;
    private SwipeRefreshLayout swipeRefresh;
    private RisultatoRicercaCertificazioniActivity risultatoRicercaCertificazioniActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risultato_ricerca_certificazioni);

        risultatoRicercaCertificazioniActivity = this;

        manageDrawer();
        managePullToRefresh();
        manageSearchFromPreviewActivity();
    }


    /**
     * Gestisce e visualizza la prima ricerca della activity precedente
     */
    private void manageSearchFromPreviewActivity()
    {
        //Recupera i dati dalla ricerca della pagina precedente
        Intent i = getIntent();
        ArrayList<Item> items = (ArrayList<Item>) i.getSerializableExtra("ITEM_CERT");
        if(items != null && items.size() > 0 )
        {
            mListView = (ListView) findViewById(R.id.id_lista_risultato_certificazioni);
            mAdapter = new ListCertificazioniAdapter(getApplicationContext(), items);
            mListView.setAdapter(mAdapter);
        }
        else
        {
            //Gestione dell'errore
        }
    }


    /**
     * Gestisce il pull to refresh della lista
     */
    private void managePullToRefresh()
    {
        swipeRefresh = (SwipeRefreshLayout)findViewById(R.id.id_swiperefresh_certificazioni);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                CallsToRicercaCertificazioni callsToRicercaCertificazioni = new CallsToRicercaCertificazioni();
                callsToRicercaCertificazioni.load(getApplicationContext(), risultatoRicercaCertificazioniActivity);
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
        ArrayList<Item> items = (ArrayList<Item>)object;
        swipeRefresh.setRefreshing(false);
        mAdapter.updateAnswers(items);
    }

    @Override
    public void onSearchFailed()
    {
        swipeRefresh.setRefreshing(false);
        Toast.makeText(risultatoRicercaCertificazioniActivity, R.string.search_failed, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure()
    {
        swipeRefresh.setRefreshing(false);
        Toast.makeText(risultatoRicercaCertificazioniActivity, R.string.search_failed, Toast.LENGTH_SHORT).show();
    }
}
