package modele.question;

import java.util.ArrayList;

public class Question {
    private String question;
    private String trueAnswer;
    private ArrayList<String> answers;

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