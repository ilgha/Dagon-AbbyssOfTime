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
        this.player = new Player(0,0,new HitBox(window.getMapHeight()/110*1,window.getMapWidth()/110*1));
        this.floor = new Floor(5,new HitBox(window.getMapHeight()/100*3,window.getMapWidth()/100*1));
        window.setPlayer(this.player);
        window.setFloor(this.floor);
	}
	
	
	
	public void movePlayer(int x, int y) {		
		int nextX = player.getPosX() + x;
        int nextY = player.getPosY() + y;
        
		player.move(x,y);
		//System.out.println("("+player.getPosX()+","+player.getPosY()+")");
		//System.out.println("("+player.getHitBox().getDeltaX()+","+player.getHitBox().getDeltaY()+")\n");

		
		
		
		for (Door door : this.floor.currentRoom().getDoors()) {
			//System.out.println("("+door.getPosX()+","+door.getPosY()+")");
			//System.out.println("("+door.getHitBox().getDeltaX()+","+door.getHitBox().getDeltaY()+")\n");
            if (door.isAtPosition(this.player)) {
            	this.floor.nextRoom();
            	if(door.getDirection() == 0) {
	    			player.setPosX(door.getPosX()-player.getHitBox().getDeltaX()*10);
	    			player.setPosY(door.getPosY());
            	}
            	if(door.getDirection() == 1) {
	    			player.setPosX(door.getPosX());
	    			player.setPosY(door.getPosY()+player.getHitBox().getDeltaY()*10);
            	}
            	if(door.getDirection() == 2) {
	    			player.setPosX(door.getPosX());
	    			player.setPosY(door.getPosY()-player.getHitBox().getDeltaY()*10);
            	}
            	if(door.getDirection() == 3) {
	    			player.setPosX(door.getPosX()+player.getHitBox().getDeltaX()*10);
	    			player.setPosY(door.getPosY());
            	}
    			System.out.println("Nombre de salles: " + this.floor.getRooms().size());
            }
		
		window.update();
		}
	}
}