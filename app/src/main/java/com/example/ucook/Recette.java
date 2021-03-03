package com.example.ucook;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.util.Scanner;

public class Recette {
    String Nom, Instructions;
    int Temps, Note, Difficulte, NbPersones;
    ArrayList<Composition> TabComposition = new ArrayList<Composition>();
    File Recette = new File("Recette.txt");


    /*public Recette(String nom, String instruction, int temps, int note, int difficulte, int nbpersonnes){
        Nom=nom;
        Instructions=instruction;
        Temps=temps;
        Note=note;
        Difficulte=difficulte;
        NbPersones=nbpersonnes;
    }*/

    public Recette(){ // Test

    }
     public void ImporterRecette(){
         Scanner myReader = null;
         try {
             myReader = new Scanner(Recette);
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         }
         while (myReader.hasNextLine()) {
             String data = myReader.nextLine();
             System.out.println(data);
         }
         myReader.close();



     }
}
