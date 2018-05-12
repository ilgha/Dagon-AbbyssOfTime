package model;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Rock extends GameObject implements ContientConsomable  {

	private BufferedImage Rock;
	private BufferedImage RockSprites;
	private int consomable = 0;
	
	public Rock(int X, int Y, HitBox hb) {
		super(X, Y, hb);
		
		try {
			this.RockSprites = ImageIO.read(getClass().getResource("/images/Rock.png"));
		} catch (IOException e) {
			System.out.println("no Image");
		}

		Rock = RockSprites;

	}
	

	
	public void setConsomable(int i) {
		this.consomable=i;
		
	}

	public boolean hasConsomable() {
        if (consomable==0) {
            return false;
        }else {
            return true;
        }

    }
        public int getConsomable() {
            return consomable;
        }

	
	public void activate() {
		this.notifyKillableObserver();
		
	}


	public void activate(int i) {
		this.notifyKillableObserver();
		
	}


	public BufferedImage getImage() {
		return Rock;
	}

}

