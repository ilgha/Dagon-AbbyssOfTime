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
  private Player player;
  private Floor floor;

  
  public Map() {
      this.setFocusable(true);
      this.requestFocusInWindow();
  }

  

	public void paintComponent(Graphics g){
		int centerX = this.getWidth()/2 - this.getWidth()/(19*2);
		int centerY = this.getHeight()/2 - this.getHeight()/(6*2);
		Room room = floor.currentRoom();
		ArrayList<Door> doors = room.getDoors();
		
		
		g.drawImage(room.getImage(),0,0,this.getWidth(),this.getHeight(),null); 
		
		for(Door door : doors) {
			if(door.getDirection() == 0) {
				g.drawImage(door.getImage(),this.getWidth()/100*94,this.getHeight()/100*41, this.getWidth()/100*13, this.getHeight()/100*21, null);
			}
			if(door.getDirection() == 1) {
				g.drawImage(door.getImage(),this.getWidth()/100*48,this.getHeight()/100*2, this.getWidth()/100*13, this.getHeight()/100*21, null);
			}
			if(door.getDirection() == 2) {
				g.drawImage(door.getImage(),this.getWidth()/100*47,this.getHeight()/100*83, this.getWidth()/100*13, this.getHeight()/100*21, null);
			}
			if(door.getDirection() == 3) {
				g.drawImage(door.getImage(),this.getWidth()/100*2,this.getHeight()/100*41, this.getWidth()/100*13, this.getHeight()/100*21, null);
			}
		}
		
		g.drawImage(player.getImage(),player.getPosX()+centerX, player.getPosY()+centerY, this.getWidth()/100*6,this.getHeight()/100*15, null);
		
  }


	public void setPlayer(Player player) {
		this.player = player;
		
	}
	
	public void setFloor(Floor floor) {
		this.floor = floor;
	}

  
}