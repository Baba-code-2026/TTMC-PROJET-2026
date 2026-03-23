package com.example.ttmc2026.test;

import com.example.ttmc2026.models.Game;
import com.example.ttmc2026.models.GameBoard;

import java.io.File;
import java.net.URI;

/**
 * Classe de test console pour vérifier le chargement du fichier du plateau.
 *
 * Ce test :
 *      - récupère le fichier JSON du plateau dans les ressources,
 *      - initialise le singleton GameBoard,
 *      - appelle la méthode de chargement du plateau.
 */
public class TestGameboard {

    /**
     * Point d'entrée du test console.
     *
     * @param args arguments de lancement
     */
    public static void main(String[] args) {
        // La variable existe dans le fichier initial.
        Game game = null;

        // Récupération du singleton du plateau.
        GameBoard gameBoard = GameBoard.getInstance(game);

        try {
            // Récupération du fichier JSON du plateau depuis les ressources.
            URI uri = TestGameboard.class.getResource("/JSONFILE/tilde.json").toURI();
            File jsonTilde = new File(uri);

            // Appel de la méthode de création/chargement des cases.
            gameBoard.createTilde(jsonTilde);

            System.out.println("Chargement réussi !");
        } catch (Exception exception) {
            System.err.println("Erreur : vérifiez que le fichier est bien dans src/main/resources/JSONFILE/tilde.json");
            exception.printStackTrace();
        }
    }
}