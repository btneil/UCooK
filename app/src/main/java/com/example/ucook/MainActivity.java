package com.example.ucook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Scanner;

public class MainActivity extends AppCompatActivity {


    Recette CroqueM = new Recette ("Croque-monsieur", "pas grand chose, cf Neil", 15, 4, "facile", 1,"@drawable/icon2");
    ListeDeRecettes LivreRecette = new ListeDeRecettes(CroqueM);


    private Button Menu_btn;
    private Button Connexion_btn;
    private Button Panier_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.Menu_btn = findViewById(R.id.Menu_btn);
        this.Connexion_btn =  findViewById(R.id.Connexion_btn);
        this.Panier_btn = findViewById(R.id.Panier_btn);

        Menu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Aff_Menu = new Intent(getApplicationContext(), MenuPrincipal.class);
                startActivity(Aff_Menu);
                finish();
            }
        });
        Connexion_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Aff_PageDeConnexion = new Intent(getApplicationContext(), PageDeConnexion.class);
                startActivity(Aff_PageDeConnexion);
                finish();
            }
        });
        Panier_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Aff_Panier = new Intent(getApplicationContext(), AfficherPanier.class);
                startActivity(Aff_Panier);
                finish();
            }
        });


    }


}

