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
		if(id == 8)return new Artefakt2();
		if(id == 9)return new Artefakt3();
		if(id == 10)return new Artefakt4();
		if(id == 11)return new Artefakt5();
		if(id == 12)return new Artefakt6();
		if(id == 13)return new Body2();
		if(id == 14)return new Artefakt7();
		if(id == 15)return new Body3();
		if(id == 16)return new Artefakt8();
		if(id == 17)return new Head2();
		if(id == 18)return new EnemyBody();
		if(id == 19)return new EnemyHead();
		
		return new Head();
	}
	
	public void update() {};

	public void render(Screen s, int x, int y, Camera c) {};
}
