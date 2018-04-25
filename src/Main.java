

import controller.Keyboard;
import view.Window;
public class Main {

	public static void main(String[] args) {

		Window fen = new Window();
        Keyboard clav = new Keyboard(fen);
        fen.setKeyListener(clav);
        System.out.println("bala");

	}

}