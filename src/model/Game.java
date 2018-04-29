package model;

import java.util.ArrayList;

import model.Player;
import view.Window;
import model.GameObject;

public class Game {
	private Window window;
	private Player player;
	private Floor floor;
	
	
	public Game(Window window) {
        this.window = window;       
        this.player = new Player(0,0);
        this.floor = new Floor(5);
        
        window.setPlayer(this.player);
        window.setFloor(this.floor);
	}
	
	
	
	public void movePlayer(int x, int y) {		
		int playerWidth = window.getMapWidth()/19;
		int playerHeight = window.getMapHeight()/6;
		int DoorX = window.getMapWidth()/2-window.getMapWidth()/20;
		int DoorY = -window.getMapHeight()/2;
		int DoorWidth = window.getMapWidth()/10;
		int deltaX = DoorWidth/2;
		int nextX = player.getPosX() + x;
        int nextY = player.getPosY() + y;
        
		player.move(x,y);
		System.out.println("("+player.getPosX()+","+player.getPosY()+")");
		System.out.println("("+ 0 +","+(DoorY + (playerHeight/2))+")");
		
		
		
		for (Door door : this.floor.currentRoom().getDoors()) {
            if (door.isAtPosition(nextX, nextY)) {
            	this.floor.nextRoom();
    			player.setPosX(0);
    			player.setPosY(0);
    			System.out.println("Nombre de salles: " + this.floor.getRooms().size());
            }
		
		
		if(Math.abs(player.getPosX()) < deltaX + deltaX && player.getPosY() == DoorY + playerHeight/2) {
			
			this.floor.nextRoom();
			player.setPosX(0);
			player.setPosY(0);
			System.out.println("Nombre de salles: " + this.floor.getRooms().size());
			
		}
		
		
		window.update();
	}
	}
}