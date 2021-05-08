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

    MyDataBase myDB;
    ArrayList<String> rct_id, rct_titre, rct_diff, rct_ing, rct_tmps, rct_inst, rct_nb_personnes, rct_image;
    ListeDeRecettes Livre_rct = new ListeDeRecettes();

    ArrayList s1;
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

        myDB = new MyDataBase(MenuPrincipal.this);

        rct_id = new ArrayList<>();
        rct_titre = new ArrayList<>();
        rct_diff = new ArrayList<>();
        rct_ing = new ArrayList<>();
        rct_tmps = new ArrayList<>();
        rct_inst = new ArrayList<>();
        rct_image = new ArrayList<>();

        displayData();

        Remplir_Liste_Recette();

        MyAdapter myAdapter = new MyAdapter(MenuPrincipal.this,this,Livre_rct,rct_id);
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
            myDB.initialiser_bdd();
            Intent Retour_ecran_acc = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(Retour_ecran_acc);
            finish();
        }
        else{
            while(cursor.moveToNext()){
                rct_id.add(cursor.getString(0));
                rct_titre.add(cursor.getString(1));
                rct_diff.add(cursor.getString(2));
                rct_ing.add(cursor.getString(3));
                rct_tmps.add(cursor.getString(4));
                rct_inst.add(cursor.getString(6)); // /!\ colonne 6 pas 5 (ordre colonne bdd) /!\
                rct_image.add(cursor.getString(5)); //colonne à rajouter dans bdd
                System.out.print("Dans la while");


                //Recette rct_id = new Recette(); // idéalement, il faudrait ajouter dans une liste de recette (objet)
                // des recettes crééent à partir des parametres ci-dessus. On pourrait alors y accéder plus facilement, et l'affichage serait plus simple
                //sinon on peut tjs essayer de "cacher" l'info qqpart dans l'apercu de la recette pour les récupérer dans son affichage

            }
        }
    }
    void Remplir_Liste_Recette(){
        //Il faut avant cela recreer la tab des compositions
        String [] tab_compo_txt = String.valueOf(rct_ing).replace("[","").replace("]","").split(", "); //on obtient tableau de ing/type/qtt
        //[ing/type/qtt,ing/type/qtt,ing/type/qtt]

        int i = 0;
        while(i!=rct_id.size()){

            ArrayList<Composition> Tab_Compo = new ArrayList<Composition>();

            String[] ingredient=tab_compo_txt[i].split(","); //[ing/type/qtt]
            int k= 0;
            while(k!=ingredient.length){
                Ingredient ign = new Ingredient(ingredient[k].split("/")[0],ingredient[k].split("/")[1]); //création d'un ingrédient
                Tab_Compo.add(new Composition(ign,Integer.parseInt(ingredient[k].split("/")[2]))); //creation et ajout d'une compo
                k++;


            }
            Recette rct = new Recette(rct_titre.get(i),rct_inst.get(i),rct_diff.get(i),
                    rct_tmps.get(i),1,Integer.parseInt(rct_image.get(i)),Tab_Compo); //String to int

            Livre_rct.AjouterRecetteDansListe(rct);
            i++;
        }

    }
}