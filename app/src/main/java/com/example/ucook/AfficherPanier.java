package com.example.ucook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AfficherPanier extends AppCompatActivity {

    RecyclerView recyclerView_panier;
    ArrayList Nom_ing,Qte;
    private FloatingActionButton home_btn_panier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Nom_ing = new ArrayList<>();
        Qte = new ArrayList<>();
        Nom_ing.add("aaa");
        Nom_ing.add("bbb");
        Nom_ing.add("ccc");
        Nom_ing.add("ddd");
        Qte.add("5g");
        Qte.add("6");
        Qte.add("9");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_panier);
        recyclerView_panier = findViewById(R.id.recyclerView_panier);
        MyAdapter_panier myAdapter = new MyAdapter_panier(AfficherPanier.this,this,Nom_ing,Qte);
        recyclerView_panier.setAdapter(myAdapter);
        recyclerView_panier.setLayoutManager(new LinearLayoutManager(this));
        Qte.add(String.valueOf(getIntent().hasExtra("Ingrédient_a_ajouter")));

        this.home_btn_panier=findViewById(R.id.home_btn_panier);

        home_btn_panier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Retour_menu = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(Retour_menu);
                finish();
            }
        });


    }
}