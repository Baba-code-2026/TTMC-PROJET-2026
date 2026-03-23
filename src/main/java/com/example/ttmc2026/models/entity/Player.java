package com.example.ttmc2026.models.entity;

import com.example.ttmc2026.models.state.StatePlayer;
import com.example.ttmc2026.models.state.Waiting;

/**
 * Représente un joueur humain dans la partie.
 * Cette classe hérite de la classe Entity et ajoute la gestion de l'état du joueur dans le jeu.
 *
 * Un joueur peut par exemple être :
 *      - en attente,
 *      - en train de jouer,
 *      - en duel,
 *      - éliminé.
 */
public class Player extends Entity {

    /* Indique si le joueur a déjà participé à un duel pendant le tour courant. */
    private boolean alreadyVersusInTurn = false;

    /** État courant du joueur.
     * La gestion du comportement du joueur est déléguée à cet objet afin d'appliquer le design pattern State
     */
    private StatePlayer statePlayer;

    /** Construit un joueur avec son score, son nom et sa position de départ.
     *
     * @param score score initial du joueur
     * @param name nom du joueur
     * @param positionX position horizontale de départ
     * @param positionY position verticale de départ
     */
    public Player(int score, String name, int positionX, int positionY) {
        super(score, name, positionX, positionY);
        statePlayer = new Waiting(this);
    }

    /** Déplace le joueur.
     * Le déplacement réel est délégué à l'état courant du joueur.
     *
     * @param numberCase numéro de la case visée
     * @param x nouvelle position horizontale
     * @param y nouvelle position verticale
     */
    @Override
    public void moveEntity(int numberCase, int x, int y) {
        statePlayer.movePlayer(numberCase, x, y);
    }

    /* Passe au tour suivant. Cette action est déléguée à l'état courant du joueur. */
    @Override
    public void nextTurn() {
        statePlayer.endTurn();
    }

    /* Termine l'état de duel du joueur. */
    public void endVersusPlayer() {
        statePlayer.endVersus();
    }

    /* Getters et Setters */
    public void setEtat(StatePlayer statePlayer) {
        this.statePlayer = statePlayer;
    }

    public StatePlayer getStatePlayer() {
        return statePlayer;
    }

    public void setAlreadyVersusInTurn(boolean alreadyVersusInTurn) {
        this.alreadyVersusInTurn = alreadyVersusInTurn;
    }

    public boolean isAlreadyVersusInTurn() {
        return alreadyVersusInTurn;
    }
}