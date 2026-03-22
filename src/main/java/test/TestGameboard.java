package test;

import models.Game;
import models.GameBoard;
import java.io.File;
import java.net.URI;

public class TestGameboard {
    public static void main(String[] args) {
        // 1. Crée un vrai objet Game au lieu de null
        Game game = new Game();

        try {
            // 3. Récupération du fichier dans le dossier resources
            // On passe par URI pour que le constructeur de File comprenne le chemin
            URI uri = TestGameboard.class.getResource("/JSONFILE/tilde.json").toURI();
            File jsonTilde = new File(uri);

            // 4. Appel de la création
            game.getGBoard().createTilde(jsonTilde);

            System.out.println("Chargement réussi !");
        } catch (Exception e) {
            System.err.println("Erreur : Vérifiez que le fichier est bien dans src/main/resources/JSONFILE/tilde.json");
            e.printStackTrace();
        }
    }
}