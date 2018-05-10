package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GameOver extends JPanel {

	public void paintComponent(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		Font font = new Font("Courier", Font.BOLD, this.getHeight() / 100 * 20);
		g.setFont(font);
		g.setColor(Color.red);
		g.drawString("Game Over",this.getWidth()/100*17,this.getHeight()/100*55);
	}
	
	
}
