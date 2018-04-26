package model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Door extends GameObject implements Directable, Image{
	private int Direction;
	private BufferedImage doorSprites;
	private BufferedImage door;
	
	public Door(int x,int y) {
		super(x,y,1);
		try{
			this.doorSprites = ImageIO.read(getClass().getResource("/images/doors.png"));
		}catch (IOException e){
			System.out.println("no Image");
		}
		
		door = doorSprites.getSubimage(109, 0, 49, 43);
	}
	
	public void OpenDoor() {
		
	}


	public int getDirection() {
		return this.Direction;
	}
	
	public BufferedImage getImage() {
		return this.door;
	}

	

}
