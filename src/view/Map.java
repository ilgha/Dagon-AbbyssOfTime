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

import model.Boss;
import model.Dinamite;
import model.DinamiteC;
import model.Door;
import model.Floor1;
import model.GameObject;
import model.Key;
import model.Player;
import model.Potion;
import model.Projectil;
import model.Rock;
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
		
		int bossWidth = this.getWidth() / 100 * 30;
		int bossHeight = this.getHeight() / 100*70;

		int rockWidth = this.getWidth() / 100 * 6;
		int rockHeight = this.getHeight() / 100 * 15;

		int projectilSize = this.getHeight() / 100 * 2;

		int itemWidth = this.getWidth() / 100 * 6;
		int itemHeight = this.getHeight() / 100 * 6;

		int doorWidth = this.getWidth() / 100 * 13;
		int doorHeight = this.getHeight() / 100 * 21;

		Room room = floor1.getCurrentRoom();
		ArrayList<Door> doors = room.getDoors();

		g.drawImage(room.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);

		for (Door door : doors) {
			if (door.getDirection() == 0) {
				door.setPosX(this.getWidth() / 100 * 94);
				door.setPosY(this.getHeight() / 100 * 50);
				g.drawImage(door.getImage(), door.getPosX() - doorWidth / 100 * 25,
						door.getPosY() - doorHeight / 100 * 50, doorWidth, doorHeight, null);

			}
			if (door.getDirection() == 1) {
				door.setPosX(this.getWidth() / 100 * 51);
				door.setPosY(this.getHeight() / 100 * 12);
				g.drawImage(door.getImage(), door.getPosX() - doorWidth / 100 * 50,
						door.getPosY() - doorHeight / 100 * 50, doorWidth, doorHeight, null);

			}
			if (door.getDirection() == 2) {
				door.setPosX(this.getWidth() / 100 * 51);
				door.setPosY(this.getHeight() / 100 * 86);
				g.drawImage(door.getImage(), door.getPosX() - doorWidth / 100 * 53,
						door.getPosY() - doorHeight / 100 * 20, doorWidth, doorHeight, null);

			}
			if (door.getDirection() == 3) {
				door.setPosX(this.getWidth() / 100 * 10);
				door.setPosY(this.getHeight() / 100 * 50);
				g.drawImage(door.getImage(), door.getPosX() - doorWidth / 100 * 50,
						door.getPosY() - doorHeight / 100 * 50, doorWidth, doorHeight, null);

			}
			g.setColor(Color.BLUE);
			g.drawRect(door.getPosX() - door.getHitBox().getDeltaX() / 2,
					door.getPosY() - door.getHitBox().getDeltaY() / 2, door.getHitBox().getDeltaX(),
					door.getHitBox().getDeltaY()); // hitbox porte

		}
		for (GameObject obj : objects) {
			//on gère la superposition des sprites avec ces if
			if (obj instanceof Opponent) {
				Opponent enemy = (Opponent) obj;
				g.drawImage(enemy.getImage(), enemy.getPosX() - playerWidth / 2, enemy.getPosY() - playerHeight / 2,
						playerWidth, playerHeight, null);

			}
			if (obj instanceof Rock) {
				Rock r = (Rock) obj;
				g.drawImage(r.getImage(), r.getPosX() - rockWidth /2, r.getPosY()- rockHeight/2, rockWidth, rockHeight, null);

			}
			if (obj instanceof Projectil) {
				Projectil proj = (Projectil) obj;
				g.drawImage(proj.getImage(), proj.getPosX() - projectilSize / 2, proj.getPosY() - projectilSize / 2,
						projectilSize, projectilSize, null);

			}
			if (obj instanceof Key) {
				Key k = (Key) obj;
				g.drawImage(k.getImage(), k.getPosX() - itemWidth / 2, k.getPosY() - itemWidth / 3, itemWidth,
						itemHeight, null);

			}
			if (obj instanceof Potion) {
				Potion p = (Potion) obj;
				g.drawImage(p.getImage(), p.getPosX() - itemWidth / 2, p.getPosY()- itemHeight / 2, itemWidth ,
						itemHeight, null);

			}

			if (obj instanceof DinamiteC) {

				DinamiteC d = (DinamiteC) obj;
				g.drawImage(d.getImage(), d.getPosX() - itemWidth / 2, d.getPosY()- itemHeight / 2, itemWidth ,
						itemHeight, null);

			}
			if (obj instanceof Dinamite) {

				Dinamite d = (Dinamite) obj;
				g.drawImage(d.getImage(), d.getPosX() - itemWidth / 2, d.getPosY() - itemHeight / 2, itemWidth,
						itemHeight, null);

			}
			if (obj instanceof Boss) {

                Boss b = (Boss) obj;
                g.drawImage(b.getImage(), b.getPosX() - bossWidth / 2, b.getPosY() - bossHeight / 2,
                		bossWidth, bossHeight, null);

            }
			if (obj instanceof Player) {
				Player player = (Player) obj;
				g.drawImage(player.getImage(), player.getPosX() - playerWidth / 2, player.getPosY() - playerHeight / 2,
						playerWidth, playerHeight, null);

			}
			g.setColor(Color.BLUE);
			g.drawRect(obj.getPosX() - obj.getHitBox().getDeltaX() / 2, obj.getPosY() - obj.getHitBox().getDeltaY() / 2,
					obj.getHitBox().getDeltaX(), obj.getHitBox().getDeltaY()); // hitbox des objets
		}
	}

	public void setObjects(ArrayList<GameObject> objects) {
		this.objects = objects;
	}

	public void setFloor(Floor1 floor1) {
		this.floor1 = floor1;
	}

}