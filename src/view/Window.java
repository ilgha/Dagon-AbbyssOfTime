package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
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
	  private JProgressBar healthBar;
	  private JPanel panelGame = new JPanel(new BorderLayout());
	  private JPanel panelHub = new JPanel(new BorderLayout());
	  private JPanel panelBar = new JPanel(new GridLayout(1,0));
	
	  
	  public Window(){
	    this.setTitle("Dagon");
	    this.setSize(980, 565);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    panelGame.setBackground(Color.white);
	    panelGame.add(pan);
	    healthBar = new JProgressBar();
		healthBar.setStringPainted(true);
		healthBar.setString("29" + "/" + "30");
		healthBar.setBackground(Color.BLACK);
		healthBar.setForeground(Color.RED);
		panelBar.add(healthBar);
		panelHub.add(panelBar, BorderLayout.CENTER);
	    this.getContentPane().add(panelGame, BorderLayout.CENTER);
	    this.getContentPane().add(panelHub, BorderLayout.WEST);
	    this.setContentPane(panelGame);
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