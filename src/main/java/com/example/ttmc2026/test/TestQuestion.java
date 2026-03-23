package com.example.ttmc2026.test;

import com.example.ttmc2026.models.question.Theme;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe de test console permettant de vérifier :
 *      - le chargement des thèmes,
 *      - l'affichage d'une question,
 *      - la vérification d'une réponse utilisateur.
 */
public class TestQuestion {

    /**
     * Point d'entrée du test console.
     *
     * @param args arguments de lancement
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Theme> themes = Theme.initAllThemes();

        System.out.println("Choose a theme: ");
        for (int index = 0; index < themes.size(); index++) {
            System.out.println((index + 1) + ". " + themes.get(index).getThemeName());
        }

        System.out.print("Your choice: ");
        int choixTheme = scanner.nextInt();

        if (choixTheme < 1 || choixTheme > themes.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Theme themeSelectionne = themes.get(choixTheme - 1);

        System.out.println("Choose your difficulty: (1-4) ");
        int difficulte = scanner.nextInt();
        scanner.nextLine();

        if (difficulte < 1 || difficulte > 4) {
            System.out.println("Invalid difficulty.");
        } else {
            System.out.println("\n" + themeSelectionne.askQuestion(difficulte - 1));

            System.out.print("Your answer (a,b,c,d) : ");
            String choixReponse = scanner.nextLine().trim();

            if (themeSelectionne.checkAnswer(choixReponse)) {
                System.out.println("GOOD JOB, that's the good answer.");
            } else {
                System.out.println("Wrong answer.");
            }
        }
    }
}