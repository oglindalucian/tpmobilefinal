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

public class ListeEtapes extends AppCompatActivity {
    public static final int TEXT_REQUEST = 10;
    private EtapesAdapter eAdapter;
   // private SQLiteDatabase mDb;
   long idCircuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_etapes);

       // idCircuit= (int) savedInstanceState.getSerializable("EXTRA_ID_CIRCUIT"); //?
       // idCircuit = Integer.parseInt(getIntent().getStringExtra("EXTRA_ID_CIRCUIT"));

       Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
            idCircuit = (long) bd.get("EXTRA_ID_CIRCUIT");
        }

        RecyclerView etapesRecyclerView;
        etapesRecyclerView = (RecyclerView) this.findViewById(R.id.vue_les_etapes);
        etapesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
     //   CircuitDBHelper dbHelper = new CircuitDBHelper(this);
      //  MainActivity.mDb = dbHelper.getDatabase();
        Cursor cursor = obtenirEtape();
        eAdapter = new EtapesAdapter(this, cursor);
        etapesRecyclerView.setAdapter(eAdapter);
    }

    public void ajouterEtape(View view) {
     /*   Intent i = getIntent();
        Bundle bd = i.getExtras();
        if(bd != null)
        {
            idCircuit = (long) bd.get("EXTRA_ID_CIRCUIT");
        } */
        Intent intent = new Intent(this, AjouterEtape.class);
        intent.putExtra("EXTRA_LE_ID_CIRCUIT", idCircuit);
        startActivityForResult(intent, TEXT_REQUEST);

    }

    private Cursor obtenirEtape() {
        return CircuitDBHelper.getInstance(this).getWritableDatabase().query(
                CircuitContrat.Etape.NOM_TABLE,
                null,
                CircuitContrat.Etape.COLONNE_ID_CIRCUIT + "=" + idCircuit,
                null,
                null,
                null,
                CircuitContrat.Etape.COLONNE_NOM_ETAPE

        );
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Test for the right intent reply
        if (requestCode == TEXT_REQUEST) {
            // Test to make sure the intent reply result was good.
            if (resultCode == RESULT_OK) {
                String nomEtape = data.getStringExtra("EXTRA_NOM");
                String circuit = data.getStringExtra("EXTRA_CIRCUIT");
                String nbJours = data.getStringExtra("EXTRA_NBRE_JOURS");
                String dateArrivee = data.getStringExtra("EXTRA_ARRIVEE");
                String dateDepart = data.getStringExtra("EXTRA_DEPART");
                String description = data.getStringExtra("EXTRA_DESCRIPTION");
                String photo = data.getStringExtra("EXTRA_PHOTO");
                if (nomEtape != "") {
                    ajouterNouvelleEtape(nomEtape, Integer.parseInt(circuit), Integer.parseInt(nbJours), dateArrivee,
                            dateDepart,  description , photo);
                    eAdapter.echangerCurseur(obtenirEtape());
                }

            }
        }
    }

    private long ajouterNouvelleEtape (String nom, int idCircuit, int nbJours, String arrivee, String depart,
                                         String descriptionEtape, String photoE) {
        ContentValues cv = new ContentValues();
        cv.put(CircuitContrat.Etape.COLONNE_NOM_ETAPE, nom);
        cv.put(CircuitContrat.Etape.COLONNE_ID_CIRCUIT, idCircuit);
        cv.put(CircuitContrat.Etape.COLONNE_NBRE_JOUR, nbJours);
        cv.put(CircuitContrat.Etape.COLONNE_DATE_ARRIVEE, arrivee);
        cv.put(CircuitContrat.Etape.COLONNE_DATE_DEPART, depart);
        cv.put(CircuitContrat.Etape.COLONNE_DESCRIPTION_ETAPE, descriptionEtape);
        cv.put(CircuitContrat.Etape.COLONNE_PHOTO_ETAPE, photoE);

        return CircuitDBHelper.getInstance(this).getWritableDatabase().insert(CircuitContrat.Etape.NOM_TABLE, null, cv);

    }
}
