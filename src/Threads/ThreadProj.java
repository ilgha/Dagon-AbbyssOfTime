package Threads;

import model.Game;

public class ThreadProj implements Runnable {
	public Game game;
	int waitTime;
	
	public ThreadProj(int waitTime,Game game){
		this.waitTime = waitTime;
		this.game=game;
		
		}

	public void run() {
		try{
			while(!game.projectilesEmpty()){
				
		    System.out.println("on");
			game.moveProjectil();
			
			
			
			Thread.sleep(waitTime); 
			}
			}catch(Exception e){};
			}
}
