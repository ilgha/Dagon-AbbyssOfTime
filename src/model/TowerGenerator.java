package model;

import java.util.TimerTask;

public class TowerGenerator extends TimerTask{
     public Game game;

      TowerGenerator ( Game game ) {
          this.game = game;
        }

    public void run() {
        while(!game.towerEmpty()) {
            game.towerGenerate();
        }


    }

 

}