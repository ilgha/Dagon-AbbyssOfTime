package model;

public class Player extends GameObject{

	public Player(int X, int Y) {
		super(X, Y);
		
	}

	public void move(int x, int y) {
		 this.posX = this.posX + x;
	     this.posY = this.posY + y;
		
	}

}
