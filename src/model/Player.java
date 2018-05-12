package model;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends GameObject implements Directable {
	private BufferedImage heroSprites;
	private BufferedImage hero;
	private int direction = SOUTH;
	private static int maxLife = 5;
	private int life = 5;
	private int Keys = 0;
	private int Potions = 0;
	private int Dinamites = 0;
	private boolean pistol = true;

	public Player(int X, int Y, HitBox hb) {
		super(X, Y, hb);
		try {
			this.heroSprites = ImageIO.read(getClass().getResource("/images/BigDaddy.png"));
		} catch (IOException e) {
			System.out.println("no Image");
		}

		hero = heroSprites.getSubimage(26 + this.direction * 60, 4, 54, 82);

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

	public void move(int x, int y) {
		if (x > 0)
			this.direction = EAST;
		if (y < 0)
			this.direction = NORTH;
		if (x < 0)
			this.direction = WEST;
		if (y > 0)
			this.direction = SOUTH;

		this.posX = this.posX + x;
		this.posY = this.posY + y;

		try {
			this.heroSprites = ImageIO.read(getClass().getResource("/images/BigDaddy.png"));
		} catch (IOException e) {
			System.out.println("no Image");
		}

		hero = heroSprites.getSubimage(26 + this.direction * 62, 4, 54, 82);

	}

	public void shortAttack() {
		hero = heroSprites.getSubimage(this.direction * 76, 106, 76, 82);
	}

	public void pickUpKey() {
		this.Keys += 1;
	}

	public boolean useKey() {
		if (Keys == 0) {
			return false;
		} else {
			this.Keys -= 1;
			return true;
		}
	}

	@Override
	public int getDirection() {
		return this.direction;
	}

	public BufferedImage getImage() {
		return this.hero;
	}

	public int getNumberOfKey() {
		return this.Keys;
	}

	public int getLife() {
		return this.life;
	}
	
	public int getMaxLife() {
		return Player.maxLife;
	}

	public void takePotion() {
		if (this.Potions > 0) {
			this.life++;
			this.Potions--;
		}
	}

	public void pickUpPotion() {
		this.Potions++;
	}
	
	public int getNumberOfPotion() {
		return this.Potions;
	}

	public void pickUp(int i) {
		if (i == 1) {
			this.pickUpKey();
		} else if (i == 2) {
			this.pickUpPotion();
		} else if (i == 3) {
			this.PickUpDinamite();
		}

	}

	public boolean throwDinamite() {
		if (Dinamites == 0) {
			System.out.println("Pas de dinamite");
			return false;
		} else {
			this.Dinamites--;
			
			return true;
		}

	}

	private void PickUpDinamite() {
		this.Dinamites++;
	}
	
	public int getNumberOfDinamite() {
		return this.Dinamites;
	}

	@Override
	public void activate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void activate(int dmg) {
		if (this.life > 0) {
			this.life -= dmg;
		}

	}

	public boolean shoot() {
		return pistol;
	}

}
