package modele;

public class Tilde {

    //Attributs
    private static int nbInstances = 0;
    private int positionX, PositionY, number; // aucun des attributs ne peut être changer
    private String color; // Pas sur de celle-là;
    private Theme theme;


    public Tilde(int positionX, int positionY, String color, Theme theme) {
        this.positionX = positionX;
        this.PositionY = positionY;
        this.color = color;
        this.theme = theme;
        number = ++nbInstances;
    }


    public int getPositionX() {
        return positionX;
    }

    public int getNumber() {
        return number;
    }

    public int getPositionY() {
        return PositionY;
    }

    public String getColor() {
        return color;
    }

    public Theme getTheme() {
        return theme;
    }
}
