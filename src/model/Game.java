package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import Threads.ThreadDinamite;
import Threads.ThreadEnemys;
import Threads.ThreadProj;
import model.Player;
import view.Window;
import model.GameObject;

public class Game implements KillableObserver {
	private Window window;
	private Player player;
	private Floor1 floor1;
	private int delta;
	private int x;
	private int y;
	private HitBox hb;
	private HitBox hbKey;
	private HitBox hbDoor;
	private HitBox hbRock;
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();

	public Game(Window window) {
		this.window = window;
		int playerCenterX = this.window.getMapWidth() / 2;
		int playerCenterY = this.window.getMapHeight() / 2;
		this.delta = this.window.getMapWidth() / 100 * 3;
		this.hb = new HitBox(window.getMapWidth() / 100 * 3, window.getMapHeight() / 100 * 6);
		this.hbKey = new HitBox(window.getMapWidth() / 100 * 2, window.getMapHeight() / 100 * 4);
		this.hbDoor = new HitBox(window.getMapWidth() / 100 * 5, window.getMapHeight() / 100 * 5);
		this.hbRock = new HitBox(window.getMapWidth() / 100 * 5, window.getMapHeight() / 100 * 5);

		Player player = new Player(playerCenterX, playerCenterY, hb);
		this.player = player;
		this.objects.add(player);
		this.floor1 = new Floor1(this.window.getMapWidth(), this.window.getMapHeight(), this.hbDoor);
		Timer timer = new Timer(33, new ActionListener() {
			 
			@Override
			public void actionPerformed(ActionEvent e) {
				animatePlayer();
			}
		});
		timer.start();
		window.setObjects(this.objects);
		window.setFloor(this.floor1);
		window.setPlayer(this.player);

	}
	
