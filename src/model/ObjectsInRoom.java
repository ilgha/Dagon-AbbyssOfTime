package model;

import java.util.ArrayList;
import java.util.Random;

public class ObjectsInRoom {

	private ArrayList<Door> doors = new ArrayList<Door>();
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private int nEnemys;
	private int nKeys = 0;
	private int nPotions = 1;
	private int nDinamites = 1;
	private int nRocks = 3;

	public ObjectsInRoom(int mapWidth, int mapHeight, ArrayList<Door> doors, int NumberEnemys) {
		int playerWidth = mapWidth / 100 * 6;
		int playerHeight = mapHeight / 100 * 15;
		int playerCenterX = mapWidth / 2 - playerWidth / 2;
		int playerCenterY = mapHeight / 2 - playerHeight / 2;

		this.doors = doors;
		this.nEnemys = NumberEnemys;

		for (Door door : this.doors) {
			if (door.getType() == 2) {
				nKeys++;
			}
		}

		int nEnemysSansConsomables = nEnemys - (nDinamites + nPotions + nKeys);
		
		for (int i=0; i<nRocks;i++) {
            Rock rock = new Rock(
                    (int) ((mapHeight / 100 * Math.random() * 20) * Math.pow(-1, i) + playerCenterX),
                    (int) ((mapWidth / 100 * Math.random() * 20) * Math.pow(-1, i + 1) + playerCenterY), null);
            rock.setConsomable(2);
            this.objects.add(rock);
        }
		for (int i = 0; i < nKeys; i++) {
			Opponent enemy = new Opponent(
					(int) ((mapHeight / 100 * Math.random() * 20) * Math.pow(-1, i) + playerCenterX),
					(int) ((mapWidth / 100 * Math.random() * 20) * Math.pow(-1, i + 1) + playerCenterY), null);
			enemy.setConsomable(1);
			this.objects.add(enemy);
		}

		for (int i = 0; i < nPotions; i++) {
			Opponent enemy = new Opponent(
					(int) ((mapHeight / 100 * Math.random() * 20) * Math.pow(-1, i) + playerCenterX),
					(int) ((mapWidth / 100 * Math.random() * 20) * Math.pow(-1, i + 1) + playerCenterY), null);
			enemy.setConsomable(2);
			this.objects.add(enemy);
		}

		for (int i = 0; i < nDinamites; i++) {

			Opponent enemy = new Opponent(
					(int) ((mapHeight / 100 * Math.random() * 20) * Math.pow(-1, i) + playerCenterX),
					(int) ((mapWidth / 100 * Math.random() * 20) * Math.pow(-1, i + 1) + playerCenterY), null);
			enemy.setConsomable(3);
			this.objects.add(enemy);

		}
		for (int i = 0; i < nEnemysSansConsomables; i++) {

			Opponent enemy = new Opponent(
					(int) ((mapHeight / 100 * Math.random() * 20) * Math.pow(-1, i) + playerCenterX),
					(int) ((mapWidth / 100 * Math.random() * 20) * Math.pow(-1, i + 1) + playerCenterY), null);
			enemy.setConsomable(0);
			this.objects.add(enemy);

		}

	}

	public ArrayList<GameObject> getObjects() {

		return this.objects;

	}

}
