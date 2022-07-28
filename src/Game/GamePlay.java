package Game;

import Graphics.Screen;
import Main.Game;
import Snake.EnemySnake;
import Snake.Snake;
import World.Map;

public class GamePlay extends GameState {

	private Map map;
	private Camera camera;
	private Snake snake;
	private EnemySnake enemySnake;
	
	public GamePlay() {
		map = new Map(Game.WordSize, Game.WordSize);
		camera = new Camera(-30, -30);
		camera.setSpeed(3);
		snake = new Snake();
		camera.ControlDisable();   // Disable control by user
		Game.score = 0;
		enemySnake = new EnemySnake();
	}

	@Override
	public void update() {
		camera.update(); 
		map.update();
		enemySnake.update();
		snake.update();
	}

	@Override
	public void render(Screen s) {
		s.paintScreenOneColor(0x000000);
		map.render(s, camera);
		enemySnake.render(s, camera);
		snake.render(s, camera);
		camera.render(s);
	}

}
