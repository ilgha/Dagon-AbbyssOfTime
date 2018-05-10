package model;

import java.util.ArrayList;

public class Floor1 {
	private ArrayList<Room> rooms = new ArrayList<Room>();
	private HitBox hb;
	private int curentRoom = 0;

	public Floor1(HitBox hb) {
		this.hb = hb;

		ArrayList<Door> drset1 = new ArrayList<Door>();
		drset1.add(new Door(0, 2));
		drset1.add(new Door(1, 1));
		Room r1 = new Room(this.hb, drset1);
		rooms.add(r1);

		ArrayList<Door> drset2 = new ArrayList<Door>();
		drset2.add(new Door(2, 1));
		Room r2 = new Room(this.hb, drset2);
		rooms.add(r2);

		ArrayList<Door> drset3 = new ArrayList<Door>();
		drset3.add(new Door(0, 1));
		drset3.add(new Door(2, 1));
		drset3.add(new Door(3, 1));
		Room r3 = new Room(this.hb, drset3);
		rooms.add(r3);

		ArrayList<Door> drset4 = new ArrayList<Door>();
		drset4.add(new Door(3, 1));
		Room r4 = new Room(this.hb, drset4);
		rooms.add(r4);

		ArrayList<Door> drset5 = new ArrayList<Door>();
		drset5.add(new Door(0, 1));
		drset5.add(new Door(1, 1));
		Room r5 = new Room(this.hb, drset5);
		rooms.add(r5);

		ArrayList<Door> drset6 = new ArrayList<Door>();
		drset6.add(new Door(3, 1));
		Room r6 = new Room(this.hb, drset6);
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
