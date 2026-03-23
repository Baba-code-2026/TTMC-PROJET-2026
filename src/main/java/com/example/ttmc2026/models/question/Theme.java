package com.example.ttmc2026.models.question;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Représente un thème du jeu.
 * Un thème contient plusieurs sujets, et chaque sujet contient plusieurs questions.
 */
public class Theme {

    private String themeName;

    private ArrayList<Subject> subjects;

    /* Question actuellement posée au joueur */
    private Question currentQuestion;

    /* Constructeur */
    public Theme(String themeName) {
        this.themeName = themeName;
        subjects = new ArrayList<>();
    }

    /**
     * Charge depuis un fichier JSON toutes les questions appartenant à ce thème.
     *
     * Pour chaque question trouvée dans le fichier :
     *      - si son thème correspond au thème courant,
     *      - alors on la range dans le bon sujet.
     *
     * @param JSON fichier JSON contenant les questions
     */
    public void loadJson(File JSON) {
        ArrayList<Question> questions = Question.loadQuestions(JSON);

        for (Question q : questions) {
            if (q.getTheme().equalsIgnoreCase(this.themeName)) {
                findCreateSubject(q.getSubject()).addQuestion(q);
            }
        }
    }

    /**
     * Recherche un sujet à partir de son nom.
     *
     * Si le sujet existe déjà, il est retourné.
     * Sinon, il est créé puis ajouté à la liste.
     *
     * @param sub nom du sujet recherché
     * @return sujet trouvé ou nouvellement créé
     */
    private Subject findCreateSubject(String sub) {
        for (Subject s : subjects) {
            if (s.getName().equalsIgnoreCase(sub)) {
                return s;
            }
        }

        Subject newSubject = new Subject(sub);
        subjects.add(newSubject);
        return newSubject;
    }

    /**
     * Retourne un sujet à partir de son nom.
     *
     * @param name nom du sujet recherché
     * @return sujet correspondant, ou null s'il n'existe pas
     */
    public Subject getSubject(String name) {
        for (Subject s : subjects) {
            if (s.getName().equalsIgnoreCase(name)) {
                return s;
            }
        }
        return null;
    }

    /* Getter */
    public String getThemeName() {
        return themeName;
    }

    /* Méthode toString() pour afficher le nom du thème */
    @Override
    public String toString() {
        return themeName;
    }

    /**
     * Initialise tous les thèmes du jeu.
     *Cette méthode crée la liste des thèmes prévus dans le projet puis charge les questions depuis le fichier JSON.
     *
     * @return liste de tous les thèmes initialisés
     */
    public static ArrayList<Theme> initAllThemes() {
        ArrayList<Theme> list = new ArrayList<>();
        list.add(new Theme("Mystery"));
        list.add(new Theme("Informatics"));
        list.add(new Theme("Entertainment"));
        list.add(new Theme("Tourism"));

        try {
            URL urlJson = Theme.class.getResource("/JSONFILE/JSON.json");

            if (urlJson != null) {
                File json = new File(urlJson.toURI());

                // On charge les données de chaque thème à partir du même fichier JSON
                for (Theme t : list) {
                    t.loadJson(json);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * Retourne une question aléatoire correspondant à une difficulté donnée.
     *
     * Choisit d'abord un sujet aléatoire, puis récupère une question de la difficulté demandée.
     *
     * @param difficulty difficulté demandée
     * @return question choisie, ou null si aucun sujet n'est disponible
     */
    public Question getQuestionAlea(int difficulty) {
        if (subjects.isEmpty()) {
            return null;
        }

        Random rand = new Random();
        Subject randSubject = subjects.get(rand.nextInt(subjects.size()));

        return randSubject.getRandomQuestion(difficulty);
    }

    /**
     * Prépare et retourne le texte d'une question à afficher.
     *
     * Cette méthode :
     *      - choisit une question aléatoire selon la difficulté,
     *      - mélange les réponses,
     *      - construit le texte affichable.
     *
     * @param difficulty difficulté demandée
     * @return texte complet de la question avec ses choix
     */
    public String askQuestion(int difficulty) {
        this.currentQuestion = getQuestionAlea(difficulty);

        if (this.currentQuestion == null) {
            return "No question available";
        }

        ArrayList<String> answers = currentQuestion.getAnswers();
        Collections.shuffle(answers);

        return currentQuestion.getQuestion() + "\n" +
                "choice a: " + answers.getFirst() + "\n" +
                "choice b: " + answers.get(1) + "\n" +
                "choice c: " + answers.get(2) + "\n" +
                "choice d: " + answers.getLast() + "\n";
    }

    /**
     * Vérifie si le choix du joueur correspond à la bonne réponse.
     *
     * Le joueur répond avec une lettre :
     * - a
     * - b
     * - c
     * - d
     *
     * @param choice choix du joueur
     * @return vrai si la réponse est correcte, sinon faux
     */
    public boolean checkAnswer(String choice) {
        if (currentQuestion == null) {
            return false;
        }

        ArrayList<String> answers = currentQuestion.getAnswers();
        String playerChoice = "";

        switch (choice.toLowerCase()) {
            case "a" -> playerChoice = answers.get(0);
            case "b" -> playerChoice = answers.get(1);
            case "c" -> playerChoice = answers.get(2);
            case "d" -> playerChoice = answers.get(3);
            default -> {
                System.out.println("Entrée invalide !");
                return false;
            }
        }

        return currentQuestion.isCorrect(playerChoice);
    }
}