package modele;

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

    public String getName() {
        return name;
    }

    public void addQuestion(Question q)
    {
        if(questions.size() < MAX_QUESTION)
        {
            questions.add(q);
        }
    }
}
