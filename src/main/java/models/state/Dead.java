package models.state;

import models.entity.Player;

public class Dead extends StatePlayer {

    //Constructeur
    public Dead(Player player) {
        super(player);
    }

    @Override
    public void endTurn() {

    }

    @Override
    public void movePlayer(int score, int x, int y) {
        return ; //
    }

    @Override
    public void endVersus() {

    }
}

