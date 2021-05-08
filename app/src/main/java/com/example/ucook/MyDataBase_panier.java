package com.example.ucook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDataBase_panier extends SQLiteOpenHelper {

    private Context context;
    private static final String DATA_BASE_NAME ="Panier";
    private static final int DATA_BASE_VERSION =18;
    private static final String TABLE_NAME = "ma_liste";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_INGR = "ingrédient";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_QTE = "quantité";


    MyDataBase_panier(@Nullable Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_INGR + " TEXT, " +
                COLUMN_TYPE + " TEXT, " +
                COLUMN_QTE + " int);"; //Declaration du SQL dans query, ATTENTION AUX ESPACES ! ! ! +Int plutot que string plus malin, mais pb??? à revoir
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void ajouter_rct(String ingredient,String type,int qte){
        SQLiteDatabase db = this.getWritableDatabase(); //pour écrire das la bdd
        ContentValues cv = new ContentValues(); //ce qui permet d'écrire dans la bdd

        cv.put(COLUMN_INGR, ingredient); //on ajoute les 3 champs suivants dans la bdd
        cv.put(COLUMN_TYPE, type);
        cv.put(COLUMN_QTE,qte);

        long result = db.insert(TABLE_NAME,null, cv);
        if (result==-1){ // si l'appli échoue
            Toast.makeText(context, "Une erreur c'est produite", Toast.LENGTH_SHORT).show(); //on affiche le message suivant dans l'appli
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db!=null){
            cursor = db.rawQuery(query, null); //cursor contient maintenant toute la bdd
        }
        return cursor; //cursor contient donc mtn toute la bdd
    }

    void updateData(String rows_id, int qte){ //pour modifier la quantité d'un ingrédient déjà existant
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_QTE, qte);
        long result = db.update(TABLE_NAME, cv,"_id=?", new String[]{rows_id}); //on stocke le resultat dans long result
        if(result == -1){
            Toast.makeText(context, "ECHEC DE L'AJOUT DE LA QTE", Toast.LENGTH_SHORT).show();
        }
    }

    void Supp_1_item(String id_item){ //supression d'un ingrédient
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME,"_id=?", new String[]{id_item});
        if (result == -1){
            Toast.makeText(context,"Echec de la supression", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context,"Supprimé avec succès", Toast.LENGTH_SHORT).show();
        }
    }
}
