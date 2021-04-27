package com.example.ucook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter_Ajout_Ing extends RecyclerView.Adapter<MyAdapter_Ajout_Ing.MyViewHolder>{

    ArrayList<Ingredient> Composition;
    Context context;
    Activity activity;
    String AAA;

    MyAdapter_Ajout_Ing(Activity activity,Context ct, String AAA){
        context = ct;
        this.activity = activity;
        this.AAA = AAA;
    }

    @NonNull
    @Override
    public MyAdapter_Ajout_Ing.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.apercu_ingredient, parent,false);
        return new MyAdapter_Ajout_Ing.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter_Ajout_Ing.MyViewHolder holder, int position) {
        holder.composition_txt.setText(AAA);
        holder.layout_compo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Ajout_ingredient.class);
                intent.putExtra("composition", "test");
                activity.startActivityForResult(intent,1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView composition_txt;
        ConstraintLayout layout_compo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            composition_txt = itemView.findViewById(R.id.composition_txt);
            layout_compo = itemView.findViewById(R.id.layout_compo);
        }
    }
}

