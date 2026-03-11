package modele.state;

import modele.entity.Player;

public class Waiting extends StatePlayer {

    // Constructeur
    Waiting(Player player) {
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
