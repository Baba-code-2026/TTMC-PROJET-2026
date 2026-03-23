package com.example.ttmc2026.models.entity;

/**
 * Représente un joueur contrôlé automatiquement par le programme.
 *
 * Cette classe hérite de la classe Entity.
 * Pour l'instant, son comportement :
 *      - il peut être déplacé,
 *      - il peut passer au tour suivant.
 */
public class Bot extends Entity {

    /* Constructeur */
    public Bot(int score, String name, int positionX, int positionY) {
        super(score, name, positionX, positionY);
    }

    /**
     * Déplace le bot.
     *
     * Consiste à :
     *      - modifier son score,
     *      - mettre à jour sa position horizontale,
     *      - mettre à jour sa position verticale.
     *
     * @param numberCase valeur utilisée actuellement pour mettre à jour le score
     * @param x nouvelle position horizontale
     * @param y nouvelle position verticale
     */
    @Override
    public void moveEntity(int numberCase, int x, int y) {
        setScore(getScore() + numberCase);
        setPositionX(x);
        setPositionY(y);
    }

    /* Passe au tour suivant. */
    @Override
    public void nextTurn() {
    }
}