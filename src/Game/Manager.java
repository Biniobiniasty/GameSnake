package Game;

import Graphics.Screen;

public class Manager extends GameState {
	
	public static final int GAME_STATE_EXIT = -1;
	public static final int GAME_STATE_MENU = 0;
	public static final int GAME_STATE_GAME = 1;
	public static final int GAME_STATE_PAUZE = 2;

	public static boolean exit = false;

	private static GameState game;

	public static void ChangeGameState(int ID) {
		if (ID == GAME_STATE_MENU)
			game = new Menu();
		if (ID == GAME_STATE_GAME)
			game = new GamePlay();
		if (ID == GAME_STATE_EXIT)
			exit = true; 

	}

	public Manager() {
		ChangeGameState(GAME_STATE_MENU);
	}

	public void update() {
		game.update();
	}

	public void render(Screen s) {
		game.render(s);
	}

}
