package com.example.ttmc2026.models.question;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Représente une question du jeu.
 * Cette classe contient aussi une méthode utilitaire permettant de charger toutes les questions depuis un fichier JSON.
 */
public class Question {

    private String theme;
    private String subject;
    private int difficulty;
    private String question;
    /**
     * Bonne réponse à la question.
     * Le nom du champ JSON est "answer", d'où l'utilisation de l'annotation SerializedName.
     */
    @SerializedName("answer")
    private String trueAnswer;
    /**
     * Liste des réponses proposées à l'utilisateur.
     * Le nom du champ JSON est "choices".
     */
    @SerializedName("choices")
    private ArrayList<String> answers;

    /* Constructeur vide */
    public Question() {
    }

    /* Constructeur complet. */
    public Question(String theme, String subject, String question, String trueAnswer, int difficulty, ArrayList<String> answers) {
        this.theme = theme;
        this.subject = subject;
        this.question = question;
        this.trueAnswer = trueAnswer;
        this.difficulty = difficulty;
        this.answers = answers;
    }

    /**
     * Charge toutes les questions contenues dans un fichier JSON.
     * En cas d'erreur, la méthode retourne une liste vide afin d'éviter un arrêt brutal du programme.
     *
     * @param jsonFile fichier JSON contenant les questions
     * @return liste des questions chargées
     */
    public static ArrayList<Question> loadQuestions(File jsonFile) {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(jsonFile)) {
            Type typeListeQuestions = new TypeToken<ArrayList<Question>>() {}.getType();
            return gson.fromJson(reader, typeListeQuestions);
        } catch (Exception exception) {
            System.err.println("Erreur lors du chargement du fichier JSON des questions.");
            exception.printStackTrace();
            return new ArrayList<>();
        }
    }

    /* Getters et Setters */
    public String getTheme() {
        return theme;
    }

    public String getSubject() {
        return subject;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public String getTrueAnswer() {
        return trueAnswer;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public boolean isCorrect(String playerAnswer) {
        return this.trueAnswer.equalsIgnoreCase(playerAnswer);
    }

    /* Méthode toString() pour afficher les données */
    @Override
    public String toString() {
        return "Question [" + theme + " - " + subject + " (Diff: " + difficulty + ")] : " + question + "\n\t\t";
    }
}