package modele.question;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;

public class Question {
    // Attributs
    private String theme;
    private String subject;
    private int difficulty;
    private String question;

    @SerializedName("answer")
    private String trueAnswer;

    @SerializedName("choices")
    private ArrayList<String> answers;

    // Constructeur vide (Obligatoire pour que Gson puisse instancier la classe)
    public Question() {
    }

    // Constructeur complet (Utile pour créer des questions manuellement)
    public Question(String theme, String subject, String question, String trueAnswer, int difficulty, ArrayList<String> answers) {
        this.theme = theme;
        this.subject = subject;
        this.question = question;
        this.trueAnswer = trueAnswer;
        this.difficulty = difficulty;
        this.answers = answers;
    }

    // lecture des questions et du JSON
    public static ArrayList<Question> loadQuestions(File jsonFile) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(jsonFile)) {
            // On définit le type attendu : ArrayList<Question>
            Type listType = new TypeToken<ArrayList<Question>>(){}.getType();

            // On désérialise le JSON directement en liste d'objets
            return gson.fromJson(reader, listType);
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement du JSON de questions :");
            e.printStackTrace();
            return new ArrayList<>(); // Retourne une liste vide pour éviter les crashs
        }
    }

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

    @Override
    public String toString() {
        return "Question [" + theme + " - " + subject + " (Diff: " + difficulty + ")] : " + question +"\n\t\t";
    }
}