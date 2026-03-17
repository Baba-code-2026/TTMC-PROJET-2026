package modele.state;

import modele.entity.Entity;
import modele.entity.Player;

import java.util.ArrayList;

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

