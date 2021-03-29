package com.example.ucook;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;

public class ListeDeCourse extends AppCompatActivity {  //se renseigner sur cette ligne, importante pour que super.machinbiduletruc fonctionne
    ArrayList<Composition> Liste_De_Courses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //appelle du constructeur parent et charge du calque associé
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_panier);

        //On va maintenant récupérer la liste des recettes. Il faudra transformer el'arraylist en string json, puis la stocker
        //et apres on la récuperera sous forme de chaine, on la retransformera en tableau fixe puis en arraylist

        SharedPreferences prefsStockees = getSharedPreferences("mesPrefs", MODE_PRIVATE);
        Gson gson = new Gson(); // création json
        // on extrait la liste referencée par le mot cle_listeEtudiants qu'on avait stocké dans les
        // préférences partagées
        String listeEtudiantTxtJson = prefsStockees.getString("cle_listeIngrédients", "");
        // desormais dans listeEtudiantsTxtJson on a tous nos etudiants stockés dans un format json
        // on reconstruit un tableau d'objets de type étudiants grace à al liste au format json
        if (listeEtudiantTxtJson.equals("")) {
            Liste_De_Courses = new ArrayList<Composition>();
        } else {
            Composition[] ListeDeCourseTemporaire = gson.fromJson(listeEtudiantTxtJson, Composition[].class);
            // on reconstruit l'array liste à partir d'un tableau temporaire
            Liste_De_Courses = new ArrayList<Composition>(Arrays.asList(ListeDeCourseTemporaire));
        }
        BaseAdapter customBaseAdapter = new BaseAdapter() {
            // Return list view item count.
            @Override
            public int getCount() {
                return Liste_De_Courses.size();
            }

            @Override
            public Object getItem(int i) {
                // getItem doit renvoyer l'item qui est associé à l'éléméent de liste d'indice i
                // on renvoie simplement le i^eme elemnt de listeEtudiant, car la listview doit etre
                // etre synchronisée avec listeEtudiants
                return Liste_De_Courses.get(i);
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(final int itemIndex, View itemView, ViewGroup viewGroup) {
                if (itemView == null) {   // on va creer une case réponse (une ligne du listview ) avec un modele défini dans le fichier
                    // xml main_activity_base_adapter
                    itemView = LayoutInflater.from(ListeDeCourse.this).inflate(R.layout.activity_element_liste, null);
                }

                // On récupere les 3 cases (image + zone identite + zone age de ce modele)
                // on va les remplir par la suite avec les valeurs à affcher pour cette ligne
                // ImageView imageView = (ImageView) itemView.findViewById(R.id.baseUserImage);
                TextView textTest_bis = (TextView) itemView.findViewById(R.id.textTest);
                TextView textQtt_bis = (TextView) itemView.findViewById(R.id.textQtt);


                // on lit les valeur des ressources par rapport à listeetudiants
                Composition CompoAafficher = (Composition) Liste_De_Courses.get(itemIndex);
                //imageView.setImageResource(R.mipmap.ic_launcher);
                final Ingredient Ingredient = CompoAafficher.Ingredient;
                final int Qtt = CompoAafficher.Quantite;

                // on les insère dans les champs correspondants
                textTest_bis.setText(Ingredient.Nom);
                textQtt_bis.setText(Qtt);
                return itemView;

            }


    /*public void MettreDansLePanier(Recette recette) {
        //while (recette.TabComposition.hasNext());
        for (int i = 0; i < recette.TabComposition.size(); i++) {
            Ingredient ingcourant = recette.TabComposition.get(i).Ingredient;
            int qttcourant = recette.TabComposition.get(i).Quantite;
            for (int j = 0; j < TabCompo.size(); j++) {
                if (ingcourant == TabCompo.get(j).Ingredient) {
                    int qttfinal = qttcourant + TabCompo.get(j).Quantite;
                    TabCompo.remove(j);
                    Composition compofinale = new Composition(ingcourant, qttfinal);
                    TabCompo.add(compofinale);

                } else {
                    TabCompo.add(recette.TabComposition.get(i));

                }
            }


        }


    }*/
        };
    }
}
