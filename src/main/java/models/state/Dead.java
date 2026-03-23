package models.state;

import models.entity.Player;

/**
 * Représente l'état éliminé d'un joueur.
 *
 * Cet état correspond à un joueur qui ne peut plus agir dans la partie.
 */
public class Dead extends StatePlayer {

    /* Construit l'état éliminé pour un joueur donné. */
    public Dead(Player player) {
        super(player);
    }

    /* Action exécutée en fin de tour. */
    @Override
    public void endTurn() {
    }

    /**
     * Tente de déplacer le joueur.
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