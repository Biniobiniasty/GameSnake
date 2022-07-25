package Snake;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import Game.Camera;
import Game.Manager;
import Graphics.Screen;
import Input.Keyboard;
import Main.Game;
import World.Artefakt1;
import World.Map;
import World.Tile;

public class Snake {

	private class vector {
		int x, y;
		int lastX, lastY;

		public vector(int x, int y, int X, int Y) {
			this.x = x;
			this.y = y;
			this.lastX = X;
			this.lastY = Y;
		}
	}

	private int speed;
	private int centerX, centerY;

	private int kierunekX, kierunekY;
	private int lastDirectionX, lastDirectionY;
	private int newDirectionX, newDirectionY;
	private boolean changeDirection;
	private boolean MaxSpeed;

	private List<vector> abdomen;
	private long TimeNOW, TimeLAST;

	public Snake() {
		speed = 200; // to less value for faster
		TimeNOW = System.nanoTime();
		TimeLAST = TimeNOW;
		centerX = Game.WordSize / 2;
		centerY = Game.WordSize / 2;
		abdomen = new ArrayList<>();
		kierunekY = 0;
		kierunekX = 1;
		lastDirectionX = 1;
		lastDirectionY = 0;
		newDirectionX = 1;
		newDirectionY = 0;
		abdomen.add(new vector(centerX, centerY, centerX, centerY));
		abdomen.add(new vector(centerX - 1, centerY, centerX - 1, centerY));
		abdomen.add(new vector(centerX - 2, centerY, centerX - 2, centerY));
		changeDirection = true;
		MaxSpeed = true;
	}

	public void direction() {

		if (Keyboard.getKey(KeyEvent.VK_UP) && this.newDirectionY != 1) {
			this.newDirectionX = 0;
			this.newDirectionY = -1;
		}
		if (Keyboard.getKey(KeyEvent.VK_DOWN) && this.newDirectionY != -1) {
			this.newDirectionX = 0;
			this.newDirectionY = 1;
		}
		if (Keyboard.getKey(KeyEvent.VK_RIGHT) && this.newDirectionX != -1) {
			this.newDirectionX = 1;
			this.newDirectionY = 0;
		}
		if (Keyboard.getKey(KeyEvent.VK_LEFT) && this.newDirectionX != 1) {
			this.newDirectionX = -1;
			this.newDirectionY = 0;
		}
		if (changeDirection) { 
			changeDirection = false;

			if (Math.abs(lastDirectionX) == 1) {
				if (newDirectionX != (lastDirectionX * (-1))) {
					lastDirectionX = newDirectionX;
					lastDirectionY = newDirectionY;

					kierunekX = newDirectionX;
					kierunekY = newDirectionY;
				}
			} else if (Math.abs(lastDirectionY) == 1) {

				if (newDirectionY != (lastDirectionY * (-1))) {
					lastDirectionX = newDirectionX;
					lastDirectionY = newDirectionY;

					kierunekX = newDirectionX;
					kierunekY = newDirectionY;
				}
			}
		}
	}

	int anime = 16;
	int animeHead = 1;

	public void update() {
		TimeNOW = System.nanoTime();

		if (TimeNOW - TimeLAST > 1000000 * speed) {
			anime = 16;
			animeHead = 0;
			TimeLAST = TimeNOW;
			List<vector> refresh = new ArrayList<>();

			// Go ahead
			boolean Head = false;
			boolean addlength = false;
			int posX = 0, posY = 0;
			int lastX = 0, lastY = 0;

			for (vector x : abdomen) {
				if (!Head) {
					refresh.add(new vector(x.x + kierunekX, x.y + kierunekY, x.x, x.y));
					Head = true;

					posX = refresh.get(0).x;
					posY = refresh.get(0).y;
					lastX = x.x;
					lastY = x.y;

					// Check artefact
					addlength = false;
					if (Map.tiles[posX][posY] instanceof Artefakt1) {
						addlength = true;
						Map.tiles[posX][posY] = Tile.getTile(6);
						Map.counterArtefact--;
						Game.score += 10;
					}

					// Check colide band
					if (posX < 1 || posY < 1 || posX > Game.WordSize - 2 || posY > Game.WordSize - 2) {
						Manager.ChangeGameState(Manager.GAME_STATE_MENU);
					}
				}
				refresh.add(new vector(x.x, x.y, lastX, lastY));
				lastX = x.x;
				lastY = x.y;
				// Bit yourself
				if ((posX == x.x) && (posY == x.y))
					Manager.ChangeGameState(Manager.GAME_STATE_MENU);
			}
			if (!addlength) {
				refresh.remove(refresh.size() - 1);
			} else {
				speed -= 1;
				if (speed < 1)
					speed = 1;
			}
			abdomen = refresh;
			changeDirection = true;
		}
		direction();
		if(Keyboard.getKey(KeyEvent.VK_SPACE))
		{
			if(MaxSpeed)
			{
				speed -= 110;
				System.out.println("MaxSpeed");
			}
			MaxSpeed = false;
		}
		else
		{
			if(!MaxSpeed)
			{
				speed += 110;
				System.out.println("Normal speed");
			}
			MaxSpeed = true;
		}
	}

	long animeTimeNow = System.nanoTime();
	long animeTimeLast = animeTimeNow;

	public void render(Screen s, Camera c) {

		animeTimeNow = System.nanoTime();
		if (animeTimeNow - animeTimeLast > ((1000000 * speed) / 16)) {
			anime--;
			animeTimeLast = animeTimeNow;
			if (anime < 1)
				anime = 1;
			animeHead++;
			if (animeHead > 16)
				animeHead = 16;
		}

		boolean PrintHead = true;
		boolean Neck = false;

		for (vector x : abdomen) {
			if (PrintHead) {
				Tile.getTile(0).render(s, (x.lastX * 16) + (x.x - x.lastX) * animeHead,
						(x.lastY * 16) + (x.y - x.lastY) * animeHead, c);
//				Tile.getTile(0).render(s, x.x*16, x.y*16, c);

				// Set center camera
				c.x = (x.lastX * 16) + (x.x - x.lastX) * animeHead - (Game.rozdzielczoscX / 2);
				c.y = (x.lastY * 16) + (x.y - x.lastY) * animeHead - (Game.rozdzielczoscY / 2);
				PrintHead = false;

			} else {

				if (Neck) {

					double XX = (((double) x.lastX) * 16.0) + (((double) x.x - (double) x.lastX) * (double) anime);
					double YY = (((double) x.lastY) * 16.0) + (((double) x.y - (double) x.lastY) * (double) anime);

					Tile.getTile(2).render(s, (int) XX, (int) YY, c);
				}
				Neck = true;
			}
		}
	}

}
