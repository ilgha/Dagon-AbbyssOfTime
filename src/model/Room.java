package model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Room extends GameObject implements Image{
	private BufferedImage floor;
	private Door doorN;
	private Door doorS;
	private Door doorE;
	private Door doorW;
	private ArrayList<Door> doors = new ArrayList<Door>();
	
	public Room(int x, int y) {
		super(x,y,2);
		
		try{
	    	  floor = ImageIO.read(getClass().getResource("/images/binding_of_isaac_rebirth_tiles_Ruins.png"));   	  	
			}
			catch (IOException e){
				System.out.println("no Image");
			}
		
		for(int i=0; i<3; i++) {
			doors.add(new Door(i));
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
