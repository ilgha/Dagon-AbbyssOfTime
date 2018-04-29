package model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Room implements Image{
	private BufferedImage floor;
	private ArrayList<Door> doors = new ArrayList<Door>();
	
	public Room() {
		try{
	    	  floor = ImageIO.read(getClass().getResource("/images/binding_of_isaac_rebirth_tiles_Ruins.png"));   	  	
			}
			catch (IOException e){
				System.out.println("no Image");
			}
		
		Random rand = new Random();
		
		for(int i=0; i<=rand.nextInt(4); i++) {
			int direction = rand.nextInt(4);
			doors.add(new Door(0,0,direction));
			System.out.println("porte"+direction);
		}
		
	}

	@Override
	public BufferedImage getImage() {
		return this.floor;
	}

	public void finalRoom() {
		
		
	}
	public ArrayList<Door> getDoors(){
		return this.doors;
	}

}
