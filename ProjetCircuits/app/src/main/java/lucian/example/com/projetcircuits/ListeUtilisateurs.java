package lucian.example.com.projetcircuits;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import lucian.example.com.projetcircuits.Data.CircuitDBHelper;

public class ListeUtilisateurs extends AppCompatActivity {

    ListView listeUtilisateurs;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_utilisateurs);
        listeUtilisateurs = (ListView)findViewById(R.id.listUsers);
        final ArrayList<String> users = new ArrayList<>();
        SQLiteDatabase db = CircuitDBHelper.getInstance(this).getWritableDatabase();
        if((db.rawQuery("SELECT * FROM courrielUtilisateur", null))!=null) {
            Cursor cursor = db.rawQuery("SELECT * FROM courrielUtilisateur", null);

           // if (cursor.getCount() > 0) {
               if(cursor.moveToFirst()) {
                   do {
                       user = cursor.getString(cursor.getColumnIndex("courriel"));
                       users.add(user);
                   } while (cursor.moveToNext());
               }
         //   }
            cursor.close();
         //   db.close();

        }

        ArrayAdapter<String> usersAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, users);
        listeUtilisateurs.setAdapter(usersAdapter);
    }
}
