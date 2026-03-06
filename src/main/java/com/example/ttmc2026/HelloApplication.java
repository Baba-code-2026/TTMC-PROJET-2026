package com.example.ttmc2026;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.io.IOException;

public class HelloApplication extends Application {
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

    private final List<int[]> cheminCoordonnees = new ArrayList<>();
    private int indexPion1 = 0;
    private int indexPion2 = 0;
    private Circle pion1, pion2;

    public void start(Stage stage)  {
        VBox root = new VBox(20); // Conteneur vertical principal
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: white; -fx-padding: 20;");

        GridPane plateau = new GridPane();
        plateau.setAlignment(Pos.CENTER);

        int nbTiles = 0;
        for (int [] ligne : chemin) {
            for (int valeur : ligne) if (valeur > 0) nbTiles++;
        }


        Scene scene = new Scene(root);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
