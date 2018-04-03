package lucian.example.com.projetcircuits;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import lucian.example.com.projetcircuits.Data.BaseDeDonnees;
import lucian.example.com.projetcircuits.Data.CircuitContrat;

public class ListeEtapes extends AppCompatActivity {
    public static final int TEXT_REQUEST = 10;
    private EtapesAdapter eAdapter;
   // private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_etapes);

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
        Intent intent = new Intent(this, AjouterEtape.class);
        startActivityForResult(intent, TEXT_REQUEST);

    }

    private Cursor obtenirEtape() {
        return BaseDeDonnees.CircuitDBHelper.getDatabase().query(
                CircuitContrat.Etape.NOM_TABLE,
                null,
                null,
                null,
                null,
                null,
                CircuitContrat.Etape.COLONNE_NOM_ETAPE

        );
    }
}
