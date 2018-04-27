package model;

import java.util.ArrayList;

import model.Player;
import view.Window;
import model.GameObject;

public class Game {
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private Window window;
	private Player player;
	private Floor floor;
	
	
	public Game(Window window) {
        this.window = window;
        objects.add(new Room(0,0));
        
        
        this.player = new Player(0,0);
        this.floor = new Floor(5);
        
        window.setGameObjects(this.objects);
        window.setPlayer(this.player);
        window.setFloor(this.floor);
	}
	
	
	
	public void movePlayer(int x, int y) {
		player.move(x,y);
		window.update();
	}
}
