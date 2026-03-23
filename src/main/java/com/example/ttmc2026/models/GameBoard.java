package com.example.ttmc2026.models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.example.ttmc2026.models.entity.Entity;
import com.example.ttmc2026.models.question.Theme;

import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;

/**
 * Représente le plateau de jeu.
 *
 * Cette classe contient la liste des cases du plateau ainsi qu'une référence vers la partie en cours.
 * Cette classe est gérée comme un singleton.
 */
public class GameBoard {

    private static GameBoard singleton = null; /* Instance unique du plateau = Singleton */
    private Game game; /* Partie associée à ce plateau */
    private ArrayList<Tilde> tildes; /* Liste des cases du plateau. */

    /* Constructeur */
    private GameBoard(Game game) {
        this.game = game;
        tildes = new ArrayList<>();
    }

    /**
     * Gère la réponse à une question pour une entité donnée sur une case donnée.
     *
     * @param e entité concernée
     * @param t case concernée
     */
    public int answerQuestion(Entity e, Tilde t) {
        return 0;
    }

    /**
     * Retourne la case suivante à partir d'une case donnée
     *
     * @param t case de départ
     * @param times nombre de déplacements à effectuer
     * @return case suivante, actuellement null
     */
    public Tilde getNextTilde(Tilde t, int times) {
        return null;
    }

    /**
     * Recherche une case à partir de son numéro.
     *
     * @param score numéro recherché
     * @return case correspondante, ou null si elle n'existe pas
     */
    public Tilde checkTilde(int score) {
        for (Tilde t : tildes) {
            if (score == t.getNumber()) {
                return t;
            }
        }
        return null;
    }

    /**
     * Charge les cases du plateau depuis un fichier JSON.
     *
     * @param json fichier JSON contenant les cases
     */
    public void createTilde(File json) {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(json)) {
            java.lang.reflect.Type listType = new com.google.gson.reflect.TypeToken<ArrayList<Tilde>>() {}.getType();
            ArrayList<Tilde> listTildes = gson.fromJson(reader, listType);

            for (Tilde tilde : listTildes) {
                this.tildes.add(tilde);
            }

            System.out.println("Chargement réussi : " + tildes.size() + " cases.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retourne l'instance unique du plateau.
     * Si elle n'existe pas encore, elle est créée.
     *
     * @param game partie associée
     * @return instance unique du plateau
     */
    public static GameBoard getInstance(Game game) {
        if (singleton == null) {
            singleton = new GameBoard(game);
        }
        return singleton;
    }

    /* Getter */
    public Game getGame() {
        return game;
    }

    /**
     * Lit les données du fichier JSON du plateau.
     *
     * La méthode charge le fichier "tilde.json", récupère le tableau "chemin", puis prépare la lecture de ses données.
     */
    public void readTilde() {
        try {
            URL urlJson = Theme.class.getResource("/JSONFILE/tilde.json");

            if (urlJson != null) {
                File json = new File(urlJson.toURI());

                Gson gson = new Gson();

                // Lecture de l'objet JSON racine
                JsonObject root = gson.fromJson(new FileReader(json), JsonObject.class);

                // Extraction du tableau "chemin"
                int[][] tableau = gson.fromJson(root.get("chemin"), int[][].class);

                // Création de la liste des cases à partir du tableau
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Tilde> getTiles() {
        return tildes;
    }
}