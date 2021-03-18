package com.example.ucook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private Button Menu_btn;
    private Button Connexion_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Recette CroqueMonsieur = new Recette();
        System.out.println("Bonjour");

        this.Menu_btn = findViewById(R.id.Menu_btn);
        this.Connexion_btn =  findViewById(R.id.Connexion_btn);

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


        }
    }

