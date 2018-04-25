package model;

import java.util.ArrayList;

import view.Window;
import model.GameObject;

public class Game {
	private Player player = new Player(0,0);
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private Window window;
	
	
	public Game(Window window) {
        this.window = window;
        
        window.setPlayer(this.player);
	}
	
	
	
	public void movePlayer(int x, int y) {
		player.move(x,y);
		window.update();
	}
}
