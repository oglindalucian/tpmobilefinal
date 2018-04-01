package lucian.example.com.projetcircuits.Data;

import android.provider.BaseColumns;

/**
 * Created by lucian on 2018-03-30.
 */

public class CircuitContrat {
    private CircuitContrat() {}

    public static final class Circuit implements BaseColumns {
        public static final String NOM_TABLE = "circuit";
        public static final String ID_CIRCUIT = "IdCircuit";
        public static final String COLONNE_NOM_CIRCUIT = "nomCircuit";
        public static final String COLONNE_NBRE_PLACES_MIN = "nbPlacesMinimum";
        public static final String COLONNE_NBRE_PLACES_MAX = "nbrPlacesMaximum";
        public static final String COLONNE_NBRE_PLACES_RESERVEES = "nbPlacesReservees";
        public static final String COLONNE_ETAT = "etat";
        public static final String COLONNE_DATE_DEPART = "dateDepart";
        public static final String COLONNE_DATE_ARRIVEE = "dateArrivee";
        public static final String COLONNE_PHOTO_CIRCUIT = "photoCircuit";
        public static final String COLONNE_PRIX_CIRCUIT = "prixCircuit";
        public static final String COLONNE_GUIDE = "guide";
        public static final String COLONNE_TRANSPORT = "transport";
        public static final String COLONNE_ID_RABAIS = "idRabais";
    }

    public static final class Etape implements BaseColumns {
        public static final String NOM_TABLE = "etape";
        public static final String COLONNE_ID_ETAPE = "idEtape";
        public static final String COLONNE_NOM_ETAPE = "nomEtape";
        public static final String COLONNE_ID_CIRCUIT = "IdCircuit";
        public static final String COLONNE_NBRE_JOUR = "nombreJour";
        public static final String COLONNE_DATE_ARRIVEE= "dateArrivee";
        public static final String COLONNE_DATE_DEPART= "dateDepart";
        public static final String COLONNE_DESCRIPTION_ETAPE= "descriptionEtape";
        public static final String COLONNE_PHOTO_ETAPE= "photoEtape";
    }

    public static final class Jour implements BaseColumns {
        public static final String NOM_TABLE = "jour";
        public static final String COLONNE_ID_JOUR = "idJour";
        public static final String COLONNE_NOM_JOUR = "nomJour";
        public static final String COLONNE_NOM_VILLE = "nomVille";
        public static final String COLONNE_ID_ETAPE = "idEtape";
        public static final String COLONNE_HOTEL = "hotel";
        public static final String COLONNE_RESTAURANT= "restaurant";

    }

    public static final class Usager implements BaseColumns {
        public static final String NOM_TABLE = "usager";
        public static final String COLONNE_ID_USAGER = "idUsager";
        public static final String COLONNE_NOM_USAGER = "nomUsager";
        public static final String COLONNE_PRENOM_USAGER = "prenomUsager";
        public static final String COLONNE_ADRESSE = "adresse";
        public static final String COLONNE_DDN_USAGER = "ddnUsager";
        public static final String COLONNE_TELEPHONE_USAGER= "telephoneUsager";
        public static final String COLONNE_ETAT= "etat";
        public static final String COLONNE_ID_RESERVATION= "idReservation";
        public static final String COLONNE_COURRIEL= "courriel";
        public static final String COLONNE_MDP= "mdp";

    }

    public static final class Accompagnateur implements BaseColumns {
        public static final String NOM_TABLE = "accompagnateur";
        public static final String COLONNE_ID_ACCOMPAGNATEUR = "IdAccompagnateur";
        public static final String COLONNE_NOM_ACCOMPAGNATEUR = "NomAccompagnateur";
        public static final String COLONNE_PRENOM_ACCOMPAGNATEUR = "PrenomAccompagnateur";
        public static final String COLONNE_TEL_ACCOMPAGNATEUR = "TelAccompagnateur";
        public static final String COLONNE_MAIL_ACCOMPAGNATEUR = "MailAccompagnateur";
        public static final String COLONNE_ID_RESERVATION= "idReservation";

    }

    public static final class Reservation implements BaseColumns {
        public static final String NOM_TABLE = "reservation";
        public static final String COLONNE_ID_RESERVATION = "idReservation";
        public static final String COLONNE_ID_CIRCUIT = "idCircuit";
        public static final String COLONNE_ID_USAGER = "idUsager";
        public static final String COLONNE_DATE_RESERVATION = "dateReservation";
        public static final String COLONNE_SOLDE_INITIALE = "soldeInitiale";
        public static final String COLONNE_DEPOT = "depot";
        public static final String COLONNE_DATE_ECHEANCE = "dateEcheance";
        public static final String COLONNE_ID_ACCOMPAGNATEUR = "idAccompagniateur";
    }

    public static final class Rabais implements BaseColumns {
        public static final String NOM_TABLE = "rabais";
        public static final String COLONNE_ID_RABAIS = "idRabais";
        public static final String COLONNE_NOM_RABAIS = "nomRabais";
        public static final String COLONNE_DESCRIPTION = "description";
        public static final String COLONNE_POURCENTAGE = "pourcentage";
        public static final String COLONNE_CODE_PROMO = "codePromo";

    }
}
