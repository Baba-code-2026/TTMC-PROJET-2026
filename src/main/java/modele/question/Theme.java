package modele.question;

import javax.security.auth.Subject;
import java.util.ArrayList;

public class Theme {
    // Attributs
    private String themeName; // aucune modif
    private ArrayList<Subject> subjects;

    // Constructeur
    public Theme(String themeName) {
        this.themeName = themeName;
        subjects = new ArrayList<Subject>();
    }

    public String getThemeName() {
        return themeName;
    }

    public void addSubject(){

    }
}
