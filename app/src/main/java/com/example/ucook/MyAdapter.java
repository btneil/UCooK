package com.example.ucook;

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

    ArrayList rct_titre, rct_diff;
    int images[];
    Context context;

    public MyAdapter(Context ct, ArrayList rct_titre, ArrayList rct_diff, int img[]){
        context = ct;
        this.rct_titre = rct_titre;
        this.rct_diff = rct_diff;
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

        /*holder.layout_affichage_recette.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AffichageRecette.class);
                intent.putExtra("data1", data1[position]);
                intent.putExtra("data2", data2[position]);
                intent.putExtra("images", images[position]);
                context.startActivity(intent);
            }
        });*/

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
