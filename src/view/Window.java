package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
}