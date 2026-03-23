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

/**
 * Représente une partie du jeu.
 *
 * Cette classe gère :
 *      - le plateau,
 *      - le nombre de tours,
 *      - la liste des entités présentes dans la partie,
 *      - les joueurs éliminés.
 */
public class Game {

    private final GameBoard gBoard; /* Plateau associé à la partie */
    private int nbTurn = 1; /* Numéro du tour actuel */

    private final ArrayList<Entity> entities; /* Liste des entités encore présentes dans la partie */
    private ArrayList<Player> playersDead; /* Liste des joueurs éliminés */
    private final int MAX_ENTITIES = 5; /* Nombre max d'entités dans le projet */


    public Game() {
        this.gBoard = GameBoard.getInstance(this);
        entities = new ArrayList<>();
        playersDead = new ArrayList<>();
    }

    /**
     * Lance la partie.
     *
     * La logique actuelle :
     *      - fixe un nombre de joueurs,
     *      - crée les entités,
     *      - initialise leur position de départ,
     *      - lance ensuite la gestion des tours.
     */
    public void start() {
        // Lecture des fichiers JSON du plateau et des questions
        // Demande le nombre d'entités à créer
        // Création de 3 à 5 entités

        int nbEntities = 4;
        String[] tabNamePlayer = {"Flash McQueen", "Mater", "Sally", "Doc Hudson"};

        for (int i = 0; i < nbEntities; i++) {
            // Il faut aller chercher la case de départ
            Tilde depart = gBoard.checkTilde(0);
            entities.add(new Player(0, tabNamePlayer[i], depart.getPositionX(), depart.getPositionY()));

            // Lancement du jeu
            turnManagement();
        }
    }

    /**
     * Gère le déroulement des tours de jeu.
     *
     * Cette méthode contient :
     *      - le tour des joueurs,
     *      - le déplacement,
     *      - la gestion des duels,
     *      - l'apparition et le comportement du bot Frank.
     */
    public void turnManagement() {
        while (entities.size() <= 2) { // Condition actuelle pour vérifier la fin de partie (2 joueurs en vie minimum)
            for (Entity e : entities) {
                if (e instanceof Player) {
                    Player p = (Player) e;

                    // Créer une méthode startTurn dans Player et ses états
                    // Cette méthode startTurn s'occupera de mettre l'attribut alreadyVersusInTurn à false

                    Tilde tildePlayer = gBoard.checkTilde(p.getScore());
                    Theme themeTildePlayer = tildePlayer.getTheme();

                    // Donne le sujet du thème puis demande la difficulté et renvoie la question
                    Question questionForPlayer = themeTildePlayer.getQuestionAlea(1);

                    // Donne la question au joueur et il doit y répondre avec une méthode qui renvoie le score
                    // Exemple : int point = answerQuestion(questionForPlayer);
                    int point = 1;

                    Tilde newTildePlayer = gBoard.checkTilde(p.getScore() + point);
                    p.moveEntity(newTildePlayer.getNumber(), newTildePlayer.getPositionX(), newTildePlayer.getPositionY());

                    if (getNbTurn() >= 3) { // Pour empêcher des duels au début du jeu
                        Iterator<Entity> it = iterator();

                        while (it.hasNext()) {
                            Entity entity = it.next();

                            if (entity instanceof Player) {
                                Player p2 = (Player) entity;

                                if (p2.getScore() == p.getScore() && !(p.getName().equals(p2.getName()))) {
                                    // Créer méthode startVersus pour mettre les deux joueurs en état de versus
                                    // Quand les deux joueurs sont en duel, on va dans le thème duel et on fait pour
                                    // les deux joueurs une recherche de deux sujets puis demander le niveau de la question
                                    // D'abord le joueur p qui s'est déplacé répond puis l'autre p2 qui était déjà sur la case
                                    // Ensuite on compare les points de leurs questions
                                    // Si aucun des deux n'a bien répondu ou s'ils ont le même niveau de question bien répondu,
                                    // alors personne n'avance. Si un gagne, il avancera de la différence des points entre les deux
                                    // Cette méthode endVersusPlayer s'occupera de mettre l'attribut alreadyVersusInTurn à true
                                    p.endVersusPlayer();
                                    p2.endVersusPlayer();
                                }
                            }
                        }
                    }

                } else { // Tour du bot Frank
                    if (getNbTurn() >= 3) { // Vérifie quand Frank arrive sur le jeu
                        Bot frank = (Bot) e;
                        Random rand = new Random();
                        int point = rand.nextInt(2) + 1;

                        Tilde newTildeBot = gBoard.checkTilde(frank.getScore() + point);
                        frank.moveEntity(newTildeBot.getNumber(), newTildeBot.getPositionX(), newTildeBot.getPositionY());

                        Iterator<Entity> itBot = iterator();

                        while (itBot.hasNext()) {
                            Entity entity = itBot.next();

                            if (entity instanceof Player) {
                                Player p2 = (Player) entity;

                                if (p2.getScore() <= e.getScore()) {
                                    // Créer méthode startVersus pour mettre le joueur en état de versus
                                    // Quand le joueur est en duel contre Frank, on va dans le thème Frank et on fait pour
                                    // le joueur une recherche d'une question de niveau 2
                                    // int point = answerQuestion(questionForPlayer);
                                    // S'il répond correctement à la question, il avance de 2 points ; sinon il meurt
                                    int survie = 1;

                                    if (survie == 0) {
                                        // Soit on fait une méthode qui change l'état du joueur en Dead, soit
                                        p2.setEtat(new Dead(p2));
                                        playersDead.add(p2);
                                        entities.remove(p2);
                                    } else {
                                        Tilde newTildePlayer = gBoard.checkTilde(p2.getScore() + 2);
                                        p2.moveEntity(newTildePlayer.getNumber(), newTildePlayer.getPositionX(), newTildePlayer.getPositionY());

                                        // Cette méthode endVersusPlayer s'occupera de mettre l'attribut alreadyVersusInTurn à true
                                        p2.endVersusPlayer();
                                    }
                                }
                            }
                        }
                    }
                }

                // Ici faire quelque chose qui affiche les scores à chaque fin de tour (idée)
                nbTurn++;
            }
        }
    }

    /**
     * Ajoute une entité à la partie.
     *
     * @param entity entité à ajouter
     * @return vrai si l'ajout a réussi, sinon faux
     */
    public boolean addEntity(Entity entity) {
        return entities.add(entity);
    }

    /* Retourne un itérateur sur les entités de la partie */
    public Iterator<Entity> iterator() {
        return entities.iterator();
    }

    /**
     * Lit des données de jeu.
     *
     * @param s donnée à lire
     */
    public void readData(String s) {
    }

    /* Getter du nombre de tours */
    public int getNbTurn() {
        return nbTurn;
    }

    public GameBoard getGBoard() {
        return gBoard;
    }
}