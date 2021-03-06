package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Floor;
import model.GameObject;
import model.Player;
import model.Projectil;
import model.Opponent;

public class Window extends JFrame{
	  private Map pan = new Map();
	  private JPanel container = new JPanel();
	
	  
	  public Window(){
	    this.setTitle("Dagon");
	    this.setSize(980, 565);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    container.setBackground(Color.white);
	    container.setLayout(new BorderLayout());
	    container.add(pan, BorderLayout.CENTER);
	    this.setContentPane(container);
	    this.setVisible(true);    
	  }
	  
	  public void movePlayer(int x, int y) {
		  this.pan.move(x, y);
		  pan.repaint();
	  }
	  public void setKeyListener(KeyListener clavier) {
	      this.pan.addKeyListener(clavier);
	  }
	  
	  public void update() {
		  this.pan.repaint();
	  }
	
	  public void setPlayer(Player player) {
		  this.pan.setPlayer(player);
		  this.pan.repaint();
	  }
	  public void setFloor(Floor floor) {
		  this.pan.setFloor(floor);
		  this.pan.repaint();
	  }
	  public int getMapWidth() {
		  return this.pan.getWidth();
	  }
	  public int getMapHeight() {
		  return this.pan.getHeight();
	  }

	public void setProjectiles(ArrayList<Projectil> projectiles) {
		this.pan.setProjectiles(projectiles);
		
	}
	
	public void setOpponents(ArrayList<Opponent> Opponents) {
		this.pan.setOpponents(Opponents);
		
	}
	  
	  
	  
	  
	  
	  
	  
}