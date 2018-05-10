package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import model.Floor1;
import model.GameObject;
import model.Player;

public class Window extends JFrame {
	private Map pan = new Map();
	private Inventaire inv = new Inventaire();

	public Window() {
		this.setTitle("Dagon");
		this.setSize(980, 565);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	    
		this.inv.setPreferredSize(new Dimension(this.getWidth() / 100 * 15, this.getHeight()));
		this.pan.setPreferredSize(new Dimension(this.getWidth() / 100 * 85, this.getHeight()));

		this.setLayout(new BorderLayout());

		this.getContentPane().add(pan, BorderLayout.CENTER);
		this.getContentPane().add(inv, BorderLayout.WEST);

		this.setVisible(true);

	}

	public void setKeyListener(KeyListener clavier) {
		this.pan.addKeyListener(clavier);
	}

	public void update() {
		this.pan.repaint();
		this.inv.repaint();
	}

	public void setObjects(ArrayList<GameObject> objects) {
		this.pan.setObjects(objects);
		update();
	}

	public void setFloor(Floor1 floor1) {
		this.pan.setFloor(floor1);
		this.pan.repaint();
	}
	public void setPlayer(Player player) {
		this.inv.setPlayer(player);
		this.inv.repaint();
	}

	public int getMapWidth() {
		return this.pan.getWidth();
	}

	public int getMapHeight() {
		return this.pan.getHeight();
	}

}