	public void movePlayer(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void nextRoom(Door door, Room room) {

		if (door.getDirection() == 0) {
			player.setPosX(window.getMapWidth() / 100 * 15);
			player.setPosY(door.getPosY());
		}
		if (door.getDirection() == 1) {
			player.setPosX(door.getPosX());
			player.setPosY(window.getMapHeight() / 100 * 80);
		}
		if (door.getDirection() == 2) {
			player.setPosX(door.getPosX());
			player.setPosY(window.getMapHeight() / 100 * 20);
		}
		if (door.getDirection() == 3) {
			player.setPosX(window.getMapWidth() - door.getPosX() - player.getHitBox().getDeltaX() * 12);
			player.setPosY(door.getPosY());
		}
		this.floor1.nextRoom(room, door);
		System.out.println("salle " + ((this.floor1.getRooms().indexOf(this.floor1.getCurrentRoom())) + 1));

	}

	public void animatePlayer() {

		ArrayList<Integer> toActivate = new ArrayList<Integer>();

		for (int i = 0; i < objects.size(); i++) {
			GameObject obj = objects.get(i);
			if (objects.get(i) instanceof Consomables) {
				if (this.player.isAtPosition(obj)) {
					System.out.println("sur clé");
					int TypeDeConsomable = ((Consomables) objects.get(i)).getType();
					toActivate.add(i);
					this.player.pickUp(TypeDeConsomable);
				}
			}
		}
		for (int i : toActivate) {
			objects.get(i).activate();
		}
		// Verifier si contre le mur
		if (hitWall(player, x, y)) {
			player.move(0, 0);
		} else if (hitRock(player, x, y)) {
			player.move(0, 0);
		} else {
			player.move(x, y);
		}
		// Verifier si contre une porte
		boolean changedRoom = false;

		Room room = this.floor1.getCurrentRoom();
		for (Door door : room.getDoors()) {

			if (door.isAtPosition(this.player)) {
				if (door.isOpen()) {
					this.nextRoom(door, room);
					changedRoom = true;
				} else {
					if (door.getType() == 1) {
						if (this.enemysEmpty()) {
							this.nextRoom(door, room);
							changedRoom = true;
							System.out.println("ok");
							door.activate();
						} else {
							System.out.println("Kill everyone");
						}
					}
					if (door.getType() == 2) {
						if (this.player.useKey()) {
							this.nextRoom(door, room);
							changedRoom = true;
							door.activate();
						} else {
							System.out.println("it's Locked, look for a key !");
						}

					}
				}

			}
		}

		// Verifier si changé de room
		if (changedRoom) {
			room = this.floor1.getCurrentRoom();
			this.objects = room.getObjects();
			objects.add(this.player);

			this.prepareObjects();

			Thread t1 = new Thread(new ThreadEnemys(100, this));
			t1.start();
			System.out.println("thread start");
		}

		window.setObjects(objects);
		window.update();

	}

	private void prepareObjects() {
		for (GameObject obj : objects) {
			obj.attachKillableObserver(this);
			if (obj instanceof Opponent) {
				Opponent o = (Opponent) obj;
				o.setHitbox(hb);
			}
			if (obj instanceof Rock) {
				Rock r = (Rock) obj;
				r.setHitBox(hb);
			}

		}

	}

	public synchronized void moveEnemy() {

		// calcul du target;

		for (GameObject obj : objects) {

			if (obj instanceof Opponent) {
				Opponent o = (Opponent) obj;
				int diffX = player.getPosX() - o.getPosX();
				int diffY = player.getPosY() - o.getPosY();
				if (o.isAtPosition(this.player)) {
					this.player.activate(o.getDmg());
					if (this.player.getLife() == 0)
						this.gameOver();
					if (o.getDirection() == 0)
						this.movePlayer(-this.delta, 0);
					if (o.getDirection() == 1)
						this.movePlayer(0, this.delta);
					if (o.getDirection() == 2)
						this.movePlayer(0, -this.delta);
					if (o.getDirection() == 3)
						this.movePlayer(this.delta, 0);
				} else {
					float angle = (float) Math.atan2(diffY, diffX);

					float x = (float) Math.cos(angle);
					float y = (float) Math.sin(angle);
					float d = distanceInBetween(o, this.player);
					if (hitWall(o, x * 3, y * 3)) {
						o.move(0, 0, 0);
					} else if (hitRock(o, x * 3, y * 3)) {
						int direction = o.getDirection();
						if (direction == 0 || direction == 3) {
							o.move(0, 1, 4);
						} else {
							o.move(1, 0, 4);

						}
					} else if (d <= this.window.getMapWidth() / 100 * 30) {
						o.move(x, y, 3);
					}
				}

				window.update();
			}
		}

	}

	public synchronized void shoot(int dir) {
		if (player.shoot()) {
			Projectil p = new Projectil(player.getPosX(), player.getPosY(), dir,
					new HitBox(window.getMapWidth() / 160 * 1, window.getMapWidth() / 160 * 1));
			p.attachKillableObserver(this);
			objects.add(p);
			window.setObjects(this.objects);

			if (this.projectilesNumber() == 1) {
				Thread t2 = new Thread(new ThreadProj(100, this));
				t2.start();
			}
		}

		window.setObjects(objects);

	}

	public synchronized void moveProjectil() {

		ArrayList<Integer> toActivate = new ArrayList<Integer>();

		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i) instanceof Projectil) {
				Projectil p = (Projectil) objects.get(i);
				for (int j = 0; j < objects.size(); j++) {
					if (objects.get(j) instanceof Opponent) {

						Opponent o = (Opponent) objects.get(j);

						if (p.isAtPosition(o)) {
							toActivate.add(i);
							toActivate.add(j);
							if (p.getDirection() == 0)
								o.move(-this.delta, 0, 1);
							if (p.getDirection() == 1)
								o.move(0, this.delta, 1);
							if (p.getDirection() == 2)
								o.move(0, -this.delta, 1);
							if (p.getDirection() == 3)
								o.move(this.delta, 0, 1);

						}
					}

				}

				for (int j = 0; j < objects.size(); j++) {
					if (objects.get(j) instanceof Rock) {
						Rock r = (Rock) objects.get(j);
						if (p.isAtPosition(r)) {
							toActivate.add(i);
						}

					}
				}

				if (hitWall(p)) {
					toActivate.add(i);

				}
				p.move(10);

			}

		}

		for (int i = 0; i < toActivate.size(); i++) {
			int j = toActivate.get(i);
			objects.get(j).activate(1);

		}
		window.setObjects(objects);
	}

	public void shortAttack() {
		this.player.shortAttack();
		this.player.setHitBox(new HitBox(window.getMapHeight() / 100 * 4, window.getMapWidth() / 100 * 6));
		for (GameObject obj : objects) {
			if (obj instanceof ContientConsomable) {
				GameObject o = (GameObject) obj;
				if (player.isAtPosition(o)) {
					o.activate(5);
				}
			}
		}
		this.player.setHitBox(hb);
		window.update();
	}

	public void usePotion() {
		if (this.player.getLife() < this.player.getMaxLife()) {
			this.player.takePotion();
		}
		window.update();
	}

	public int DinamiteNumber() {
		int i = 0;
		for (GameObject obj : objects) {
			if (obj instanceof Dinamite)
				i++;
		}
		return i;
	}

	public void CheckDinamites() {

		ArrayList<Integer> toActivate = new ArrayList<Integer>();

		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i) instanceof Dinamite) {
				Dinamite d = (Dinamite) objects.get(i);
				if (System.currentTimeMillis() - d.getTictacOn() > d.getTimer()) {
					toActivate.add(i);
					System.out.println("in");
					for (int j = 0; j < objects.size(); j++) {
						if (objects.get(j) instanceof Opponent) {
							Opponent o = (Opponent) objects.get(j);
							if (distanceInBetween(o, d) < 20) {
								toActivate.add(j);

							}
						}
					}
				}
			}
		}

		for (int l : toActivate) {
			GameObject e = objects.get(l);
			e.activate(5);

		}
		window.setObjects(objects);

	}

	public synchronized void throwDinamaite() {
		if (player.throwDinamite()) {
			Dinamite d = new Dinamite(player.getPosX(), player.getPosY(), this.hbKey, 3, System.currentTimeMillis());
			d.attachKillableObserver(this);
			objects.add(d);
		}

		if (this.DinamiteNumber() == 1) {
			Thread t3 = new Thread(new ThreadDinamite(100, this));
			t3.start();
		}

		window.setObjects(objects);
	}

	public void kill(Killable K) {

		objects.remove(K);

		if (K instanceof ContientConsomable) {
			ContientConsomable o = (ContientConsomable) K;

			if (o.hasConsomable()) {
				if (o.getConsomable() == 1) {
					Key k1 = new Key(((GameObject) o).getPosX(), ((GameObject) o).getPosY(), hbKey);
					k1.attachKillableObserver(this);
					objects.add(k1);
				} else if (o.getConsomable() == 2) {
					System.out.println("in");
					Potion p1 = new Potion(((GameObject) o).getPosX(), ((GameObject) o).getPosY(), hbKey);
					p1.attachKillableObserver(this);
					objects.add(p1);
				} else if (o.getConsomable() == 3) {
					DinamiteC d = new DinamiteC(((GameObject) o).getPosX(), ((GameObject) o).getPosY(), hbKey);
					d.attachKillableObserver(this);
					objects.add(d);
				}
			}

			window.setObjects(this.objects);

		}

	}

	public float distanceInBetween(GameObject g1, GameObject g2) {
		float d;
		d = (float) Math.sqrt(Math.pow(g1.getPosX() - g2.getPosX(), 2) + Math.pow(g1.getPosY() - g2.getPosY(), 2));
		return d;

	}

	public boolean hitWall(GameObject obj, float f, float g) {
		return obj.getPosX() + f < this.window.getMapWidth() / 100 * 10
				|| obj.getPosX() + f > this.window.getMapWidth() / 100 * 93
				|| obj.getPosY() + g < this.window.getMapHeight() / 100 * 13
				|| obj.getPosY() + g > this.window.getMapHeight() / 100 * 84;
	}

	public boolean hitWall(GameObject obj) {
		return obj.getPosX() < this.window.getMapWidth() / 100 * 6
				|| obj.getPosX() > this.window.getMapWidth() / 100 * 94
				|| obj.getPosY() < this.window.getMapHeight() / 100 * 10
				|| obj.getPosY() > this.window.getMapHeight() / 100 * 80;
	}

	public boolean hitRock(GameObject object, float x, float y) {

		boolean Hit = false;
		for (GameObject obj : objects) {
			if (obj instanceof Rock) {
				Rock r = (Rock) obj;
				if (r.isAtNextPosition(object, x, y)) {
					Hit = true;
				}
			}
		}
		return Hit;
	}

	// Boolean functions pour arreter les threads quand pas besoin

	public boolean projectilesEmpty() {
		int i = 0;
		for (GameObject obj : objects) {
			if (obj instanceof Projectil)
				i++;
		}
		return (i == 0);
	}

	public int projectilesNumber() {
		int i = 0;
		for (GameObject obj : objects) {
			if (obj instanceof Projectil)
				i++;
		}
		return i;
	}

	public boolean enemysEmpty() {
		int i = 0;
		for (GameObject obj : objects) {
			if (obj instanceof Opponent)
				i++;
		}
		return (i == 0);
	}

	public int getMapWidth() {
		return this.window.getMapWidth();
	}

	public int getMapHeight() {
		return this.window.getMapHeight();
	}

	private void gameOver() {
		this.window.gameOver();

	}
}