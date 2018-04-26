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
  
  private BufferedImage floor;
  
  public Map() {
      this.setFocusable(true);
      this.requestFocusInWindow();
      try{
    	  floor = ImageIO.read(getClass().getResource("/images/binding_of_isaac_rebirth_tiles_Ruins.png"));
    	  
    	 
    	  	
		}
		catch (IOException e){
			System.out.println("no Image");
		}
  }

  

public void paintComponent(Graphics g){
	int centerX = this.getWidth()/2 - this.getWidth()/(19*2);
	int centerY = this.getHeight()/2 - this.getHeight()/(6*2);
	
	g.drawImage(floor,0,0,this.getWidth(),this.getHeight(),null);
	
	for (GameObject object : this.objects) {
		
		//placement temporaire d'une porte
		if(object.getType() == 4) g.drawImage(object.getImage(),this.getWidth()/2-this.getWidth()/20,0, this.getWidth()/10, this.getHeight()/5, null);
		//On dessine le hero aux coordonnées souhaitées
		if(object.getType() == 0) g.drawImage(object.getImage(), object.getPosX()+centerX, object.getPosY()+centerY, this.getWidth()/19,this.getHeight()/6, null);
	    
	    
	    
	}
  }



public void setObjects(ArrayList<GameObject> objects) {
	this.objects = objects;
	
}

  
  
  
  
}