package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import model.Floor1;
import model.GameObject;

public class Window extends JFrame{
	  private Map pan = new Map();
	  private Inventaire inv = new Inventaire();
	  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	  
	  public Window(){
	    this.setTitle("Dagon");
	    this.setSize(980, 565);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    //this.inv.setPreferredSize(new Dimension(screenSize.width/100*7,screenSize.height));
        //this.pan.setPreferredSize(new Dimension(4*screenSize.width/100*90,screenSize.height));
	    this.setLayout(new BorderLayout());

	    this.getContentPane().add(pan, BorderLayout.CENTER);
	    //this.getContentPane().add(inv, BorderLayout.WEST);

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
	
	  public void setFloor(Floor1 floor1) {
		  this.pan.setFloor(floor1);
		  this.pan.repaint();
	  }
	  public int getMapWidth() {
		  return this.pan.getWidth();
	  }
	  public int getMapHeight() {
		  return this.pan.getHeight();
	  }


}