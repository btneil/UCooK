package com.example.ucook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MenuPrincipal extends AppCompatActivity {

    RecyclerView recyclerView;

    String s1[], s2[];
    int images[] = {R.drawable.carotte,R.drawable.lasagne,
            R.drawable.carotte,R.drawable.lasagne,R.drawable.carotte,
            R.drawable.lasagne,R.drawable.carotte,R.drawable.lasagne,
            R.drawable.carotte,R.drawable.lasagne,R.drawable.carotte,
            R.drawable.lasagne,R.drawable.carotte,R.drawable.lasagne,
            R.drawable.carotte,R.drawable.lasagne,R.drawable.carotte,
            R.drawable.lasagne,R.drawable.carotte,R.drawable.lasagne,
            R.drawable.carotte,R.drawable.lasagne,R.drawable.carotte,
            R.drawable.lasagne,R.drawable.carotte,R.drawable.lasagne,
            R.drawable.carotte,R.drawable.lasagne,R.drawable.carotte,
            R.drawable.lasagne,R.drawable.carotte,R.drawable.lasagne};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        recyclerView = findViewById(R.id.recyclerView);

        s1 = getResources().getStringArray(R.array.titre_liste_recette);
        s2 = getResources().getStringArray(R.array.description );

        MyAdapter myAdapter = new MyAdapter(this,s1,s2,images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}