package com.example.ucook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter_panier extends RecyclerView.Adapter<MyAdapter_panier.MyViewHolder>{

    ArrayList Nom_ingredient, quantite, type, id;
    Context context;
    Activity activity;
    String unite,multipl;

    MyAdapter_panier(Activity activity,Context ct, ArrayList Nom_ingredient, ArrayList quantite,ArrayList<String> type,ArrayList id){
        context = ct;
        this.activity = activity;
        this.Nom_ingredient = Nom_ingredient;
        this.quantite = quantite;
        this.type = type;
        this.id = id;
    }

    @NonNull
    @Override
    public MyAdapter_panier.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.element_panier_course, parent,false);
        return new MyAdapter_panier.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter_panier.MyViewHolder holder, int position) {
        holder.titre_ing_txt.setText(String.valueOf(Nom_ingredient.get(position)).replace("_"," "));
        mise_en_forme((String) type.get(position));
        String Qte = multipl + quantite.get(position) + unite;
        holder.quantite_txt.setText(Qte);
        holder.titre_ing_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDataBase_panier MyDB = new MyDataBase_panier(context);
                MyDB.Supp_1_item(String.valueOf(id.get(position)));
                holder.quantite_txt.setText("0");
                holder.Supp_txt.setText("Supprimé");
            }
        });
    }

    @Override
    public int getItemCount() {
        return Nom_ingredient.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        CheckBox titre_ing_txt;
        TextView quantite_txt,Supp_txt;
        ConstraintLayout layout_affichage_ing;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titre_ing_txt = itemView.findViewById(R.id.titre_ing);
            quantite_txt = itemView.findViewById(R.id.qte);
            layout_affichage_ing = itemView.findViewById(R.id.layout_affichage_ing);
            Supp_txt = itemView.findViewById(R.id.Supp_txt);
        }
    }
    public void mise_en_forme(String ing_type){
        unite="";
        multipl = "";

        if(ing_type.equals("liquide")){
            unite="mL";
        }//si l'ingrédient est un liquide

        if(ing_type.equals("poids")){
            unite="g";
        }//si l'ingrédient se pese

        if(ing_type.equals("pincée")){
            unite="pincée";
        }//si l'ingredient se compte en "pincée"

        if(ing_type.equals("nombre")){
            multipl="x";
        }//si l'ingredient se compte en nombre d'ingredient

        if(ing_type.equals("cuillière")){
            unite=" cuillière";
            multipl="x";
        }//si l'ingredient se compte en cuillière
        if(ing_type.equals("botte")){
            unite=" botte(s)";
        }
        if(ing_type.equals("poignée")){
            unite="poignée(s)";
        }
    }
}
