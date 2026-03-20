package models.entity;

public class Bot extends Entity {


    //Constructeur
    Bot(int score, String name, int positionX, int positionY){
        super(score,name,positionX,positionY);
    }


    @Override
    public void moveEntity(int numberCase, int x, int y) {
        setScore(getScore() + numberCase);
        setPositionX(x);
        setPositionY(y);
    }

    @Override
    public void nextTurn() {
        //Pas besoin
    }
}
