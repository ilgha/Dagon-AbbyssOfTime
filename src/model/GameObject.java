package model;

import java.awt.image.BufferedImage;

public class GameObject implements Image {
	protected int posX;
    protected int posY;
    protected int type;
    

    public GameObject(int X, int Y, int type) {
        this.posX = X;
        this.posY = Y;
        this.type = type;
        
    }

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }
    
    public int getType() {
        return this.type;
    }

	@Override
	public BufferedImage getImage() {
		return null;
	}
}
