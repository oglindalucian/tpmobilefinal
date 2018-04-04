package lucian.example.com.projetcircuits;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import lucian.example.com.projetcircuits.Data.DatePickerFragment;

public class AjouterEtape extends AppCompatActivity implements View.OnClickListener{

    EditText nom;
    Spinner nbJours;
    EditText arrivee;
    EditText depart;
    EditText descriptionEtape;
    Button choixImage;
    ImageView imageToUpload;

    //private SQLiteDatabase mDb;
    private Calendar startDate;
    private Calendar endDate;
    private Calendar activeDate;
    private TextView activeDateDisplay;

    String stringUri;
    Intent replyIntent;
    long idCircuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_etape);
        replyIntent = new Intent();

        Intent i = getIntent();
        Bundle bd = i.getExtras();
        if(bd != null)
        {
            idCircuit = (long) bd.get("EXTRA_LE_ID_CIRCUIT");
        }


       // Intent i = getIntent();
      //  idCircuit = (long)Integer.parseInt(i.getStringExtra("EXTRA_LE_ID_CIRCUIT"));
        //idCircuit = Integer.parseInt(getIntent().getStringExtra("EXTRA_LE_ID_CIRCUIT")); //?

        nom = (EditText)this.findViewById(R.id.nom);
        nbJours = (Spinner)this.findViewById(R.id.nbJours);
        arrivee = (EditText)this.findViewById(R.id.arrivee);
        depart = (EditText)this.findViewById(R.id.depart);
        descriptionEtape = (EditText)this.findViewById(R.id.descriptionEtape);
        choixImage = (Button)this.findViewById(R.id.choisir_image);
        imageToUpload = (ImageView)this.findViewById(R.id.imageChoisie);

        choixImage.setOnClickListener(this);

        depart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                showDatePickerDialog(view);
            }
        });
    }

    public void ajouterEtape(View view) {

        if(nom.getText().length()==0) {
            return;
        }

/*
        try {

            prixC = Integer.parseInt(prix.getText().toString());


        }
        catch (NumberFormatException ex) {
            Log.e("", "Erreur lors de la conversion en entier" + ex.getMessage());
            //  Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
            Toast.makeText(this, "Le prix doit etre un nombre", Toast.LENGTH_LONG).show();
            // Toast.makeText(this, etat.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
        }
        */
//if(nomCircuit!="" && min!=-1 && max!=-1 && res!=-1 && departC!="" && arriveeC!="" && prixC!=-1
        // && guideCircuit !="" && transportC !="") {
        //  if(min<max) {

        //  replyIntent = new Intent();
        replyIntent.putExtra("EXTRA_NOM", nom.getText().toString());
        replyIntent.putExtra("EXTRA_CIRCUIT", String.valueOf(idCircuit));
        replyIntent.putExtra("EXTRA_NBRE_JOURS", nbJours.getSelectedItem().toString());
        replyIntent.putExtra("EXTRA_ARRIVEE", arrivee.getText().toString());
        replyIntent.putExtra("EXTRA_DEPART", depart.getText().toString());
        replyIntent.putExtra("EXTRA_DESCRIPTION", descriptionEtape.getText().toString());
        // replyIntent.putExtra("EXTRA_PHOTO", photo.getText().toString());

        setResult(RESULT_OK, replyIntent);

        finish();
        //    } else {Toast.makeText(this, "Le nombre de places minimum ne peut pas etre plus grand que le maximum!", Toast.LENGTH_LONG).show();}
        //    } else {
        //      Toast.makeText(this, "Il faut completer tous les champs!", Toast.LENGTH_LONG).show();
        //Toast.makeText(this, Integer.parseInt(etat.getSelectedItem().toString()), Toast.LENGTH_LONG).show();
        //    }

    }


    public void onClick(View view) {
        Intent gallerie = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallerie, 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null) {
            Uri imageSelectionne = data.getData();
            imageToUpload.setImageURI(imageSelectionne);
            stringUri = imageSelectionne.toString();
            replyIntent.putExtra("EXTRA_PHOTO", stringUri);
            Toast.makeText(this, stringUri, Toast.LENGTH_LONG).show();
        }

    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}
