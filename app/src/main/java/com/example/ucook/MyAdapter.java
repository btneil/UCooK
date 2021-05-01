package com.example.ucook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<String> rct_titre = new ArrayList<String>();
    ArrayList<String> rct_diff = new ArrayList<String>();
    ArrayList<Integer> images = new ArrayList<Integer>();
    ArrayList<String> rct_inst = new ArrayList<String>();
    ArrayList<String> rct_ing = new ArrayList<String>();
    ArrayList<String> id_liste = new ArrayList<>();
    Context context;
    Activity activity;
    String unite,multipl;

    MyAdapter(Activity activity, Context ct, ListeDeRecettes Livre_rct, ArrayList<String> id_liste){ //changement! plus de liste s1 et s2 mais uns liste de recette en entrée
        context = ct;
        this.activity = activity;
        int j=0;
        while(j!=Livre_rct.Liste.size()){
            this.rct_titre.add(Livre_rct.Liste.get(j).Nom);
            System.out.println(rct_titre);
            this.rct_diff.add(Livre_rct.Liste.get(j).Difficulte);
            this.images.add(Livre_rct.Liste.get(j).Image);
            this.rct_inst.add(Livre_rct.Liste.get(j).Instructions);
            this.id_liste = id_liste;

            //Comme ailleur, on adapte la liste d'ingrédient
            int i =0;
            String compo = "";

            String type_ing;

            while (i!=Livre_rct.Liste.get(j).TabComposition.size()){ //On crée ici le texte des ingrédients
                type_ing = Livre_rct.Liste.get(j).TabComposition.get(i).Ingredient.Type;

                mise_en_forme(type_ing);

                compo = compo + Livre_rct.Liste.get(j).TabComposition.get(i).Ingredient.Nom + " "
                        + multipl + String.valueOf(Livre_rct.Liste.get(j).TabComposition.get(i).Quantite) + unite + "!"+type_ing+"!";
                i++;
            }
            this.rct_ing.add(compo);
            j++;
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.apercu_recette, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.rct_titre_txt.setText(String.valueOf(rct_titre.get(position)));
        holder.rct_diff_txt.setText(String.valueOf(rct_diff.get(position)));
        holder.myImage.setImageResource(images.get(position));
        holder.layout_affichage_recette.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AffichageRecette.class);
                intent.putExtra("rct_titre", String.valueOf(rct_titre.get(position)));
                intent.putExtra("rct_diff", String.valueOf(rct_diff.get(position)));
                intent.putExtra("images", Integer.valueOf(images.get(position)));
                intent.putExtra("instructions", String.valueOf(rct_inst.get(position)));
                intent.putExtra("ingredients", String.valueOf(rct_ing.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return rct_titre.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView rct_titre_txt, rct_diff_txt;
        ImageView myImage;
        ConstraintLayout layout_affichage_recette;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rct_titre_txt = itemView.findViewById(R.id.titre_recette_txt);
            rct_diff_txt = itemView.findViewById(R.id.description_txt);
            myImage = itemView.findViewById(R.id.image_recette);
            layout_affichage_recette = itemView.findViewById(R.id.layout_affichage_recette);
        }
    }
    public void mise_en_forme(String test){
        unite="";
        multipl = "";

        if(test.equals("liquide")){
            unite="mL";
        }//si l'ingrédient est un liquide

        if(test.equals("poids")){
            unite="g";
        }//si l'ingrédient se pese

        if(test.equals("pincée")){
            unite="pincée";
        }//si l'ingredient se compte en "pincée"

        if(test.equals("nombre")){
            multipl="x";
        }//si l'ingredient se compte en nombre d'ingredient

        if(test.equals("cuillière")){
            unite=" cuillière";
            multipl="x";
        }//si l'ingredient se compte en cuillière
    }
}
