package models.state;

import models.entity.Player;

/**
 * Représente l'état d'attente d'un joueur.
 *
 * Dans cet état, le joueur n'est pas en train d'effectuer une action spéciale.
 * Cette classe s'inscrit dans le design pattern State.
 */
public class Waiting extends StatePlayer {

    /* Construit l'état d'attente pour un joueur donné */
    public Waiting(Player player) {
        super(player);
    }

    /* Action exécutée en fin de tour. */
    @Override
    public void endTurn() {
    }

    /** Déplace le joueur.
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