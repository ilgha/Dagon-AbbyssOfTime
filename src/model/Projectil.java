package model;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Projectil extends GameObject implements Activable,Directable,Image {
boolean OnTarget=false;
int direction;
int target;
private BufferedImage projectil;
private BufferedImage projectilSprites;


	
	public Projectil(int X, int Y,int direction,HitBox hb) {
		super(X, Y,hb,2);
		this.direction=direction;
		
		try{
			this.projectilSprites = ImageIO.read(getClass().getResource("/images/BigDaddy.png"));
		}catch (IOException e){
			System.out.println("no Image");
		}
		
		projectil = projectilSprites.getSubimage(26+this.direction*60, 4, 54, 82);
	}
	
	public void move(int s) {
		
		if (direction==0) {
			this.posX = this.posX - s;
		     
		} if (direction==3) {
			this.posX = this.posX + s;
		    
		} if (direction==2) {
			
		     this.posY = this.posY - s;
		} if (direction==1) {
			
		     this.posY = this.posY + s;
		}
	}
	
	
		
    public boolean isOnTarget() {
    	return OnTarget;
    }

	@Override
	public void activate() {
		OnTarget=true;
		
	}

	@Override
	public int getDirection() {
		
		return direction;
	}
	public BufferedImage getImage() {
		return projectil;
		
	}

	
	
	

}
