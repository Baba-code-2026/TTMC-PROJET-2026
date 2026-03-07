package com.example.ttmc2026;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class Plateau {
    private final int TAILLE_CASE = 60;
    private final int[][] chemin = {
            { 1, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0},
            { 0, 0, 4, 5, 0, 0, 0, 0, 0, 0, 0},
            { 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0},
            { 0, 0, 8, 7, 0, 0,26,27,28,29, 0},
            { 0, 0, 9, 0, 0,24,25, 0, 0,30, 0},
            {12,11,10, 0,22,23, 0, 0, 0,31, 0},
            {13, 0, 0, 0,21, 0, 0, 0, 0,32, 0},
            {14,15, 0,19,20, 0, 0,35,34,33, 0},
            { 0,16,17,18, 0, 0, 0,36, 0, 0, 0},
            { 0, 0, 0, 0, 0, 0, 0,37,38, 0, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0,39,40,41},
    };

    public Plateau() {
        VBox root = new VBox(20); // Conteneur vertical principal
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: white; -fx-padding: 20;");
        GridPane plateau = new GridPane();
        plateau.setAlignment(Pos.CENTER);
    }


}
