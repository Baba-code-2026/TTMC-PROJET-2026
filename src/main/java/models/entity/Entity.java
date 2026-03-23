package models.entity;

/**
 * Classe abstraite représentant une entité du jeu.
 *
 * Une entité correspond à un participant pouvant être placé sur le plateau et évoluer pendant la partie.
 *
 * Cette classe sert de base commune aux différents types d'entités. Par exemple :
 *      - un joueur humain,
 *      - un bot.
 *
 * Chaque entité possède :
 *      - un score,
 *      - un nom,
 *      - une position horizontale,
 *      - une position verticale.
 */
public abstract class Entity {

    private int score = 0;
    private final String name; /* Nom de l'entité. Il est défini à la création et ne change pas ensuite. */
    private int positionX;
    private int positionY;

    /**
     * Constructeur
     *
     * @param score score initial
     * @param name nom de l'entité
     * @param positionX position horizontale initiale
     * @param positionY position verticale initiale
     */
    Entity(int score, String name, int positionX, int positionY) {
        this.score = score;
        this.name = name;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    /**
     * Déplace l'entité. Chaque sous-classe définit sa propre manière de gérer ce déplacement.
     *
     * @param numberCase numéro de la case visée
     * @param x nouvelle position horizontale
     * @param y nouvelle position verticale
     */
    public abstract void moveEntity(int numberCase, int x, int y);

    /* Passe au tour suivant. Chaque sous-classe définit son propre comportement en fin de tour. */
    public abstract void nextTurn();

    /* Getters et Setters */
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
}