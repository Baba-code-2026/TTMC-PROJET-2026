package modele;

import com.google.gson.Gson;
import modele.entity.Entity;
import modele.question.Question;

import java.util.ArrayList;

public class Game {

    // Attribut
    private GameBoard gBoard;
    private int nbTurn = 1;
    private ArrayList<Entity> entities;
    private final int MAX_ENTITIES = 5;

    //Constructeur
    Game(GameBoard gBoard) {
        this.gBoard = GameBoard.getInstance(this);
        entities = new ArrayList<>();
    }

    // Lance le jeu
    public void start(){

    }

    public void turnManagement(){

    }

    public boolean addEntity(Entity entity){
        return false;
    }

    public void readData(String s){

    }

    public int getNbTurn() {
        return nbTurn;
    }

    public GameBoard getGBoard() {
        return gBoard;
    }

    public void setNbTurn(int turn){
        nbTurn = turn;
    }


}
