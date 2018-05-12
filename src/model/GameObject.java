package model;

import java.awt.image.BufferedImage;

public abstract class GameObject implements Killable {
	protected int posX;
	protected int posY;
	protected HitBox hitbox;
	private KillableObserver Ko;

	public GameObject(int X, int Y, HitBox hb) {
		this.posX = X;
		this.posY = Y;
		this.hitbox = hb;
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

	public void setPosX(int x) {
		this.posX = x;
	}

	public void setPosY(int y) {
		this.posY = y;
	}
	
	public void setHitBox(HitBox hb) {
		this.hitbox = hb;
	}

	public boolean isAtPosition(GameObject go) {
		int deltXmax = Math.abs(this.hitbox.getDeltaX()/2 + go.getHitBox().getDeltaX()/2); // retourne si la distance entre
																						// les deux centre <= les
																						// dimensions des deux hitbox
		int deltYmax = Math.abs(this.hitbox.getDeltaY()/2 + go.getHitBox().getDeltaY()/2);
		int deltCenterX = Math.abs(this.posX - go.getPosX());
		int deltCenterY = Math.abs(this.posY - go.getPosY());
		return deltCenterX <= deltXmax && deltCenterY <= deltYmax;
	}
	
	public boolean isAtNextPosition(GameObject go,float x,float y) {
        int deltXmax = Math.abs(this.hitbox.getDeltaX()/2 + go.getHitBox().getDeltaX()/2); // retourne si la distance entre
                                                                                        // les deux centre <= les
                                                                                        // dimensions des deux hitbox
        int deltYmax = Math.abs(this.hitbox.getDeltaY()/2 + go.getHitBox().getDeltaY()/2);
        int deltCenterX = (int) Math.abs(this.posX - (go.getPosX()+x));
        int deltCenterY = (int) Math.abs(this.posY - (go.getPosY()+y));

        return deltCenterX <= deltXmax && deltCenterY <= deltYmax;
    }

	public void attachKillableObserver(KillableObserver Ko) {
		this.Ko = Ko;

	}

	public void notifyKillableObserver() {
		Ko.kill(this);

	}
}
