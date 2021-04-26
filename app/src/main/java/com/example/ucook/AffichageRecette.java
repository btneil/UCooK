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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

public class AffichageRecette extends AppCompatActivity {

    ImageView image_affichage_recette;
    TextView titre_affichage_recette;
    TextView text_diff;
    TextView instructions_txt;
    TextView Ingredients_txt;

    String data1, data2, instructions, ingredients;
    int images;

    private FloatingActionButton home_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_recette);

        titre_affichage_recette = findViewById(R.id.titre_affichage_recette); //on recherche les 2 textes à récupérer et l'image à charger pour afficher la recette
        text_diff = findViewById(R.id.rct_diff);
        image_affichage_recette = findViewById(R.id.image_affichage_recette);
        instructions_txt = findViewById(R.id.instructions_txt);
        Ingredients_txt = findViewById(R.id.Ingredients_txt);


        getData(); //ces 2 méthodes font passer les infos d'une activité à l'autre
        setData(); //les 2 méthodes sont crées plus bas



        this.home_btn = findViewById(R.id.home_btn);
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Retour_Menu = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(Retour_Menu);
                finish();
            }
        });



    }

    private void getData(){
        if(getIntent().hasExtra("images") && getIntent().hasExtra("rct_titre") && getIntent().hasExtra("rct_diff")){ // on vérifie que les variables existent

            data1 = getIntent().getStringExtra("rct_titre");
            data2 = getIntent().getStringExtra("rct_diff");
            images = getIntent().getIntExtra("images",1);
            instructions = getIntent().getStringExtra("instructions");
            ingredients = getIntent().getStringExtra("ingredients");
        }
        else{
            Toast.makeText(this,"No data.", Toast.LENGTH_SHORT).show();
        }
    }
    private void setData(){
        titre_affichage_recette.setText(data1);
        text_diff.setText(data2);
        image_affichage_recette.setImageResource(images);
        instructions_txt.setText(instructions);
        Ingredients_txt.setText(ingredients);

    }
}