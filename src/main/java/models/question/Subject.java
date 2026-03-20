package models.question;

import java.util.ArrayList;

public class Subject {

    //Attributs
    private String name;
    private ArrayList<Question> questions;
    private final int MAX_QUESTION = 4;

    //Constructeur
    public Subject(String name){
        this.name = name;
        questions = new ArrayList<>();
    }

    public String getName() { return name; }

    public void addQuestion(Question q) {
        questions.add(q);
    }

    public Question getRandomQuestion(int difficulty) {
        if (questions.isEmpty()) return null;
        return questions.get(difficulty);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "name='" + name + "'" +
                ", questions=\n\t\t" + questions +
                "}\n\t";
    }
}
