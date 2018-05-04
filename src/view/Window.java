package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Floor;
import model.GameObject;

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
	  
	  public void setKeyListener(KeyListener clavier) {
	      this.pan.addKeyListener(clavier);
	  }
	  
	  public void update() {
		  this.pan.repaint();
	  }
	  
	  public void setObjects(ArrayList<GameObject> objects) {
		  this.pan.setObjects(objects);
		  update();
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


}