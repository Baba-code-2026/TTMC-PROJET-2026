package com.example.ttmc2026.controllers;

import com.example.ttmc2026.AppTTMC;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * Cette classe gère les interactions de l'utilisateur avec la vue des paramètres de l'application.
 *
 * Pour l'instant, ce contrôleur permet :
 *      - de préparer l'activation ou la désactivation du mode plein écran,
 *      - de préparer l'activation ou la désactivation du mode muet,
 *      - de mémoriser une valeur de volume,
 *      - de sauvegarder les paramètres choisis,
 *      - de revenir au menu principal avec ou sans sauvegarde.
 *
 * Important :
 *      - le bouton "Save" enregistre les modifications puis retourne au menu principal,
 *      - le bouton "Previous" retourne au menu principal sans enregistrer les modifications.
 */
public class SettingsController {

    /* Racine visuelle du contenu des paramètres. Sert à appliquer un zoom automatique en plein écran. */
    @FXML
    private BorderPane border;

    /* Case à cocher liée au mode plein écran */
    @FXML
    private CheckBox fullscreenCheckBox;

    /* Case à cocher liée au mode muet */
    @FXML
    private CheckBox muteCheckBox;

    /* Curseur de volume. Sa valeur est conservée comme paramètre, même si aucun moteur audio n'est encore branché. */
    @FXML
    private Slider volumeSlider;

    /**
     * Initialise l'écran des paramètres à partir des dernières valeurs sauvegardées.
     *
     * Ainsi :
     *      - si l'utilisateur revient dans Settings,
     *      - il retrouve les derniers réglages enregistrés avec "Save".
     *
     * Cette méthode vérifie aussi si la fenêtre est en plein écran
     * afin d'appliquer automatiquement un zoom au menu pour améliorer la lisibilité.
     *
     * Enfin, elle écoute les changements de mode d'affichage :
     *      - si on passe en plein écran, le zoom est appliqué,
     *      - si on revient en fenêtré, le menu reprend sa taille normale.
     */
    @FXML
    private void initialize() {
        fullscreenCheckBox.setSelected(AppTTMC.isModePleinEcranSauvegarde());
        muteCheckBox.setSelected(AppTTMC.isModeMuetSauvegarde());
        volumeSlider.setValue(AppTTMC.getVolumeSauvegarde());

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
     * Méthode appelée lorsque l'utilisateur clique sur la case "Fullscreen".
     *
     * Ici, on ne modifie pas immédiatement la fenêtre principale.
     * Le changement n'est réellement appliqué que lorsque l'utilisateur clique sur "Save".
     */
    @FXML
    private void isFullscreen() {
        // Aucun traitement immédiat : la sauvegarde se fait uniquement via le bouton Save.
    }

    /**
     * Méthode appelée lorsque l'utilisateur clique sur la case "Mute".
     *
     * Ici aussi, aucun changement définitif n'est appliqué immédiatement.
     * Le choix est enregistré uniquement si l'utilisateur clique sur "Save".
     */
    @FXML
    private void isMute() {
        // Aucun traitement immédiat : la sauvegarde se fait uniquement via le bouton Save.
    }

    /**
     * Sauvegarde les paramètres choisis, applique ceux qui ont un effet immédiat, puis revient au menu principal.
     *
     * Paramètres sauvegardés :
     *      - plein écran,
     *      - mode muet,
     *      - volume.
     *
     * Paramètre appliqué immédiatement :
     *      - plein écran.
     */
    @FXML
    private void onSaveButtonClick() {
        try {
            boolean modePleinEcranChoisi = fullscreenCheckBox.isSelected();
            boolean modeMuetChoisi = muteCheckBox.isSelected();
            double volumeChoisi = volumeSlider.getValue();

            AppTTMC.setModePleinEcranSauvegarde(modePleinEcranChoisi);
            AppTTMC.setModeMuetSauvegarde(modeMuetChoisi);
            AppTTMC.setVolumeSauvegarde(volumeChoisi);

            AppTTMC.getFenetrePrincipale().setFullScreen(modePleinEcranChoisi);

            AppTTMC.afficherVue("/views/Title_menu.fxml", "TTMC Cars");
        } catch (IOException exception) {
            afficherErreurDansLaConsole("Impossible d'enregistrer les paramètres et de revenir au menu principal.", exception);
        }
    }

    /**
     * Revient au menu principal sans enregistrer les modifications en cours.
     *
     * Cela signifie que :
     *      - les cases cochées/décochées non sauvegardées sont ignorées,
     *      - le volume modifié non sauvegardé est ignoré,
     *      - seuls les paramètres déjà enregistrés restent valides.
     */
    @FXML
    private void onPreviousButtonClick() {
        try {
            AppTTMC.afficherVue("/views/Title_menu.fxml", "TTMC Cars");
        } catch (IOException exception) {
            afficherErreurDansLaConsole("Impossible de revenir au menu principal.", exception);
        }
    }

    /**
     * Méthode utilitaire locale pour afficher une erreur dans la console.
     *
     * @param messageUtilisateur message expliquant l'action qui a échoué
     * @param exception exception technique déclenchée
     */
    private void afficherErreurDansLaConsole(String messageUtilisateur, Exception exception) {
        System.err.println(messageUtilisateur);
        exception.printStackTrace();
    }
}