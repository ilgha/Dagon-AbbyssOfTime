package model;

import java.util.ArrayList;

public class Floor1 {
	private ArrayList<Room> rooms = new ArrayList<Room>();
	private int curentRoom = 0;

	public Floor1(int mapWidth, int mapHeight, HitBox hbDoor, HitBox hbOp, HitBox hbRk) {

		ArrayList<Door> drset1 = new ArrayList<Door>();
		drset1.add(new Door(0, 2,hbDoor));
		drset1.add(new Door(1, 1,hbDoor));
		drset1.get(1).activate();
		ArrayList<Opponent> opset1 = new ArrayList<Opponent>();
		ArrayList<Rock> rkset1 = new ArrayList<Rock>();
		ObjectsInRoom ob1 = new ObjectsInRoom(mapWidth, mapHeight, drset1, opset1,rkset1,0,0,0);
		ArrayList<GameObject> obset1 = ob1.getObjects();
		Room r1 = new Room(drset1, obset1);
		rooms.add(r1);

		ArrayList<Door> drset2 = new ArrayList<Door>();
		drset2.add(new Door(2, 1,hbDoor));
		ArrayList<Opponent> opset2 = new ArrayList<Opponent>();
		opset2.add(new Opponent(mapWidth/100*51,mapHeight/100*27,hbOp));
		opset2.add(new Opponent(mapWidth/100*25,mapHeight/100*45,hbOp));
		opset2.add(new Opponent(mapWidth/100*75,mapHeight/100*45,hbOp));
		ArrayList<Rock> rkset2 = new ArrayList<Rock>();
		rkset2.add(new Rock(mapWidth/100*51,mapHeight/100*57,hbRk));
		rkset2.add(new Rock(mapWidth/100*25,mapHeight/100*40,hbRk));
		rkset2.add(new Rock(mapWidth/100*75,mapHeight/100*40,hbRk));
		ObjectsInRoom ob2 = new ObjectsInRoom(mapWidth, mapHeight, drset2, opset2,rkset2,2,0,1);
		ArrayList<GameObject> obset2 = ob2.getObjects();
		Room r2 = new Room(drset2, obset2);
		rooms.add(r2);

		ArrayList<Door> drset3 = new ArrayList<Door>();
		drset3.add(new Door(0, 1,hbDoor));
		drset3.add(new Door(2, 1,hbDoor));
		drset3.add(new Door(3, 1,hbDoor));
		ArrayList<Opponent> opset3 = new ArrayList<Opponent>();
		opset3.add(new Opponent(mapWidth/100*25,mapHeight/100*25,hbOp));
		opset3.add(new Opponent(mapWidth/100*25,mapHeight/100*75,hbOp));
		opset3.add(new Opponent(mapWidth/100*50,mapHeight/100*50,hbOp));
		opset3.add(new Opponent(mapWidth/100*60,mapHeight/100*30,hbOp));
		opset3.add(new Opponent(mapWidth/100*60,mapHeight/100*60,hbOp));
		ObjectsInRoom ob3 = new ObjectsInRoom(mapWidth, mapHeight, drset3, opset3,rkset1,2,4,1);
		ArrayList<GameObject> obset3 = ob3.getObjects();
		Room r3 = new Room(drset3, obset3);
		rooms.add(r3);

		ArrayList<Door> drset4 = new ArrayList<Door>();
		drset4.add(new Door(3, 1,hbDoor));
		ObjectsInRoom ob4 = new ObjectsInRoom(mapWidth, mapHeight, drset1, opset1,rkset1,3,0,1);
		ArrayList<GameObject> obset4 = ob4.getObjects();
		Room r4 = new Room(drset4, obset4);
		rooms.add(r4);

		ArrayList<Door> drset5 = new ArrayList<Door>();
		drset5.add(new Door(0, 2,hbDoor));
		drset5.add(new Door(1, 1,hbDoor));
		ObjectsInRoom ob5 = new ObjectsInRoom(mapWidth, mapHeight, drset1, opset1,rkset1,2,3,0);
		ArrayList<GameObject> obset5 = ob5.getObjects();
		Room r5 = new Room(drset5, obset5);
		rooms.add(r5);

		ArrayList<Door> drset6 = new ArrayList<Door>();
		drset6.add(new Door(3, 1,hbDoor));
		ArrayList<GameObject> ob6 = new ArrayList<GameObject>(); //ObjectsInRoom(mapWidth, mapHeight, drset1, opset1,rkset1,0,0,0);
		Boss b= new Boss(500,500,null);
			//ArrayList<GameObject> obset6 = ob6.getObjects();
		ob6.add(b);
		Room r6 = new Room(drset6, ob6);
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
