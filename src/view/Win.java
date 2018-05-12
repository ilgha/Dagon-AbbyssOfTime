package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Win extends JPanel {

	public void paintComponent(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		Font font = new Font("Courier", Font.BOLD, this.getHeight() / 100 * 20);
		g.setFont(font);
		g.setColor(Color.green);
		g.drawString("You Win! GG",this.getWidth()/100*11,this.getHeight()/100*55);
	}
	
	
}
