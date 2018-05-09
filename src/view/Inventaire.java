package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Inventaire extends JPanel{
	BufferedImage key;
	
	
	public void paintComponent(Graphics g){
		try{
			this.key = ImageIO.read(getClass().getResource("/images/clé_sprite.png"));
		}catch (IOException e){
			System.out.println("no Image");
		}
		g.setColor(Color.cyan);
		g.fillRect(0, 0, 100,1000);
		g.drawImage(key, 0, 0, this.getWidth()*50, this.getHeight(), null);
	}

}
