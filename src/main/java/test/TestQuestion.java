package test;

import models.question.Theme;

import java.util.ArrayList;
import java.util.Scanner;


public class TestQuestion {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Theme> themes = Theme.initAllThemes();

        System.out.println("Choose a theme: ");
        for (int i = 0; i < themes.size(); i++) {
            System.out.println((i + 1) + ". " + themes.get(i).getThemeName());
        }

        System.out.print("Your choice: ");
        int themeChoice = sc.nextInt();

        if (themeChoice < 1 || themeChoice > themes.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Theme selectedTheme = themes.get(themeChoice - 1);
        System.out.println("Choose your difficulty: (1-4) ");
        int difficulty = sc.nextInt();
        sc.nextLine();

        if (difficulty < 1 || difficulty > 4) {
            System.out.println("Invalid difficulty.");
        } else {
            System.out.println("\n" + selectedTheme.askQuestion(difficulty - 1));

            System.out.print("Your answer (a,b,c,d) : ");
            String choice = sc.nextLine().trim();

            if (selectedTheme.checkAnswer(choice)) {
                System.out.println("GOOD JOB, that's the good answer.");
            } else {
                System.out.println("NICE JOB, that's the wrong answer dick head.");
            }
        }
    }
}