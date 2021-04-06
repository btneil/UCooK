package com.example.ucook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MenuPrincipal extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton Ajouter_rct_btn;

    MyDataBase myDB;
    ArrayList<String> rct_id, rct_titre, rct_diff, rct_ing, rct_tmps, rct_inst;

    String s1[], s2[];
    int images[] = {R.drawable.banane_plantain,R.drawable.daube_carotte,
                R.drawable.galette_des_rois,R.drawable.gateau_choco,R.drawable.gnocci,
                R.drawable.pdt_hasselback,R.drawable.poivron_farci,R.drawable.pokebowl,
                R.drawable.salade_nicoise,R.drawable.tomate_provencale,R.drawable.lasagne,
                R.drawable.lasagne,R.drawable.carotte,R.drawable.lasagne,
                R.drawable.carotte,R.drawable.lasagne,R.drawable.carotte,
                R.drawable.lasagne,R.drawable.carotte,R.drawable.lasagne,
                R.drawable.carotte,R.drawable.lasagne,R.drawable.carotte,
                R.drawable.lasagne,R.drawable.carotte,R.drawable.lasagne,
                R.drawable.carotte,R.drawable.lasagne,R.drawable.carotte,
                R.drawable.lasagne,R.drawable.carotte,R.drawable.lasagne};

    Recette CroqueM=new Recette();
    String [] Tab_recette = {CroqueM.Nom};
    String[] Tab_diff = {CroqueM.Difficulte};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        recyclerView = findViewById(R.id.recyclerView);
        Ajouter_rct_btn = findViewById(R.id.Ajouter_rct_btn);
        Ajouter_rct_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipal.this, AddActivity.class);
                startActivity(intent);
            }
        });

        myDB = new MyDataBase(MenuPrincipal.this);


        s1 = getResources().getStringArray(R.array.titre_liste_recette); //entrer ici les titres des recettes
        s2 = getResources().getStringArray(R.array.description ); //entrer ici les descriptions des recettes (note: pour l'instant présents dans le fichier STRING dans l'ordre)
        //s1 = Tab_recette;
        //s2 = Tab_diff;

        MyAdapter myAdapter = new MyAdapter(this,s1,s2,images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        rct_id = new ArrayList<>();
        rct_titre = new ArrayList<>();
        rct_diff = new ArrayList<>();
        rct_ing = new ArrayList<>();
        rct_tmps = new ArrayList<>();
        rct_inst = new ArrayList<>();

        //displayData();

    }

    void displayData(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "Pas de Recette", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                rct_id.add(cursor.getString(0));
                rct_titre.add(cursor.getString(1));
                rct_diff.add(cursor.getString(2));
                rct_ing.add(cursor.getString(3));
                rct_tmps.add(cursor.getString(4));
                rct_inst.add(cursor.getString(5));
                System.out.print("Dans la while");
            }
        }
    }
}