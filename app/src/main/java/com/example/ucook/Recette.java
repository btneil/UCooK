package com.example.ucook;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.util.Scanner;

public class Recette {
    String Nom, Instructions;
    int Temps, Note, Difficulte, NbPersones;
    ArrayList<Composition> TabComposition = new ArrayList<Composition>();
    File recette = new File("Recette/Recette.txt");


    /*public Recette(String nom, String instruction, int temps, int note, int difficulte, int nbpersonnes){
        Nom=nom;
        Instructions=instructions;
        Temps=temps;
        Note=note;
        Difficulte=difficulte;
        NbPersonnes=nbpersonnes;
    }*/

    public Recette(){ // Test

    }
    public void ImporterRecette() throws FileNotFoundException {
        Scanner myReader = new Scanner(recette);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            System.out.println(data);
        }
        myReader.close();
    }

}
