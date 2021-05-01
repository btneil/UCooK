package com.example.ucook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class Ajout_ingredient extends AppCompatActivity {

    RecyclerView recyclerview_Ing;
    String composition;
    String [] Tab_Compo;
    private Button confirmer_btn;
    ArrayList<String> ing_nom,ing_qte;
    String ing_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_ingredient);
        recyclerview_Ing = findViewById(R.id.recyclerview_Ing);
        confirmer_btn = findViewById(R.id.confirmer_btn);
        getData();
        setData();
        ing_nom = new ArrayList<>();
        ing_qte = new ArrayList<>();
        confirmer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDataBase_panier MyDB = new MyDataBase_panier(Ajout_ingredient.this);
                int i = 0;
                while(i!=Tab_Compo.length){
                    String [] Compo = Tab_Compo[i].split(" ");
                    if(Ingredient_deja_present(MyDB,Compo[0])==false){ //on test si l'ingrédient est déjà présent dans la bdd
                        MyDB.ajouter_rct(Compo[0],Compo[1],Compo[1]); //actuelleemnt, type inutile donc à enlever?
                    }
                    else{
                        //ici il faut trouver l'ingredient deja present, puis ajouter la quantité souhaitée
                        System.out.println(formatage_data(recup_ancienne_qte(MyDB,Compo[0])));
                        int new_qte = Integer.parseInt(formatage_data(Compo[1]))+Integer.parseInt(formatage_data(recup_ancienne_qte(MyDB,Compo[0]))); //si qte = int dans la bdd -> + simple
                        MyDB.updateData(ing_id,String.valueOf(new_qte));
                    }

                    i++;
                }

                Intent Ajout_dans_panier = new Intent(getApplicationContext(),AfficherPanier.class);
                startActivity(Ajout_dans_panier);
                finish();
            }
        });


    }
    private void getData(){
        if(getIntent().hasExtra("composition")){ // on vérifie que composition existe
            composition = getIntent().getStringExtra("composition");
            ing_id = getIntent().getStringExtra("ing_id");
        }
        else{
            Toast.makeText(this,"Pas d'ingredient", Toast.LENGTH_SHORT).show();
        }
        Tab_Compo = composition.split("\n");
    }
    private void setData(){
        MyAdapter_Ajout_Ing myAdapter = new MyAdapter_Ajout_Ing(Ajout_ingredient.this,this,Tab_Compo);
        recyclerview_Ing.setAdapter(myAdapter);
        recyclerview_Ing.setLayoutManager(new LinearLayoutManager(this));
    }
    private boolean Ingredient_deja_present(MyDataBase_panier MyDB, String Nom_ing){
        boolean ingredient_deja_present = false;
        Cursor cursor = MyDB.readAllData();
        if(cursor.getCount() != 0){
            while(cursor.moveToNext()){
                ing_nom.add(cursor.getString(1));
            }
            int i = 0;
            while (i!=ing_nom.size()){
                if(String.valueOf(ing_nom.get(i)).equals(Nom_ing)==true){
                    ingredient_deja_present=true;
                }
                i++;
            }
        }

        return ingredient_deja_present;
    }

    private String recup_ancienne_qte(MyDataBase_panier MyDB, String Nom_ing){
        String Qte="";
        Cursor cursor = MyDB.readAllData();
        if(cursor.getCount() != 0){
            while(cursor.moveToNext()){
                ing_nom.add(cursor.getString(1));
                ing_qte.add(cursor.getString(2));
            }
            int i = 0;
            while (i!=ing_nom.size()){
                if(String.valueOf(ing_nom.get(i)).equals(Nom_ing)==true){
                    return String.valueOf(ing_qte.get(i));
                }
                i++;
            }
        }
        return "0";
    }
    public String formatage_data(String string){
        String string2 = string.replace("x","").replace(" ","").replace("cuillière","").replace("pincée","").replace("mL","");
        return string2;
    }
}