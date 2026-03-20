package models.state;

import models.entity.Player;

public class Versus extends StatePlayer {

    // Constructeur
    public Versus(Player p){
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
