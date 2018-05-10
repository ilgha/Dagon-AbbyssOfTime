package model;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Dinamite extends GameObject {
	public int timer = 2 * 1000;
	public long tictacOn;
	private BufferedImage Dinamite;
	private BufferedImage DinamiteSprites;

	public Dinamite(int X, int Y, HitBox hb, int type, long tictacon) {
		super(X, Y, hb);

		this.tictacOn = tictacon;
		try {
			this.DinamiteSprites = ImageIO.read(getClass().getResource("/images/Bombe.png"));
		} catch (IOException e) {
			System.out.println("no Image");
		}
		Dinamite = DinamiteSprites.getSubimage(0, 0, 25, 20);
	}

	public void activate() {
		this.notifyKillableObserver();
		System.out.println("Boom");

	}

	public void activate(int dmg) {
		this.notifyKillableObserver();
		System.out.println("Boom");

	}

	public int getTimer() {
		return timer;
	}

	public long getTictacOn() {

		return tictacOn;
	}

	public BufferedImage getImage() {
		return this.Dinamite;
	}

}
