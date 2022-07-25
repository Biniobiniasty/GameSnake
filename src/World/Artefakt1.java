package World;

import Game.Camera;
import Graphics.Screen;
import Graphics.Sprite;
import Graphics.SpriteSheet;

public class Artefakt1 extends Tile{
	public static final Sprite texture = new Sprite(128, 0, 16, SpriteSheet.spriteSheet);

	public int ID;
	public boolean colider;
	
	public Artefakt1() {
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
