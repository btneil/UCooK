package com.example.ucook;

import android.provider.Settings;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Recette {
    String Nom, Instructions, Difficulte, Image;
    int Temps, Note, NbPersonnes;
    ArrayList<Composition> TabComposition = new ArrayList<Composition>();
    File recette = new File("Recette/Recette.txt");/*public Recette(String nom, String instructions, int temps, int note, String difficulte, int nbpersonnes, String Image){Nom=nom;Instructions=instructions;Temps=temps;Note=note;Difficulte=difficulte;NbPersonnes=nbpersonnes;}*/

    public Recette() {
        File fichier = new File("C:/Users/quent/OneDrive/Desktop/Recette.txt");
        if (!fichier.exists()) {
            System.out.println("Le fichier n'existe pas");//test debug
            try {
                fichier.createNewFile();
                System.out.println("fichier créé");
                FileWriter writer = new FileWriter(fichier); //on va remplir le fichier
                BufferedWriter bw = new BufferedWriter(writer); //on se connecte au fichier
                bw.write("Croque-Monsieur");
                bw.newLine();//on saute une ligne
                bw.write("Facile");
                bw.close();//on arrete d'écrire
                writer.close();//on arrete d'ecrire

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("échec de la création du fichier");
            }
        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fichier),"UTF-8"));
            String ligne = reader.readLine();
            Nom=ligne;
            System.out.println("Le fichier a été correctement lu");
            ligne = reader.readLine();
            Difficulte=ligne;
        }
        catch (Exception e) {
            System.out.println("erreur lecture du fichier");
        }
        System.out.println(Nom + " " + Difficulte);

        //openFile();
        //lectureFichier();
    }

    private Scanner rec;


    /*public void openFile() {
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
    }*/
}