package Threads;

import model.Game;

public class ThreadDinamite implements Runnable {
	 public Game game;
     int waitTime;


		public ThreadDinamite(int waitTime,Game game){
		this.waitTime = waitTime;
		this.game=game;
		
		}
	
	public void run() {
	try{
			
			while(!(game.DinamiteNumber()==0)){
				
				game.CheckDinamites();
			
					
				Thread.sleep(waitTime); 
			}
			
		}catch(Exception e){};
		
	}

}