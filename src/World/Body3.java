package World;

import Game.Camera;
import Graphics.Screen;
import Graphics.Sprite;
import Graphics.SpriteSheet;

public class Body3 extends Tile {
	public static final Sprite texture = new Sprite(80, 16, 16, SpriteSheet.spriteSheet);

	public int ID;
	public boolean colider;
	
	public Body3() {
		ID = 3;
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
