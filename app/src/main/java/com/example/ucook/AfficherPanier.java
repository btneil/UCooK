package com.example.ucook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class AfficherPanier extends AppCompatActivity {

    RecyclerView recyclerView_panier;
    ArrayList Nom_ing,Qte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Nom_ing = new ArrayList<>();
        Qte = new ArrayList<>();
        Nom_ing.add("aaa");
        Nom_ing.add("bbb");
        Nom_ing.add("ccc");
        Qte.add("5g");
        Qte.add("6");
        Qte.add("9");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_panier);
        recyclerView_panier = findViewById(R.id.recyclerView_panier);
        MyAdapter_panier myAdapter = new MyAdapter_panier(AfficherPanier.this,this,Nom_ing,Qte);
        recyclerView_panier.setAdapter(myAdapter);
        recyclerView_panier.setLayoutManager(new LinearLayoutManager(this));
    }
}