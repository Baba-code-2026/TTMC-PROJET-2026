package models;

import models.question.Theme;

/**
 * Représente une case du plateau.
 */
public class Tilde {

    private final int positionX;

    private final int positionY;

    private final int number; /* numéro de la case */

    private Theme theme; /* Thème de la case */

    /* Constructeur */
    public Tilde(int number) {
        this.number = number;
        this.positionY = 0;
        this.positionX = 0;
    }

    /**
     * Compte le nombre de cases présentes dans un chemin.
     *
     * @param chemin tableau représentant le chemin du plateau
     * @return nombre de cases trouvées
     */
    public int countTilde(int[][] chemin) {
        int nombreDeCases = 0;

        for (int[] ligne : chemin) {
            for (int valeur : ligne) {
                if (valeur > 0) {
                    nombreDeCases++;
                }
            }
        }

        return nombreDeCases;
    }

    /* Getters et Setters */
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