package com.example.ttmc2026;

import com.example.ttmc2026.models.question.Question;
import com.example.ttmc2026.models.question.Theme;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Contrôleur principal de l'écran de jeu.
 *
 * Cette classe gère :
 *      - l'affichage du plateau,
 *      - la création des pions,
 *      - la gestion du tour courant,
 *      - le choix de la difficulté,
 *      - l'affichage des questions,
 *      - le déplacement des pions selon la réponse.
 */
public class HelloController {

    private static final int LARGEUR_CASE = 80; /* Largeur graphique d'une case du plateau */
    private static final int HAUTEUR_CASE = 55; /* Hauteur graphique d'une case du plateau */
    private static final int RAYON_PION = 8; /* Rayon d'un pion pour permettre à 4 pions de tenir sur une case */

    /* Ce tableau n'est utilisé que si la lecture du fichier JSON échoue. */
    private static final int[][] CHEMIN_PAR_DEFAUT = {
            {1, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 4, 5, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 8, 7, 0, 0, 26, 27, 28, 29, 0},
            {0, 0, 9, 0, 0, 24, 25, 0, 0, 30, 0},
            {12, 11, 10, 0, 22, 23, 0, 0, 0, 31, 0},
            {13, 0, 0, 0, 21, 0, 0, 0, 0, 32, 0},
            {14, 15, 0, 19, 20, 0, 0, 35, 34, 33, 0},
            {0, 16, 17, 18, 0, 0, 0, 36, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 37, 38, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 39, 40, 41}
    };

    /**
     * Noms des thèmes utilisés dans le projet.
     * L'association case → thème se fait ici par cycle de couleur.
     */
    private static final String[] NOMS_THEMES = {
            "Mystery",
            "Informatics",
            "Entertainment",
            "Tourism"
    };

    /**
     * Couleurs du plateau.
     * Chaque couleur correspond à un thème du même index dans le tableau NOMS_THEMES.
     */
    private static final Color[] COULEURS_THEMES = {
            Color.web("#a29bfe"),
            Color.web("#55efc4"),
            Color.web("#ffeaa7"),
            Color.web("#fab1a0")
    };

    /* Couleurs des pions des joueurs. */
    private static final Color[] COULEURS_PIONS = {
            Color.YELLOW,
            Color.RED,
            Color.DEEPSKYBLUE,
            Color.LIMEGREEN
    };

    /* Grille graphique du plateau. */
    @FXML
    private GridPane plateauGrille;

    /* Label affichant le joueur courant. */
    @FXML
    private Label joueurActuelLabel;

    /* Label affichant la case actuelle du joueur courant. */
    @FXML
    private Label caseActuelleLabel;

    /* Label affichant le thème de la case actuelle. */
    @FXML
    private Label themeActuelLabel;

    /* Label affichant le statut du tour courant. */
    @FXML
    private Label statutTourLabel;

    /* Matrice du plateau chargée depuis le JSON ou depuis la copie locale. */
    private int[][] chemin;

    /**
     * Liste ordonnée des coordonnées des cases.
     *
     * L'indice 0 correspond à la case 1,
     * L'indice 1 correspond à la case 2, etc.
     */
    private final ArrayList<int[]> cheminCoordonnees = new ArrayList<>();

    /**
     * Associe chaque numéro de case à sa zone graphique de pions.
     * Cela permet d'afficher plusieurs pions sur une même case.
     */
    private final Map<Integer, HBox> conteneursPionsParCase = new HashMap<>();

    /* Liste des thèmes disponibles dans la partie. */
    private ArrayList<Theme> themesDisponibles = new ArrayList<>();

    /* Nombre de joueurs choisi au lancement. */
    private int nombreJoueurs = 2;

    /**
     * Position de chaque joueur dans cheminCoordonnees.
     * Exemple :
     *      - valeur 0 → case 1
     *      - valeur 1 → case 2
     */
    private int[] positionsJoueurs;

