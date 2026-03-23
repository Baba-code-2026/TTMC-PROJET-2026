package com.example.ttmc2026.controllers;

import com.example.ttmc2026.AppTTMC;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Contrôleur du menu principal de l'application.
 *
 * Cette classe gère les actions des boutons présents sur l'écran principal.
 * Elle permet :
 *      - d'ouvrir l'écran de jeu,
 *      - d'ouvrir l'écran des paramètres,
 *      - de quitter l'application.
 *
 * Avant d'ouvrir l'écran de jeu, on demande maintenant le nombre de joueurs dans un popup bloquant.
 */
public class MainController {

    /* Racine visuelle du menu principal. Sert à appliquer un zoom automatique en plein écran. */
    @FXML
    private BorderPane border;

    /**
     * Initialise le menu principal.
     *
     * Cette méthode applique automatiquement un zoom lorsque la fenêtre est en plein écran, puis remet la taille normale en mode fenêtré.
     * Le zoom n'est appliqué qu'au menu principal.
     */
    @FXML
    private void initialize() {
        Platform.runLater(() -> {
            appliquerZoomSelonModeAffichage(AppTTMC.getFenetrePrincipale().isFullScreen());

            AppTTMC.getFenetrePrincipale().fullScreenProperty().addListener((observable, ancienneValeur, nouvelleValeur) -> {
                appliquerZoomSelonModeAffichage(nouvelleValeur);
            });
        });
    }

    /**
     * Applique le zoom du menu selon le mode d'affichage.
     *
     * En plein écran : le menu est agrandi de 50 %, donc x1.5.
     * En mode fenêtré : le menu revient à sa taille normale.
     *
     * @param modePleinEcran vrai si la fenêtre est en plein écran, sinon faux
     */
    private void appliquerZoomSelonModeAffichage(boolean modePleinEcran) {
        if (modePleinEcran) {
            border.setScaleX(1.5);
            border.setScaleY(1.5);
        } else {
            border.setScaleX(1.0);
            border.setScaleY(1.0);
        }
    }

    /**
     * Méthode appelée lorsque l'utilisateur clique sur le bouton "Start".
     *
     * Avant d'ouvrir l'écran de jeu, on demande le nombre de joueurs, puis on enregistre cette valeur dans la classe
     * principale de l'application afin que l'écran de jeu puisse la récupérer.
     */
    @FXML
    private void onButtonStartClick() {
        try {
            int nombreJoueursChoisi = demanderNombreDeJoueursAvantPartie();
            AppTTMC.setNombreJoueursSelectionne(nombreJoueursChoisi);
            AppTTMC.afficherVue("/views/hello-view.fxml", "TTMC Cars - Game");
        } catch (IOException exception) {
            afficherErreurDansLaConsole("Impossible d'ouvrir l'écran de jeu.", exception);
        }
    }

    /* Méthode appelée lorsque l'utilisateur clique sur le bouton "Settings" et ouvre les paramètres. */
    @FXML
    private void onButtonSettingsClick() {
        try {
            AppTTMC.afficherVue("/views/Settings_menu.fxml", "TTMC Cars - Settings");
        } catch (IOException exception) {
            afficherErreurDansLaConsole("Impossible d'ouvrir l'écran des paramètres.", exception);
        }
    }

    /* Méthode appelée lorsque l'utilisateur clique sur le bouton "Exit" et quitte l'application. */
    @FXML
    private void onButtonExitClick() {
        AppTTMC.getFenetrePrincipale().close();
    }

    /**
     * Ouvre un popup pour demander le nombre de joueurs avant le lancement de la partie.
     *
     * L'utilisateur doit choisir entre :
     *      - 2 joueurs,
     *      - 3 joueurs,
     *      - 4 joueurs.
     *
     * Le popup ne peut pas être fermé avec la croix.
     *
     * @return nombre de joueurs choisi
     */
    private int demanderNombreDeJoueursAvantPartie() {
        final int[] resultat = {2};

        Stage popup = new Stage();
        popup.initOwner(AppTTMC.getFenetrePrincipale());
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle("Choose number of players");
        popup.setOnCloseRequest(event -> event.consume());

        Label titre = new Label("Choose the number of players");

        ComboBox<String> choixJoueurs = new ComboBox<>();
        choixJoueurs.getItems().addAll("2 players", "3 players", "4 players");
        choixJoueurs.setValue("2 players");

        Button boutonValider = new Button("Validate");
        boutonValider.setOnAction(event -> {
            String valeurChoisie = choixJoueurs.getValue();

            if ("2 players".equals(valeurChoisie)) {
                resultat[0] = 2;
            } else if ("3 players".equals(valeurChoisie)) {
                resultat[0] = 3;
            } else {
                resultat[0] = 4;
            }

            popup.close();
        });

        VBox racine = new VBox(15, titre, choixJoueurs, boutonValider);
        racine.setAlignment(Pos.CENTER);
        racine.setPadding(new Insets(20));

        popup.setScene(new Scene(racine, 320, 180));
        popup.showAndWait();

        return resultat[0];
    }

    /**
     * Méthode permettant d'afficher une erreur dans la console.
     *
     * @param messageUtilisateur message expliquant l'action qui a échoué
     * @param exception exception technique déclenchée
     */
    private void afficherErreurDansLaConsole(String messageUtilisateur, Exception exception) {
        System.err.println(messageUtilisateur);
        exception.printStackTrace();
    }
}