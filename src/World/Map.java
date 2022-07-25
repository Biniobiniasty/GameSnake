package World;

import java.util.Random;

import Game.Camera;
import Graphics.Screen;

public class Map {

	public final int WIDTH, HEIGHT;

	public static Tile[][] tiles;
	public static int counterArtefact;
	public static int counterArtefact1;
	public static int counterArtefact2;
	public static int counterArtefact3;
	public static int counterArtefact4;

	public Map(int width, int height) {
		this.WIDTH = width;
		this.HEIGHT = height;
		tiles = new Tile[WIDTH][HEIGHT];
		clear(6);
		addbackground();
		counterArtefact = 0;
		counterArtefact1 = 0;
		counterArtefact2 = 0;
		counterArtefact3 = 0;
		counterArtefact4 = 0;
	}

	public void addbackground() {
		for (int y = 0; y < HEIGHT; y++)
			for (int x = 0; x < WIDTH; x++)
				if (!(x == 0 || y == 0 || x == HEIGHT - 1 || y == WIDTH - 1)) {
					Random r = new Random();
					int los = r.nextInt(100);

					if (los < 10)
						tiles[x][y] = Tile.getTile(3);
					else if (los < 20)
						tiles[x][y] = Tile.getTile(4);
					else if (los < 30)
						tiles[x][y] = Tile.getTile(5);
					else
						tiles[x][y] = Tile.getTile(6); // Black
				}

	}

	public void clear(int ID) {
		for (int y = 0; y < HEIGHT; y++)
			for (int x = 0; x < WIDTH; x++)
				if (x == 0 || y == 0 || x == HEIGHT - 1 || y == WIDTH - 1)
					tiles[x][y] = Tile.getTile(1); // banda
				else
					tiles[x][y] = Tile.getTile(ID);
	}

	public void render(Screen s, Camera c) {
		for (int y = 0; y < HEIGHT; y++)
			for (int x = 0; x < WIDTH; x++)
				tiles[x][y].render(s, x * 16, 16 * y, c);

	}

	public void update() {
		Random r = new Random();
		int los = r.nextInt(100);

		// Artefakt1
		if (los == 5) {
			if (counterArtefact < 10) {
				int x = r.nextInt(WIDTH - 2) + 1;
				int y = r.nextInt(HEIGHT - 2) + 1;
				tiles[x][y] = Tile.getTile(7);
				counterArtefact++;
			}
		}

		// Artefakt2

		if (los == 18) {
			if (counterArtefact1 < 5) {
				int x = r.nextInt(WIDTH - 2) + 1;
				int y = r.nextInt(HEIGHT - 2) + 1;
				tiles[x][y] = Tile.getTile(8);
				counterArtefact1++;
			}
		}

		// Artefakt3

		if (los == 52) {
			if (counterArtefact2 < 3) {
				int x = r.nextInt(WIDTH - 2) + 1;
				int y = r.nextInt(HEIGHT - 2) + 1;
				tiles[x][y] = Tile.getTile(9);
				counterArtefact2++;
			}
		}

//		// Artefakt4

		if (los == 80) {
			if (counterArtefact3 < 2) {
				int x = r.nextInt(WIDTH - 2) + 1;
				int y = r.nextInt(HEIGHT - 2) + 1;
				tiles[x][y] = Tile.getTile(10);
				counterArtefact3++;
			}
		}

//		// Artefakt5

		if (los == 92) {
			if (counterArtefact4 < 2) {
				int x = r.nextInt(WIDTH - 2) + 1;
				int y = r.nextInt(HEIGHT - 2) + 1;
				tiles[x][y] = Tile.getTile(11);
				counterArtefact4++;
			}
		}

	}

}
