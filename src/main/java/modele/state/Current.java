package modele.state;

import modele.entity.Player;

public class Current extends StatePlayer {

    // Constructeur
    Current(Player player) {
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
