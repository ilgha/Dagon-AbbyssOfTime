package model;

import java.awt.image.BufferedImage;

public abstract class GameObject implements Killable {
	protected int posX;
    protected int posY;
    protected int type;
    protected HitBox hitbox;
    private KillableObserver Ko;


    public GameObject(int X, int Y, HitBox hb, int type) {
        this.posX = X;
        this.posY = Y;
        this.hitbox = hb;
        this.type = type;
    }
    
    public abstract void activate();
    public abstract void activate(int i);
    
    public abstract BufferedImage getImage(); 


    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }
    public HitBox getHitBox() {
    	return this.hitbox;
    }
    
    public int getType() {
        return this.type;
    }
    public void setPosX(int x) {
        this.posX = x;
    }

    public void setPosY(int y) {
        this.posY = y;
    }
    public boolean isAtPosition(GameObject go) {
    	int deltXmax = Math.abs(this.hitbox.getDeltaX()+go.getHitBox().getDeltaX());  //retourne si la distance entre les deux centre <= les dimensions des deux hitbox
    	int deltYmax = Math.abs(this.hitbox.getDeltaY()+go.getHitBox().getDeltaY());
    	int deltCenterX = Math.abs(this.posX-go.getPosX());
    	int deltCenterY = Math.abs(this.posY-go.getPosY());
        return deltCenterX <= deltXmax && deltCenterY <= deltYmax;
    }


	public void attachKillableObserver(KillableObserver Ko) {
		this.Ko=Ko;
		
	}


	public void notifyKillableObserver() {
		Ko.kill(this);
		
	}
}
