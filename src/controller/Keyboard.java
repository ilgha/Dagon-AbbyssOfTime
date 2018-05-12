package controller;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



import model.Game;

public class Keyboard implements KeyListener {

	private Game game;
	private int mapWidth;
	private int mapHeight;
	private int direction;
	private static boolean[] pressed = new boolean[128]; // liste qui determine si une touche est press�e
	
	public Keyboard(Game game) {
		this.game = game;

		this.mapWidth = this.game.getMapWidth();
		this.mapHeight = this.game.getMapHeight();
	}

	public static boolean isPressed(int key) {
		return pressed[key];
	}

	public void keyPressed(KeyEvent event) {
    	int spd = this.mapWidth/150*1;
    	int code = event.getKeyCode();
    	
    	if (code < pressed.length) {
    		pressed[code] = true;
            }
    	if (Keyboard.isPressed(KeyEvent.VK_SPACE)) {
    		spd *= 5;
    	}
    	if (Keyboard.isPressed(KeyEvent.VK_Z)) {
    		this.direction = 1;
    	}
    	if (Keyboard.isPressed(KeyEvent.VK_S)) {
    		this.direction = 5;
    	}
    	if (Keyboard.isPressed(KeyEvent.VK_Q)) {
    		this.direction = 7;
    	}
    	if (Keyboard.isPressed(KeyEvent.VK_D)) {
    		this.direction = 3;
    	} 
    	if (Keyboard.isPressed(KeyEvent.VK_Z)&&Keyboard.isPressed(KeyEvent.VK_D)) {
    		this.direction = 2;
    	}
    	if (Keyboard.isPressed(KeyEvent.VK_S)&&Keyboard.isPressed(KeyEvent.VK_D)) {
    		this.direction = 4;
    	}
    	if (Keyboard.isPressed(KeyEvent.VK_Q)&&Keyboard.isPressed(KeyEvent.VK_S)) {
    		this.direction = 6;
    	}
    	if (Keyboard.isPressed(KeyEvent.VK_Z)&&Keyboard.isPressed(KeyEvent.VK_Q)) {
    		this.direction = 8;
    	} 
    	/*if(!(Keyboard.isPressed(KeyEvent.VK_Z)&&Keyboard.isPressed(KeyEvent.VK_Q)&&Keyboard.isPressed(KeyEvent.VK_S)&&Keyboard.isPressed(KeyEvent.VK_D))){
    		this.direction = 0;
    	}
    	*/
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
    	if (Keyboard.isPressed(KeyEvent.VK_A)) {
    		game.usePotion();
    	}
    	System.out.println(Keyboard.isPressed(KeyEvent.VK_Z));
    	

        switch (this.direction) {
        case 0:
        	game.movePlayer( 0,0);
            break;
        case 1:
        	game.movePlayer( 0,-spd);
            break;
        case 2:
        	game.movePlayer(spd, -spd);
            break;
        case 3:
        	game.movePlayer(spd, 0);
            break;
        case 4:
            game.movePlayer(spd, spd);
             break;
         case 5:
        	 game.movePlayer(0, spd);
			break;
		case 6:
			game.movePlayer(-spd, spd);
			break;
		case 7:
			game.movePlayer(-spd, 0);
			break;
		case 8:
			game.movePlayer(-spd, -spd);
			break;
        }
        
	}
/*
    @Override

	public void keyPressed(KeyEvent e) {
    	int spd = this.mapWidth/150;
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
    	if (Keyboard.isPressed(KeyEvent.VK_A)) {
    		game.usePotion();
    	}
    	
    	
     }
		
		*/
		
		
		
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