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
		int doorWidth = this.getWidth()/100*13;
		int doorHeight = this.getHeight()/100*21;
		Room room = floor.currentRoom();
		ArrayList<Door> doors = room.getDoors();
		
		g.drawImage(room.getImage(),0,0,this.getWidth(),this.getHeight(),null); 

		for(Door door : doors) {
			if(door.getDirection() == 0) {
				door.setPosX(this.getWidth()/100*94-this.getWidth()/2+doorWidth/2);
				door.setPosY(this.getHeight()/100*41-this.getHeight()/2+doorHeight/2);
			}
			if(door.getDirection() == 1) {
				door.setPosX(this.getWidth()/100*48-this.getWidth()/2+doorWidth/2);
				door.setPosY(this.getHeight()/100*2-this.getHeight()/2+doorHeight/2);
			}
			if(door.getDirection() == 2) {
				door.setPosX(this.getWidth()/100*47-this.getWidth()/2+doorWidth/2);
				door.setPosY(this.getHeight()/100*83-this.getHeight()/2+doorHeight/2);
			}
			if(door.getDirection() == 3) {
				door.setPosX(this.getWidth()/100*2-this.getWidth()/2+doorWidth/2);
				door.setPosY(this.getHeight()/100*41-this.getHeight()/2+doorHeight/2);
			}
			g.drawImage(door.getImage(),door.getPosX()+this.getWidth()/2-doorWidth/2,door.getPosY()+this.getHeight()/2-doorHeight/2, doorWidth, doorHeight, null);
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