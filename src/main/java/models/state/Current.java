package models.state;

import models.entity.Player;

public class Current extends StatePlayer {

    // Constructeur
    public Current(Player player) {
        super(player);
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
