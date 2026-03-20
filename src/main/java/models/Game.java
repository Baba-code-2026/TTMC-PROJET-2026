package models;

import models.entity.Entity;
import models.entity.Player;

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
        // Lecture des fichiers Json de Tilde et Questions

        // Demande le nombre d'entity à créer

        // Créer entre 3 et 5 entites
        int nbEntities = 4;
        String []tabNamePlayer = {"Flash McQueen", "Mater", "Sally", "Doc Hudson"};
        for (int i = 0; i < nbEntities; i++){
            // IL faut aller Chercher Tield de Départ.
            Tilde depart = gBoard.checkTilde(0);
            entities.add(new Player(0,tabNamePlayer[i],depart.getPositionX(),depart.getPositionY()));

            //Lancement du jeu
            turnManagement();
        }
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
