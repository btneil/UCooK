package com.example.ucook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AffichageRecette extends AppCompatActivity {

    ImageView image_affichage_recette;
    TextView titre_affichage_recette;
    EditText text_description;

    String data1, data2;
    int images;

    private Button Retour_Menu_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        titre_affichage_recette = findViewById(R.id.titre_affichage_recette); //on recherche les 2 textes à récupérer et l'image à charger pour afficher la recette
        text_description = findViewById(R.id.text_description);
        image_affichage_recette = findViewById(R.id.image_affichage_recette);

        getData(); //ces 2 méthodes font passer les infos d'une activité à l'autre
        setData(); //les 2 méthodes sont crées plus bas

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

    private void getData(){
        if(getIntent().hasExtra("images") && getIntent().hasExtra("data1") && getIntent().hasExtra("data2")){ // on vérifie que les variables existent

            data1 = getIntent().getStringExtra("data1");
            data2 = getIntent().getStringExtra("data2");
            images = getIntent().getIntExtra("images",1);
        }
        else{
            Toast.makeText(this,"No data.", Toast.LENGTH_SHORT).show();
        }
    }
    private void setData(){
        titre_affichage_recette.setText(data1);
        text_description.setText(data2);
        image_affichage_recette.setImageResource(images);
    }
}