package com.example.ucook;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Recette {
    String Nom, Instructions, Difficulte, Image;
    int Temps, Note, NbPersonnes;
    ArrayList<Composition> TabComposition = new ArrayList<Composition>();
    File recette = new File("Recette/Recette.txt");/*public Recette(String nom, String instructions, int temps, int note, String difficulte, int nbpersonnes, String Image){Nom=nom;Instructions=instructions;Temps=temps;Note=note;Difficulte=difficulte;NbPersonnes=nbpersonnes;}*/

    public Recette() {
        openFile();
        lectureFichier();
    }

    private Scanner rec;

    public void openFile() {
        try {
            System.out.println("aaaaaaaaaaaaa");
            rec = new Scanner(new File("Recette.txt"));

        } catch (Exception e) {
            System.out.println("erreur");
        }
    }

    public void lectureFichier() {
        rec.useDelimiter(",");
        while (rec.hasNext()) {
            Nom = rec.next();
            Difficulte = rec.next();
            //Temps = rec.next();
            //Note = rec.next();
            //NbPersonnes = rec.next();
            Instructions = rec.next();
        }
    }
}