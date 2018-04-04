package lucian.example.com.projetcircuits;

/**
 * Created by lucian on 2018-04-04.
 */

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import lucian.example.com.projetcircuits.Data.CircuitContrat;

public class JourAdapter extends RecyclerView.Adapter <JourAdapter.JourViewHolder> {

    public static final int TEXT_REQUEST = 5;
    private Context mContext;
    private Cursor mCursor;

    public JourAdapter(Context context, Cursor cursor) {
        this.mContext = context;
        this.mCursor = cursor;
    }

    public JourAdapter.JourViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Pour obtenir le layout d'un élément du RecyclerView
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.element_jour, parent, false);
        return new JourAdapter.JourViewHolder(view);
    }

    public void onBindViewHolder(JourAdapter.JourViewHolder holder, int position) {
        if(!mCursor.moveToPosition(position))
            return;
        String nom = mCursor.getString(mCursor.getColumnIndex(CircuitContrat.Jour.COLONNE_NOM_JOUR));
        String laVille = mCursor.getString(mCursor.getColumnIndex(CircuitContrat.Jour.COLONNE_NOM_VILLE));
        int etape = mCursor.getInt(mCursor.getColumnIndex(CircuitContrat.Jour.COLONNE_ID_ETAPE));
        String hotelJour = mCursor.getString(mCursor.getColumnIndex(CircuitContrat.Jour.COLONNE_HOTEL));
        String restaurantJour = mCursor.getString(mCursor.getColumnIndex(CircuitContrat.Jour.COLONNE_RESTAURANT));

        holder.nomTextView.setText(nom);
        holder.nomVille.setText(laVille);
        holder.idEtape.setText(String.valueOf(etape));
        holder.hotel.setText(hotelJour);
        holder.restaurant.setText(restaurantJour);

        long id = mCursor.getLong(mCursor.getColumnIndex(CircuitContrat.Jour._ID));
        holder.itemView.setTag(id);


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

    class JourViewHolder extends RecyclerView.ViewHolder{

        private OnItemClickListener itemClickListener;
        TextView nomTextView;
        TextView nomVille;
        TextView idEtape;
        TextView hotel;
        TextView restaurant;


        public JourViewHolder(View itemView) {
            super(itemView);
            nomTextView = (TextView) itemView.findViewById(R.id.nom_text_view);
            nomVille = (TextView) itemView.findViewById(R.id.nomVille_text_view);
            idEtape = (TextView) itemView.findViewById(R.id.idEtape_text_view);
            hotel = (TextView) itemView.findViewById(R.id.hotel_text_view);
            restaurant = (TextView) itemView.findViewById(R.id.restaurant_text_view);
        }


    }
}
