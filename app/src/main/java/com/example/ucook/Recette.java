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
    String Nom, Instructions, Difficulte;
    int Temps, Note, NbPersonnes, Image;
    ArrayList<Composition> TabComposition = new ArrayList<Composition>();
    File recette = new File("Recette/Recette.txt");/*public Recette(String nom, String instructions, int temps, int note, String difficulte, int nbpersonnes, String Image){Nom=nom;Instructions=instructions;Temps=temps;Note=note;Difficulte=difficulte;NbPersonnes=nbpersonnes;}*/

    public Recette(String nom, String inst, String diff, int tpms, int nbp,int img) {
        Nom = nom;
        Instructions = inst;
        Difficulte = diff;
        Temps = tpms;
        NbPersonnes = nbp;
        Image = img;
    }
}