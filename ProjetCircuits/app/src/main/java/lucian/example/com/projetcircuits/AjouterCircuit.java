package lucian.example.com.projetcircuits;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import lucian.example.com.projetcircuits.Data.DatePickerFragment;

public class AjouterCircuit extends AppCompatActivity implements View.OnClickListener{

    EditText nom;
   // EditText minPlaces;
    Spinner  minPlaces;
   // EditText maxPlaces;
    Spinner maxPlaces;
    //EditText resPlaces;
    Spinner resPlaces;
   // EditText etat;
    Spinner etat;
    EditText depart;
    EditText arrivee;
    EditText photo;
    EditText prix;
    EditText guide;
   // EditText transport;
    Spinner transport;
    Button choixImage;
    ImageView imageToUpload;
    //private SQLiteDatabase mDb;
    private Calendar startDate;
    private Calendar endDate;
    private Calendar activeDate;
    private TextView activeDateDisplay;

    String stringUri;
    Intent replyIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_circuit);
        replyIntent = new Intent();
        nom = (EditText)this.findViewById(R.id.nom);
        minPlaces = (Spinner)this.findViewById(R.id.min);
        maxPlaces = (Spinner) this.findViewById(R.id.max);
        resPlaces = (Spinner)this.findViewById(R.id.res);
       // etat = (EditText)this.findViewById(R.id.etat);
        depart = (EditText)this.findViewById(R.id.depart);
        arrivee = (EditText)this.findViewById(R.id.arrivee);
        photo = (EditText)this.findViewById(R.id.photo);
        prix = (EditText)this.findViewById(R.id.prix);
        guide = (EditText)this.findViewById(R.id.guide);
        transport = (Spinner)this.findViewById(R.id.transport);

        etat = (Spinner) findViewById(R.id.etat);
        choixImage = (Button)findViewById(R.id.choisir_image);
        imageToUpload = (ImageView)findViewById(R.id.imageChoisie);
        choixImage.setOnClickListener(this);
       // imageToUpload.setOnClickListener(this);


         /* get the current date */
        startDate = Calendar.getInstance();

        depart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDateDialog(depart, startDate);
            }
        });

         /* get the current date */
        endDate = Calendar.getInstance();

    /*
        arrivee.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDateDialog(arrivee, endDate);
            }
        });  */

      //  updateDisplay(depart, startDate);
       // updateDisplay(arrivee, endDate);

      //  CircuitDBHelper dbHelper = new CircuitDBHelper(this);
     //   mDb = dbHelper.getWritableDatabase();

        /*
      arrivee.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                showDatePickerDialog(view);
            }
        });  */

    /*
        depart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("DATE",1);

                DialogFragment newFragment = new DatePickerFragment();
                newFragment.setArguments(bundle);

                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });  */
/*
        arrivee.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                showDatePickerDialog2(view);
            }
        }); */

