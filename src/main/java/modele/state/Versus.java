package modele.state;

import modele.entity.Player;

public class Versus extends StatePlayer {

    // Constructeur
    Versus(Player p){
        super(p);
    }

    @Override
    public void endTurn() {

    }

    @Override
    public void movePlayer(int score, int x, int y) {

    }

    @Override
    public void endVersus() {

    }
}
