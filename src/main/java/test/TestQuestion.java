package test;

import modele.question.Question;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

public class TestQuestion {
    public static void main(String[] args) {
        try {
            // 1. Récupération de l'URL de la ressource
            URL resourceUrl = TestQuestion.class.getResource("/JSONFILE/JSON.json");

            if (resourceUrl == null) {
                System.err.println("ERREUR : Le fichier JSON est introuvable dans le dossier resources.");
                return;
            }

            // 2. Conversion en File
            URI uri = resourceUrl.toURI();
            File file = new File(uri);

            // 3. Chargement des questions
            ArrayList<Question> toutesLesQuestions = Question.loadQuestions(file);

            // 4. Affichage
            if (toutesLesQuestions.isEmpty()) {
                System.out.println("Aucune question trouvée ou erreur de lecture.");
            } else {
                for (Question q : toutesLesQuestions) {
                    System.out.println("-----------------------------------");
                    System.out.println("Thème : " + q.getTheme());
                    System.out.println("Question : " + q.getQuestion());
                    System.out.println("Réponse : " + q.getTrueAnswer());
                    System.out.println("Choix : " + q.getAnswers());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}