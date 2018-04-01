package lucian.example.com.projetcircuits.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lucian on 2018-03-30.
 */

public class CircuitDBHelper extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "h18circuits.db";
    private final static int DATABASE_VERSION = 1;

    final String SQL_CREATE_CIRCUIT_TABLE = "CREATE TABLE " +
            CircuitContrat.Circuit.NOM_TABLE + " (" +
            CircuitContrat.Circuit._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            CircuitContrat.Circuit.COLONNE_NOM_CIRCUIT + " TEXT, " +
            CircuitContrat.Circuit.COLONNE_NBRE_PLACES_MIN + " INTEGER, " +
            CircuitContrat.Circuit.COLONNE_NBRE_PLACES_MAX + " INTEGER, " +
            CircuitContrat.Circuit.COLONNE_NBRE_PLACES_RESERVEES + " INTEGER, " +
            CircuitContrat.Circuit.COLONNE_ETAT + " INTEGER, " +
            CircuitContrat.Circuit.COLONNE_DATE_DEPART  + " DATE, " +
            CircuitContrat.Circuit.COLONNE_DATE_ARRIVEE  + " DATE, " +
            CircuitContrat.Circuit.COLONNE_PHOTO_CIRCUIT   + " TEXT, " +
            CircuitContrat.Circuit.COLONNE_PRIX_CIRCUIT   + " INTEGER, " +
            CircuitContrat.Circuit.COLONNE_GUIDE  + " TEXT, " +
            CircuitContrat.Circuit.COLONNE_TRANSPORT  + " TEXT, " +
            CircuitContrat.Circuit.COLONNE_ID_RABAIS  + " INTEGER  " +

            "); ";

    final String SQL_CREATE_ETAPE_TABLE = "CREATE TABLE " +
            CircuitContrat.Etape.NOM_TABLE + " (" +
            CircuitContrat.Etape._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            CircuitContrat.Etape.COLONNE_NOM_ETAPE + " TEXT, " +
            CircuitContrat.Etape.COLONNE_ID_CIRCUIT + " INTEGER, " +
            CircuitContrat.Etape.COLONNE_NBRE_JOUR + " INTEGER, " +
            CircuitContrat.Etape.COLONNE_DATE_ARRIVEE + " DATE, " +
            CircuitContrat.Etape.COLONNE_DATE_DEPART  + " DATE, " +
            CircuitContrat.Etape.COLONNE_DESCRIPTION_ETAPE  + " TEXT, " +
            CircuitContrat.Etape.COLONNE_PHOTO_ETAPE  + " TEXT  " +

            "); ";

    final String SQL_CREATE_JOUR_TABLE = "CREATE TABLE " +
            CircuitContrat.Jour.NOM_TABLE + " (" +
            CircuitContrat.Jour._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            CircuitContrat.Jour.COLONNE_NOM_JOUR + " TEXT, " +
            CircuitContrat.Jour.COLONNE_NOM_VILLE + " TEXT, " +
            CircuitContrat.Jour.COLONNE_ID_ETAPE + " INTEGER, " +
            CircuitContrat.Jour.COLONNE_HOTEL + " TEXT, " +
            CircuitContrat.Jour.COLONNE_RESTAURANT  + " TEXT  " +

            "); ";

    public CircuitDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_CIRCUIT_TABLE);
        db.execSQL(SQL_CREATE_ETAPE_TABLE);
        db.execSQL(SQL_CREATE_JOUR_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CircuitContrat.Circuit.NOM_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CircuitContrat.Etape.NOM_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CircuitContrat.Etape.NOM_TABLE);
        onCreate(db);
    }
}
