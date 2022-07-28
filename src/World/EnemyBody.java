package World;

import Game.Camera;
import Graphics.Screen;
import Graphics.Sprite;
import Graphics.SpriteSheet;

public class EnemyBody extends Tile {

	public static final Sprite texture = new Sprite(80, 48, 16, SpriteSheet.spriteSheet);

	public int ID;
	public boolean colider;
	
	public EnemyBody() {
		ID = 1;
		colider = true;
	}
	

	@Override
	public void update() {

	}

	@Override
	public void render(Screen s, int x, int y, Camera c) {
		s.render(x, y, texture, c);
	}
}
