package Game;

import java.awt.event.KeyEvent;

import Graphics.Screen;
import Graphics.Sprite;
import Graphics.SpriteSheet;
import Input.Keyboard;
import Main.Game;

public class Menu extends GameState {

	public static final Sprite Pgame = new Sprite(0, 16, 64, 16, SpriteSheet.spriteSheet);
	public static final Sprite Pexit = new Sprite(0, 32, 48, 16, SpriteSheet.spriteSheet);
	public static final Sprite Ppointer = new Sprite(0, 0, 16, 16, SpriteSheet.spriteSheet);

	private int choose;

	private int xMenu, yMenu;

	public Menu() {
		choose = 0;
		xMenu = (int)(Game.rozdzielczoscX/2)-(int)(Game.rozdzielczoscX/16);
		yMenu = (int)(Game.rozdzielczoscY/4);
	}

    
	@Override 
	public void update() {
		if (Keyboard.getKeyOnce(KeyEvent.VK_ENTER)) {
			if (choose == 0)
				Manager.ChangeGameState(Manager.GAME_STATE_GAME);
			if (choose == 1)
				Manager.ChangeGameState((Manager.GAME_STATE_EXIT));
		}

		if (Keyboard.getKeyOnce(KeyEvent.VK_UP)) {
			choose--;
			if (choose < 0)
				choose = 1;
		}

		if (Keyboard.getKeyOnce(KeyEvent.VK_DOWN)) {
			choose++;
			if (choose > 1)
				choose = 0;
		}
	}

	@Override
	public void render(Screen s) {
		s.paintScreenOneColor(0x000000);
		s.render(xMenu - 18, yMenu + (choose * 15), Ppointer);
		s.render(xMenu, yMenu, Pgame);
		s.render(xMenu, yMenu + 15, Pexit);
	}

}
