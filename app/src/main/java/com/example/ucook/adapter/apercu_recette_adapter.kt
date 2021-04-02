package com.example.ucook.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.ucook.R

class apercu_recette_adapter : RecyclerView.Adapter<apercu_recette_adapter.ViewHolder(){
    //boite à ranger composants à controler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val Recette_Image = view.findViewById<ImageView>(R.id.image_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_apercu_recette, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }
}