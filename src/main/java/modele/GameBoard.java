package modele;

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
