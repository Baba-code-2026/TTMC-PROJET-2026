package modele;

public abstract class StatePlayer {

    //Attributs
    private Player player;

    // Constructeur
    StatePlayer(Player player){
        this.player = player;
    }

    // Méthode abstraite
    public abstract void endTurn();
    public abstract  void movePlayer(int score, int x, int y);
    public abstract void endVersus();


    public Player getPlayer() {
        return player;
    }
}