    /* Liste des pions graphiques. */
    private final ArrayList<Circle> pionsJoueurs = new ArrayList<>();

    /* Index du joueur dont c'est actuellement le tour. */
    private int indexJoueurCourant = 0;

    /* Indique si la partie est terminée. */
    private boolean partieTerminee = false;

    /**
     * Méthode appelée automatiquement après l'injection FXML.
     * On lance ensuite l'initialisation réelle via Platform.runLater afin d'être certain que la fenêtre JavaFX existe déjà.
     */
    @FXML
    private void initialize() {
        Platform.runLater(this::initialiserPartie);
    }

    /* Cette méthode est appelée par le bouton "Back to menu". */
    @FXML
    private void onRetourMenuClick() {
        try {
            partieTerminee = true;
            AppTTMC.afficherVue("/views/Title_menu.fxml", "TTMC Cars");
        } catch (Exception exception) {
            System.err.println("Impossible de revenir au menu principal.");
            exception.printStackTrace();
        }
    }

    /* Initialise complètement la partie. */
    private void initialiserPartie() {
        nombreJoueurs = AppTTMC.getNombreJoueursSelectionne();
        themesDisponibles = Theme.initAllThemes();
        chemin = chargerCheminDepuisJson();
        construirePlateau();
        initialiserJoueurs();
        mettreAJourInformationsJoueurCourant();
        lancerTourJoueurCourant();
    }

    /**
     * Charge le plateau depuis le fichier tilde.json.
     * Si le chargement échoue, on retourne le plateau de la constante CHEMIN_PAR_DEFAUT.
     *
     * @return matrice du plateau
     */
    private int[][] chargerCheminDepuisJson() {
        try {
            URL urlJson = HelloController.class.getResource("/JSONFILE/tilde.json");

            if (urlJson != null) {
                File json = new File(urlJson.toURI());
                Gson gson = new Gson();
                JsonObject racine = gson.fromJson(new FileReader(json), JsonObject.class);

                return gson.fromJson(racine.get("chemin"), int[][].class);
            }
        } catch (Exception exception) {
            System.err.println("Lecture de tilde.json impossible. Utilisation du plateau par défaut.");
            exception.printStackTrace();
        }

        return CHEMIN_PAR_DEFAUT;
    }

    /**
     * Construit visuellement tout le plateau de jeu à partir de la matrice "chemin".
     *
     * Cette méthode parcourt la matrice contenant les numéros des cases et :
     *      - repère chaque case dans le bon ordre,
     *      - mémorise ses coordonnées dans "cheminCoordonnees",
     *      - crée une zone graphique pour afficher la case,
     *      - crée une zone dédiée à l'affichage des pions,
     *      - ajoute la case dans la `GridPane` du plateau.
     *
     * Les cases normales utilisent une couleur liée à leur thème.
     * La première case et la dernière case sont traitées comme des cases spéciales :
     *      - START,
     *      - FINISH.
     * Ces deux cases sont dessinées différemment grâce à un fond en damier.
     */
    private void construirePlateau() {
        plateauGrille.getChildren().clear();
        cheminCoordonnees.clear();
        conteneursPionsParCase.clear();

        int nombreDeCases = compterNombreDeCases(chemin);

        for (int numeroCase = 1; numeroCase <= nombreDeCases; numeroCase++) {
            for (int ligne = 0; ligne < chemin.length; ligne++) {
                for (int colonne = 0; colonne < chemin[ligne].length; colonne++) {
                    if (chemin[ligne][colonne] == numeroCase) {
                        cheminCoordonnees.add(new int[]{colonne, ligne});

                        /* On réduit l'espace entre les pions pour permettre aux 4 pions de tenir sur une case sans modifier le design du plateau. */
                        HBox conteneurPions = new HBox(2);
                        conteneurPions.setAlignment(Pos.CENTER);

                        StackPane caseGraphique = creerCaseGraphique(numeroCase, nombreDeCases, conteneurPions);

                        plateauGrille.add(caseGraphique, colonne, ligne);
                        conteneursPionsParCase.put(numeroCase, conteneurPions);
                    }
                }
            }
        }
    }

