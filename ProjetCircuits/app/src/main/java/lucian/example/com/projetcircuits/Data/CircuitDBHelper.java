package lucian.example.com.projetcircuits.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import lucian.example.com.projetcircuits.App;

/**
 * Created by lucian on 2018-04-04.
 */

public class CircuitDBHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "h18circuits.db";
    private final static int DATABASE_VERSION = 1;
    private static CircuitDBHelper circuitDBHelper;// = new CircuitDBHelper();

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

    final String SQL_CREATE_ADMIN_TABLE = "CREATE TABLE " +
            CircuitContrat.Admin.NOM_TABLE + " (" +
            CircuitContrat.Admin._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            CircuitContrat.Admin.COLONNE_IS_ADMIN + " TEXT " +
            "); ";

    final String SQL_CREATE_LOGIN_TABLE = "CREATE TABLE " +
            CircuitContrat.Login.NOM_TABLE + " (" +
            CircuitContrat.Login._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            CircuitContrat.Login.COLONNE_LOGIN + " TEXT " +
            "); ";

    final String SQL_CREATE_LISTE_COURRIELS_TABLE = "CREATE TABLE " +
            CircuitContrat.CourrielUtilisateur.NOM_TABLE + " (" +
            CircuitContrat.CourrielUtilisateur._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            CircuitContrat.CourrielUtilisateur.COLONNE_COURRIEL + " TEXT " +
            "); ";

    /*
    public CircuitDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    } */

/*
    private CircuitDBHelper() {
        super(App.getAppContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }  */



        private CircuitDBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_CIRCUIT_TABLE);
        db.execSQL(SQL_CREATE_ETAPE_TABLE);
        db.execSQL(SQL_CREATE_JOUR_TABLE);
        db.execSQL(SQL_CREATE_ADMIN_TABLE);
        db.execSQL(SQL_CREATE_LOGIN_TABLE);
        db.execSQL(SQL_CREATE_LISTE_COURRIELS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion>oldVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + CircuitContrat.Circuit.NOM_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CircuitContrat.Etape.NOM_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CircuitContrat.Jour.NOM_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CircuitContrat.Admin.NOM_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CircuitContrat.Login.NOM_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CircuitContrat.CourrielUtilisateur.NOM_TABLE);
            onCreate(db);
        }
    }


    public static synchronized CircuitDBHelper getInstance(Context context) {
        //   Context context = App.getAppContext();
        if(circuitDBHelper==null)
        {
            circuitDBHelper = new CircuitDBHelper(context.getApplicationContext());
        }

        return circuitDBHelper;     //.getWritableDatabase();
    }
}

