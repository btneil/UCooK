package com.example.ucook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_ingredient);
        recyclerview_Ing = findViewById(R.id.recyclerview_Ing);
        confirmer_btn = findViewById(R.id.confirmer_btn);
        getData();
        setData();
        confirmer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDataBase_panier MyDB = new MyDataBase_panier(Ajout_ingredient.this);
                int i = 0;
                while(i!=Tab_Compo.length){
                    String [] Compo = Tab_Compo[i].split(" ");
                    MyDB.ajouter_rct(Compo[0],Compo[1],Compo[1]); //actuelleemnt, type inutile donc à enlever?
                    i++;
                }
                MyDB.ajouter_rct("Ingrédient","Type","Qte");
                Intent Ajout_dans_panier = new Intent(getApplicationContext(),AfficherPanier.class);
                startActivity(Ajout_dans_panier);
                finish();
            }
        });


    }
    private void getData(){
        if(getIntent().hasExtra("composition")){ // on vérifie que composition existe
            composition = getIntent().getStringExtra("composition");
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
}