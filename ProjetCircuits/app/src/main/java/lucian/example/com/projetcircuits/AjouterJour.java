package lucian.example.com.projetcircuits;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import lucian.example.com.projetcircuits.Data.DatePickerFragment;

public class AjouterJour extends AppCompatActivity {

    EditText nom;
    EditText ville;
    EditText leHotel;
    EditText leRestaurant;
    Intent replyIntent;
    long idEtape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_jour);

        replyIntent = new Intent();

        Intent i = getIntent();
        Bundle bd = i.getExtras();
        if(bd != null)
        {
            idEtape = (long) bd.get("EXTRA_LE_ID_ETAPE");
        }




        nom = (EditText)this.findViewById(R.id.nom);
        ville = (EditText)this.findViewById(R.id.nomVille);
        leHotel = (EditText)this.findViewById(R.id.hotel);
        leRestaurant = (EditText)this.findViewById(R.id.restaurant);
    }

    public void ajouterJour(View view) {

        if(nom.getText().length()==0) {
            return;
        }


        replyIntent.putExtra("EXTRA_NOM_JOUR", nom.getText().toString());
        replyIntent.putExtra("EXTRA_ETAPE_JOUR", String.valueOf(idEtape));
        replyIntent.putExtra("EXTRA_VILLE_JOUR", ville.getText().toString());
        replyIntent.putExtra("EXTRA_HOTEL_JOUR", leHotel.getText().toString());
        replyIntent.putExtra("EXTRA_RESTAURANT_JOUR", leRestaurant.getText().toString());

        setResult(RESULT_OK, replyIntent);

        finish();
    }
}
