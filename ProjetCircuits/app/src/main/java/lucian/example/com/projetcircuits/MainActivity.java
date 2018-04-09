package lucian.example.com.projetcircuits;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import lucian.example.com.projetcircuits.Data.CircuitContrat;
import lucian.example.com.projetcircuits.Data.CircuitDBHelper;
import lucian.example.com.projetcircuits.Data.CircuitDataTemp;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    private CircuitAdapter cAdapter;
  //  public static SQLiteDatabase mDb;

    private final static String LOG_TAG = MainActivity.class.getSimpleName();
    public static final int TEXT_REQUEST = 1;
    Button connecterUtilisateur;
    Button ajouterCircuit;
    Button logOutBtn;
    String verifierAdmin;
    TextView msgBienvenu;
    Button listeCourriels;
    Button anglais;
    Button francais;
    Button liste;
    Button arriere;
    ImageView leLogo;
    RecyclerView circuitRecyclerView;


  //  private OnItemClickListener listener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        circuitRecyclerView = (RecyclerView) this.findViewById(R.id.vue_les_circuits);
        circuitRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        msgBienvenu = (TextView)this.findViewById(R.id.bienvenu);
        msgBienvenu.setText(R.string.bienvenu1);
        connecterUtilisateur=(Button) this.findViewById(R.id.bouton_signIn);
        ajouterCircuit = (Button) this.findViewById(R.id.bouton_ajouter);
        logOutBtn = (Button) this.findViewById(R.id.bouton_logOut);
        listeCourriels = (Button) this.findViewById(R.id.bouton_emailList);
        anglais = (Button) this.findViewById(R.id.bouton_anglais);
        francais = (Button) this.findViewById(R.id.bouton_francais);
        liste = (Button) this.findViewById(R.id.bouton_showListe);
        arriere = (Button) this.findViewById(R.id.bouton_arriere);
        leLogo = (ImageView)this.findViewById(R.id.img_logo);
        anglais.setOnClickListener(this);
        francais.setOnClickListener(this);
        liste.setOnClickListener(this);
        arriere.setOnClickListener(this);

        ajouterCircuit.setVisibility(View.GONE);
        logOutBtn.setVisibility(View.GONE);
        listeCourriels.setVisibility(View.GONE);
        arriere.setVisibility(View.GONE);
        circuitRecyclerView.setVisibility(View.GONE);

        if(getIntent()!=null) {
            Intent i = getIntent();
            Bundle bd = i.getExtras();
            if (bd != null) {
                verifierAdmin = (String) bd.get("EXTRA_CONNECT");
                msgBienvenu.setText(R.string.bienvenu2 + verifierAdmin + "!");
                logOutBtn.setVisibility(View.VISIBLE);
                if (verifierAdmin.compareTo("oglindalucian@gmail.com")==0) {
                          ajouterCircuit.setVisibility(View.VISIBLE);
                          connecterUtilisateur.setVisibility(View.GONE);
                          listeCourriels.setVisibility(View.VISIBLE);
                       CircuitDataTemp.insererAdmin(CircuitDBHelper.getInstance(this).getWritableDatabase());
                }
                else {
                  // ContentValues cv = new ContentValues();
                  // cv.put(CircuitContrat.Login.COLONNE_LOGIN, verifierAdmin);
                  // CircuitDBHelper.getInstance(this).getWritableDatabase().insert(CircuitContrat.Login.NOM_TABLE, null, cv);
                    CircuitDataTemp.ajouterUnCourriel(CircuitDBHelper.getInstance(this).getWritableDatabase(), verifierAdmin);
                   connecterUtilisateur.setVisibility(View.GONE);

                  //  CircuitDataTemp.ajouterListeCourriels(CircuitDBHelper.getInstance(this).getWritableDatabase(), verifierAdmin);
                }
            }
        }

     // CircuitDataTemp.insererData(CircuitDBHelper.getInstance(this).getWritableDatabase());//
      Cursor cursor = obtenirCircuit();
      cAdapter = new CircuitAdapter(this, cursor);
      circuitRecyclerView.setAdapter(cAdapter);
    }

    public void signIn(View view) {
      startActivity(new Intent(this, SignIn.class));
    }

    public void logOut(View view) {
        CircuitDataTemp.deconnexion(CircuitDBHelper.getInstance(this).getWritableDatabase());
        connecterUtilisateur.setVisibility(View.VISIBLE);
        logOutBtn.setVisibility(View.GONE);
        ajouterCircuit.setVisibility(View.GONE);
        listeCourriels.setVisibility(View.GONE);
        Toast.makeText(this, R.string.msg_vous_vous_etes_deconnecte, Toast.LENGTH_LONG).show();
        msgBienvenu.setText(R.string.bienvenu1);
    }

    public void listerCourriels(View view) {
        startActivity(new Intent(this, ListeUtilisateurs.class));
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

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.bouton_anglais:
                String languageToLoad  = "en";
                Locale locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
            /*    Intent intent = getIntent();
                finish();
                startActivity(intent);  */
                finish();

                startActivity(getIntent());
                break;

            case R.id.bouton_francais:
                String languageToLoad2  = "fr";
                Locale locale2 = new Locale(languageToLoad2);
                Locale.setDefault(locale2);
                Configuration config2 = new Configuration();
                config2.locale = locale2;
                getBaseContext().getResources().updateConfiguration(config2,
                        getBaseContext().getResources().getDisplayMetrics());
         /*      Intent intent2 = getIntent();
                finish();
                startActivity(intent2);   */
                finish();

                startActivity(getIntent());
                break;

            case R.id.bouton_showListe:
                leLogo.setVisibility(View.GONE);
                anglais.setVisibility(View.GONE);
                francais.setVisibility(View.GONE);
                msgBienvenu.setVisibility(View.GONE);
                listeCourriels.setVisibility(View.GONE);
                ajouterCircuit.setVisibility(View.GONE);
                connecterUtilisateur.setVisibility(View.GONE);
                logOutBtn.setVisibility(View.GONE);
                liste.setVisibility(View.GONE);
                arriere.setVisibility(View.VISIBLE);
                circuitRecyclerView.setVisibility(View.VISIBLE);

                break;

            case R.id.bouton_arriere:
                leLogo.setVisibility(View.VISIBLE);
                anglais.setVisibility(View.VISIBLE);
                francais.setVisibility(View.VISIBLE);
                msgBienvenu.setVisibility(View.VISIBLE);
                liste.setVisibility(View.VISIBLE);
                arriere.setVisibility(View.GONE);
                circuitRecyclerView.setVisibility(View.GONE);

                /*
                listeCourriels.setVisibility(View.VISIBLE);
                ajouterCircuit.setVisibility(View.VISIBLE);
                connecterUtilisateur.setVisibility(View.VISIBLE);
                logOutBtn.setVisibility(View.VISIBLE);  */

                finish();
                startActivity(getIntent());


                break;
        }


    }

    /*
    @Override
    public void onDestroy() {
        super.onDestroy();
        CircuitDataTemp.deconnexion(CircuitDBHelper.getInstance(this).getWritableDatabase());
    }  */
}
