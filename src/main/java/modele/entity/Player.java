package modele.entity;

import modele.state.StatePlayer;
import modele.state.Waiting;

import java.util.ArrayList;

public class Player extends Entity {

    // Attributs
    private boolean alreadyVersusInTurn = false;
    private StatePlayer statePlayer;
    //Constructeur
    public Player(int score, String name, int positionX, int positionY) {
        super(score,name,positionX,positionY);
        statePlayer = new Waiting(this);
    }

    @Override
    public void moveEntity(int numberCase, int x, int y) {

        statePlayer.movePlayer(numberCase,x,y);
    }

    @Override
    public void nextTurn() {
        statePlayer.endTurn();
    }

    public void endVersusPlayer()
    {
        statePlayer.endVersus();
    }

    // Sélecteur et modificateur

    public void setEtat( StatePlayer statePlayer){
        this.statePlayer = statePlayer;
    }

    public StatePlayer getStatePlayer()
    {
        return statePlayer;
    }

    public void setAlreadyVersusInTurn(boolean alreadyVersusInTurn){
        this.alreadyVersusInTurn = alreadyVersusInTurn;
    }

    public boolean isAlreadyVersusInTurn() {
        return alreadyVersusInTurn;
    }


}
