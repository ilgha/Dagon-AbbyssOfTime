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
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	
	public Game(Window window)  {
        this.window = window; 
        int playerWidth = this.window.getMapWidth()/100*6;
		int playerHeight = this.window.getMapHeight()/100*15;
		int playerCenterX = this.window.getMapWidth()/2 - playerWidth/2;
		int playerCenterY = this.window.getMapHeight()/2 - playerHeight/2;
        Player player = new Player(playerCenterX,playerCenterY);
        this.player = player;
        this.objects.add(player);
        this.floor = new Floor(5,new HitBox(window.getMapHeight()/100*3,window.getMapWidth()/100*1));
        for(int i=0;i<3;i++) {
			
   		 Opponent enemy=new Opponent((int)((window.getMapHeight()/100*Math.random()*20)*Math.pow(-1, i)),(int)((window.getMapWidth()/100*Math.random()*20)*Math.pow(-1, i+1)),new HitBox(window.getMapHeight()/110*1,window.getMapWidth()/110*1));
   		 enemy.attachKillableObserver(this);
   		 this.objects.add(enemy);
   		 
   		
   		}
        window.setObjects(this.objects);
        window.setFloor(this.floor);
        
        Thread t1 = new Thread(new ThreadEnemys(100,this));
		t1.start();
		System.out.println("thread start");
		     
	}
	
	
	
	public void movePlayer(int x, int y) {	
		for(GameObject obj : objects) {
			if(obj instanceof Player) {
		        Player player = (Player)obj;
		        if(hitWall(player)) {
		        	player.move(1, 1);
		        }else {
		        	player.move(x,y);
					//System.out.println("("+player.getPosX()+","+player.getPosY()+")");
					//System.out.println("("+player.getHitBox().getDeltaX()+","+player.getHitBox().getDeltaY()+")\n");
				
					for (Door door : this.floor.currentRoom().getDoors()) {
						//System.out.println("("+door.getPosX()+","+door.getPosY()+")");
						//System.out.println("("+door.getHitBox().getDeltaX()+","+door.getHitBox().getDeltaY()+")\n");
			            if (door.isAtPosition(player)) {
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
				
			}
		}
		
	}




	public void moveEnemy() {
		
		//calcul du target;
		
		for(GameObject obj : objects) {
			
			if(obj instanceof Opponent) {
			Opponent o = (Opponent)obj;
			int diffX = player.getPosX()-o.getPosX();
			int diffY = player.getPosY()-o.getPosY();
			if (diffX==0 && diffY==0) {
				o.attack(); 
			}else if((diffX^2+diffY^2)<this.window.getMapHeight()/100*10) {
				float angle = (float) Math.atan2(diffY, diffX);
		
				float x = (float) Math.cos(angle);
				float y = (float) Math.sin(angle);
				
				
				o.move(x,y,3);
			}
			
			window.update();
			}
		}
		
	}



		public synchronized void shoot() {
			
			
			   //if(this.projectilesEmpty()) {
				   Projectil p=new Projectil(player.getPosX(), player.getPosY(), player.getDirection(),new HitBox(window.getMapHeight()/110*1,window.getMapWidth()/110*1) );
				   p.attachKillableObserver(this);
				   objects.add(p);
				   window.setObjects(this.objects);
					
				   System.out.println("in");
				 
				   Thread t2 = new Thread(new ThreadProj(100,this));
				   t2.start();
				
			   //} else {
				   //Projectil p=new Projectil(player.getPosX(), player.getPosY(), player.getDirection(),new HitBox(window.getMapHeight()/110*1,window.getMapWidth()/110*1) );
				   //objects.add(p);
				   //window.setObjects(this.objects);

			   
			   //}
		}



		public void moveProjectil() {
			
			for(GameObject obj1:objects) {
				if(obj1 instanceof Projectil) {
					Projectil p = (Projectil) obj1;
					for(GameObject obj2:objects) {
						if(obj2 instanceof Opponent) {
							
							Opponent o = (Opponent) obj2;
							if(p.isAtPosition(o)) {
								
								o.activate(p.getDamage());
								p.activate();
							
							}
						}
						
					}
					p.move(10);
					if(hitWall(p)) {
						p.activate();
					}
					
				}
						
			}
		}

	
	public void kill(Killable K) {
		
		objects.remove(K);	
		
		objects.remove(K);
		
	}
	
	//distance 
	
	public float distanceInBetween(GameObject g1,GameObject g2)  {
		float d;
		d=(float) Math.sqrt((g1.getPosX()-g2.getPosX())^2+(g1.getPosY()-g2.getPosY())^2);
		return d;
		
	}
	
	public boolean hitWall(GameObject obj) {
		return obj.getPosX() < this.window.getMapWidth()/100*10 || obj.getPosX() > this.window.getMapWidth()/100*90 || obj.getPosY() < this.window.getMapWidth()/100*10 || obj.getPosX() > this.window.getMapWidth()/100*80;
	}
	
//Boolean functions pour arreter les threads quand pas besoin
	
	public boolean projectilesEmpty() {
		int i = 0;
		for(GameObject obj:objects) {
			if(obj instanceof Projectil) i++;
		}
		return (i==0);
	}
		
	public boolean enemysEmpty() {
		int i = 0;
		for(GameObject obj:objects) {
			if(obj instanceof Opponent) i++;
		}
		return (i==0);
	}
}