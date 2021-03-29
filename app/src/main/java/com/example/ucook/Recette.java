package com.example.ucook;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.util.Scanner;

public class Recette {
    String Nom, Instructions, Difficulte, Image;
    int Temps, Note, NbPersonnes;
    ArrayList<Composition> TabComposition = new ArrayList<Composition>();
    File recette = new File("Recette/Recette.txt");


    public Recette(String nom, String instructions, int temps, int note, String difficulte, int nbpersonnes, String Image){
        Nom=nom;
        Instructions=instructions;
        Temps=temps;
        Note=note;
        Difficulte=difficulte;
        NbPersonnes=nbpersonnes;
    }

    /*public Recette(){ // Test

    }*/
    public void ImporterRecette() throws FileNotFoundException {
        Scanner myReader = new Scanner(recette);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            System.out.println(data);
        }
        myReader.close();
    }

}
