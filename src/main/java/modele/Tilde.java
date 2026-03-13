package modele;

import modele.question.Theme;

import java.util.Random;

public class Tilde {

    //Attributs
    private static int nbInstances = 0;
    private final int positionX, positionY, number; // aucun des attributs ne peut être changer
    private String color; // Pas sur de celle-là;
    private Theme theme;

    public Tilde(int positionX, int positionY, String color, Theme theme) {
        this.positionX = positionX;
        this.positionY = positionY;
        number = ++nbInstances;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getNumber() {
        return number;
    }

    public int getPositionY() {
        return positionY;
    }

    public String getColor() {
        return color;
    }

    public Theme getTheme() {
        return theme;
    }
}
