package lucian.example.com.projetcircuits.Model;

import android.content.ContentValues;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import lucian.example.com.projetcircuits.Data.CircuitContrat;

/**
 * Created by lucian on 2018-03-30.
 */

public class Circuit {
    private String nom;
    private int  minPlaces;
    private int maxPlaces;
    private int resPlaces;
    private int etat;
    private String depart;
    private String arrivee;
    private String photo;
    private int prix;
    private String guide;
    private String transport;
   // public static List<Circuit> listeCircuits = new ArrayList<Circuit>();

    public Circuit(String nom, int min, int max, int res, int etat, String depart, String arrivee, String photo, int prix,
                   String guide, String transport) {
        this.nom=nom;
        this.minPlaces=min;
        this.maxPlaces=max;
        this.resPlaces=res;
        this.etat=etat;
        this.depart=depart;
        this.arrivee=arrivee;
        this.photo=photo;
        this.prix=prix;
        this.guide=guide;
        this.transport=transport;
    }

/*
    public static void addCircuit (String nom, int etat, int min, int max, int res, String depart,
                                             String arrivee, int prix, String guide, String transport, String photoC) {
        listeCircuits.add(new Circuit(nom, min, max, res, etat, depart, arrivee, photoC, prix, guide, transport));

    }
*/


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getMinPlaces() {
        return minPlaces;
    }

    public void setMinPlaces(int minPlaces) {
        this.minPlaces = minPlaces;
    }

    public int getMaxPlaces() {
        return maxPlaces;
    }

    public void setMaxPlaces(int maxPlaces) {
        this.maxPlaces = maxPlaces;
    }

    public int getResPlaces() {
        return resPlaces;
    }

    public void setResPlaces(int resPlaces) {
        this.resPlaces = resPlaces;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getArrivee() {
        return arrivee;
    }

    public void setArrivee(String arrivee) {
        this.arrivee = arrivee;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }
}
