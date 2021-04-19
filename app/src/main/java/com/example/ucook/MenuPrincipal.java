package com.example.ucook;

import androidx.annotation.Nullable;
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

    private FloatingActionButton home_btn;

    RecyclerView recyclerView;
    FloatingActionButton Ajouter_rct_btn;

    MyDataBase myDB;
    ArrayList<String> rct_id, rct_titre, rct_diff, rct_ing, rct_tmps, rct_inst;
    ListeDeRecettes Livre_rct = new ListeDeRecettes();

    ArrayList s1, s2;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        this.home_btn=findViewById(R.id.home_btn);

        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Retour_menu = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(Retour_menu);
                finish();
            }
        });

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


        //s1 = getResources().getStringArray(R.array.titre_liste_recette); //entrer ici les titres des recettes
        //s2 = getResources().getStringArray(R.array.description ); //entrer ici les descriptions des recettes (note: pour l'instant présents dans le fichier STRING dans l'ordre)
        //s1 = Tab_recette;
        //s2 = Tab_diff;
        rct_id = new ArrayList<>();
        rct_titre = new ArrayList<>();
        rct_diff = new ArrayList<>();
        rct_ing = new ArrayList<>();
        rct_tmps = new ArrayList<>();
        rct_inst = new ArrayList<>();

        displayData();

        Remplir_Liste_Recette();
        int j = 0;
        while(j!=Livre_rct.Liste.size()){
            System.out.println("\n" + Livre_rct.Liste.get(j).Nom);//lignes de test
            j++;
        }

        s1 = rct_titre;
        s2 = rct_diff;
        MyAdapter myAdapter = new MyAdapter(MenuPrincipal.this,this,s1,s2,images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void displayData(){
        Cursor cursor = myDB.readAllData(); //contient toutes les données de la bdd
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


                //Recette rct_id = new Recette(); // idéalement, il faudrait ajouter dans une liste de recette (objet)
                // des recettes crééent à partir des parametres ci-dessus. On pourrait alors y accéder plus facilement, et l'affichage serait plus simple
                //sinon on peut tjs essayer de "cacher" l'info qqpart dans l'apercu de la recette pour les récupérer dans son affichage

            }
        }
    }
    void Remplir_Liste_Recette(){
        int i = 0;
        while(i!=rct_id.size()){
            Recette rct = new Recette();
            rct.Nom = rct_titre.get(i);
            rct.Difficulte = rct_diff.get(i);
            rct.Instructions = rct_inst.get(i);
            Livre_rct.AjouterRecetteDansListe(rct);
            i++;
        }//La liste de recette est donc bien remplie ! ! !

    }
}