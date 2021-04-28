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

public class MyAdapter_panier extends RecyclerView.Adapter<MyAdapter_panier.MyViewHolder>{

    ArrayList Nom_ingredient, quantite;
    Context context;
    Activity activity;

    MyAdapter_panier(Activity activity,Context ct, ArrayList Nom_ingredient, ArrayList quantite){
        context = ct;
        this.activity = activity;
        this.Nom_ingredient = Nom_ingredient;
        this.quantite = quantite;
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
        holder.titre_ing_txt.setText(String.valueOf(Nom_ingredient.get(position)));
        holder.quantite_txt.setText(String.valueOf(quantite.get(position)));
    }

    @Override
    public int getItemCount() {
        return Nom_ingredient.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titre_ing_txt, quantite_txt;
        ConstraintLayout layout_affichage_ing;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titre_ing_txt = itemView.findViewById(R.id.titre_ing);
            quantite_txt = itemView.findViewById(R.id.qte);
            layout_affichage_ing = itemView.findViewById(R.id.layout_affichage_ing);
        }
    }
}
