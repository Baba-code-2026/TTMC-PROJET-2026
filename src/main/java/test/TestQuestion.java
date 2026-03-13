package test;

import modele.question.Question;
import modele.question.Theme;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;


public class TestQuestion {
    public static void main(String[] args) {

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

        Question randQuest = Entertainment.getQuestionAlea();
        System.out.println("Question Alea: " + randQuest);

    }catch(Exception e){
        e.printStackTrace();
    }

    }
}