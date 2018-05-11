package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.Dinamite;
import model.Door;
import model.Floor1;
import model.GameObject;
import model.Key;
import model.Player;
import model.Potion;
import model.Projectil;
import model.Room;
import model.Opponent;

public class Map extends JPanel {
	private Floor1 floor1;
	private ArrayList<GameObject> objects = null;

	public Map() {
		this.setFocusable(true);
		this.requestFocusInWindow();
	}

	public void paintComponent(Graphics g) {

		int playerWidth = this.getWidth() / 100 * 6;
		int playerHeight = this.getHeight() / 100 * 15;
		int playerCenterX = this.getWidth() / 2 - playerWidth / 2;
		int playerCenterY = this.getHeight() / 2 - playerHeight / 2;

		int doorWidth = this.getWidth() / 100 * 13;
		int doorHeight = this.getHeight() / 100 * 21;
		int doorCenterX = -this.getWidth() / 2 + doorWidth / 2;
		int doorCenterY = -this.getHeight() / 2 + doorHeight / 2;

		Room room = floor1.getCurrentRoom();
		ArrayList<Door> doors = room.getDoors();

		g.drawImage(room.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);

		for (Door door : doors) {
			if (door.getDirection() == 0) {
				door.setPosX(this.getWidth() / 100 * 94);
				door.setPosY(this.getHeight() / 100 * 50);
				g.drawImage(door.getImage(), door.getPosX() - doorWidth / 100 * 25,
						door.getPosY() - doorHeight / 100 * 50, doorWidth, doorHeight, null);
				g.setColor(Color.BLUE);
				g.drawRect(door.getPosX() - door.getHitBox().getDeltaX() / 2,
						door.getPosY() - door.getHitBox().getDeltaY() / 2, door.getHitBox().getDeltaX(),
						door.getHitBox().getDeltaY()); // hitbox porte
			}
			if (door.getDirection() == 1) {
				door.setPosX(this.getWidth() / 100 * 52);
				door.setPosY(this.getHeight() / 100 * 12);
				g.drawImage(door.getImage(), door.getPosX() - doorWidth / 100 * 50,
						door.getPosY() - doorHeight / 100 * 50, doorWidth, doorHeight, null);
				g.setColor(Color.BLUE);
				g.drawRect(door.getPosX() - door.getHitBox().getDeltaX() / 2,
						door.getPosY() - door.getHitBox().getDeltaY() / 2, door.getHitBox().getDeltaX(),
						door.getHitBox().getDeltaY()); // hitbox porte
			}
			if (door.getDirection() == 2) {
				door.setPosX(this.getWidth() / 100 * 52);
				door.setPosY(this.getHeight() / 100 * 86);
				g.drawImage(door.getImage(), door.getPosX() - doorWidth / 100 * 53,
						door.getPosY() - doorHeight / 100 * 20, doorWidth, doorHeight, null);
				g.setColor(Color.BLUE);
				g.drawRect(door.getPosX() - door.getHitBox().getDeltaX() / 2,
						door.getPosY() - door.getHitBox().getDeltaY() / 2, door.getHitBox().getDeltaX(),
						door.getHitBox().getDeltaY()); // hitbox porte
			}
			if (door.getDirection() == 3) {
				door.setPosX(this.getWidth() / 100 * 10);
				door.setPosY(this.getHeight() / 100 * 50);
				g.drawImage(door.getImage(), door.getPosX() - doorWidth / 100 * 50,
						door.getPosY() - doorHeight / 100 * 50, doorWidth, doorHeight, null);
				g.setColor(Color.BLUE);
				g.drawRect(door.getPosX() - door.getHitBox().getDeltaX() / 2,
						door.getPosY() - door.getHitBox().getDeltaY() / 2, door.getHitBox().getDeltaX(),
						door.getHitBox().getDeltaY()); // hitbox porte
			}
			
		}
		for (GameObject obj : objects) {
			if (obj instanceof Opponent) {
				Opponent enemy = (Opponent) obj;
				g.drawImage(enemy.getImage(), enemy.getPosX() - playerWidth / 2, enemy.getPosY() - playerHeight / 2,
						this.getWidth() / 100 * 6, this.getHeight() / 100 * 15, null);
				g.setColor(Color.BLUE);
				g.drawRect(enemy.getPosX() - enemy.getHitBox().getDeltaX() / 2,
						enemy.getPosY() - enemy.getHitBox().getDeltaY() / 2, enemy.getHitBox().getDeltaX(),
						enemy.getHitBox().getDeltaY()); // hitbox du player

			}
			if (obj instanceof Projectil) {
				Projectil proj = (Projectil) obj;
				g.drawImage(proj.getImage(), proj.getPosX() , proj.getPosY() ,
						this.getWidth() / 100 * 1, this.getHeight() / 100 * 1, null);
				g.setColor(Color.BLUE);
				g.drawRect(proj.getPosX() , proj.getPosY() ,
						proj.getHitBox().getDeltaX() , proj.getHitBox().getDeltaY() ); // hitbox du player

			}
			if (obj instanceof Key) {
				Key k = (Key) obj;
				g.drawImage(k.getImage(), k.getPosX(), k.getPosY(), this.getWidth() / 100 * 6,
						this.getHeight() / 100 * 6, null);
				g.setColor(Color.BLUE);
				g.drawRect(k.getPosX() + k.getHitBox().getDeltaX() * 2, k.getPosY(), k.getHitBox().getDeltaX() * 2,
						k.getHitBox().getDeltaY() * 2);
			}
			if (obj instanceof Potion) {
				Potion p = (Potion) obj;
				g.drawImage(p.getImage(), p.getPosX(), p.getPosY(), this.getWidth() / 100 * 6,
						this.getHeight() / 100 * 6, null);
				g.setColor(Color.BLUE);
				g.drawRect(p.getPosX() + p.getHitBox().getDeltaX() * 2, p.getPosY(), p.getHitBox().getDeltaX() * 2,
						p.getHitBox().getDeltaY() * 2);
			}

			if (obj instanceof Dinamite) {

				Dinamite d = (Dinamite) obj;
				g.drawImage(d.getImage(), d.getPosX(), d.getPosY(), this.getWidth() / 100 * 3,
						this.getHeight() / 100 * 3, null);
				g.setColor(Color.BLUE);
				g.drawRect(d.getPosX() + d.getHitBox().getDeltaX() * 2, d.getPosY(), d.getHitBox().getDeltaX() * 2,
						d.getHitBox().getDeltaY() * 2);
			}
			if (obj instanceof Player) {
				Player player = (Player) obj;
				g.drawImage(player.getImage(), player.getPosX() - playerWidth / 2, player.getPosY() - playerHeight / 2,
						this.getWidth() / 100 * 6, this.getHeight() / 100 * 15, this);
				g.setColor(Color.BLUE);
				g.drawRect(player.getPosX() - player.getHitBox().getDeltaX() / 2,
						player.getPosY() - player.getHitBox().getDeltaY() / 2, player.getHitBox().getDeltaX(),
						player.getHitBox().getDeltaY()); // hitbox du player
			}
		}
	}

	public void setObjects(ArrayList<GameObject> objects) {
		this.objects = objects;
	}

	public void setFloor(Floor1 floor1) {
		this.floor1 = floor1;
	}

}