    /**
     * Crée l'apparence graphique d'une case jouable du plateau.
     *
     * Cette méthode construit uniquement les cases réellement présentes dans la matrice de jeu.
     *
     * Cas particuliers :
     *      - la case 1 est affichée comme case de départ avec le texte "START",
     *      - la dernière case réelle du plateau est affichée comme case d'arrivée avec le texte "FINISH",
     *      - ces deux cases spéciales utilisent un fond en damier.
     *
     * Toutes les autres cases conservent leur numéro et utilisent la couleur correspondant à leur thème.
     *
     * @param numeroCase numéro de la case en cours de création
     * @param nombreDeCases nombre total de cases présentes sur le plateau
     * @param conteneurPions zone graphique qui contiendra les pions placés sur cette case
     * @return case graphique complète prête à être ajoutée au plateau
     */
    private StackPane creerCaseGraphique(int numeroCase, int nombreDeCases, HBox conteneurPions) {
        StackPane caseGraphique = new StackPane();

        if (estCaseDepart(numeroCase) || estCaseArrivee(numeroCase, nombreDeCases)) {
            GridPane damier = creerFondDamier();
            caseGraphique.getChildren().add(damier);
        } else {
            Rectangle rectangleCase = new Rectangle(LARGEUR_CASE, HAUTEUR_CASE);
            rectangleCase.setArcWidth(20);
            rectangleCase.setArcHeight(20);
            rectangleCase.setFill(getCouleurPourNumeroCase(numeroCase));
            rectangleCase.setStroke(Color.DARKSLATEGRAY);
            caseGraphique.getChildren().add(rectangleCase);
        }

        Label labelCase;

        if (estCaseDepart(numeroCase)) {
            labelCase = new Label("START\n" + numeroCase);
        } else if (estCaseArrivee(numeroCase, nombreDeCases)) {
            labelCase = new Label("FINISH\n" + numeroCase);
        } else {
            labelCase = new Label(String.valueOf(numeroCase));
        }

        labelCase.setStyle("-fx-font-weight: bold; -fx-text-alignment: center;");

        caseGraphique.getChildren().addAll(labelCase, conteneurPions);
        StackPane.setAlignment(labelCase, Pos.CENTER);
        StackPane.setAlignment(conteneurPions, Pos.BOTTOM_CENTER);
        StackPane.setMargin(conteneurPions, new Insets(0, 0, 5, 0));

        return caseGraphique;
    }

    /**
     * Crée un fond en damier pour les cases spéciales du plateau.
     *
     * Ce damier est utilisé pour distinguer visuellement :
     *      - la case de départ,
     *      - la case d'arrivée.
     *
     * La méthode construit une petite grille de rectangles alternant deux couleurs afin d'obtenir l'effet damier.
     *
     * @return une "GridPane" représentant graphiquement le damier
     */
    private GridPane creerFondDamier() {
        GridPane damier = new GridPane();

        int nombreCasesDamier = 6;
        double largeurCase = (double) LARGEUR_CASE / nombreCasesDamier;
        double hauteurCase = (double) HAUTEUR_CASE / nombreCasesDamier;

        for (int ligne = 0; ligne < nombreCasesDamier; ligne++) {
            for (int colonne = 0; colonne < nombreCasesDamier; colonne++) {
                Rectangle carre = new Rectangle(largeurCase, hauteurCase);

                if ((ligne + colonne) % 2 == 0) {
                    carre.setFill(Color.WHITE);
                } else {
                    carre.setFill(Color.DARKGRAY);
                }

                damier.add(carre, colonne, ligne);
            }
        }

        return damier;
    }

