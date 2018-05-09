package model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Door extends GameObject implements Directable{
	private int Direction;
	private BufferedImage doorSprites;
	private BufferedImage door;
	private static HitBox hb = new HitBox(21,16);
	private static int posX = 0;
	private static int posY = 0;	
	private int doorType;
	private boolean open = false;
	
	public Door(int dir, int DoorType) {
		super(posX,posY,hb, 1);
		this.Direction = dir;
		this.doorType = DoorType;
		try{
			this.doorSprites = ImageIO.read(getClass().getResource("/images/doors.png"));
		}catch (IOException e){
			System.out.println("no Image");
		}
		
		door = doorSprites.getSubimage(dir*107, 0, 57, 49);
	}

	public int getType(){
		return this.doorType;
	}


	public int getDirection() {
		return this.Direction;
	}
	
	public BufferedImage getImage() {
		return this.door;
	}
	
	public boolean isOpen() {
		return this.open;
	}
	

	@Override
	public void activate() {
		this.open = true;
		
	}


	@Override
	public void activate(int i) {
		// TODO Auto-generated method stub
		
	}



	

}
