package com.example.ucook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

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
            public void onClick(View v) { //basculer vers Liste de recette
                Intent Aff_Menu = new Intent(getApplicationContext(), MenuPrincipal.class);
                startActivity(Aff_Menu);
                finish();
            }
        });

        shoppingcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //basculer vers panier
                Intent Aff_Panier = new Intent(getApplicationContext(), AfficherPanier.class);
                startActivity(Aff_Panier);
                finish();
            }
        });

        Ajouter_rct_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //basculer vers affichage recette
                Intent intent = new Intent(MainActivity.this, Ajouter_Recette.class);
                startActivity(intent);
            }
        });


    }


}