    /**
     * Vérifie si une case correspond à la case spéciale de départ.
     *
     * La case de départ est définie comme étant la case 1.
     *
     * @param numeroCase numéro de la case à tester
     * @return vrai si la case correspond au départ, sinon faux
     */
    private boolean estCaseDepart(int numeroCase) {
        return numeroCase == 1;
    }

    /**
     * Vérifie si une case correspond à la vraie case d'arrivée du plateau.
     *
     * La dernière case réelle du plateau est directement la case "FINISH".
     *
     * @param numeroCase numéro de la case à tester
     * @param nombreDeCases nombre total de cases du plateau
     * @return vrai si la case correspond à l'arrivée, sinon faux
     */
    private boolean estCaseArrivee(int numeroCase, int nombreDeCases) {
        return numeroCase == nombreDeCases;
    }

    /* Initialise les joueurs et leurs pions. Tous les joueurs commencent sur la première case du plateau. */
    private void initialiserJoueurs() {
        positionsJoueurs = new int[nombreJoueurs];
        pionsJoueurs.clear();

        for (int indexJoueur = 0; indexJoueur < nombreJoueurs; indexJoueur++) {
            Circle pion = new Circle(RAYON_PION, COULEURS_PIONS[indexJoueur]);
            pion.setStroke(Color.BLACK);
            pionsJoueurs.add(pion);
            positionsJoueurs[indexJoueur] = 0;
        }

        actualiserAffichagePions();
    }

    /**
     * Met à jour l'affichage des pions sur le plateau.
     * Tous les conteneurs de pions sont d'abord vidés, puis chaque pion est replacé sur sa case actuelle.
     */
    private void actualiserAffichagePions() {
        for (HBox conteneur : conteneursPionsParCase.values()) {
            conteneur.getChildren().clear();
        }

        for (int indexJoueur = 0; indexJoueur < nombreJoueurs; indexJoueur++) {
            int numeroCase = getNumeroCaseDepuisIndexPosition(positionsJoueurs[indexJoueur]);
            HBox conteneur = conteneursPionsParCase.get(numeroCase);

            if (conteneur != null) {
                conteneur.getChildren().add(pionsJoueurs.get(indexJoueur));
            }
        }
    }

