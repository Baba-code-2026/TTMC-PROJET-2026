package modele.state;

import modele.entity.Entity;
import modele.entity.Player;

import java.util.ArrayList;

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
