package model;

import java.util.ArrayList;

public class Floor {
	private int size;
	private int maxSize;
	private ArrayList<Room> rooms = new ArrayList<Room>();
	private HitBox hb;
	
	public Floor(int size, HitBox hb) {
		this.hb = hb;
		this.size = size;
		for(int i=0; i<=size; i++) {
			rooms.add(new Room(this.hb));
		}
		
	}
	
	public void isFullFloor() {
		if(this.size == maxSize) this.rooms.get(rooms.size()-1).finalRoom();
	}
	
	public void nextRoom() {
		rooms.add(new Room(this.hb));
		this.size++;
	}
	
	public ArrayList<Room> getRooms(){
		return this.rooms;
	}

	public Room currentRoom() {
		return this.rooms.get(rooms.size()-1);
	}
	
	

}
