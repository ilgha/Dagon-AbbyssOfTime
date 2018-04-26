package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.GameObject;
import model.Player;
 
public class Map extends JPanel {
  private ArrayList<GameObject> objects = null;
  private Player player;
  private BufferedImage spritesDoor;
  private BufferedImage openDoor;
  private BufferedImage floor;
  
  public Map() {
      this.setFocusable(true);
      this.requestFocusInWindow();
      try{
    	  floor = ImageIO.read(getClass().getResource("/images/binding_of_isaac_rebirth_tiles_Ruins.png"));
    	  
    	  spritesDoor = ImageIO.read(getClass().getResource("/images/Binding_of_Isaac_Rebirth_dice_room_door.png"));
    	  	openDoor = spritesDoor.getSubimage(125, 9, 49, 33);
		}
		catch (IOException e){
			System.out.println("no Image");
		}
  }

  

public void paintComponent(Graphics g){
	int centerX = this.getWidth()/2 - this.getWidth()/(19*2);
	int centerY = this.getHeight()/2 - this.getHeight()/(6*2);
    
	g.drawImage(floor,0,0,this.getWidth(),this.getHeight(),null);
    g.drawImage(openDoor,this.getWidth()/2-this.getWidth()/20,0, this.getWidth()/10, this.getHeight()/5, null);
    
    
    
    //On dessine le hero aux coordonnées souhaitées
    g.drawImage(player.getImage(), player.getPosX()+centerX, player.getPosY()+centerY, this.getWidth()/19,this.getHeight()/6, null);
    
  }



public void setPlayer(Player player) {
	this.player = player;
	
}

  
  
  
  
}