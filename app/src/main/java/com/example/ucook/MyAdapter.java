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
    ListeDeRecettes Livre_rct;
    int images[];
    Context context;
    Activity activity;

    MyAdapter(Activity activity,Context ct, ListeDeRecettes Livre_rct, int img[]){ //changement! plus de liste s1 et s2 mais uns liste de recette en entrée
        context = ct;
        this.activity = activity;
        int j=0;
        while(j!=Livre_rct.Liste.size()){
            this.rct_titre.add(Livre_rct.Liste.get(j).Nom);
            this.rct_diff.add(Livre_rct.Liste.get(j).Difficulte);
            j++;
        }

        images = img;
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
        holder.myImage.setImageResource(images[position]);
        holder.layout_affichage_recette.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AffichageRecette.class);
                intent.putExtra("rct_titre", String.valueOf(rct_titre.get(position)));
                intent.putExtra("rct_diff", String.valueOf(rct_diff.get(position)));
                intent.putExtra("images", images[position]);
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
}
