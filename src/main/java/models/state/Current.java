package models.state;

import models.entity.Player;

/**
 * Représente l'état courant d'un joueur.
 * Cet état correspond au moment où le joueur est en train d'effectuer son tour dans la partie.
 */
public class Current extends StatePlayer {

    /* Construit l'état courant pour un joueur donné. */
    public Current(Player player) {
        super(player);
    }

    /* Action exécutée en fin de tour. */
    @Override
    public void endTurn() {
    }

    /**
     * Déplace le joueur selon les règles de l'état courant.
     *
     * @param score score ou case visée selon la logique actuelle
     * @param x nouvelle position horizontale
     * @param y nouvelle position verticale
     */
    @Override
    public void movePlayer(int score, int x, int y) {
    }

    /* Termine un éventuel duel. */
    @Override
    public void endVersus() {
    }
}