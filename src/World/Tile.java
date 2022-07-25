package World;

import Game.Camera;
import Graphics.Screen;

public class Tile {
	
	public int ID;
	public boolean colider;
	
	public static Tile getTile(int id)
	{
		if(id == 0)return new Head();
		if(id == 1)return new Band();
		if(id == 2)return new Body();
		if(id == 3)return new Background1();
		if(id == 4)return new Background2();
		if(id == 5)return new Background3();
		if(id == 6)return new Background_black();
		if(id == 7)return new Artefakt1();
		return new Head();
	}
	
	public void update() {};

	public void render(Screen s, int x, int y, Camera c) {};
}
