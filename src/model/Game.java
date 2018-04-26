package model;

import java.util.ArrayList;

import model.Player;
import view.Window;
import model.GameObject;

public class Game {
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private Window window;
	
	
	public Game(Window window) {
        this.window = window;
        
        objects.add(new Player(0,0));
        objects.add(new Door(0,0));
        window.setGameObjects(this.objects);
	}
	
	
	
	public void movePlayer(int x, int y) {
		Player player = (Player) objects.get(0);
		player.move(x,y);
		window.update();
	}
}
