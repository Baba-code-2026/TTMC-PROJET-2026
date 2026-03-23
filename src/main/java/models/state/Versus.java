package models.state;

import models.entity.Player;

/**
 * Représente l'état de duel d'un joueur.
 *
 * Cet état correspond à une situation où le joueur est engagé dans un affrontement ou un duel avec un autre participant.
 */
public class Versus extends StatePlayer {

    /* Construit l'état de duel pour un joueur donné. */
    public Versus(Player player) {
        super(player);
    }

    /* Action exécutée en fin de tour. */
    @Override
    public void endTurn() {
    }

    /**
     * Déplace le joueur selon les règles de l'état de duel.
     *
     * @param score score ou case visée selon la logique actuelle
     * @param x nouvelle position horizontale
     * @param y nouvelle position verticale
     */
    @Override
    public void movePlayer(int score, int x, int y) {
    }

    /* Termine l'état de duel du joueur. */
    @Override
    public void endVersus() {
    }
}