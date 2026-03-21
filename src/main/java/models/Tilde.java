package models;

import models.question.Theme;

public class Tilde {

    //Attributs
    private final int positionX, positionY, number;
    private Theme theme;

    public Tilde(int number) {
        this.number = number ;

        this.positionY = 0 ;
        this.positionX = 0 ;
    }
    
    public int countTilde(int[][] chemin)
    {
        int nombreDeCases = 0;
        for (int[] ligne : chemin) {
            for (int valeur : ligne) if (valeur > 0) nombreDeCases++;
        }
        return nombreDeCases;
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

    public Theme getTheme() {
        return theme;
    }
}
