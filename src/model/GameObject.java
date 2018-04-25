package model;

public class GameObject {
	protected int posX;
    protected int posY;
    

    public GameObject(int X, int Y) {
        this.posX = X;
        this.posY = Y;
        
    }

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

}
