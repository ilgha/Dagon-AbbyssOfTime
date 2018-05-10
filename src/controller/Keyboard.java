package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Game;


public class Keyboard implements KeyListener {
	
    private Game game;
    private int mapWidth; 
    private int mapHeight; 
    private static boolean[] pressed = new boolean[128]; //liste qui determine si une touche est press�e
    

    public Keyboard(Game game) {
        this.game = game;
        this.mapWidth  = this.game.getMapWidth();
        this.mapHeight = this.game.getMapHeight();
    }
    
    
    public static boolean isPressed(int key) {
           return pressed[key];
        }
     
      
    @Override

	public void keyPressed(KeyEvent e) {
    	int spd = this.mapWidth/100*1;
    	int code = e.getKeyCode();
    	
    	
    	if (code < pressed.length) {
    		pressed[code] = true;
            }
    	if (Keyboard.isPressed(KeyEvent.VK_SPACE)) {
    		spd *= 5;
    	}
    	if (Keyboard.isPressed(KeyEvent.VK_Z)) {
    		game.movePlayer(0, -spd);
    	}
    	if (Keyboard.isPressed(KeyEvent.VK_S)) {
    		game.movePlayer(0, spd);
    	}
    	if (Keyboard.isPressed(KeyEvent.VK_Q)) {
    		game.movePlayer(-spd, 0);
    	}
    	if (Keyboard.isPressed(KeyEvent.VK_D)) {
    		game.movePlayer(spd, 0);
    	} 
    	if (Keyboard.isPressed(KeyEvent.VK_LEFT)) {
    		game.shoot(0);
    	}
    	if (Keyboard.isPressed(KeyEvent.VK_DOWN)) {
    		game.shoot(1);
    	}
    	if (Keyboard.isPressed(KeyEvent.VK_UP)) {
    		game.shoot(2);
    	}
    	if (Keyboard.isPressed(KeyEvent.VK_RIGHT)) {
    		game.shoot(3);
    	}
    	if (Keyboard.isPressed(KeyEvent.VK_SPACE)) {
    		game.shortAttack();
    	}
    	if (Keyboard.isPressed(KeyEvent.VK_E)) {
    		game.throwDinamaite();
    	}
    	
     }

     @Override
     public void keyReleased(KeyEvent e) {
	     int code = e.getKeyCode();
	     if (code < pressed.length) {
	    	 pressed[code] = false;
         	}
         }


     @Override
     public void keyTyped(KeyEvent e) {
     }
     
	}