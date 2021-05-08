package com.example.ucook;

import java.util.ArrayList;

public class Recette {
    String Nom, Instructions, Difficulte,Temps;
    int NbPersonnes, Image;
    ArrayList<Composition> TabComposition = new ArrayList<Composition>();

    public Recette(String nom, String inst, String diff, String tpms, int nbp,int img,ArrayList<Composition>Liste_ing) {
        Nom = nom;
        Instructions = inst;
        Difficulte = diff;
        Temps = tpms;
        NbPersonnes = nbp;
        Image = img;
        TabComposition = Liste_ing;
    }
}