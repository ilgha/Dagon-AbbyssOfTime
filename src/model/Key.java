package model;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Key extends Consomables {
	private BufferedImage keySprites;
	private BufferedImage key;

	public Key(int X, int Y, HitBox hb) {
		super(X, Y, hb);
		this.Type = 1;
		try {
			this.keySprites = ImageIO.read(getClass().getResource("/images/Key.png"));
		} catch (IOException e) {
			System.out.println("no Image");
		}

		key = keySprites;

	}

	@Override
	public BufferedImage getImage() {
		return key;
	}

	@Override
	public void activate(int dmg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void activate() {
		this.notifyKillableObserver();

	}

}
