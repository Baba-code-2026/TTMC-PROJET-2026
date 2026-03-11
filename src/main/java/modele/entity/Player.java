package modele.entity;

import modele.state.StatePlayer;

public class Player extends Entity {

    // Attributs
    private boolean alreadyVersusInTurn = false;
    private StatePlayer statePlayer;
    //Constructeur
    Player(int score, String name, int positionX, int positionY, StatePlayer state) {
        super(score,name,positionX,positionY);
        statePlayer = state;
    }

    @Override
    public void moveEntity(int score, int x, int y) {
        statePlayer.movePlayer(score,x,y);
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
