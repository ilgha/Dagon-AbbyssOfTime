package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.Door;
import model.Floor;
import model.GameObject;
import model.Player;
import model.Room;
 
public class Map extends JPanel {
  private ArrayList<GameObject> objects = null;
  private Player player;
  private Floor floor;

  
  public Map() {
      this.setFocusable(true);
      this.requestFocusInWindow();
  }

  

	public void paintComponent(Graphics g){
		int centerX = this.getWidth()/2 - this.getWidth()/(19*2);
		int centerY = this.getHeight()/2 - this.getHeight()/(6*2);
		ArrayList<Room> rooms = this.floor.getRooms();
		Room room = rooms.get(rooms.size()-1);
		ArrayList<Door> doors = room.getDoors();
		Door door = doors.get(1);
		
		
		g.drawImage(room.getImage(),0,0,this.getWidth(),this.getHeight(),null); 
		g.drawImage(door.getImage(),this.getWidth()/2-this.getWidth()/20,0, this.getWidth()/10, this.getHeight()/5, null);		
		g.drawImage(player.getImage(),player.getPosX()+centerX, player.getPosY()+centerY, this.getWidth()/19,this.getHeight()/6, null);
		
  }



	public void setObjects(ArrayList<GameObject> objects) {
		this.objects = objects;
		
	}



	public void setPlayer(Player player) {
		this.player = player;
		
	}
	
	public void setFloor(Floor floor) {
		this.floor = floor;
	}

  
}