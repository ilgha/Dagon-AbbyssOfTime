package model;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DinamiteC extends Consomables  {
	private BufferedImage DinamiteSprites;
	private BufferedImage Dinamite;

	public DinamiteC(int X, int Y, HitBox hb) {
		super(X, Y, hb);

		
		try {
			this.DinamiteSprites = ImageIO.read(getClass().getResource("/images/Bombe.png"));
		} catch (IOException e) {
			System.out.println("no Image");
		}
		Dinamite = DinamiteSprites.getSubimage(0, 0, 25, 20);
	}
		
	public BufferedImage getImage() {
		return Dinamite;
	}

}
	

