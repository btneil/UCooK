package com.example.ucook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Ajouter_Recette extends AppCompatActivity {


    EditText titre_rct, diff_rct, ing_rct, tmps_rct, instructions_rct;
    Button add_btn;
    ArrayList<Composition> Tab_compo_ign = new ArrayList<Composition>();
    String [] text_ing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_recette);

        titre_rct = findViewById(R.id.titre_rct);
        diff_rct = findViewById(R.id.diff_rct);
        ing_rct = findViewById(R.id.ing_rct);
        tmps_rct = findViewById(R.id.tmps_rct);
        instructions_rct = findViewById(R.id.instructions);
        add_btn = findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(Champs_presents(titre_rct,diff_rct,ing_rct,tmps_rct,instructions_rct));
                if(Champs_presents(titre_rct,diff_rct,ing_rct,tmps_rct,instructions_rct)==true){
                    int i = 0;
                    text_ing = String.valueOf(ing_rct.getText()).trim().toLowerCase().replace(", ",",").split(",");//on récup le texte des ingrédients et on le formate pour qu'il n'y ai pas de pb d'espaces
                    // ni de majuscule. Enfin on le divise en tableau à chaque ","

                    while(i!=text_ing.length){//on va essayer de remplir la liste d'ingredient ici à l'aide du text récupéré (idée: séparation par des "," et couple ing/type/qtt)
                        Tab_compo_ign.add(new Composition(new Ingredient(text_ing[i].split("/")[0],text_ing[i].split("/")[1]),//ici on crée l'ingredient avec les 2 premieres cases
                                Integer.parseInt(text_ing[i].split("/")[2]))); //ici,  quantité de l'ingrédient
                        i++;
                    }//si tout s'est bien passé, Tab_compo_ign est maintenant rempli de compo (ingredient+quantité)
                    Recette Rct = new Recette(titre_rct.getText().toString(),instructions_rct.getText().toString(),diff_rct.getText().toString(),
                            tmps_rct.getText().toString(),1,R.drawable.image_defaut,Tab_compo_ign); //nbp fixé à 1, à changer?
                    MyDataBase myDB = new MyDataBase(Ajouter_Recette.this);
                    myDB.ajouter_rct(Rct); //on converti chaque champs pour qu'ils correspondent à ceux attendus
                }
            }
        });
    }
    public boolean Champs_presents(EditText titre_rct,EditText diff_rct, EditText ing_rct, EditText tmps_rct,EditText instructions_rct){
        if(titre_rct.getText().toString().equals("") || diff_rct.getText().toString().equals("") || ing_rct.getText().toString().equals("")
                || tmps_rct.getText().toString().equals("") || instructions_rct.getText().toString().equals("")){
            Toast.makeText(this,"Champs absents",Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            return true;
        }
    }
}