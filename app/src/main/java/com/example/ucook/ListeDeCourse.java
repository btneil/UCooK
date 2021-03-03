package com.example.ucook;

import java.util.ArrayList;

public class ListeDeCourse {
    ArrayList<Composition> TabCompo = new ArrayList<Composition>();






}

public void MettreDansLePanier(Recette recette){
    //while (recette.TabComposition.hasNext());
    for(int i=0;i<recette.TabComposition.size();i++){
        Ingredient ingcourant=recette.TabComposition.get(i).Ingredient;
        int qttcourant =recette.TabComposition.get(i).Quantite;
        for(int j=0;j<TabCompo.size();j++){
            if (ingcourant==TabCompo.get(j).Ingredient){
                int qttfinal=qttcourant+TabCompo.get(j).Quantite;
                TabCompo.remove(j);
                Composition compofinale=new Composition(ingcourant,qttfinal);
                TabCompo.add(compofinale);

            }
            else {
                TabCompo.add(recette.TabComposition.get(i));

            }
        }


    }


}