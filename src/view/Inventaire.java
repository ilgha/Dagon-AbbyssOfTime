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
	private BufferedImage potionSprites;
	private BufferedImage potion;
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
		try {
			this.potionSprites = ImageIO.read(getClass().getResource("/images/potion.png"));
		} catch (IOException e) {
			System.out.println("no Image");
		}
		this.potion = potionSprites.getSubimage(0, 0, 32, 32);
		
		//Fond Noir
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		//Barre de vie
		this.lb = new LifeBar(this.player);
		g.drawImage(this.lb.getLife(), this.getHeight() / 100 * 6, this.getHeight() / 100 * 5,
				this.getWidth() / 100 * 50, this.getHeight() / 100 * 60, null);
		//Clés
		g.drawImage(key, 0, this.getHeight() / 100 * 69, this.getWidth() / 100 * 50, this.getHeight() / 100 * 10, null);
		Font font = new Font("Courier", Font.BOLD, this.getHeight() / 100 * 5);

		g.setFont(font);
		g.setColor(Color.white);
		g.drawString(" x " + this.player.getNumberOfKey(), this.getWidth() / 100 * 32, this.getHeight() / 100 * 75);
		
		//Dinamites
		g.drawImage(bomb, 0, this.getHeight() / 100 * 79, this.getWidth() / 100 * 50,
		this.getHeight() / 100 * 10, null);

		g.setFont(font);
		g.setColor(Color.white);
		g.drawString(" x " + this.player.getNumberOfDinamite(), this.getWidth() / 100 * 32, this.getHeight() / 100 * 86);
		
		//Potions
		g.drawImage(potion, 0, this.getHeight() / 100 * 90, this.getWidth() / 100 * 50,
				this.getHeight() / 100 * 10, null);

				g.setFont(font);
				g.setColor(Color.white);
				g.drawString(" x " + this.player.getNumberOfPotion(), this.getWidth() / 100 * 32, this.getHeight() / 100 * 98);
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}
