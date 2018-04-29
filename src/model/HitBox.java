package model;

public class HitBox {
	private int deltaX;
	private int deltaY;
	
	public HitBox(int deltx,int delty) {
		this.deltaX = deltx;
		this.deltaY = delty;
	}
	
	public int getDeltaX() {
		return this.deltaX;
	}
	public int getDeltaY() {
		return this.deltaY;
	}
	
}
