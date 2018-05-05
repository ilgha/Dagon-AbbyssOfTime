package view;

import java.awt.Color;
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
import model.Projectil;
import model.Room;
import model.Opponent;
 
public class Map extends JPanel {
  private Floor floor;
  private ArrayList<GameObject> objects = null;
  
  public Map() {
      this.setFocusable(true);
      this.requestFocusInWindow();
  }

  

	public void paintComponent(Graphics g){
		int playerWidth = this.getWidth()/100*6;
		int playerHeight = this.getHeight()/100*15;
		int playerCenterX = this.getWidth()/2 - playerWidth/2;
		int playerCenterY = this.getHeight()/2 - playerHeight/2;
		
		int doorWidth = this.getWidth()/100*13;
		int doorHeight = this.getHeight()/100*21;
		int doorCenterX = -this.getWidth()/2+doorWidth/2;
		int doorCenterY = -this.getHeight()/2+doorHeight/2;
		
		Room room = floor.currentRoom();
		ArrayList<Door> doors = room.getDoors();
		
		g.drawImage(room.getImage(),0,0,this.getWidth(),this.getHeight(),null); 

		for(Door door : doors) {
			if(door.getDirection() == 0) {
				door.setPosX(this.getWidth()/100*94+doorCenterX);
				door.setPosY(this.getHeight()/100*41+doorCenterY);
			}
			if(door.getDirection() == 1) {
				door.setPosX(this.getWidth()/100*48+doorCenterX);
				door.setPosY(this.getHeight()/100*2+doorCenterY);
			}
			if(door.getDirection() == 2) {
				door.setPosX(this.getWidth()/100*47+doorCenterX);
				door.setPosY(this.getHeight()/100*83+doorCenterY);
			}
			if(door.getDirection() == 3) {
				door.setPosX(this.getWidth()/100*2+doorCenterX);
				door.setPosY(this.getHeight()/100*41+doorCenterY);
			}
			g.drawImage(door.getImage(),door.getPosX()-doorCenterX,door.getPosY()-doorCenterY, doorWidth, doorHeight, null);
			g.setColor(Color.BLUE);
			g.drawRect(door.getPosX()-doorCenterX+doorWidth/2, door.getPosY()-doorCenterY+doorHeight/2, door.getHitBox().getDeltaX()*2,door.getHitBox().getDeltaY()*2); //hitbox porte
		}
		for(GameObject obj : objects) {
			if(obj instanceof Opponent) {
				Opponent enemy = (Opponent) obj;
				g.drawImage(enemy.getImage(),enemy.getPosX()+playerCenterX, enemy.getPosY()+playerCenterY, this.getWidth()/100*6,this.getHeight()/100*15, null);
				g.setColor(Color.BLUE);
				//g.drawOval(enemy.getPosX()+playerCenterX, enemy.getPosY()+playerCenterY, this.getHeight()/100*10, this.getHeight()/100*10); champs de vision de l'ennemi
				g.drawRect(enemy.getPosX()+playerCenterX+playerWidth/2, enemy.getPosY()+playerCenterY+playerHeight/2, enemy.getHitBox().getDeltaX()*2,enemy.getHitBox().getDeltaY()*2); //hitbox du player
		  
			}
			if(obj instanceof Projectil) {
				Projectil proj = (Projectil) obj;
				g.drawImage(proj.getImage(),proj.getPosX()+playerCenterX+playerWidth/2, proj.getPosY()+playerCenterY+playerHeight/2, this.getWidth()/100*1,this.getHeight()/100*1, null);
				g.setColor(Color.BLUE);
				g.drawRect(proj.getPosX()+playerCenterX+playerWidth/2, proj.getPosY()+playerCenterY+playerHeight/2, proj.getHitBox().getDeltaX()*2,proj.getHitBox().getDeltaY()*2); //hitbox du player
		  
			}
			if(obj instanceof Player) {
				Player player = (Player) obj;
				g.drawImage(player.getImage(),player.getPosX(), player.getPosY(), this.getWidth()/100*6,this.getHeight()/100*15, null);
				g.setColor(Color.BLUE);
				g.drawRect(player.getPosX(), player.getPosY(), player.getHitBox().getDeltaX()*2,player.getHitBox().getDeltaY()*2); //hitbox du player
			}			
		}
   }
	
	public void setObjects(ArrayList<GameObject> objects) {
		this.objects = objects;
	}


	public void setFloor(Floor floor) {
		this.floor = floor;
	}

  
}