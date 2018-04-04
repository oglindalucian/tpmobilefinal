package lucian.example.com.projetcircuits;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import lucian.example.com.projetcircuits.Data.CircuitContrat;
import lucian.example.com.projetcircuits.Data.CircuitDBHelper;

public class ListeJours extends AppCompatActivity {
    public static final int TEXT_REQUEST = 20;
    private JourAdapter jAdapter;
  //  long idCircuit;
    long idEtape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_jours);

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
            idEtape = (long) bd.get("EXTRA_ID_ETAPE");
        }


        RecyclerView joursRecyclerView;
        joursRecyclerView = (RecyclerView) this.findViewById(R.id.vue_les_jours);
        joursRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Cursor cursor = obtenirJour();
        jAdapter = new JourAdapter(this, cursor);
        joursRecyclerView.setAdapter(jAdapter);
    }

    public void ajouterJour(View view) {
        Intent intent = new Intent(this, AjouterJour.class);
        intent.putExtra("EXTRA_LE_ID_ETAPE", idEtape);
        startActivityForResult(intent, TEXT_REQUEST);

    }

    private Cursor obtenirJour() {
        return CircuitDBHelper.getInstance(this).getWritableDatabase().query(
                CircuitContrat.Jour.NOM_TABLE,
                null,
                CircuitContrat.Jour.COLONNE_ID_ETAPE + "=" + idEtape,
                null,
                null,
                null,
                CircuitContrat.Jour.COLONNE_NOM_JOUR

        );
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Test for the right intent reply
        if (requestCode == TEXT_REQUEST) {
            // Test to make sure the intent reply result was good.
            if (resultCode == RESULT_OK) {
                String nomJour = data.getStringExtra("EXTRA_NOM_JOUR");
                String etape = data.getStringExtra("EXTRA_ETAPE_JOUR");
                String ville = data.getStringExtra("EXTRA_VILLE_JOUR");
                String hotel = data.getStringExtra("EXTRA_HOTEL_JOUR");
                String restaurant = data.getStringExtra("EXTRA_RESTAURANT_JOUR");

                if (nomJour != "") {
                    ajouterNouveauJour(nomJour, ville, Integer.parseInt(etape), hotel,
                            restaurant);
                    jAdapter.echangerCurseur(obtenirJour());
                }

            }
        }
    }

    private long ajouterNouveauJour (String nom, String ville, int idEtape, String hotel,
                                       String restaurant) {
        ContentValues cv = new ContentValues();
        cv.put(CircuitContrat.Jour.COLONNE_NOM_JOUR, nom);
        cv.put(CircuitContrat.Jour.COLONNE_NOM_VILLE, ville);
        cv.put(CircuitContrat.Jour.COLONNE_ID_ETAPE, idEtape);
        cv.put(CircuitContrat.Jour.COLONNE_HOTEL, hotel);
        cv.put(CircuitContrat.Jour.COLONNE_RESTAURANT, restaurant);

        return CircuitDBHelper.getInstance(this).getWritableDatabase().insert(CircuitContrat.Jour.NOM_TABLE, null, cv);


    }
}
