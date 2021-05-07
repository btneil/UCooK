package com.example.ucook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDataBase extends SQLiteOpenHelper {

    private Context context;
    private static final String DATA_BASE_NAME ="Liste_de_recettes";
    private static final int DATA_BASE_VERSION =7;

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
        System.out.println("La nom de la recette ajoutée est : " + Rct.Nom);
        cv.put(COLUMN_DIFF, Rct.Difficulte);

        //On va transformer la tab des compo en String
        int i =0;
        String TabCompoTransition="";
        while (i!=Rct.TabComposition.size()){
            TabCompoTransition=TabCompoTransition+String.valueOf(Rct.TabComposition.get(i).Ingredient.Nom)+"/";
            TabCompoTransition=TabCompoTransition+String.valueOf(Rct.TabComposition.get(i).Ingredient.Type)+"/";
            TabCompoTransition=TabCompoTransition+String.valueOf(Rct.TabComposition.get(i).Quantite)+",";
            i++;
        }
        String TabCompoTxt=TabCompoTransition.substring(0,TabCompoTransition.length()-1); //on enlève la dernière virgule, et voila le String des ing/type/qtt

        cv.put(COLUMN_ING, TabCompoTxt); // liste ingredient au format String
        cv.put(COLUMN_TEMPS, Rct.Temps);
        cv.put(COLUMN_IMG, Rct.Image);
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
        cv.put(COLUMN_INST, instructions);// à compléter avec nouvelles colonnes

        long result = db.update(TABLE_NAME, cv,"_id=?", new String[]{rows_id});
        if(result == -1){
            Toast.makeText(context, "ECHEC DE LA MISE A JOUR", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "SUCCES DE LA MISE A JOUR", Toast.LENGTH_SHORT).show();
        }

    }

    void initialiser_bdd(){ //on initialise la bdd
        Ingredient huile_dolive = new Ingredient("huile_d'olive","cuillière");
        Ingredient sel = new Ingredient("sel","pincée(s)");
        Ingredient citron = new Ingredient("citron(s)","nombre");
        Ingredient persil = new Ingredient("persil","botte");
        Ingredient tomate = new Ingredient("tomate(s)","nombre");
        Ingredient oignons_v = new Ingredient("oignons_verts","nombre");
        Ingredient menthe = new Ingredient("menthe","botte");
        Ingredient Boulgour_b = new Ingredient("boulgour_brun","poignée");
        ArrayList<Composition> Compo_V_lib = new ArrayList<>();
        Compo_V_lib.add(new Composition(sel,2));
        Compo_V_lib.add(new Composition(huile_dolive,3));
        Compo_V_lib.add(new Composition(citron,1));
        Compo_V_lib.add(new Composition(persil,2));
        Compo_V_lib.add(new Composition(tomate,4));
        Compo_V_lib.add(new Composition(oignons_v,6));
        Compo_V_lib.add(new Composition(menthe,1));
        Compo_V_lib.add(new Composition(Boulgour_b,1));


        Recette Vrai_tab_libanais = new Recette("Le vrai taboulé libanais",
                "Avant de commencer mettre une poignée de boulghour dans un bol d'eau pendant 15 mn et laisser ramollir.\n" +
                        "\n" +
                        "Laver et équeuter le persil, puis le couper au couteau (ou aux ciseaux si l'on préfère).\n" +
                        "\n" +
                        "Répéter l'opération avec la menthe, vous devez obtenir des feuilles d'1 cm environ.\n" +
                        "\n" +
                        "Mettre le tout dans un saladier.\n" +
                        "\n" +
                        "Couper les oignons verts très fins, les tomates en petits dés, et mettre le tout dans le saladier.\n" +
                        "\n" +
                        "Lorsque le boulghour ne croque plus, le sortir de l'eau, et le presser entre les mains pour l'essorer. Le mettre dans le saladier avec le reste.\n" +
                        "\n" +
                        "Assaisonnement : presser 1 citron entier et arroser le contenu du saladier. Ajouter le sel et les 3 cuillères à soupe d'huile d'olive.\n" +
                        "\n" +
                        "L'aspect du taboulé doit être brillant, pour indiquer qu'il y a suffisamment d'huile d'olive.",
                "Facile","25",5,R.drawable.le_v_tb_libanais,Compo_V_lib);
        ajouter_rct(Vrai_tab_libanais);
    }
}
