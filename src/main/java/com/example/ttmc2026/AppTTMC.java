package com.example.ttmc2026;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Cette classe a plusieurs responsabilités importantes :
 *      - démarrer JavaFX,
 *      - créer et mémoriser la fenêtre principale,
 *      - charger la première vue affichée à l'utilisateur,
 *      - fournir une méthode utilitaire simple pour changer d'écran.
 */
public class AppTTMC extends Application {

    /* Fenêtre principale de l'application */
    private static Stage fenetrePrincipale;

    /* Nombre de joueurs sélectionnés avant le début de la partie */
    private static int nombreJoueursSelectionne = 2;

    /* Paramètres enregistrés de l'application */
    private static boolean modePleinEcranSauvegarde = false;
    private static boolean modeMuetSauvegarde = false;
    private static double volumeSauvegarde = 50.0;

    /** Cette méthode est appelée automatiquement par JavaFX après le lancement de l'application.
     * On y initialise la fenêtre principale puis on charge la première vue du projet.
     *
     * @param scenePrincipale la fenêtre principale créée par JavaFX
     * @throws IOException si le fichier FXML demandé ne peut pas être chargé
     */
    @Override
    public void start(Stage scenePrincipale) throws IOException {
        fenetrePrincipale = scenePrincipale;

        // On charge le menu principal comme premier écran visible
        afficherVue("/views/Title_menu.fxml", "TTMC Cars");
        // Une fois la scène chargée, on affiche la fenêtre
        fenetrePrincipale.show();
    }

    /** Cette méthode centralise le chargement des écrans. Cela évite de réécrire la même logique dans plusieurs classes
     * et facilitera la navigation entre les différentes vues du projet.
     *
     * @param cheminFxml chemin absolu du fichier FXML dans resources
     * @param titreFenetre titre à afficher dans la barre de fenêtre
     * @throws IOException si le chargement du FXML échoue
     */
    public static void afficherVue(String cheminFxml, String titreFenetre) throws IOException {
        FXMLLoader chargeurFxml = new FXMLLoader(
                Objects.requireNonNull(
                        AppTTMC.class.getResource(cheminFxml),
                        "Impossible de trouver la vue FXML : " + cheminFxml
                )
        );

        Parent racine = chargeurFxml.load();
        Scene scene = new Scene(racine);

        /* On mémorise l'état actuel du plein écran avant de changer la scène afin de le conserver quand on passe d'un écran à l'autre. */
        boolean modePleinEcranActif = fenetrePrincipale.isFullScreen();

        fenetrePrincipale.setTitle(titreFenetre);
        fenetrePrincipale.setScene(scene);

        /* En mode fenêtré, on redimensionne la fenêtre à la taille préférée de la nouvelle vue afin d'éviter qu'une partie du contenu soit coupée. */
        if (!modePleinEcranActif) {
            fenetrePrincipale.sizeToScene();
        }

        fenetrePrincipale.centerOnScreen();

        /* On réapplique l'état du plein écran après le changement de scène. */
        fenetrePrincipale.setFullScreen(modePleinEcranActif);
    }

    /* Getters et Setters */
    public static Stage getFenetrePrincipale() {
        return fenetrePrincipale;
    }

    public static void setNombreJoueursSelectionne(int nombreJoueurs) {
        nombreJoueursSelectionne = nombreJoueurs;
    }

    public static int getNombreJoueursSelectionne() {
        return nombreJoueursSelectionne;
    }

    public static boolean isModePleinEcranSauvegarde() {
        return modePleinEcranSauvegarde;
    }

    public static void setModePleinEcranSauvegarde(boolean modePleinEcranSauvegarde) {
        AppTTMC.modePleinEcranSauvegarde = modePleinEcranSauvegarde;
    }

    public static boolean isModeMuetSauvegarde() {
        return modeMuetSauvegarde;
    }

    public static void setModeMuetSauvegarde(boolean modeMuetSauvegarde) {
        AppTTMC.modeMuetSauvegarde = modeMuetSauvegarde;
    }

    public static double getVolumeSauvegarde() {
        return volumeSauvegarde;
    }

    public static void setVolumeSauvegarde(double volumeSauvegarde) {
        AppTTMC.volumeSauvegarde = volumeSauvegarde;
    }

    /** Point d'entrée standard Java
     *
     * @param arguments arguments de lancement
     */
    public static void main(String[] arguments) {
        launch(arguments);
    }
}