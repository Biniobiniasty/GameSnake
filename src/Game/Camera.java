package Game;

import java.awt.event.KeyEvent;

import Graphics.Screen;
import Input.Keyboard;
import Main.Main;
import World.Tile;

public class Camera {

	public int x, y;
	public int speed;


	public static int score;
	
	private boolean DisableControl;

	public static final int WIDTH = Main.WIDTH / 2, HEIGHT = Main.WIDTH / 2;

	public Camera(int x, int y) {
		this.x = x;
		this.y = y;
		this.speed = 1;
		DisableControl = false;
		score = 0;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void up() {
		y -= speed;
	}

	public void down() {
		y += speed;
	}

	public void left() {
		x -= speed;

	}

	public void right() {
		x += speed;
	}

	public void ControlDisable() {
		DisableControl = true;
	}
	
	public void ControlEnable() {
		DisableControl = false;
	}
	
	
	public void update() {
		if (!DisableControl) {
			if (Keyboard.getKey(KeyEvent.VK_W))
				up();
			if (Keyboard.getKey(KeyEvent.VK_S))
				down();
			if (Keyboard.getKey(KeyEvent.VK_A))
				left();
			if (Keyboard.getKey(KeyEvent.VK_D))
				right();
		}
	}
	
	public void render(Screen s)
	{
		Tile.getTile(0).render(s, 10, 10, null);
	}
	
}
