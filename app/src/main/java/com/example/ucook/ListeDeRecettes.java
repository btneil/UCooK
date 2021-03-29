package com.example.ucook;

import java.util.ArrayList;

public class ListeDeRecettes {
    ArrayList<Recette> Liste = new ArrayList<Recette>();

    public ListeDeRecettes(Recette rct){
        AjouterRecetteDansListe(rct);
    }

    public void AjouterRecetteDansListe(Recette rct){
        Liste.add(rct);
    }
}

