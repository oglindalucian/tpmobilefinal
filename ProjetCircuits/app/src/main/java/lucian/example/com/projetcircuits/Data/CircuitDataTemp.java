package lucian.example.com.projetcircuits.Data;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucian on 2018-03-30.
 */

public class CircuitDataTemp {
    public static void insererData(SQLiteDatabase db){
        if(db == null){
            return;
        }
        //create a list of fake guests
        List<ContentValues> list = new ArrayList<ContentValues>();

        ContentValues cv = new ContentValues();
        cv.put(CircuitContrat.Circuit.COLONNE_NOM_CIRCUIT, "Europe Nord Ouest");
        cv.put(CircuitContrat.Circuit.COLONNE_NBRE_PLACES_MIN, 12);
        cv.put(CircuitContrat.Circuit.COLONNE_NBRE_PLACES_MAX, 22);
        cv.put(CircuitContrat.Circuit.COLONNE_NBRE_PLACES_RESERVEES, 12);
        cv.put(CircuitContrat.Circuit.COLONNE_ETAT, 1);
        cv.put(CircuitContrat.Circuit.COLONNE_DATE_DEPART, "2018-03-30");
        cv.put(CircuitContrat.Circuit.COLONNE_DATE_ARRIVEE, "2018-04-30");
        cv.put(CircuitContrat.Circuit.COLONNE_PHOTO_CIRCUIT, "");
        cv.put(CircuitContrat.Circuit.COLONNE_PRIX_CIRCUIT, 5000);
        cv.put(CircuitContrat.Circuit.COLONNE_GUIDE, "John");
        cv.put(CircuitContrat.Circuit.COLONNE_TRANSPORT, "avion");

        list.add(cv);

        cv = new ContentValues();
        cv.put(CircuitContrat.Circuit.COLONNE_NOM_CIRCUIT, "Europe");
        cv.put(CircuitContrat.Circuit.COLONNE_NBRE_PLACES_MIN, 12);
        cv.put(CircuitContrat.Circuit.COLONNE_NBRE_PLACES_MAX, 22);
        cv.put(CircuitContrat.Circuit.COLONNE_NBRE_PLACES_RESERVEES, 12);
        cv.put(CircuitContrat.Circuit.COLONNE_ETAT, 1);
        cv.put(CircuitContrat.Circuit.COLONNE_DATE_DEPART, "2018-03-30");
        cv.put(CircuitContrat.Circuit.COLONNE_DATE_ARRIVEE, "2018-04-30");
        cv.put(CircuitContrat.Circuit.COLONNE_PHOTO_CIRCUIT, "");
        cv.put(CircuitContrat.Circuit.COLONNE_PRIX_CIRCUIT, 5000);
        cv.put(CircuitContrat.Circuit.COLONNE_GUIDE, "Johnyn");
        cv.put(CircuitContrat.Circuit.COLONNE_TRANSPORT, "bus");

        list.add(cv);

        cv = new ContentValues();
        cv.put(CircuitContrat.Etape.COLONNE_NOM_ETAPE, "Londres");
        cv.put(CircuitContrat.Etape.COLONNE_ID_CIRCUIT, 1);
        cv.put(CircuitContrat.Etape.COLONNE_NBRE_JOUR, 2);
        cv.put(CircuitContrat.Etape.COLONNE_DATE_ARRIVEE, "2018-03-30");
        cv.put(CircuitContrat.Etape.COLONNE_DATE_DEPART, "2018-04-01");
        cv.put(CircuitContrat.Etape.COLONNE_DESCRIPTION_ETAPE, "Sejour a Londres");
        cv.put(CircuitContrat.Etape.COLONNE_PHOTO_ETAPE, "");

        list.add(cv);

        cv = new ContentValues();
        cv.put(CircuitContrat.Etape.COLONNE_NOM_ETAPE, "Paris");
        cv.put(CircuitContrat.Etape.COLONNE_ID_CIRCUIT, 1);
        cv.put(CircuitContrat.Etape.COLONNE_NBRE_JOUR, 2);
        cv.put(CircuitContrat.Etape.COLONNE_DATE_ARRIVEE, "2018-04-01");
        cv.put(CircuitContrat.Etape.COLONNE_DATE_DEPART, "2018-04-03");
        cv.put(CircuitContrat.Etape.COLONNE_DESCRIPTION_ETAPE, "Sejour a Paris");
        cv.put(CircuitContrat.Etape.COLONNE_PHOTO_ETAPE, "");

        list.add(cv);

        cv = new ContentValues();
        cv.put(CircuitContrat.Etape.COLONNE_NOM_ETAPE, "Rome");
        cv.put(CircuitContrat.Etape.COLONNE_ID_CIRCUIT, 2);
        cv.put(CircuitContrat.Etape.COLONNE_NBRE_JOUR, 2);
        cv.put(CircuitContrat.Etape.COLONNE_DATE_ARRIVEE, "2018-03-30");
        cv.put(CircuitContrat.Etape.COLONNE_DATE_DEPART, "2018-04-01");
        cv.put(CircuitContrat.Etape.COLONNE_DESCRIPTION_ETAPE, "Sejour a Rome");
        cv.put(CircuitContrat.Etape.COLONNE_PHOTO_ETAPE, "");

        list.add(cv);

        //insert all guests in one transaction
        try
        {
            db.beginTransaction();
            //clear the table first
            db.delete (CircuitContrat.Circuit.NOM_TABLE,null,null);
            //go through the list and add one by one
            for(ContentValues c:list){
                db.insert(CircuitContrat.Circuit.NOM_TABLE, null, c);
            }
            db.setTransactionSuccessful();
        }
        catch (SQLException e) {
            //too bad :(
        }
        finally
        {
            db.endTransaction();
        }

    }
}