    /**
     * Lance le tour du joueur courant.
     *
     * Elle gère les cas suivants :
     *
     *  1. Si la partie est terminée, on ne fait rien.
     *  2. On met à jour les informations affichées à l'écran.
     *  3. On regarde sur quelle case se trouve le joueur courant.
     *  4. Si le joueur est sur la case START :
     *      - aucune question n'est posée,
     *      - un message est affiché,
     *      - le joueur avance automatiquement d'une case,
     *      - le tour passe ensuite au joueur suivant.
     *  5. Sinon :
     *      - on détermine le thème de la case ;
     *      - on demande une difficulté,
     *      - on récupère une question,
     *      - on affiche la question,
     *      - on vérifie la réponse du joueur.
     *
     * Si la réponse est correcte :
     *      - le joueur avance du nombre de cases correspondant à la difficulté,
     *      - un message de réussite est affiché,
     *      - si le joueur atteint la dernière case, la partie se termine.
     *
     * Si la réponse est fausse :
     *      - le joueur n'avance pas,
     *      - la bonne réponse est affichée,
     *      - son tour se termine.
     */
    private void lancerTourJoueurCourant() {
        if (partieTerminee) {
            return;
        }

        mettreAJourInformationsJoueurCourant();

        int numeroCaseActuelle = getNumeroCaseDepuisIndexPosition(positionsJoueurs[indexJoueurCourant]);

        if (estCaseDepart(numeroCaseActuelle)) {
            themeActuelLabel.setText("Current theme: Start");
            statutTourLabel.setText("Status: Start tile. No question.");

            afficherPopupMessageNonFermable(
                    "Start tile",
                    "This is the start tile.\nThere is no question here.\nThe player automatically moves to tile 2."
            );

            avancerJoueurCourant(1);
            terminerTourEtPasserAuSuivant();
            return;
        }

        String nomTheme = getNomThemePourNumeroCase(numeroCaseActuelle);
        Theme themeSelectionne = getThemeParNom(nomTheme);

        if (themeSelectionne == null) {
            afficherPopupMessageNonFermable(
                    "Theme error",
                    "No theme was found for tile " + numeroCaseActuelle + "."
            );
            return;
        }

        int difficulteChoisie = demanderDifficulte(themeSelectionne.getThemeName());

        Question questionSelectionnee = themeSelectionne.getQuestionAlea(difficulteChoisie - 1);

        if (questionSelectionnee == null) {
            afficherPopupMessageNonFermable(
                    "Question error",
                    "No question was found for this difficulty."
            );
            terminerTourEtPasserAuSuivant();
            return;
        }

        boolean reponseCorrecte = demanderReponse(questionSelectionnee, difficulteChoisie);

        if (reponseCorrecte) {
            avancerJoueurCourant(difficulteChoisie);

            afficherPopupMessageNonFermable(
                    "Correct answer",
                    "Good answer.\nThe player moves forward by " + difficulteChoisie + " tile(s)."
            );

            if (estJoueurCourantArriveALaFin()) {
                partieTerminee = true;
                mettreAJourInformationsJoueurCourant();

                afficherPopupMessageNonFermable(
                        "Game over",
                        "Player " + (indexJoueurCourant + 1) + " wins the game."
                );
                return;
            }
        } else {
            afficherPopupMessageNonFermable(
                    "Wrong answer",
                    "Wrong answer.\nThe correct answer was: " + questionSelectionnee.getTrueAnswer()
            );
        }

        terminerTourEtPasserAuSuivant();
    }

    /**
     * Demande la difficulté dans un popup qui affiche également le thème
     *
     * @param nomTheme nom du thème courant
     * @return difficulté choisie entre 1 et 4
     */
    private int demanderDifficulte(String nomTheme) {
        final int[] resultat = {1};

        Stage popup = new Stage();
        popup.initOwner(AppTTMC.getFenetrePrincipale());
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle("Choose difficulty");
        popup.setOnCloseRequest(event -> event.consume());

        Label titre = new Label("Current theme: " + nomTheme);
        Label sousTitre = new Label("Choose a difficulty between 1 and 4");

        Button bouton1 = new Button("1");
        Button bouton2 = new Button("2");
        Button bouton3 = new Button("3");
        Button bouton4 = new Button("4");

        bouton1.setOnAction(event -> {
            resultat[0] = 1;
            popup.close();
        });

        bouton2.setOnAction(event -> {
            resultat[0] = 2;
            popup.close();
        });

        bouton3.setOnAction(event -> {
            resultat[0] = 3;
            popup.close();
        });

        bouton4.setOnAction(event -> {
            resultat[0] = 4;
            popup.close();
        });

        HBox boutons = new HBox(10, bouton1, bouton2, bouton3, bouton4);
        boutons.setAlignment(Pos.CENTER);

        VBox racine = new VBox(15, titre, sousTitre, boutons);
        racine.setAlignment(Pos.CENTER);
        racine.setPadding(new Insets(20));

        popup.setScene(new Scene(racine, 360, 180));
        popup.showAndWait();

        return resultat[0];
    }

