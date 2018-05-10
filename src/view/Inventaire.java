package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.Player;

public class Inventaire extends JPanel {
	private BufferedImage key;
	private BufferedImage DinamiteSprites;
	private BufferedImage bomb;
	private Player player;
	private LifeBar lb;

	public void paintComponent(Graphics g) {
		try {
			this.key = ImageIO.read(getClass().getResource("/images/Key.png"));
		} catch (IOException e) {
			System.out.println("no Image");
		}

		try {
			this.DinamiteSprites = ImageIO.read(getClass().getResource("/images/Bombe.png"));
		} catch (IOException e) {
			System.out.println("no Image");
		}
		this.bomb = DinamiteSprites.getSubimage(0, 0, 25, 20);

		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		this.lb = new LifeBar(this.player);
		g.drawImage(this.lb.getLife(), this.getHeight() / 100 * 3, this.getHeight() / 100 * 10,
				this.getWidth() / 100 * 50, this.getHeight() / 100 * 60, null);

		g.drawImage(key, 0, this.getHeight() / 100 * 74, this.getWidth() / 100 * 50, this.getHeight() / 100 * 10, null);
		Font font = new Font("Courier", Font.BOLD, this.getHeight() / 100 * 5);

		g.setFont(font);
		g.setColor(Color.white);
		g.drawString(" x " + this.player.getNumberOfKey(), this.getWidth() / 100 * 32, this.getHeight() / 100 * 80);

		g.drawImage(bomb, 0, this.getHeight() / 100 * 85, this.getWidth() / 100 * 50,
		this.getHeight() / 100 * 10, null);

		g.setFont(font);
		g.setColor(Color.white);
		g.drawString(" x " + this.player.getNumberOfKey(), this.getWidth() / 100 * 32, this.getHeight() / 100 * 92);
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}
