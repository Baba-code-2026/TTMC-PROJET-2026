Donc pour save il faut pas push dans le main mais dans une nouvelle branche avant validation

code pour le plateau:
package com.example.testplateaujavafx;

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

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(20); // Conteneur vertical principal
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: white; -fx-padding: 20;");

        GridPane plateau = new GridPane();
        plateau.setAlignment(Pos.CENTER);

        // 1. Construction du plateau (Identique à ton code précédent)
        int nombreDeCases = 0;
        for (int[] ligne : chemin) {
            for (int valeur : ligne) if (valeur > 0) nombreDeCases++;
        }

        for (int i = 1; i <= nombreDeCases; i++) {
            for (int row = 0; row < chemin.length; row++) {
                for (int col = 0; col < chemin[row].length; col++) {
                    if (chemin[row][col] == i) {
                        cheminCoordonnees.add(new int[]{col, row});
                        Rectangle rect = new Rectangle(TAILLE_CASE, TAILLE_CASE - 5);
                        rect.setArcWidth(20); rect.setArcHeight(20);
                        rect.setFill(getColorForIndex(i));
                        rect.setStroke(Color.DARKSLATEGRAY);
                        plateau.add(rect, col, row);
                    }
                }
            }
        }

        // 2. Initialisation des pions
        pion1 = new Circle(TAILLE_CASE / 4.0, Color.YELLOW);
        pion1.setStroke(Color.BLACK);
        pion2 = new Circle(TAILLE_CASE / 4.0, Color.RED);
        pion2.setStroke(Color.BLACK);

        plateau.getChildren().addAll(pion1, pion2);
        actualiserPositions();

        // 3. Boutons de contrôle
        Button btnJoueur1 = new Button("Joueur 1 (Jaune)");
        Button btnJoueur2 = new Button("Joueur 2 (Rouge)");

        btnJoueur1.setOnAction(e -> poserQuestion(1));
        btnJoueur2.setOnAction(e -> poserQuestion(2));

        HBox controles = new HBox(20, btnJoueur1, btnJoueur2);
        controles.setAlignment(Pos.CENTER);

        root.getChildren().addAll(plateau, controles);

        Scene scene = new Scene(root);
        stage.setTitle("Jeu de l'Oie Quiz");
        stage.setScene(scene);
        stage.show();
    }

    private void poserQuestion(int numJoueur) {
        List<String> choix = List.of("Flash mcqueen", "Dumbo", "Mac Do", "Burgr Kng");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("Flash mcqueen", choix);
        dialog.setTitle("Question Difficulté 1");
        dialog.setHeaderText("Joueur " + numJoueur + " : C'est à vous !");
        dialog.setContentText("Quel est la voiture la plus rapide dans Cars ?");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(reponse -> {
            if (reponse.equals("Flash mcqueen")) {
                System.out.println("Bonne réponse !");
                avancerJoueur(numJoueur);
            } else {
                System.out.println("Mauvaise réponse...");
            }
        });
    }

    private void avancerJoueur(int numJoueur) {
        if (numJoueur == 1 && indexPion1 < cheminCoordonnees.size() - 1) {
            indexPion1++;
        } else if (numJoueur == 2 && indexPion2 < cheminCoordonnees.size() - 1) {
            indexPion2++;
        }
        actualiserPositions();
    }

    private void actualiserPositions() {
        // Pion 1
        int[] c1 = cheminCoordonnees.get(indexPion1);
        GridPane.setConstraints(pion1, c1[0], c1[1]);
        GridPane.setHalignment(pion1, (indexPion1 == indexPion2) ? HPos.RIGHT : HPos.CENTER);

        // Pion 2
        int[] c2 = cheminCoordonnees.get(indexPion2);
        GridPane.setConstraints(pion2, c2[0], c2[1]);
        GridPane.setHalignment(pion2, (indexPion1 == indexPion2) ? HPos.LEFT : HPos.CENTER);

        GridPane.setValignment(pion1, VPos.CENTER);
        GridPane.setValignment(pion2, VPos.CENTER);
    }

    private Color getColorForIndex(int i) {
        Color[] cols = {Color.web("#a29bfe"), Color.web("#55efc4"), Color.web("#ffeaa7"), Color.web("#fab1a0")};
        return cols[i % cols.length];
    }

    public static void main(String[] args) { launch(args); }
}