package Snake;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Game.Camera;
import Graphics.Screen;
import Main.Game;
import World.Map;
import World.Tile;

public class EnemySnake {

	private class vector {
		int x;
		int y;

		public vector(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	// Position abdomen
	List<vector> abdomen;

	// Position head
	private int HeadPosX, HeadPosY;
	private int length;
	public static int speed;
	private long TimeNOW, TimeLAST;
	private int DirectionX, DirectionY, LengthWay;

	public EnemySnake() {

		Random r = new Random();
		abdomen = new ArrayList<>();

		HeadPosX = r.nextInt(8) + 4;
		HeadPosY = r.nextInt(8) + 4;
		length = r.nextInt(3) + 2;

		for (int x = 0; x < length; x++)
			abdomen.add(new vector(HeadPosX, HeadPosY - 1 - x));

		speed = 200;
		TimeNOW = System.nanoTime();
		TimeLAST = TimeNOW;

		DirectionX = 0;
		DirectionY = 1;
		LengthWay = 4;
	}

	public void update() {

		TimeNOW = System.nanoTime();
		if (TimeNOW - TimeLAST > 1000000 * speed) {
			// Prepare
			TimeLAST = TimeNOW;
			int lastHeadPosX = HeadPosX;
			int lastHeadPosY = HeadPosY;

			// Chec bands

			boolean bands = false;

			// check up and down
			if ((HeadPosX + DirectionX <= 2) || (HeadPosX + DirectionX >= Game.WordSize - 2))
				bands = true;

			// check left and right
			if ((HeadPosY + DirectionY <= 2) || (HeadPosY + DirectionY >= Game.WordSize - 2))
				bands = true;
			System.out.println(bands);
			// New position head
			Random r = new Random();
			if (LengthWay <= 0 || bands)
				while (true) {
					LengthWay = new Random().nextInt(5);
					int los = r.nextInt(2);

					if (los == 0) {
						los = r.nextInt(2);
						if (los == 0) {
							// Turn Left
							if (DirectionX != 1) {
								DirectionX = -1;
								DirectionY = 0;
								break;
							}
						}
						if (los == 1) {
							// Turn Right
							if (DirectionX != -1) {
								DirectionX = 1;
								DirectionY = 0;
								break;
							}
						}
					}
					if (los == 1) {
						los = r.nextInt(2);
						if (los == 0) {
							// Go up
							if (DirectionY != 1) {
								DirectionY = -1;
								DirectionX = 0;
								break;
							}
						}
						if (los == 1) {
							// Go down
							if (DirectionY != -1) {
								DirectionY = 1;
								DirectionX = 0;
								break;
							}
						}
					}
				}

			
			HeadPosX += DirectionX;
			HeadPosY += DirectionY;
			LengthWay--;

			if(HeadPosX <= 1)
				HeadPosX -= DirectionX;
			if(HeadPosX >= Game.WordSize-1)
				HeadPosX -= DirectionX;
			if(HeadPosY <= 1)
				HeadPosY -= DirectionY;
			if(HeadPosY >= Game.WordSize-1)
				HeadPosY -= DirectionY;
			
			// Clear tiles map
			for (vector x : abdomen) {
				Map.tiles[x.x][x.y] = Tile.getTile(6);
			}

			// Change position EnemySnake
			boolean first = false;
			List<vector> newPosition = new ArrayList<>();
			newPosition.add(new vector(lastHeadPosX, lastHeadPosY));

			for (vector x : abdomen)
				newPosition.add(new vector(x.x, x.y));
			newPosition.remove(newPosition.size() - 1);
			abdomen = newPosition;

			// Show EnemySnake
			Map.tiles[HeadPosX][HeadPosY] = Tile.getTile(19);

			for (vector x : abdomen)
				Map.tiles[x.x][x.y] = Tile.getTile(18);

		}

	}

	public void render(Screen s, Camera c) {

		// Map render EnemySnake, change only tiles
	}
}
