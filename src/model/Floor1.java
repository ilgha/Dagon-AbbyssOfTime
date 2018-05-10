package model;

import java.util.ArrayList;

public class Floor1 {
	private ArrayList<Room> rooms = new ArrayList<Room>();
	private HitBox hb;
	private int curentRoom = 0;

	public Floor1(int mapWidth, int mapHeight, HitBox hb) {
		this.hb = hb;

		ArrayList<Door> drset1 = new ArrayList<Door>();
		drset1.add(new Door(0, 2));
		drset1.add(new Door(1, 1));
		for (Door d : drset1) {
			d.activate();
		}
		ObjectsInRoom ob1 = new ObjectsInRoom(mapWidth, mapHeight, drset1, 10);
		ArrayList<GameObject> obset1 = ob1.getObjects();
		Room r1 = new Room(this.hb, drset1, obset1);
		rooms.add(r1);

		ArrayList<Door> drset2 = new ArrayList<Door>();
		drset2.add(new Door(2, 1));
		ObjectsInRoom ob2 = new ObjectsInRoom(mapWidth, mapHeight, drset1, 10);
		ArrayList<GameObject> obset2 = ob2.getObjects();
		Room r2 = new Room(this.hb, drset2, obset2);
		rooms.add(r2);

		ArrayList<Door> drset3 = new ArrayList<Door>();
		drset3.add(new Door(0, 1));
		drset3.add(new Door(2, 1));
		drset3.add(new Door(3, 2));
		ObjectsInRoom ob3 = new ObjectsInRoom(mapWidth, mapHeight, drset1, 10);
		ArrayList<GameObject> obset3 = ob3.getObjects();
		Room r3 = new Room(this.hb, drset3, obset3);
		rooms.add(r3);

		ArrayList<Door> drset4 = new ArrayList<Door>();
		drset4.add(new Door(3, 1));
		ObjectsInRoom ob4 = new ObjectsInRoom(mapWidth, mapHeight, drset1, 10);
		ArrayList<GameObject> obset4 = ob4.getObjects();
		Room r4 = new Room(this.hb, drset4, obset4);
		rooms.add(r4);

		ArrayList<Door> drset5 = new ArrayList<Door>();
		drset5.add(new Door(0, 1));
		drset5.add(new Door(1, 1));
		ObjectsInRoom ob5 = new ObjectsInRoom(mapWidth, mapHeight, drset1, 10);
		ArrayList<GameObject> obset5 = ob5.getObjects();
		Room r5 = new Room(this.hb, drset5, obset5);
		rooms.add(r5);

		ArrayList<Door> drset6 = new ArrayList<Door>();
		drset6.add(new Door(3, 1));
		ObjectsInRoom ob6 = new ObjectsInRoom(mapWidth, mapHeight, drset1, 10);
		ArrayList<GameObject> obset6 = ob6.getObjects();
		Room r6 = new Room(this.hb, drset6, obset6);
		rooms.add(r6);

	}

	public ArrayList<Room> getRooms() {
		return this.rooms;
	}

	public Room getCurrentRoom() {
		return this.rooms.get(curentRoom);
	}

	public void nextRoom(Room r, Door d) {
		if (rooms.indexOf(r) == 0 && d.getDirection() == 1)
			this.curentRoom = 1; // passe salle 1 à 2
		if (rooms.indexOf(r) == 0 && d.getDirection() == 0)
			this.curentRoom = 2; // passe salle 1 à 3
		if (rooms.indexOf(r) == 1 && d.getDirection() == 2)
			this.curentRoom = 0; // passe salle 2 à 1
		if (rooms.indexOf(r) == 2 && d.getDirection() == 3)
			this.curentRoom = 0; // passe salle 3 à 1
		if (rooms.indexOf(r) == 2 && d.getDirection() == 0)
			this.curentRoom = 3; // passe salle 3 à 4
		if (rooms.indexOf(r) == 2 && d.getDirection() == 2)
			this.curentRoom = 4; // passe salle 3 à 5
		if (rooms.indexOf(r) == 3 && d.getDirection() == 3)
			this.curentRoom = 2; // passe salle 4 à 3
		if (rooms.indexOf(r) == 4 && d.getDirection() == 1)
			this.curentRoom = 2; // passe salle 5 à 3
		if (rooms.indexOf(r) == 4 && d.getDirection() == 0)
			this.curentRoom = 5; // passe salle 5 à 6
		if (rooms.indexOf(r) == 5 && d.getDirection() == 3)
			this.curentRoom = 4; // passe salle 6 à 5
	}

}
