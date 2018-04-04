package lucian.example.com.projetcircuits;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import lucian.example.com.projetcircuits.Data.CircuitContrat;
import lucian.example.com.projetcircuits.Data.CircuitDBHelper;
import lucian.example.com.projetcircuits.Data.CircuitDataTemp;

public class MainActivity extends AppCompatActivity {

    private CircuitAdapter cAdapter;
  //  public static SQLiteDatabase mDb;

    private final static String LOG_TAG = MainActivity.class.getSimpleName();
    public static final int TEXT_REQUEST = 1;

  //  private OnItemClickListener listener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView circuitRecyclerView;
        circuitRecyclerView = (RecyclerView) this.findViewById(R.id.vue_les_circuits);
        circuitRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    //   CircuitDBHelper dbHelper = new CircuitDBHelper(this);

     //   mDb = dbHelper.getWritableDatabase();
      CircuitDataTemp.insererData(CircuitDBHelper.getInstance(this).getWritableDatabase());//
      Cursor cursor = obtenirCircuit();
      cAdapter = new CircuitAdapter(this, cursor); //, listener

      //  ListView listeCircuits = (ListView)findViewById(R.id.vue_les_circuits);
     //   listeCircuits.setAdapter(cAdapter);

      circuitRecyclerView.setAdapter(cAdapter);


    }







    private Cursor obtenirCircuit() {
        return CircuitDBHelper.getInstance(this).getWritableDatabase().query(
                CircuitContrat.Circuit.NOM_TABLE,
                null,
                null,
                null,
                null,
                null,
                CircuitContrat.Circuit.COLONNE_NOM_CIRCUIT

        );
    }


    public void ajouterCircuit(View view) {
        Intent intent = new Intent(this, AjouterCircuit.class);
        startActivityForResult(intent, TEXT_REQUEST);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Test for the right intent reply
        if (requestCode == TEXT_REQUEST) {
            // Test to make sure the intent reply result was good.
            if (resultCode == RESULT_OK) {
                String nomCircuit = data.getStringExtra("EXTRA_NOM");
                String dateDepart = data.getStringExtra("EXTRA_DEPART");
                String dateArrivee = data.getStringExtra("EXTRA_ARRIVEE");
                String guide = data.getStringExtra("EXTRA_GUIDE");
                String transport = data.getStringExtra("EXTRA_TRANSPORT");
                String min = data.getStringExtra("EXTRA_MIN");
                String max = data.getStringExtra("EXTRA_MAX");
                String res = data.getStringExtra("EXTRA_RES");
                String etat = data.getStringExtra("EXTRA_ETAT");
                String prix = data.getStringExtra("EXTRA_PRIX");

                String photo = data.getStringExtra("EXTRA_PHOTO");

                if (nomCircuit != "" && dateDepart != "" && dateArrivee != "" && guide!="" && transport!="" && min!="" && max!=""
                        && res!="" && etat!="" && prix!="" && photo!="") {
                    if(prix=="")
                        prix="5000";
                    ajouterNouveauCircuit(nomCircuit, Integer.parseInt(etat), Integer.parseInt(min), Integer.parseInt(max),
                            Integer.parseInt(res), dateDepart, dateArrivee, Integer.parseInt(prix), guide, transport, photo);
                    cAdapter.echangerCurseur(obtenirCircuit());
                }

            }
        }
    }

    private long ajouterNouveauCircuit (String nom, int etat, int min, int max, int res, String depart,
                                        String arrivee, int prix, String guide, String transport, String photoC) {
        ContentValues cv = new ContentValues();
        cv.put(CircuitContrat.Circuit.COLONNE_NOM_CIRCUIT, nom);
        cv.put(CircuitContrat.Circuit.COLONNE_NBRE_PLACES_MIN, min);
        cv.put(CircuitContrat.Circuit.COLONNE_NBRE_PLACES_MAX, max);
        cv.put(CircuitContrat.Circuit.COLONNE_NBRE_PLACES_RESERVEES, res);
        cv.put(CircuitContrat.Circuit.COLONNE_ETAT, etat);
        cv.put(CircuitContrat.Circuit.COLONNE_DATE_DEPART, depart);
        cv.put(CircuitContrat.Circuit.COLONNE_DATE_ARRIVEE, arrivee);
        cv.put(CircuitContrat.Circuit.COLONNE_PHOTO_CIRCUIT, photoC);
        cv.put(CircuitContrat.Circuit.COLONNE_PRIX_CIRCUIT, prix);
        cv.put(CircuitContrat.Circuit.COLONNE_GUIDE, guide);
        cv.put(CircuitContrat.Circuit.COLONNE_TRANSPORT, transport);
        return CircuitDBHelper.getInstance(this).getWritableDatabase().insert(CircuitContrat.Circuit.NOM_TABLE, null, cv);

    }
}
