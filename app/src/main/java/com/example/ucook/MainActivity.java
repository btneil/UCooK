package com.example.ucook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private Button Recette_Btn;
    private Button Panier_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Recette CroqueMonsieur = new Recette();
        System.out.println("Bonjour");

        this.Recette_Btn = findViewById(R.id.Recette_Btn);
        this.Panier_Btn =  findViewById(R.id.Panier_Btn);

        Recette_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Aff_recette = new Intent(getApplicationContext(), AffichageRecette.class);
                startActivity(Aff_recette);
                finish();
            }
        });
        Panier_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Aff_panier = new Intent(getApplicationContext(), AfficherPanier.class);
                startActivity(Aff_panier);
                finish();
            }
        });


        }
    }

