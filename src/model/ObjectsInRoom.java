package model;

import java.util.ArrayList;
import java.util.Random;

public class ObjectsInRoom {

	private ArrayList<GameObject> objects = new ArrayList<GameObject>();


	private int nEnemys;
	private int nKeys;
	private int nPotions;
	private int nDinamites;
	private int nRocks;

	public ObjectsInRoom(int mapWidth, int mapHeight, ArrayList<Door> doors, ArrayList<Opponent> opponents,
			ArrayList<Rock> rocks, int nPo, int nDin, int nK) {

		this.nEnemys = opponents.size();
		
		this.nRocks = rocks.size();
		
		this.nPotions = nPo;
		this.nDinamites = nDin;
		this.nKeys = nK;

		int nEnemysSansConsomables = nEnemys;

		for (int i = 0; i < nRocks; i++) {

			Rock rock = rocks.get(i);
			rock.setConsomable(2);
			this.objects.add(rock);
		}

		for (int i = 0; i < nKeys; i++) {
			if (opponents.size() != 0) {
				Opponent enemy = opponents.get(i);
				enemy.setConsomable(1);
				this.objects.add(enemy);

			}
		}

		for (int i = nKeys; i < nKeys+nPotions; i++) {
			if (opponents.size() != 0) {
				Opponent enemy = opponents.get(i);

				enemy.setConsomable(2);
				this.objects.add(enemy);

			}
		}

		for (int i = nKeys+nPotions; i < nKeys+nPotions+nDinamites; i++) {
			if (opponents.size() != 0) {
				Opponent enemy = opponents.get(i);

				enemy.setConsomable(3);
				this.objects.add(enemy);
								
			}
		}

		for (int i = nKeys+nPotions+nDinamites; i < nEnemysSansConsomables; i++) {
			if (opponents.size() != 0) {
				Opponent enemy = opponents.get(i);
				
				enemy.setConsomable(0);
				this.objects.add(enemy);
				opponents.remove(i);
			}
		}

	}

	public ArrayList<GameObject> getObjects() {

		return this.objects;

	}

}
