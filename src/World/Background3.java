package World;

import Game.Camera;
import Graphics.Screen;
import Graphics.Sprite;
import Graphics.SpriteSheet;

public class Background3 extends Tile{
	public static final Sprite texture = new Sprite(96, 0, 16, SpriteSheet.spriteSheet);

	public int ID;
	public boolean colider;
	
	public Background3() {
		ID = 3;
		colider = false;
	}

	@Override
	public void update() {

	}

	@Override
	public void render(Screen s, int x, int y, Camera c) {
		s.render(x, y, texture, c);
	}
}
