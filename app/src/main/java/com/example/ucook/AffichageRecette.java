package com.example.ucook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
    TextView tmps_rct_txt;

    String data1, data2, instructions, ingredients,ing_type, ing_txt, temps_rct;
    int images;

    private FloatingActionButton home_btn;
    private ImageButton ajouter_panier_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_recette);

        titre_affichage_recette = findViewById(R.id.titre_affichage_recette); //on recherche les 2 textes à récupérer et l'image à charger pour afficher la recette
        text_diff = findViewById(R.id.rct_diff);
        image_affichage_recette = findViewById(R.id.image_affichage_recette);
        instructions_txt = findViewById(R.id.instructions_txt);
        Ingredients_txt = findViewById(R.id.Ingredients_txt);
        tmps_rct_txt = findViewById(R.id.tmps_rct_txt);

        getData(); //ces 2 méthodes font passer les infos d'une activité à l'autre
        setData(); //les 2 méthodes sont crées plus bas

        this.home_btn = findViewById(R.id.home_btn);
        this.ajouter_panier_btn = findViewById(R.id.ajouter_panier_btn);
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Retour_Menu = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(Retour_Menu);
                finish();
            }
        });
        ajouter_panier_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Ajout_Ing = new Intent(getApplicationContext(),Ajout_ingredient.class);
                Ajout_Ing.putExtra("composition", ing_txt);
                Ajout_Ing.putExtra("ing_type", ing_type);
                startActivity(Ajout_Ing);
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
            temps_rct = getIntent().getStringExtra("temps");
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
        tmps_rct_txt.setText(temps_rct+" min");
        ing_type = "";
        int i = 0;
        ing_txt = "";
        while (i < ingredients.split("!").length) {
            ing_txt = ing_txt + ingredients.split("!")[i] + "\n"; //on met en forme l'affichage des ingrédients (on enleve le type)
            ing_type = ing_type + "!" + ingredients.split("!")[i+1];
            i=i+2;
        }
        Ingredients_txt.setText(ing_txt.replace("_"," "));

    }
}