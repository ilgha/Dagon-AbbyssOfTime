package model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Room extends GameObject implements Image{
	private ArrayList<Door> doors = new ArrayList<Door>();
	private BufferedImage floor;
	
	public Room(int x, int y) {
		super(x,y,2);
		try{
	    	  floor = ImageIO.read(getClass().getResource("/images/binding_of_isaac_rebirth_tiles_Ruins.png"));   	  	
			}
			catch (IOException e){
				System.out.println("no Image");
			}
	}

	@Override
	public BufferedImage getImage() {
		return this.floor;
	}

}