    /**
     * Affiche la question dans un popup avec 4 boutons de réponse.
     *
     * @param question question à poser
     * @param difficulte difficulté choisie par le joueur
     * @return vrai si la réponse est correcte, sinon faux
     */
    private boolean demanderReponse(Question question, int difficulte) {
        final boolean[] resultat = {false};

        Stage popup = new Stage();
        popup.initOwner(AppTTMC.getFenetrePrincipale());
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle("Question");
        popup.setOnCloseRequest(event -> event.consume());

        Label titre = new Label("Difficulty " + difficulte);
        Label texteQuestion = new Label(question.getQuestion());
        texteQuestion.setWrapText(true);

        ArrayList<String> choix = question.getAnswers();

        Button boutonA = new Button("A - " + choix.get(0));
        Button boutonB = new Button("B - " + choix.get(1));
        Button boutonC = new Button("C - " + choix.get(2));
        Button boutonD = new Button("D - " + choix.get(3));

        /* On autorise les réponses longues à passer à la ligne pour afficher la réponse complète et pas une partie */
        boutonA.setMaxWidth(Double.MAX_VALUE);
        boutonB.setMaxWidth(Double.MAX_VALUE);
        boutonC.setMaxWidth(Double.MAX_VALUE);
        boutonD.setMaxWidth(Double.MAX_VALUE);

        boutonA.setWrapText(true);
        boutonB.setWrapText(true);
        boutonC.setWrapText(true);
        boutonD.setWrapText(true);

        boutonA.setPrefWidth(460);
        boutonB.setPrefWidth(460);
        boutonC.setPrefWidth(460);
        boutonD.setPrefWidth(460);

        boutonA.setAlignment(Pos.CENTER_LEFT);
        boutonB.setAlignment(Pos.CENTER_LEFT);
        boutonC.setAlignment(Pos.CENTER_LEFT);
        boutonD.setAlignment(Pos.CENTER_LEFT);

        boutonA.setOnAction(event -> {
            resultat[0] = question.isCorrect(choix.get(0));
            popup.close();
        });

        boutonB.setOnAction(event -> {
            resultat[0] = question.isCorrect(choix.get(1));
            popup.close();
        });

        boutonC.setOnAction(event -> {
            resultat[0] = question.isCorrect(choix.get(2));
            popup.close();
        });

        boutonD.setOnAction(event -> {
            resultat[0] = question.isCorrect(choix.get(3));
            popup.close();
        });

        VBox racine = new VBox(12, titre, texteQuestion, boutonA, boutonB, boutonC, boutonD);
        racine.setAlignment(Pos.CENTER_LEFT);
        racine.setPadding(new Insets(20));

        popup.setScene(new Scene(racine, 620, 380));
        popup.showAndWait();

        return resultat[0];
    }

    /**
     * Affiche un popup d'information qui ne peut pas être fermé manuellement.
     * Le joueur doit cliquer sur le bouton prévu.
     *
     * @param titre titre du popup
     * @param message message à afficher
     */
    private void afficherPopupMessageNonFermable(String titre, String message) {
        Stage popup = new Stage();
        popup.initOwner(AppTTMC.getFenetrePrincipale());
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle(titre);
        popup.setOnCloseRequest(event -> event.consume());

        Label labelMessage = new Label(message);
        labelMessage.setWrapText(true);

        Button boutonOk = new Button("OK");
        boutonOk.setOnAction(event -> popup.close());

        VBox racine = new VBox(15, labelMessage, boutonOk);
        racine.setAlignment(Pos.CENTER);
        racine.setPadding(new Insets(20));

        popup.setScene(new Scene(racine, 420, 180));
        popup.showAndWait();
    }

    /**
     * Fait avancer le joueur courant du nombre de cases indiqué.
     * Le déplacement est limité à la dernière case du plateau.
     *
     * @param nombreCases nombre de cases à avancer
     */
    private void avancerJoueurCourant(int nombreCases) {
        int nouvellePosition = positionsJoueurs[indexJoueurCourant] + nombreCases;
        int dernierePositionValide = cheminCoordonnees.size() - 1;

        if (nouvellePosition > dernierePositionValide) {
            nouvellePosition = dernierePositionValide;
        }

        positionsJoueurs[indexJoueurCourant] = nouvellePosition;
        actualiserAffichagePions();
        mettreAJourInformationsJoueurCourant();
    }

