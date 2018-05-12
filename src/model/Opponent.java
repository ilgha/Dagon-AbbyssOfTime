package model;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Opponent extends GameObject implements Killable, Directable,ContientConsomable {

	private int lifePoints = 2;
	private BufferedImage crawler;
	private int direction = SOUTH;
	private boolean Key = false;
	private int consomable = 0;
	public Opponent(int X, int Y, HitBox hb) {

		super(X, Y, hb);
		try {
			this.crawler = ImageIO.read(getClass().getResource("/images/Crawler.png"));
		} catch (IOException e) {
			System.out.println("no Image");
		}



	}

	public void move(float x, float y, int speed) {
		
		if (y > 0 && y>Math.abs(x))
			this.direction = SOUTH;
		if (x > 0 && x>Math.abs(y))
			this.direction = EAST;
		if (y < 0 && Math.abs(y)> Math.abs(x))
			this.direction = NORTH;
		if (x < 0 && Math.abs(y) < Math.abs(x))
			this.direction = WEST;
		
		System.out.println(this.direction);
		this.posX = this.posX + (int) (x * speed);
		this.posY = this.posY + (int) (y * speed);


	}

	public int getDmg() {
		return 1;

	}

	public void activate(int dmg) {
		this.lifePoints -= dmg;

		if (lifePoints <= 0) {
			this.notifyKillableObserver();
		}

	}

	public int getDirection() {
		return this.direction;
	}

	public BufferedImage getImage() {
		return this.crawler;
	}

	@Override
	public void activate() {


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

		public void setHitbox(HitBox hitBox) {
			this.hitbox = hitBox;
			
		}

}
