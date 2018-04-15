package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import view.Window;

public class Keyboard implements KeyListener {
	
    private Window fen;
    private static boolean[] pressed = new boolean[128]; //liste qui determine si une touche est pressée
    

    public Keyboard(Window fen) {
        this.fen = fen;
    }
    
    
    public static boolean isPressed(int key) {
           return pressed[key];
        }
     
      
    @Override

	public void keyPressed(KeyEvent e) {
    	int spd = 5;
    	int code = e.getKeyCode();
    	
    	
    	if (code < pressed.length) {
    		pressed[code] = true;
            }
    	if (Keyboard.isPressed(KeyEvent.VK_SPACE)) {
    		spd *= 3;
    	}
    	if (Keyboard.isPressed(KeyEvent.VK_Z)) {
    		fen.movePlayer(0, -spd);
    	}
    	if (Keyboard.isPressed(KeyEvent.VK_S)) {
    		fen.movePlayer(0, spd);
    	}
    	if (Keyboard.isPressed(KeyEvent.VK_Q)) {
    		fen.movePlayer(-spd, 0);
    	}
    	if (Keyboard.isPressed(KeyEvent.VK_D)) {
    		fen.movePlayer(spd, 0);
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