package modele.entity;

public class Bot extends Entity {


    //Constructeur
    Bot(int score, String name, int positionX, int positionY){
        super(score,name,positionX,positionY);
    }


    @Override
    public void moveEntity(int score, int x, int y) {
        setScore(score);
        setPositionX(x);
        setPositionY(y);
    }

    @Override
    public void nextTurn() {

    }
}
