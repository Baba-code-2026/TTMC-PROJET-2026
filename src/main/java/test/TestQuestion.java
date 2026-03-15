package test;

import modele.question.Question;
import modele.question.Theme;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class TestQuestion {
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    URL urlJson = TestQuestion.class.getResource("/JSONFILE/JSON.json");
    if (urlJson == null) {
        System.err.println("Fichier JSON non trouvé !");
        return;
    }
    try{
        File json = new File(urlJson.toURI());
        Theme Mystery = new Theme("Mystery");
        Theme Informatics =  new Theme("Informatics");
        Theme Entertainment =  new Theme("Entertainment");
        Theme Tourism =   new Theme("Tourism");

        Mystery.loadJson(json);
        Informatics.loadJson(json);
        Entertainment.loadJson(json);
        Tourism.loadJson(json);

        //System.out.println(Mystery);
        //System.out.println(Informatics);
        //System.out.println(Entertainment);
        //System.out.println(Tourism);
        System.out.println("choissisez la difficulté entre 1 et 4");
        int difficulty = sc.nextInt();
        if (difficulty < 1 || difficulty > 4){
            System.out.println("invalid difficulty");
        }else{
            System.out.println(Entertainment.askQuestion(difficulty-1));
            System.out.println("Your response (a,b,c,d): ");
            String choice = sc.nextLine();
            choice = sc.nextLine();
            if (Entertainment.checkAnswer(choice)){
                System.out.println("GOOD JOB, that was the good answer.");
            }else{
                System.out.println("NICE JOB, that was NOT the good answer Dick head.");
            }
        }

    }catch(Exception e){
        e.printStackTrace();
    }

    }
}