package model;

import java.util.ArrayList;

public class Floor {
	private int size;
	private int maxSize;
	private ArrayList<Room> rooms = new ArrayList<Room>();
	
	public Floor(int maxSize) {
		this.maxSize = maxSize;
		rooms.add(new Room(0,0));
		this.size = 1;
	}
	
	public void isFullFloor() {
		if(this.size == maxSize) this.rooms.get(rooms.size()-1).finalRoom();
	}
	
	public void nextRoom() {
		rooms.add(new Room(0,0));
	}
	
	public ArrayList<Room> getRooms(){
		return this.rooms;
	}

}
