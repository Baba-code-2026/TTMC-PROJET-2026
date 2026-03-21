package models.state;

import models.entity.Player;

public class Waiting extends StatePlayer {

    // Constructeur
    public Waiting(Player player) {
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
