package model;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Potion extends Consomables {
	private BufferedImage potionSprites;
	private BufferedImage potion;

	public Potion(int X, int Y, HitBox hb) {
		super(X, Y, hb);
		this.Type = 2;

		try {
			this.potionSprites = ImageIO.read(getClass().getResource("/images/potion.png"));
		} catch (IOException e) {
			System.out.println("no Image");
		}

		potion = potionSprites.getSubimage(0, 0, 32, 32);
	}

	@Override
	public BufferedImage getImage() {
		return potion;
	}

}
