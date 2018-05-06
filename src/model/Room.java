package model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Room implements Image{
	private BufferedImage floor;
	private ArrayList<Door> doors = new ArrayList<Door>();
	
	public Room(HitBox hb, ArrayList<Door> doors) {
		try{
	    	  floor = ImageIO.read(getClass().getResource("/images/binding_of_isaac_rebirth_tiles_Ruins.png"));   	  	
			}
			catch (IOException e){
				System.out.println("no Image");
			}
		this.doors = doors;

		
	}

	@Override
	public BufferedImage getImage() {
		return this.floor;
	}

	public ArrayList<Door> getDoors(){
		return this.doors;
	}

}
