package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Inventaire extends JPanel{
	
	public Inventaire() {

	  }
	
	public void paintComponent(Graphics g){
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

}
