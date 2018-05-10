package model;

import java.awt.image.BufferedImage;

public abstract class Consomables extends GameObject {

	public int Type;
	

	public Consomables(int x, int y, HitBox hb) {
		super(x,y,hb);
	}

	@Override
	public void activate() {
		this.notifyKillableObserver();
	}

	@Override
	public void activate(int i) {
		this.notifyKillableObserver();
	}
	
	public int getType() {
        return this.Type;
    }


	@Override
	public abstract BufferedImage getImage();
}
