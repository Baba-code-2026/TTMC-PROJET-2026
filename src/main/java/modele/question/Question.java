package modele.question;

import java.util.ArrayList;

public class Question {
    private String question;
    private String trueAnswer;
    private int difficulty;
    private ArrayList<String> answers;

    public Question(String question, String trueAnswer, int difficulty, ArrayList<String> answers) {
        this.question = question;
        this.trueAnswer = trueAnswer;
        this.difficulty = difficulty;
        this.answers = answers;
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
}
