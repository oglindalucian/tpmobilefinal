package lucian.example.com.projetcircuits;import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import lucian.example.com.projetcircuits.Data.CircuitContrat;
import lucian.example.com.projetcircuits.Model.Circuit;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucian on 2018-03-30.
 */

public class CircuitAdapter extends RecyclerView.Adapter <CircuitAdapter.CircuitViewHolder> {

    /*
    public interface OnItemClickListener {   //definir une interface
        void onItemClick(Circuit item);
    }  */
    public static final int TEXT_REQUEST = 2;
    private Context mContext;
    private Cursor mCursor;
  //  private List<Circuit> circuits;
  //  private ArrayList<String> nomsEtape;
  //  private ArrayAdapter<String> etapesAdapter;
   // private final OnItemClickListener listener; //declarer une variable de type interface

    /**
     * Constructeur utilisant context and the db cursor
     *
     * @param context the calling context/activity
     */
    public CircuitAdapter(Context context, Cursor cursor) { //, List<Circuit> circuits, OnItemClickListener listener
        this.mContext = context;
        this.mCursor = cursor;
       // nomsEtape = new ArrayList<String>();//
      //  remplirListeEtapes();//
    }




    //@Override
    public CircuitViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Pour obtenir le layout d'un élément du RecyclerView
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.element_circuit, parent, false);
        return new CircuitViewHolder(view);
    }

    // @Override
    public void onBindViewHolder(CircuitViewHolder holder, int position) {
        if(!mCursor.moveToPosition(position))
            return;
        String nom = mCursor.getString(mCursor.getColumnIndex(CircuitContrat.Circuit.COLONNE_NOM_CIRCUIT));
        int etat = mCursor.getInt(mCursor.getColumnIndex(CircuitContrat.Circuit.COLONNE_ETAT));
        int min = mCursor.getInt(mCursor.getColumnIndex(CircuitContrat.Circuit.COLONNE_NBRE_PLACES_MIN));
        int max = mCursor.getInt(mCursor.getColumnIndex(CircuitContrat.Circuit.COLONNE_NBRE_PLACES_MAX));
        int res = mCursor.getInt(mCursor.getColumnIndex(CircuitContrat.Circuit.COLONNE_NBRE_PLACES_RESERVEES));
        String depart = mCursor.getString(mCursor.getColumnIndex(CircuitContrat.Circuit.COLONNE_DATE_DEPART));
        String arrivee = mCursor.getString(mCursor.getColumnIndex(CircuitContrat.Circuit.COLONNE_DATE_ARRIVEE));
       // String photo = mCursor.getString(mCursor.getColumnIndex(CircuitContrat.Circuit.COLONNE_PHOTO_CIRCUIT));
        int prix = mCursor.getInt(mCursor.getColumnIndex(CircuitContrat.Circuit.COLONNE_PRIX_CIRCUIT));
        String guide = mCursor.getString(mCursor.getColumnIndex(CircuitContrat.Circuit.COLONNE_GUIDE));
        String transport = mCursor.getString(mCursor.getColumnIndex(CircuitContrat.Circuit.COLONNE_TRANSPORT));

        holder.nomTextView.setText(nom);
        holder.nombreMin.setText(String.valueOf(min));
        holder.nombreMax.setText(String.valueOf(max));
        holder.nombrePlacesReservees.setText(String.valueOf(res));
        if(etat==1)
            holder.etatTextView.setText("actif");
        if(etat==0)
            holder.etatTextView.setText("inactif");
        holder.dateDepart.setText(String.valueOf(depart));
        holder.dateArrivee.setText(String.valueOf(arrivee));
      //  holder.photo.setText(String.valueOf(photo));
        holder.prixCircuit.setText(String.valueOf(prix));
        holder.guide.setText(guide);
        holder.transport.setText(transport);
/*
        remplirListeEtapes();
        etapesAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, nomsEtape);
        viderListeEtapes();
        holder.listeEtapes.setAdapter(etapesAdapter);

        */

        final long id = mCursor.getLong(mCursor.getColumnIndex(CircuitContrat.Circuit._ID));
        holder.itemView.setTag(id);

        holder.setItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                //Toast.makeText(mContext, "Le holder no:", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(mContext, ListeEtapes.class);
                intent.putExtra("EXTRA_ID_CIRCUIT", id);
                mContext.startActivity(intent);
            }
        });

     //   holder.bind(circuits.get(position), listener);    //ajouter le bind sur le holder
    }


    // @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void echangerCurseur(Cursor newCursor) {
        if(mCursor!=null) mCursor.close();
        mCursor = newCursor;
        if(newCursor!=null)
            this.notifyDataSetChanged();
    }
/*
    public void remplirListeEtapes() {
        nomsEtape.add("Etape1");
        nomsEtape.add("Etape2");
        nomsEtape.add("Etape3");
    }

    public void viderListeEtapes() {
        nomsEtape.clear();
    }
*/
    /**
     * Classe interne permettant de retenir (hold) la vue à afficher dans un élément de la liste
     * RecyclerView. Ce qui permet de conserver une référence sur les vues
     *  et ne pas avoir à utiliser findViewById à chaque fois
     */
    class CircuitViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private OnItemClickListener itemClickListener;
        TextView nomTextView;
        TextView nombreMin;
        TextView nombreMax;
        TextView nombrePlacesReservees;
        TextView etatTextView;
        TextView dateDepart;
        TextView dateArrivee;
        ImageView photo;
        TextView prixCircuit;
        TextView guide;
        TextView transport;
      //  Button nouvelleEtape;
       // ListView listeEtapes;

        public CircuitViewHolder(View itemView) {
            super(itemView);
            nomTextView = (TextView) itemView.findViewById(R.id.nom_text_view);
            nombreMin = (TextView) itemView.findViewById(R.id.nombreMin_text_view);
            nombreMax = (TextView) itemView.findViewById(R.id.nombreMax_text_view);
            nombrePlacesReservees = (TextView) itemView.findViewById(R.id.nombrePlacesReservees_text_view);
            etatTextView = (TextView) itemView.findViewById(R.id.etat_text_view);
            dateDepart = (TextView) itemView.findViewById(R.id.dateDepart_text_view);
            dateArrivee = (TextView) itemView.findViewById(R.id.dateArrivee_text_view);
            photo = (ImageView) itemView.findViewById(R.id.photoCircuit);
            prixCircuit = (TextView) itemView.findViewById(R.id.prixCircuit_text_view);
            guide = (TextView) itemView.findViewById(R.id.guide_text_view);
            transport = (TextView) itemView.findViewById(R.id.transport_text_view);
          //  nouvelleEtape = (Button) itemView.findViewById(R.id.ajout_etape);
          //  listeEtapes = (ListView)itemView.findViewById(R.id.listEtapes);

            //remplirListeEtapes();
           // etapesAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, nomsEtape);
           // viderListeEtapes();
         //   listeEtapes.setAdapter(etapesAdapter);

            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(OnItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        public void onClick(View v) {
            itemClickListener.onItemClick(v, getAdapterPosition());
        }
    }

     /*   public void bind(final Circuit item, final OnItemClickListener listener) {
            //name.setText(item.name);
            //Picasso.with(itemView.getContext()).load(item.imageUrl).into(image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        } */


}
