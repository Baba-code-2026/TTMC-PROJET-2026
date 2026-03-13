package modele;

import modele.entity.Entity;


import com.google.gson.Gson;
import modele.question.Theme;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class GameBoard {
    //Attributs
    private static GameBoard singleton = null;
    private Game game;
    private ArrayList<Tilde> tildes;

    private GameBoard(Game game){
        this.game = game;
        tildes = new ArrayList<>();
    }

    public int answerQuestion(Entity e, Tilde t){
        return 0;
    }

    public Tilde getNextTilde(Tilde t, int times){
        return null;
    }

    public Tilde checkTilde(int score){
        for( Tilde t : tildes){
            if(score ==t.getNumber()){
                return t;
            }
        }
        return null;
    }

    public void createTilde(File json){
        // Ici on va ajouter les cases sauvegardées dans le json
        Gson gson = new Gson();
        try (java.io.FileReader reader = new java.io.FileReader(json)) {
            java.lang.reflect.Type listType = new com.google.gson.reflect.TypeToken<ArrayList<Tilde>>(){}.getType();
            ArrayList<Tilde> listTildes = gson.fromJson(reader, listType);

            for (Tilde tilde : listTildes) {
                this.tildes.add(tilde);
            }
            System.out.println("Chargement réussi : " + tildes.size() + " cases.");

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public int[] randomArray() {
        int[] tableau = new int[10]; // taille du tableau
        Random rand = new Random();
        for (int i = 0; i < tableau.length; i++) {
            tableau[i] = rand.nextInt(4) + 1; // génère 1 à 4
        }
        return tableau;
    }
    // Getter
    public static GameBoard getInstance(Game game)
    {
        if(singleton == null){
            singleton = new GameBoard(game);
        }
        return singleton;
    }

    public Game getGame() {
        return game;
    }
}
