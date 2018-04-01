package lucian.example.com.projetcircuits;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ListeEtapes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_etapes);

        RecyclerView etapesRecyclerView;
        etapesRecyclerView = (RecyclerView) this.findViewById(R.id.vue_les_etapes);
        etapesRecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}
