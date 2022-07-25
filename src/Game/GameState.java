package Game;

import Graphics.Screen;

public abstract class GameState {
	public GameState() {
	};

	public abstract void update();

	public abstract void render(Screen s);
}
