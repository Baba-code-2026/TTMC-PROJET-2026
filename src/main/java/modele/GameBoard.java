package modele;

import modele.entity.Entity;


import com.google.gson.Gson;
import modele.question.Theme;

import java.io.File;
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

    public void createTilde(File json){
        // Ici on va ajouter les cases sauvegardées dans le json
        Gson gson = new Gson();
        try (java.io.FileReader reader = new java.io.FileReader(json)) {
            java.lang.reflect.Type listType = new com.google.gson.reflect.TypeToken<ArrayList<TildeDTO>>(){}.getType();
            ArrayList<TildeDTO> dtos = gson.fromJson(reader, listType);

            for (TildeDTO dto : dtos) {
                Theme t = new Theme(dto.theme); // À remplacer par le bon thème
                Tilde tilde = new Tilde(dto.positionX, dto.positionY, dto.color, t);
                this.tildes.add(tilde);
            }

            System.out.println("Chargement réussi : " + tildes.size() + " cases.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class TildeDTO {
        int positionX;
        int positionY;
        String color;
        String theme;
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
