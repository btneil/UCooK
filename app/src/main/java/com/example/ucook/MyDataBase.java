package com.example.ucook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDataBase extends SQLiteOpenHelper {

    private Context context;
    private static final String DATA_BASE_NAME ="Liste_de_recettes_3";
    private static final int DATA_BASE_VERSION =1;

    private static final String TABLE_NAME = "mon_livre";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "titre_rct";
    private static final String COLUMN_DIFF = "difficulte";
    private static final String COLUMN_ING = "ingredients";
    private static final String COLUMN_TEMPS = "temps";
    private static final String COLUMN_IMG = "images";
    private static final String COLUMN_INST = "instructions";
    private static final String COLUMN_NB_PERSONNES = "nb_personnes";


    public MyDataBase(@Nullable Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TITLE + " TEXT, " +
                        COLUMN_DIFF + " TEXT, " +
                        COLUMN_ING + " TEXT, " +
                        COLUMN_TEMPS + " int, " +
                        COLUMN_IMG+ " int, "+
                        COLUMN_NB_PERSONNES+ "int, "+
                        COLUMN_INST + " TEXT);"; //Declaration du SQL dans query, ATTENTION AUX ESPACES ! ! ! + BLOB comme type pour image?

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void ajouter_rct(Recette Rct){
        SQLiteDatabase db = this.getWritableDatabase(); //pour écrire das la bdd
        ContentValues cv = new ContentValues(); //ce qui permet d'écrire dans la bdd

        cv.put(COLUMN_TITLE, Rct.Nom);
        cv.put(COLUMN_DIFF, Rct.Difficulte);
        cv.put(COLUMN_ING, Rct.Instructions); // trouver un moyen d'y mettre une liste d'ingrédients
        cv.put(COLUMN_TEMPS, Rct.Temps);
        cv.put(COLUMN_IMG, Rct.Image);
        cv.put(COLUMN_NB_PERSONNES, Rct.NbPersonnes);
        cv.put(COLUMN_INST, Rct.Instructions);
        long result = db.insert(TABLE_NAME,null, cv);
        if (result==-1){ // si l'appli échoue
            Toast.makeText(context, "ECHEC DE LA BDD", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "SUCCES BDD", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db!=null){
            cursor = db.rawQuery(query, null);
        }
        return cursor; //cursor contient donc mtn toute la bdd
    }

    void updateData(String rows_id, String titre, String diff, String Ing, int tmps, String instructions){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, titre);
        cv.put(COLUMN_DIFF, diff);
        cv.put(COLUMN_ING, Ing);
        cv.put(COLUMN_TEMPS, tmps);
        cv.put(COLUMN_INST, instructions);// à compléter avec les 2 nouvelles colonnes

        long result = db.update(TABLE_NAME, cv,"_id=?", new String[]{rows_id});
        if(result == -1){
            Toast.makeText(context, "ECHEC DE LA MISE A JOUR", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "SUCCES DE LA MISE A JOUR", Toast.LENGTH_SHORT).show();
        }

    }
}
