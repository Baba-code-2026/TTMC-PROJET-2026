package modele.entity;

import java.util.ArrayList;

public abstract class Entity {
    // Attributs
    private int score = 0;
    private final String name; // Pas de set car il ne peut pas changer de nom
    private int positionX;
    private int positionY;

    // Constructeur
    Entity(int score, String name, int positionX, int positionY){
        this.score = score;
        this.name = name;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public abstract void moveEntity(int numberCase, int x, int y);

    public abstract void nextTurn();


    // Sélecteur et modificateur
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
