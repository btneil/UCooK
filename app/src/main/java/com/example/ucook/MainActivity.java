package com.example.ucook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.gson.Gson;

import java.util.Scanner;

public class MainActivity extends AppCompatActivity {


    //Gson gson = new Gson();

    //Recette CroqueM = new Recette ("Croque-monsieur", "pas grand chose, cf Neil", 15, 4, "facile", 1,"@drawable/icon2");
    //ListeDeRecettes LivreRecette = new ListeDeRecettes(CroqueM);

    //String json = gson.toJson(CroqueM); //on met CroqueM dans le Json



    private Button Menu_btn;
    private ImageButton shoppingcart;
    private ImageButton Ajouter_rct_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.Menu_btn = findViewById(R.id.Menu_btn);
        this.shoppingcart = findViewById(R.id.shoppingcart);
        this.Ajouter_rct_btn = findViewById(R.id.Ajouter_rct_btn);

        Menu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Aff_Menu = new Intent(getApplicationContext(), MenuPrincipal.class);
                startActivity(Aff_Menu);
                finish();
            }
        });

        shoppingcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Aff_Panier = new Intent(getApplicationContext(), AfficherPanier.class);
                startActivity(Aff_Panier);
                finish();
            }
        });

        Ajouter_rct_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });


    }


}

