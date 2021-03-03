package com.example.ucook;

import java.util.ArrayList;

public class Recette {
    String Nom, Instructions;
    int Temps, Note, Difficulte, NbPersones;
    ArrayList<Composition> TabComposition = new ArrayList<Composition>();

    public Recette(String nom, String instruction, int temps, int note, int difficulte, int nbpersonnes){
        Nom=nom;
        Instructions=instruction;
        Temps=temps;
        Note=note;
        Difficulte=difficulte;
        NbPersones=nbpersonnes;
    }
     public boolean ImporterRecette(){



     }
}
