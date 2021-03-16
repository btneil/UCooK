package com.example.ucook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AffichageRecette extends AppCompatActivity {

    private Button Retour_Menu_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_recette);

        this.Retour_Menu_Btn = findViewById(R.id.Retour_Menu_Btn);
        Retour_Menu_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Retour_Menu = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(Retour_Menu);
                finish();
            }
        });


    }
}