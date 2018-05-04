package Threads;

import model.Game;

public class ThreadEnemys implements Runnable {
	 public Game game;
		int waitTime;
		
		public ThreadEnemys(int waitTime,Game game){
		this.waitTime = waitTime;
		this.game=game;
		
		}
		
		
		public void run(){
		try{
			
		while(!game.enemysEmpty()){
			
		game.moveEnemy();
		
		
		Thread.sleep(waitTime); 
		}
		}catch(Exception e){};
		}


}
