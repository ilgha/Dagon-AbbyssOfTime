package view;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.Player;

public class LifeBar {
	BufferedImage lifeSprite;
	BufferedImage life;

	public LifeBar(Player player) {
		try {
			this.lifeSprite = ImageIO.read(getClass().getResource("/images/LifeBar.png"));
		} catch (IOException e) {
			System.out.println("no Image");
		}
		this.life = lifeSprite.getSubimage(21 * player.getLife(), 0, 21, 137);
	}

	public BufferedImage getLife() {
		return this.life;
	}

}
