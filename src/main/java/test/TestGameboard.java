package test;

import modele.Game;
import modele.GameBoard;
import java.io.File;
import java.net.URI;

public class TestGameboard {
    public static void main(String[] args) {
        // 1. Crée un vrai objet Game au lieu de null
        Game game = null;

        // 2. Récupère le singleton
        GameBoard gb = GameBoard.getInstance(game);

        try {
            // 3. Récupération du fichier dans le dossier resources
            // On passe par URI pour que le constructeur de File comprenne le chemin
            URI uri = TestGameboard.class.getResource("/JSONFILE/tilde.json").toURI();
            File jsonTilde = new File(uri);

            // 4. Appel de la création
            gb.createTilde(jsonTilde);

            System.out.println("Chargement réussi !");
        } catch (Exception e) {
            System.err.println("Erreur : Vérifiez que le fichier est bien dans src/main/resources/JSONFILE/tilde.json");
            e.printStackTrace();
        }
    }
}