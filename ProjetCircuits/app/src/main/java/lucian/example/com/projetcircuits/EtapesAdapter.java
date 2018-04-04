package lucian.example.com.projetcircuits;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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


import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by lucian on 2018-04-02.
 */

public class EtapesAdapter extends RecyclerView.Adapter <EtapesAdapter.EtapeViewHolder> {

    public static final int TEXT_REQUEST = 3;
    private Context mContext;
    private Cursor mCursor;

    public EtapesAdapter(Context context, Cursor cursor) {
        this.mContext = context;
        this.mCursor = cursor;
    }

    public EtapesAdapter.EtapeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Pour obtenir le layout d'un élément du RecyclerView
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.element_etape, parent, false);
        return new EtapesAdapter.EtapeViewHolder(view);
    }

    public void onBindViewHolder(EtapesAdapter.EtapeViewHolder holder, int position) {
        if(!mCursor.moveToPosition(position))
            return;
        String nom = mCursor.getString(mCursor.getColumnIndex(CircuitContrat.Etape.COLONNE_NOM_ETAPE));
        int idCircuit = mCursor.getInt(mCursor.getColumnIndex(CircuitContrat.Etape.COLONNE_ID_CIRCUIT));
        int nbJours = mCursor.getInt(mCursor.getColumnIndex(CircuitContrat.Etape.COLONNE_NBRE_JOUR));
        String arrivee = mCursor.getString(mCursor.getColumnIndex(CircuitContrat.Etape.COLONNE_DATE_ARRIVEE));
        String depart = mCursor.getString(mCursor.getColumnIndex(CircuitContrat.Etape.COLONNE_DATE_DEPART));
        String photoUri = mCursor.getString(mCursor.getColumnIndex(CircuitContrat.Etape.COLONNE_PHOTO_ETAPE));
        String description = mCursor.getString(mCursor.getColumnIndex(CircuitContrat.Etape.COLONNE_DESCRIPTION_ETAPE));

        holder.nomTextView.setText(nom);
        holder.leCircuit.setText(String.valueOf(idCircuit));
        holder.nombreJours.setText(String.valueOf(nbJours));
        holder.dateArrivee.setText(String.valueOf(arrivee));
        holder.dateDepart.setText(String.valueOf(depart));
       //  holder.photo.setText(String.valueOf(photo));
        holder.description.setText(description);

        Uri myUri;
        try {
            myUri = Uri.parse(photoUri);
        } catch (Exception e) {
            //   myUri = Uri.parse("content://media/external/images/media/679684");
            myUri = Uri.parse("");
        }

        InputStream is;
        try {
            is = mContext.getContentResolver().openInputStream(myUri);
            Bitmap image = BitmapFactory.decodeStream(is);
            holder.photo.setImageBitmap(image);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
            Toast.makeText(mContext, "Image introuvable", Toast.LENGTH_LONG);
        }

        long id = mCursor.getLong(mCursor.getColumnIndex(CircuitContrat.Etape._ID));
        holder.itemView.setTag(id);

        holder.setItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(mContext, "Le holder no:", Toast.LENGTH_LONG).show();
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

    class EtapeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private OnItemClickListener itemClickListener;
        TextView nomTextView;
        TextView leCircuit;
        TextView nombreJours;
        TextView dateArrivee;
        TextView dateDepart;
        ImageView photo;
        TextView description;
      //  Button nouvelleEtape;
        // ListView listeEtapes;

        public EtapeViewHolder(View itemView) {
            super(itemView);
            nomTextView = (TextView) itemView.findViewById(R.id.nom_text_view);
            leCircuit = (TextView) itemView.findViewById(R.id.idCircuit_text_view);
            nombreJours = (TextView) itemView.findViewById(R.id.nombreJours_text_view);
            dateArrivee = (TextView) itemView.findViewById(R.id.arrivee_text_view);
            dateDepart = (TextView) itemView.findViewById(R.id.depart_text_view);
            photo = (ImageView) itemView.findViewById(R.id.photoEtape);
            description = (TextView) itemView.findViewById(R.id.description_text_view);
         //   nouvelleEtape = (Button) itemView.findViewById(R.id.ajout_etape);
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
}
