package modele.question;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Theme {
    // Attributs
    private String themeName; // aucune modif
    private ArrayList<Subject> subjects;

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
        return subjects.stream()
                .filter(s -> s.getName().equalsIgnoreCase(name))
                .findFirst().orElse(null);
    }

    public String getThemeName() {
        return themeName;
    }

    @Override
    public String toString() {
        return "Theme{" +
                "themeName='" + themeName + "'" +
                ": subjects= \n\t" + subjects +
                '}';
    }

    public Question getQuestionAlea(){
        if(subjects.isEmpty()) return null;

        Random rand = new Random();
        Subject randSubject = subjects.get(rand.nextInt(subjects.size()));

        return randSubject.getRandomQuestion();
    }
}
