package model;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Opponent extends GameObject implements Killable, Activable, Directable,Image {
	
	private KillableObserver Ko;
	private int lifePoints=2;
	private BufferedImage enemy;
	private BufferedImage enemySprites;
	private int direction=SOUTH;

	public Opponent(int X, int Y,HitBox hb) {
		super(X, Y,hb,1);
		try{
			this.enemySprites = ImageIO.read(getClass().getResource("/images/BigDaddy.png"));
		}catch (IOException e){
			System.out.println("no Image");
		}
		
		enemy = enemySprites.getSubimage(26+this.direction*60, 4, 54, 82);
	
	}

	public void move(float x, float y,int speed) {
		
		 if(x>0) this.direction = EAST;
		 if(y<0) this.direction = NORTH;
		 if(x<0) this.direction = WEST;
		 if(y>0) this.direction = SOUTH;
		 
		 this.posX = this.posX + (int)(x*speed);
		 this.posY = this.posY + (int)(y*speed);
	     
		 try{
				this.enemySprites = ImageIO.read(getClass().getResource("/images/BigDaddy.png"));
			}catch (IOException e){
				System.out.println("no Image");
			}
		 
		 System.out.println(this.posX);
			
		 enemy = enemySprites.getSubimage(26+this.direction*62, 4, 54, 82);

		
	}
	
	

	public void attack() {
		
		
	}


	public void attachKillableObserver(KillableObserver Ko) {
		this.Ko=Ko;
		
	}


	public void notifyKillableObserver() {
		Ko.kill(this);
		
	}

	
	public void activate() {
		lifePoints--;
		if (lifePoints==1) {
			this.notifyKillableObserver();
		} 
		
	}

	
	public int getDirection() {
		// TODO Auto-generated method stub
		return this.direction;
	}
	
	public BufferedImage getImage() {
		return this.enemy;
	}
}
	
	
	
	
	