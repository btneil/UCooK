package com.example.ucook;

import java.util.ArrayList;

public class ListeDeRecettes {
    ArrayList<Recette> Liste = new ArrayList<Recette>();

    public ListeDeRecettes(){

    }

    public void AjouterRecetteDansListe(Recette rct){
        Liste.add(rct);
    }
}

