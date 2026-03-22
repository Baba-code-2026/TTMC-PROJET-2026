package models;


import models.entity.Bot;
import models.entity.Entity;
import models.entity.Player;
import models.question.Question;
import models.question.Theme;
import models.state.Dead;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Game {

    // Attribut
    private final GameBoard gBoard;
    private int nbTurn = 1;
    private final ArrayList<Entity> entities;
    private ArrayList<Player> playersDead;
    private final int MAX_ENTITIES = 5;

    //Constructeur
    public Game() {
        this.gBoard = GameBoard.getInstance(this);
        entities = new ArrayList<>();
        playersDead = new ArrayList<>();
    }

    // Lance le jeu
    public void start(){
        // Lecture des fichiers Json de Tilde et Questions
        // Demande le nombre d'entity à créer
        // Créer entre 3 et 5 entites
        int nbEntities = 4;
        String []tabNamePlayer = {"Flash McQueen", "Mater", "Sally", "Doc Hudson"};
        for (int i = 0; i < nbEntities; i++){
            // IL faut aller Chercher Tield de Départ.
            Tile depart = gBoard.checkTilde(0);
            entities.add(new Player(0,tabNamePlayer[i],depart.getPositionX(),depart.getPositionY()));
            //Lancement du jeu
            turnManagement();
        }
    }




public void turnManagement(){
    while(entities.size() <= 2){ // Une condition qui vérifie que un utilisateur gagne
        for(Entity e: entities){
            if(e instanceof Player){
                Player p = (Player)e;
                // Créer une méthode starTurn dans Player et ses Etats
                // Cette méthode starTurn s'occupera de mettre l'attribut en alreadyVersusInTurn en false

                Tile tildePlayer = gBoard.checkTilde(p.getScore());
                Theme themeTildePlayer = tildePlayer.getTheme();
                //donne le Sujet du theme puis demande la difficulté et renvoie la Question
                Question questionForPlayer = themeTildePlayer.getQuestionAlea(1);
                // Donne la question au Joueur et il doit y répondre avec une méthode qui renvoie le score
                // Exemple Méthode int point = answerQuestion(questionForPlayer);
                int point = 1;
                Tile newTildePlayer = gBoard.checkTilde(p.getScore() + point);
                p.moveEntity(newTildePlayer.getNumber(), newTildePlayer.getPositionX(), newTildePlayer.getPositionY());

                if(getNbTurn() >= 3) { // Pour empêcher des duels au début du jeu.
                    Iterator<Entity> it = iterator();
                    while (it.hasNext()) {
                        Entity entity = it.next();
                        if (entity instanceof Player) {
                            Player p2 = (Player) entity;
                            if (p2.getScore() == p.getScore() && !(p.getName().equals(p2.getName()))) {
                                //Créer méthode startVersus pour mettre les deux joueurs en état de versus.
                                // Quand les deuxs sont en duel, on va dans le thème duel et on fait pour
                                // les deux joueurs, une recherche de deux Sujets puis demandez le niveau de la question.
                                // D'abord le joueur p qui s'est déplacé répond puis l'autre p2 qui était déjà sur la case
                                //Ensuite on compare les points de leurs questions.
                                //Si aucun des deux a bien répondu ou ont le même niveau de quetions bien répondu
                                // alors personnes avancent. Si un gange alors
                                //il avancera de la différence des points entre les deux.
                                // Cette méthode endVersusPlayer s'occupera de mettre l'attribut en alreadyVersusInTurn en true
                                p.endVersusPlayer();
                                p2.endVersusPlayer();
                            }
                        }

                    }
                }

            }
            else
            {// Le Tour de Frank
                if(getNbTurn() >= 3)
                { // Vérifie quand frank arrive sur le jeu
                    Bot frank = (Bot) e;
                    Random rand = new Random();
                    int point = rand.nextInt(2) + 1;
                    Tile newTildeBot = gBoard.checkTilde(frank.getScore() + point);
                    frank.moveEntity(newTildeBot.getNumber(), newTildeBot.getPositionX(), newTildeBot.getPositionY());
                    Iterator<Entity> itBot = iterator();
                    while (itBot.hasNext())
                    {
                        Entity entity = itBot.next();
                        if (entity instanceof Player)
                        {
                            Player p2 = (Player) entity;
                            if (p2.getScore() <= e.getScore() )
                            {
                                //Créer méthode startVersus pour mettre le  joueurs en état de versus.
                                // Quand le joueur est en duel contre frank, on va dans le thème Frank et on fait pour
                                // le joueur, une recherche de une question de niveau 2.
                                //int point = answerQuestion(questionForPlayer);
                                //Si il a bon à la question il avance de 2 points s'il a tord il meurt.
                                int survie = 1;
                                if(survie == 0)
                                {
                                    //Soit on fait une méthode qui change le état de joueur en Dead soit
                                    p2.setEtat(new Dead(p2));
                                    playersDead.add(p2);
                                    entities.remove(p2);
                                }else
                                {
                                    Tile newTildePlayer = gBoard.checkTilde(p2.getScore() + 2);
                                    p2.moveEntity(newTildePlayer.getNumber(), newTildePlayer.getPositionX(), newTildePlayer.getPositionY());
                                    // Cette méthode endVersusPlayer s'occupera de mettre l'attribut en alreadyVersusInTurn en true
                                    p2.endVersusPlayer();
                                }
                            }
                        }

                    }

                }
            }
            //Ici faire quelque chose qui affiche les scores à chaque fin de Tour ( idée)
            nbTurn++;
        }

    }
}

public boolean addEntity(Entity entity){
    return entities.add(entity);
}


public GameBoard getGBoard() {
        return gBoard;
}

public Iterator<Entity> iterator() {
    return entities.iterator();
}

public void readData(String s){
}

public int getNbTurn()
{
    return nbTurn;
}

}