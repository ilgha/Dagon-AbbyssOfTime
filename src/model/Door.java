package model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Door extends GameObject implements Directable {
	private int Direction;
	private BufferedImage doorSprites;
	private BufferedImage door;
	private static int posX = 0;
	private static int posY = 0;
	private int doorType;
	private boolean open = false;

	public Door(int dir, int DoorType, HitBox hb) {
		super(posX, posY, hb);
		this.Direction = dir;
		this.doorType = DoorType;
		try {
			this.doorSprites = ImageIO.read(getClass().getResource("/images/doors.png"));
		} catch (IOException e) {
			System.out.println("no Image");
		}
		if (this.doorType == 1) {
			door = doorSprites.getSubimage(dir * 107, 0, 57, 49);
		}
		if (this.doorType == 2) {
			door = doorSprites.getSubimage(dir * 107, 60, 57, 49);
		}
	}

	public int getType() {
		return this.doorType;
	}

	public int getDirection() {
		return this.Direction;
	}

	public BufferedImage getImage() {
		return this.door;
	}

	public void setType(int type) {
		this.doorType = type;

	}

	public boolean isOpen() {
		return this.open;
	}


	@Override
	public void activate() {
		this.open = true;
		this.door = doorSprites.getSubimage(54 + this.Direction * 107, 0, 57, 49);

	}

	@Override
	public void activate(int i) {
		// TODO Auto-generated method stub

	}

}
