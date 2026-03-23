package com.example.ttmc2026.models.question;

import java.util.ArrayList;

/**
 * Représente un sujet appartenant à un thème.
 * Un sujet contient une liste de questions.
 */
public class Subject {

    private String name; /* nom du sujet */

    private ArrayList<Question> questions; /* Liste des questions appartenant à ce sujet */

    private final int MAX_QUESTION = 4;

    /* Constructeur */
    public Subject(String name) {
        this.name = name;
        questions = new ArrayList<>();
    }

    /* Getter */
    public String getName() {
        return name;
    }

    /* Ajoute une question à ce sujet. */
    public void addQuestion(Question q) {
        questions.add(q);
    }

    /* Retourne une question selon la difficulté demandée. */
    public Question getRandomQuestion(int difficulty) {
        if (questions.isEmpty()) {
            return null;
        }
        return questions.get(difficulty);
    }

    /* Méthode toString() pour afficher les données */
    @Override
    public String toString() {
        return "Subject{" + "name='" + name + "'" + ", questions=\n\t\t" + questions + "}\n\t";
    }
}