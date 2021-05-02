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

public class AfficherPanier extends AppCompatActivity {

    RecyclerView recyclerView_panier;
    private FloatingActionButton home_btn_panier;
    MyDataBase_panier MyDB;
    ArrayList<String> ing_id, ing_nom, ing_type, ing_qte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ing_id = new ArrayList<>();
        ing_nom = new ArrayList<>();
        ing_type = new ArrayList<>();
        ing_qte = new ArrayList<>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_panier);
        recyclerView_panier = findViewById(R.id.recyclerView_panier);

        MyDB = new MyDataBase_panier(AfficherPanier.this);
        displayData();

        MyAdapter_panier myAdapter = new MyAdapter_panier(AfficherPanier.this,this,ing_nom,ing_qte,ing_type,ing_id);
        recyclerView_panier.setAdapter(myAdapter);
        recyclerView_panier.setLayoutManager(new LinearLayoutManager(this));

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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }
    void displayData(){
        Cursor cursor = MyDB.readAllData(); //contient toutes les données de la bdd
        if(cursor.getCount() == 0){
            Toast.makeText(this, "Le panier est vide", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                ing_id.add(cursor.getString(0));
                ing_nom.add(cursor.getString(1));
                ing_type.add(cursor.getString(2));
                ing_qte.add(cursor.getString(3));
            }
        }
    }
}