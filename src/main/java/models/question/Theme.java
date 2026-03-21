package models.question;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Theme {
    // Attributs
    private String themeName; // aucune modif
    private ArrayList<Subject> subjects;
    private Question currentQuestion;

    // Constructeur
    public Theme(String themeName) {
        this.themeName = themeName;
        subjects = new ArrayList<>();
    }

    public void loadJson(File JSON){
        ArrayList<Question> questions = Question.loadQuestions(JSON);
        for (Question q : questions){
            if(q.getTheme().equalsIgnoreCase(this.themeName)){
                findCreateSubject(q.getSubject()).addQuestion(q);
            }
        }
    }

    private Subject findCreateSubject(String sub){
        for (Subject s : subjects) {
            if (s.getName().equalsIgnoreCase(sub)) return s;
        }
        Subject newSubject = new Subject(sub);
        subjects.add(newSubject);
        return newSubject;
    }

    public Subject getSubject(String name) {
        for (Subject s : subjects) {
            if (s.getName().equalsIgnoreCase(name)) {
                return s; // On a trouvé !
            }
        }
        return null; // On a fini la boucle sans rien trouver
    }

    public String getThemeName() {
        return themeName;
    }

    @Override
    public String toString() {
        return themeName ;
    }

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
                // On charge les données pour chaque thème
                for (Theme t : list) {
                    t.loadJson(json);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Question getQuestionAlea(int  difficulty) {
        if(subjects.isEmpty()) return null;

        Random rand = new Random();
        Subject randSubject = subjects.get(rand.nextInt(subjects.size()));

        return randSubject.getRandomQuestion(difficulty);
    }

    public String askQuestion(int  difficulty) {
        this.currentQuestion = getQuestionAlea(difficulty);
        if(this.currentQuestion == null) return "No question available";
        ArrayList<String> answers = currentQuestion.getAnswers();
        Collections.shuffle(answers);
        return currentQuestion.getQuestion() + "\n" +
                "choice a: "+ answers.getFirst() +"\n" +
                "choice b: "+ answers.get(1) +"\n" +
                "choice c: "+ answers.get(2) +"\n" +
                "choice d: "+ answers.getLast() +"\n";
    }

    public boolean checkAnswer(String choice){
        if (currentQuestion == null) return false;

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