/*
        arrivee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("DATE",2);

                DialogFragment newFragment = new DatePickerFragment();
                newFragment.setArguments(bundle);

                newFragment.show(getSupportFragmentManager(), "datePicker");

            }
        });  */

    }

    private void updateDisplay(TextView dateDisplay, Calendar date) {
        dateDisplay.setText(
                new StringBuilder()
                        .append(date.get(Calendar.YEAR)).append("-")
                        .append(date.get(Calendar.MONTH) + 1).append("-")
                        .append(date.get(Calendar.DAY_OF_MONTH)));

                     /*   .append(date.get(Calendar.MONTH) + 1).append("-")
                        .append(date.get(Calendar.DAY_OF_MONTH)).append("-")
                        .append(date.get(Calendar.YEAR)).append(" ")); */

    }

    public void showDateDialog(TextView dateDisplay, Calendar date) {
        activeDateDisplay = dateDisplay;
        activeDate = date;

        if(dateDisplay==depart) {
            //showDialog(0);
            /*
            Bundle bundle = new Bundle();
            bundle.putInt("DATE",0);

            DialogFragment newFragment = new DatePickerFragment();
            newFragment.setArguments(bundle);

            newFragment.show(getSupportFragmentManager(), "datePicker");  */
        }
        if(dateDisplay==arrivee) {
           // showDialog(1);
        /*    Bundle bundle = new Bundle();
            bundle.putInt("DATE",1);

            DialogFragment newFragment = new DatePickerFragment();
            newFragment.setArguments(bundle);

            newFragment.show(getSupportFragmentManager(), "datePicker"); */
        }
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            activeDate.set(Calendar.YEAR, year);
            activeDate.set(Calendar.MONTH, monthOfYear);
            activeDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDisplay(activeDateDisplay, activeDate);
            unregisterDateDisplay();
        }
    };

    private void unregisterDateDisplay() {
        activeDateDisplay = null;
        activeDate = null;
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case 0:
                return new DatePickerDialog(this, dateSetListener, activeDate.get(Calendar.YEAR), activeDate.get(Calendar.MONTH), activeDate.get(Calendar.DAY_OF_MONTH));

            case 1:
                return new DatePickerDialog(this, dateSetListener, activeDate.get(Calendar.YEAR), activeDate.get(Calendar.MONTH), activeDate.get(Calendar.DAY_OF_MONTH));

        }
        return null;
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        switch (id) {
            case 0:
                ((DatePickerDialog) dialog).updateDate(activeDate.get(Calendar.YEAR), activeDate.get(Calendar.MONTH), activeDate.get(Calendar.DAY_OF_MONTH));
                break;
            case 1:
                ((DatePickerDialog) dialog).updateDate(activeDate.get(Calendar.YEAR), activeDate.get(Calendar.MONTH), activeDate.get(Calendar.DAY_OF_MONTH));
                break;
        }
    }

    public void ajouterCircuit(View view) {

      if(nom.getText().length()==0) {
            return;
        }
        String nomCircuit = "";
        int min = -1;
        int max = -1;
        int res = -1;
        int etatCircuit = -1;
        String etatC="";
        String departC = "";
        String arriveeC = "";
        int prixC = -1;
        String guideCircuit = "";
        String  transportC ="";
        String photoC = "";

        try {
            /*
            nomCircuit = nom.getText().toString();
            departC = depart.getText().toString();
            arriveeC = arrivee.getText().toString();
            guideCircuit = guide.getText().toString();
            transportC = transport.getSelectedItem().toString();
            min = Integer.parseInt(minPlaces.getSelectedItem().toString());
            max = Integer.parseInt(maxPlaces.getSelectedItem().toString());
            res = Integer.parseInt(resPlaces.getSelectedItem().toString());
           // etatC = etat.getSelectedItem().toString();
           // etatCircuit = Integer.parseInt(etat.getSelectedItem().toString());  */
            prixC = Integer.parseInt(prix.getText().toString());
           // photoC = photo.getText().toString();
           //ajouterNouveauCircuit(nomCircuit ,etatCircuit,min,max,res,departC,arriveeC,prixC,guideCircuit,transportC, photoC);

        }
        catch (NumberFormatException ex) {
           Log.e("", "Erreur lors de la conversion en entier" + ex.getMessage());
          //  Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
            Toast.makeText(this, "Le prix doit etre un nombre", Toast.LENGTH_LONG).show();
           // Toast.makeText(this, etat.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
        }
//if(nomCircuit!="" && min!=-1 && max!=-1 && res!=-1 && departC!="" && arriveeC!="" && prixC!=-1
       // && guideCircuit !="" && transportC !="") {
          //  if(min<max) {
                etatC = etat.getSelectedItem().toString();
                switch(etatC) {
                    case "Actif": etatCircuit=1; break;
                    case "Inactif": etatCircuit=0; break;
                    default: etatCircuit=1;
                }
                  //  replyIntent = new Intent();
                    replyIntent.putExtra("EXTRA_NOM", nom.getText().toString());
                    replyIntent.putExtra("EXTRA_DEPART", depart.getText().toString());
                    replyIntent.putExtra("EXTRA_ARRIVEE", arrivee.getText().toString());
                    replyIntent.putExtra("EXTRA_GUIDE", guide.getText().toString());
                    replyIntent.putExtra("EXTRA_TRANSPORT", transport.getSelectedItem().toString());
                    replyIntent.putExtra("EXTRA_MIN", minPlaces.getSelectedItem().toString()); //Integer.parseInt
                    replyIntent.putExtra("EXTRA_MAX", maxPlaces.getSelectedItem().toString());
                    replyIntent.putExtra("EXTRA_RES", resPlaces.getSelectedItem().toString());
                    replyIntent.putExtra("EXTRA_ETAT", String.valueOf(etatCircuit));
                    replyIntent.putExtra("EXTRA_PRIX", prix.getText().toString());
                   // replyIntent.putExtra("EXTRA_PHOTO", photo.getText().toString());

                    setResult(RESULT_OK, replyIntent);

                    finish();
        //    } else {Toast.makeText(this, "Le nombre de places minimum ne peut pas etre plus grand que le maximum!", Toast.LENGTH_LONG).show();}
    //    } else {
      //      Toast.makeText(this, "Il faut completer tous les champs!", Toast.LENGTH_LONG).show();
            //Toast.makeText(this, Integer.parseInt(etat.getSelectedItem().toString()), Toast.LENGTH_LONG).show();
        //    }

    }


    public void showDatePickerDialog(View v) {
       DialogFragment newFragment = new DatePickerFragment();
       newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onClick(View view) {
        Intent gallerie = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallerie, 1);
    }

    @Override
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

    /*

    public void showDatePickerDialog2(View v) {
        DialogFragment newFragment = new DatePickerFragment2();
        newFragment.show(getSupportFragmentManager(), "datePicker2");
    }  */


    /*
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
        return mDb.insert(CircuitContrat.Circuit.NOM_TABLE, null, cv);

    } */


}
