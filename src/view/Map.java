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
  }

  

	public void paintComponent(Graphics g){
		int centerX = this.getWidth()/2 - this.getWidth()/(19*2);
		int centerY = this.getHeight()/2 - this.getHeight()/(6*2);
		
		
		for (GameObject object : this.objects) {
			//On dessine le hero aux coordonnées souhaitées
			if(object.getType() == 0) g.drawImage(object.getImage(), object.getPosX()+centerX, object.getPosY()+centerY, this.getWidth()/19,this.getHeight()/6, null);
			//placement temporaire d'une porte
			if(object.getType() == 1) g.drawImage(object.getImage(),this.getWidth()/2-this.getWidth()/20,0, this.getWidth()/10, this.getHeight()/5, null);
			//dessin de la pièce
			if(object.getType() == 2) g.drawImage(object.getImage(),0,0,this.getWidth(),this.getHeight(),null);    
	}
  }



	public void setObjects(ArrayList<GameObject> objects) {
		this.objects = objects;
		
	}

  
}