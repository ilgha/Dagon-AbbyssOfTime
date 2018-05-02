package model;

import java.util.ArrayList;

import Threads.ThreadEnemys;
import Threads.ThreadProj;
import model.Player;
import view.Window;
import model.GameObject;


public class Game implements KillableObserver {
	private Window window;
	private Player player;
	private Floor floor;
	private ArrayList<Projectil> Projectiles = new ArrayList<Projectil>();
	private ArrayList<Opponent> enemys = new ArrayList<Opponent>();
	
	public Game(Window window)  {
        this.window = window;       
        this.player = new Player(0,0,new HitBox(window.getMapHeight()/110*1,window.getMapWidth()/110*1));
        this.floor = new Floor(5,new HitBox(window.getMapHeight()/100*3,window.getMapWidth()/100*1));
        window.setPlayer(this.player);
        window.setFloor(this.floor);
        
        
		
        
		for(int i=0;i<3;i++) {
			
		 Opponent enemy=new Opponent((int)(Math.random()*100*-1),(int)Math.random()*100*-1,new HitBox(window.getMapHeight()/110*1,window.getMapWidth()/110*1));
		 enemy.attachKillableObserver(this);
		 enemys.add(enemy);
		 
		
		}
		  window.setOpponents(this.enemys);
		  
		  Thread t1 = new Thread(new ThreadEnemys(100,this));
			t1.start();
			System.out.println("thrad start");
		  
		
	        
	}
	
	
	
	public void movePlayer(int x, int y) {		
		int nextX = player.getPosX() + x;
        int nextY = player.getPosY() + y;
        
		player.move(x,y);
		//System.out.println("("+player.getPosX()+","+player.getPosY()+")");
		//System.out.println("("+player.getHitBox().getDeltaX()+","+player.getHitBox().getDeltaY()+")\n");

		
		
		
		for (Door door : this.floor.currentRoom().getDoors()) {
			//System.out.println("("+door.getPosX()+","+door.getPosY()+")");
			//System.out.println("("+door.getHitBox().getDeltaX()+","+door.getHitBox().getDeltaY()+")\n");
            if (door.isAtPosition(this.player)) {
            	this.floor.nextRoom();
            	if(door.getDirection() == 0) {
	    			player.setPosX(door.getPosX()-player.getHitBox().getDeltaX()*10);
	    			player.setPosY(door.getPosY());
            	}
            	if(door.getDirection() == 1) {
	    			player.setPosX(door.getPosX());
	    			player.setPosY(door.getPosY()+player.getHitBox().getDeltaY()*10);
            	}
            	if(door.getDirection() == 2) {
	    			player.setPosX(door.getPosX());
	    			player.setPosY(door.getPosY()-player.getHitBox().getDeltaY()*10);
            	}
            	if(door.getDirection() == 3) {
	    			player.setPosX(door.getPosX()+player.getHitBox().getDeltaX()*10);
	    			player.setPosY(door.getPosY());
            	}
    			System.out.println("Nombre de salles: " + this.floor.getRooms().size());
            }
		
		window.update();
		}
	}




	public void moveEnemy() {
		
		//calcul du target;
		
		for(int i=0;i<enemys.size();i++) {
			
			Opponent o = enemys.get(i);
			
		int diffX = player.getPosX()-o.getPosX();
		int diffY = player.getPosY()-o.getPosY();
			if (diffX==0 && diffY==0) {
					o.attack(); 
			}else {
		float angle = (float) Math.atan2(diffY, diffX);

		float x = (float) Math.cos(angle);
		float y = (float) Math.sin(angle);
		
		
		o.move(x,y,3);
}
			
}
		window.update();
		
		
	}



	public synchronized void shoot() {
		
           if(this.projectilesEmpty()) {
			
			Thread t2 = new Thread();
			t2.start();
			
		}
		
		Projectil p=new Projectil(player.getPosX(), player.getPosY(), player.getDirection(), null);
		Projectiles.add(p);
		window.setProjectiles(this.Projectiles);
		
		System.out.println(Projectiles.size());
		
		
		
		
		
	}



	public synchronized void moveProjectil() {
		
		for(int j=0;j<Projectiles.size();j++) {
			Projectil p =Projectiles.get(j);
			
			for(int i=0;i<enemys.size();i++) {
				Opponent o = enemys.get(i);
				
				if(distanceInBetween(p,o)<5 ) {
					o.activate();
					p.activate();
		
		}
			
		}
		
		if(p.isOnTarget()) {
			Projectiles.remove(p);
		} else {
			
		}	
		p.move(1);
		}
		
			window.update();
			
	}

	
	public void kill(Killable K) {
		enemys.remove(K);		
	}
	
	//distance 
	
	public float distanceInBetween(GameObject g1,GameObject g2)  {
		float d;
		d=(float) Math.sqrt((g1.getPosX()-g2.getPosX())^2+(g1.getPosY()-g2.getPosY())^2);
		return d;
		
	}
	
//Boolean functions pour arreter les threads quand pas besoin
	
	public boolean projectilesEmpty() {
		if (Projectiles.size()==0) {
		return true;
		} else {
			return false;
		}
	}
		
		public boolean enemysEmpty() {
			if (enemys.size()==0) {
				return true;
				} else {
					return false;
				}
				
			
		}
}