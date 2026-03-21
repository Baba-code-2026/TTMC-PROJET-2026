package models;

import com.google.gson.JsonObject;
import models.entity.Entity;


import com.google.gson.Gson;
import models.question.Theme;

import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;

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

    public void readTilde() {
        try {
            URL urlJson = Theme.class.getResource("/JSONFILE/tilde.json");
            if (urlJson != null) {
                File json = new File(urlJson.toURI());

                Gson gson = new Gson();

                // Lecture du fichier
                JsonObject root = gson.fromJson(new FileReader(json), JsonObject.class);

                // Extraction du tableau "chemin"
                int[][] tableau = gson.fromJson(root.get("chemin"), int[][].class);

                //creation de la liste des tildes

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
