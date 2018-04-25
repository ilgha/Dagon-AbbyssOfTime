

import controller.Keyboard;
import model.Game;
import view.Window;
public class Main {

	public static void main(String[] args) {
		
		Window fen = new Window();
		Game game = new Game(fen);
        Keyboard clav = new Keyboard(game);
        fen.setKeyListener(clav);
        System.out.println("balaaaaaa");

	}

}