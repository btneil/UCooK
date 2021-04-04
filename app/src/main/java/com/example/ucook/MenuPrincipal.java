package com.example.ucook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MenuPrincipal extends AppCompatActivity {

    RecyclerView recyclerView;

    String s1[], s2[];
    int images[] = {R.drawable.banane_plantain};
    //,R.drawable.daube_carotte,
    //            R.drawable.galette_des_rois,R.drawable.gateau_choco,R.drawable.gnocci,
    //            R.drawable.pdt_hasselback,R.drawable.poivron_farci,R.drawable.pokebowl,
    //            R.drawable.salade_nicoise,R.drawable.tomate_provencale,R.drawable.lasagne,
    //            R.drawable.lasagne,R.drawable.carotte,R.drawable.lasagne,
    //            R.drawable.carotte,R.drawable.lasagne,R.drawable.carotte,
    //            R.drawable.lasagne,R.drawable.carotte,R.drawable.lasagne,
    //            R.drawable.carotte,R.drawable.lasagne,R.drawable.carotte,
    //            R.drawable.lasagne,R.drawable.carotte,R.drawable.lasagne,
    //            R.drawable.carotte,R.drawable.lasagne,R.drawable.carotte,
    //            R.drawable.lasagne,R.drawable.carotte,R.drawable.lasagne

    Recette CroqueM=new Recette();
    String [] Tab_recette = {CroqueM.Nom};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        recyclerView = findViewById(R.id.recyclerView);

        s1 = getResources().getStringArray(R.array.titre_liste_recette); //entrer ici les titres des recettes
        //s2 = getResources().getStringArray(R.array.description ); //entrer ici les descriptions des recettes (note: pour l'instant présents dans le fichier STRING dans l'ordre)
        s2 = Tab_recette;

        MyAdapter myAdapter = new MyAdapter(this,s1,s2,images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}