package model;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends GameObject implements Directable{
	private BufferedImage heroSprites;
	private BufferedImage hero;
	int direction = SOUTH;
	
	public Player(int X, int Y) {
		super(X, Y);
		try{
			this.heroSprites = ImageIO.read(getClass().getResource("/images/BigDaddy.png"));
		}catch (IOException e){
			System.out.println("no Image");
		}
		hero = heroSprites.getSubimage(89, 4, 54, 82);
		
		
	}

	public void move(int x, int y) {
		 this.posX = this.posX + x;
	     this.posY = this.posY + y;
	     
		
	}

	@Override
	public int getDirection() {
		return this.direction;
	}
	
	public BufferedImage getImage() {
		return this.hero;
	}
}
