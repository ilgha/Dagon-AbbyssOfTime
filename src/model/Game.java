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
	private Floor1 floor1;
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	
	public Game(Window window)  {
        this.window = window; 
        int playerWidth = this.window.getMapWidth()/100*6;
		int playerHeight = this.window.getMapHeight()/100*15;
		int playerCenterX = this.window.getMapWidth()/2 - playerWidth/2;
		int playerCenterY = this.window.getMapHeight()/2 - playerHeight/2;
		HitBox hb = new HitBox(window.getMapHeight()/100*2,window.getMapWidth()/100*3);
		
		
        Player player = new Player(playerCenterX,playerCenterY,hb);
        this.player = player;
        this.objects.add(player);
        this.floor1 = new Floor1(new HitBox(window.getMapHeight()/100*3,window.getMapWidth()/100*1));
        for(int i=0;i<0;i++) {
			
   		 Opponent enemy=new Opponent((int)((window.getMapHeight()/100*Math.random()*20)*Math.pow(-1, i)+playerCenterX),(int)((window.getMapWidth()/100*Math.random()*20)*Math.pow(-1, i+1)+playerCenterY),hb);
   		 enemy.attachKillableObserver(this);
   		 this.objects.add(enemy);
   		 
   		
   		}
        window.setObjects(this.objects);
        window.setFloor(this.floor1);
        
        Thread t1 = new Thread(new ThreadEnemys(100,this));
		t1.start();
		System.out.println("thread start");
		     
	}
	
	
	
	public void movePlayer(int x, int y) {	
		for(GameObject obj : objects) {
			if(obj instanceof Player) {
				Player player = (Player)obj;
			
				if(hitWall(player,x,y)) {
					player.move(0,0);
				}else {
		        	player.move(x,y);

		        	for(Room room : this.floor1.getRooms()) {
						for (Door door : room.getDoors()) {
				            if (door.isAtPosition(player)) {
				            	if(door.getDirection() == 0) {
					    			player.setPosX(window.getMapWidth()-door.getPosX()+player.getHitBox().getDeltaX());
					    			player.setPosY(door.getPosY());
				            	}
				            	if(door.getDirection() == 1) {
					    			player.setPosX(door.getPosX());
					    			player.setPosY(window.getMapHeight()-door.getPosY()-player.getHitBox().getDeltaX()*15);
				            	}
				            	if(door.getDirection() == 2) {
					    			player.setPosX(door.getPosX());
					    			player.setPosY(window.getMapHeight()-door.getPosY()+player.getHitBox().getDeltaX());
				            	}
				            	if(door.getDirection() == 3) {
					    			player.setPosX(window.getMapWidth()-door.getPosX()-player.getHitBox().getDeltaX()*12);
					    			player.setPosY(door.getPosY());
				            	}
				            	this.floor1.nextRoom(room, door);
				    			System.out.println("salle " + ((this.floor1.getRooms().indexOf(this.floor1.getCurrentRoom()))+1));
				            }
						}
					
					window.update();
					
					}
				}
		
			}
		}
	}



	public synchronized void moveEnemy() {
		
		//calcul du target;
		
		for(GameObject obj : objects) {
			
			if(obj instanceof Opponent) {
			Opponent o = (Opponent)obj;
			int diffX = player.getPosX()-o.getPosX();
			int diffY = player.getPosY()-o.getPosY();
			if (diffX==0 && diffY==0) {
				o.attack(); 
			}else{
				float angle = (float) Math.atan2(diffY, diffX);
		
				float x = (float) Math.cos(angle);
				float y = (float) Math.sin(angle);
				
				
				o.move(x,y,3);
			}
			
			window.update();
			}
		}
		
	}



		public synchronized void shoot(int dir) {
			
			Projectil p=new Projectil(player.getPosX(), player.getPosY(), dir,new HitBox(window.getMapWidth()/160*1, window.getMapHeight()/200*1) );   
			p.attachKillableObserver(this);
			objects.add(p);
			window.setObjects(this.objects);
					
			System.out.println("in");
				 
	        Thread t2 = new Thread(new ThreadProj(100,this));
			t2.start();
			window.update();
		}



		public synchronized void moveProjectil() {
			
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
					window.update();
					
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
	
	public boolean hitWall(GameObject obj,int x,int y) {
		return obj.getPosX()+x < this.window.getMapWidth()/100*6 || obj.getPosX()+x > this.window.getMapWidth()/100*94 || obj.getPosY()+y < this.window.getMapHeight()/100*10 || obj.getPosY()+y > this.window.getMapHeight()/100*80;
	}
	public boolean hitWall(GameObject obj) {
		return obj.getPosX() < this.window.getMapWidth()/100*6 || obj.getPosX() > this.window.getMapWidth()/100*94 || obj.getPosY() < this.window.getMapHeight()/100*10 || obj.getPosY() > this.window.getMapHeight()/100*80;
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