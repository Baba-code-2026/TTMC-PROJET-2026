package com.example.ttmc2026.models;

import com.example.ttmc2026.models.question.Theme;

public class Tile {

    //Attributs
    private final int positionX, positionY, number;
    private Theme theme;

    public Tile(int number, int positionX, int positionY, Theme theme) {
        this.number = number ;
        this.theme =  theme;
        this.positionY = positionY ;
        this.positionX = positionX ;
    }
    
    public int countTile(int[][] chemin)
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
