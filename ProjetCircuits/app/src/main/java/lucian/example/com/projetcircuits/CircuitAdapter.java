package lucian.example.com.projetcircuits;import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

    private Context mContext;
    private Cursor mCursor;
  //  private final List<Circuit> circuits;
   // private final OnItemClickListener listener; //declarer une variable de type interface

    /**
     * Constructeur utilisant context and the db cursor
     *
     * @param context the calling context/activity
     */
    public CircuitAdapter(Context context, Cursor cursor) { //, List<Circuit> circuits, OnItemClickListener listener
        this.mContext = context;
        this.mCursor = cursor;
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
        long id = mCursor.getLong(mCursor.getColumnIndex(CircuitContrat.Circuit._ID));
        holder.itemView.setTag(id);

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


    /**
     * Classe interne permettant de retenir (hold) la vue à afficher dans un élément de la liste
     * RecyclerView. Ce qui permet de conserver une référence sur les vues
     *  et ne pas avoir à utiliser findViewById à chaque fois
     */
    class CircuitViewHolder extends RecyclerView.ViewHolder {

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
}
