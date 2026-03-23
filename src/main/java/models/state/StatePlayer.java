package models.state;

import models.entity.Player;

/** Classe abstraite représentant un état possible d'un joueur.
 *
 * Elle permet de faire varier le comportement du joueur selon son état courant dans la partie.
 *
 * Par exemple, un joueur peut être :
 *      - en attente,
 *      - en train de jouer,
 *      - en duel,
 *      - éliminé.
 */
public abstract class StatePlayer {

    /* Joueur concerné par cet état. */
    private final Player player;

    /** Construit un état associé à un joueur donné.
     *
     * @param player joueur auquel cet état est appliqué
     */
    StatePlayer(Player player) {
        this.player = player;
    }

    /* Action exécutée en fin de tour. */
    public abstract void endTurn();

    /** Méthode abstraite qui déplace le joueur selon les règles de l'état courant.
     *
     * @param score score ou case visée selon la logique actuelle du projet
     * @param x nouvelle position horizontale
     * @param y nouvelle position verticale
     */
    public abstract void movePlayer(int score, int x, int y);

    /* Termine l'état de duel du joueur. */
    public abstract void endVersus();

    /* Getter */
    public Player getPlayer() {
        return player;
    }
}