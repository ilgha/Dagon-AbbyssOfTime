package model;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Projectil extends GameObject implements Directable, Damage {
	boolean OnTarget = false;
	int direction;
	int target;
	private BufferedImage projectil;
	private BufferedImage projectilSprites;

	public Projectil(int X, int Y, int direction, HitBox hb) {
		super(X, Y, hb);
		this.direction = direction;

		try {
			this.projectil = ImageIO.read(getClass().getResource("/images/projectile_balle.png"));
		} catch (IOException e) {
			System.out.println("no Image");
		}
		/*if (this.direction == 0 || this.direction == 3) {
			projectil = projectilSprites.getSubimage(8, 0, 14, 14);
		} else {
			projectil = projectilSprites.getSubimage(0, 0, 7, 14);
		}
		*/
	}

	public void move(int s) {

		if (direction == 0) {
			this.posX = this.posX - s;

		}
		if (direction == 3) {
			this.posX = this.posX + s;

		}
		if (direction == 2) {

			this.posY = this.posY - s;
		}
		if (direction == 1) {

			this.posY = this.posY + s;
		}
	}

	public boolean isOnTarget() {
		return OnTarget;
	}

	@Override
	public void activate(int i) {
		OnTarget = true;
		this.notifyKillableObserver();

	}

	@Override
	public int getDirection() {

		return direction;
	}

	public BufferedImage getImage() {
		return projectil;

	}

	@Override
	public int getDamage() {
		return 1;
	}

	@Override
	public void activate() {
		OnTarget = true;
		this.notifyKillableObserver();

	}

}
