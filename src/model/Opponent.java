package model;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Opponent extends GameObject implements Killable, Directable {

	private int lifePoints = 2;
	private BufferedImage enemy;
	private BufferedImage enemySprites;
	private int direction = SOUTH;
	private boolean Key = false;

	public Opponent(int X, int Y, HitBox hb) {

		super(X, Y, hb, 1);
		try {
			this.enemySprites = ImageIO.read(getClass().getResource("/images/BigDaddy.png"));
		} catch (IOException e) {
			System.out.println("no Image");
		}

		enemy = enemySprites.getSubimage(26 + this.direction * 60, 4, 54, 82);

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

		try {
			this.enemySprites = ImageIO.read(getClass().getResource("/images/BigDaddy.png"));
		} catch (IOException e) {
			System.out.println("no Image");
		}

		enemy = enemySprites.getSubimage(26 + this.direction * 62, 4, 54, 82);

	}

	public int getDmg() {
		return 1;

	}

	public void setKey() {
		Key = true;
	}

	public boolean hasKey() {

		return Key;
	}

	public void activate(int dmg) {
		this.lifePoints -= dmg;

		if (lifePoints <= 0) {
			this.notifyKillableObserver();
		}

	}

	public int getDirection() {
		// TODO Auto-generated method stub
		return this.direction;
	}

	public BufferedImage getImage() {
		return this.enemy;
	}

	@Override
	public void activate() {
		// TODO Auto-generated method stub

	}

}
