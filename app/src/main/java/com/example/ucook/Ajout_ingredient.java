package com.example.ucook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class Ajout_ingredient extends AppCompatActivity {

    RecyclerView recyclerview_Ing;
    ArrayList Compo;
    String Aff_ing,composition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_ingredient);
        Compo = new ArrayList<Ingredient>();
        recyclerview_Ing = findViewById(R.id.recyclerview_Ing);
        getData();
        setData();
        MyAdapter_Ajout_Ing myAdapter = new MyAdapter_Ajout_Ing(Ajout_ingredient.this,this,Aff_ing);
        recyclerview_Ing.setAdapter(myAdapter);
        recyclerview_Ing.setLayoutManager(new LinearLayoutManager(this));

    }
    private void getData(){
        if(getIntent().hasExtra("composition")){ // on vérifie que composition existe
            composition = getIntent().getStringExtra("composition");
        }
        else{
            Toast.makeText(this,"Pas d'ingredient", Toast.LENGTH_SHORT).show();
        }
    }
    private void setData(){
        Aff_ing="Nom ingrédient: "+composition+"\nQte:";
    }
}