    /* Termine le tour courant puis passe au joueur suivant. */
    private void terminerTourEtPasserAuSuivant() {
        indexJoueurCourant = (indexJoueurCourant + 1) % nombreJoueurs;
        Platform.runLater(this::lancerTourJoueurCourant);
    }

    /**
     * Met à jour la zone d'informations affichée à droite du plateau.
     *
     * Cette méthode affiche :
     *      - le joueur dont c'est le tour,
     *      - le numéro de sa case actuelle,
     *      - le thème lié à cette case,
     *      - l'état général du jeu.
     *
     * Pour les cases spéciales :
     *      - la case 1 affiche "Start",
     *      - la dernière case affiche "Finish".
     *
     * Pour les autres cases, le thème est calculé à partir du numéro de case.
     */
    private void mettreAJourInformationsJoueurCourant() {
        int numeroCase = getNumeroCaseDepuisIndexPosition(positionsJoueurs[indexJoueurCourant]);
        String nomTheme;

        if (estCaseDepart(numeroCase)) {
            nomTheme = "Start";
        } else if (estCaseArrivee(numeroCase, cheminCoordonnees.size())) {
            nomTheme = "Finish";
        } else {
            nomTheme = getNomThemePourNumeroCase(numeroCase);
        }

        joueurActuelLabel.setText("Current player: Player " + (indexJoueurCourant + 1));
        caseActuelleLabel.setText("Current tile: " + numeroCase);
        themeActuelLabel.setText("Current theme: " + nomTheme);

        if (partieTerminee) {
            statutTourLabel.setText("Status: Game finished.");
        } else {
            statutTourLabel.setText("Status: Waiting for Player " + (indexJoueurCourant + 1) + ".");
        }
    }

    /**
     * Retourne vrai si le joueur courant se trouve sur la dernière case.
     *
     * @return vrai si le joueur courant a gagné, sinon faux
     */
    private boolean estJoueurCourantArriveALaFin() {
        return positionsJoueurs[indexJoueurCourant] >= cheminCoordonnees.size() - 1;
    }

    /**
     * Compte le nombre total de cases présentes dans le plateau.
     *
     * @param plateau matrice du plateau
     * @return nombre de cases strictement positives
     */
    private int compterNombreDeCases(int[][] plateau) {
        int nombreDeCases = 0;

        for (int[] ligne : plateau) {
            for (int valeur : ligne) {
                if (valeur > 0) {
                    nombreDeCases++;
                }
            }
        }

        return nombreDeCases;
    }

    /**
     * Retourne la couleur associée à un numéro de case.
     *
     * La logique choisie est cyclique sur 4 couleurs.
     *
     * @param numeroCase numéro de la case
     * @return couleur de la case
     */
    private Color getCouleurPourNumeroCase(int numeroCase) {
        return COULEURS_THEMES[(numeroCase - 1) % COULEURS_THEMES.length];
    }

    /**
     * Retourne le nom du thème associé à un numéro de case.
     * Le thème suit le même cycle que la couleur.
     *
     * @param numeroCase numéro de la case
     * @return nom du thème associé
     */
    private String getNomThemePourNumeroCase(int numeroCase) {
        return NOMS_THEMES[(numeroCase - 1) % NOMS_THEMES.length];
    }

    /**
     * Recherche un thème par son nom dans la liste des thèmes chargés.
     *
     * @param nomTheme nom recherché
     * @return thème correspondant, ou null s'il n'existe pas
     */
    private Theme getThemeParNom(String nomTheme) {
        for (Theme theme : themesDisponibles) {
            if (theme.getThemeName().equalsIgnoreCase(nomTheme)) {
                return theme;
            }
        }
        return null;
    }

    /**
     * Convertit une position interne de joueur en numéro de case réel.
     *
     * @param indexPosition position interne du joueur
     * @return numéro réel de la case
     */
    private int getNumeroCaseDepuisIndexPosition(int indexPosition) {
        return indexPosition + 1;
    }
}