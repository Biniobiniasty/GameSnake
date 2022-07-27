package World;

import java.util.Random;
import Main.Main;

import Game.Camera;
import Graphics.Screen;

public class Map {

	public final int WIDTH, HEIGHT;

	public static boolean reapaintArtefact;
	public static Tile[][] tiles;
	public static int counterArtefact;
	public static int counterArtefact1;
	public static int counterArtefact2;
	public static int counterArtefact3;
	public static int counterArtefact4;
	public static int counterArtefact5;
	public static int counterArtefact6;
	public static int counterArtefact7;

	public Map(int width, int height) {
		this.WIDTH = width;
		this.HEIGHT = height;
		tiles = new Tile[WIDTH][HEIGHT];
		clear(6);
		addbackground();
		reapaintArtefact = false;
		counterArtefact = 0;
		counterArtefact1 = 0;
		counterArtefact2 = 0;
		counterArtefact3 = 0;
		counterArtefact4 = 0;
		counterArtefact5 = 0;
		counterArtefact6 = 0;
		counterArtefact7 = 0;
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
		if (los == 5 || los == 25 || los == 99) {

			// Amount
			int amount = 16;
			if (Main.chckbxNewCheckBox_1.isSelected() == true)
				amount = 250;

			// Frequency
			int frequency = 1;
			if (Main.chckbxNewCheckBox_1_1.isSelected())
				frequency = 10;

			// Doing
			for (int Frx = 0; Frx < frequency; Frx++) {
				if (counterArtefact < amount) {
					int x = r.nextInt(WIDTH - 2) + 1;
					int y = r.nextInt(HEIGHT - 2) + 1;
					tiles[x][y] = Tile.getTile(7);
					counterArtefact++;
				}
			}
		}

		// Artefakt2

		if (los == 18 || los == 50) {

			// Amount
			int amount = 7;
			if (Main.chckbxNewCheckBox_1.isSelected() == true)
				amount = 120;

			// Frequency
			int frequency = 1;
			if (Main.chckbxNewCheckBox_1_1.isSelected())
				frequency = 8;

			// Doing
			for (int Frx = 0; Frx < frequency; Frx++) {
				if (counterArtefact1 < amount) {
					int x = r.nextInt(WIDTH - 2) + 1;
					int y = r.nextInt(HEIGHT - 2) + 1;
					tiles[x][y] = Tile.getTile(8);
					counterArtefact1++;
				}
			}
		}

		// Artefakt3

		if (los == 52) {

			// Amount
			int amount = 4;
			if (Main.chckbxNewCheckBox_1.isSelected() == true)
				amount = 80;

			// Frequency
			int frequency = 1;
			if (Main.chckbxNewCheckBox_1_1.isSelected())
				frequency = 6;

			// Doing
			for (int Frx = 0; Frx < frequency; Frx++) {
				if (counterArtefact2 < amount) {
					int x = r.nextInt(WIDTH - 2) + 1;
					int y = r.nextInt(HEIGHT - 2) + 1;
					tiles[x][y] = Tile.getTile(9);
					counterArtefact2++;
				}
			}
		}

//		// Artefakt4

		if (los == 80) {

			// Amount
			int amount = 1;
			if (Main.chckbxNewCheckBox_1.isSelected() == true)
				amount = 20;

			// Frequency
			int frequency = 1;
			if (Main.chckbxNewCheckBox_1_1.isSelected())
				frequency = 3;

			// Doing
			for (int Frx = 0; Frx < frequency; Frx++) {
				if (counterArtefact3 < amount) {
					if (r.nextInt(50) == 8) {
						int x = r.nextInt(WIDTH - 2) + 1;
						int y = r.nextInt(HEIGHT - 2) + 1;
						tiles[x][y] = Tile.getTile(10);
						counterArtefact3++;
					}
				}
			}
		}

//		// Artefakt5

		if (los == 92) {

			// Amount
			int amount = 1;
			if (Main.chckbxNewCheckBox_1.isSelected() == true)
				amount = 20;

			// Frequency
			int frequency = 1;
			if (Main.chckbxNewCheckBox_1_1.isSelected())
				frequency = 3;

			// Doing
			for (int Frx = 0; Frx < frequency; Frx++) {
				if (counterArtefact4 < amount) {
					if (r.nextInt(50) == 12) {
						int x = r.nextInt(WIDTH - 2) + 1;
						int y = r.nextInt(HEIGHT - 2) + 1;
						tiles[x][y] = Tile.getTile(11);
						counterArtefact4++;
					}
				}
			}
		}

//		// Artefakt6

		if (los == 82) {

			// Amount
			int amount = 4;
			if (Main.chckbxNewCheckBox_1.isSelected() == true)
				amount = 50;

			// Frequency
			int frequency = 1;
			if (Main.chckbxNewCheckBox_1_1.isSelected())
				frequency = 4;

			// Doing
			for (int Frx = 0; Frx < frequency; Frx++) {
				if (counterArtefact5 < amount) {
					if (r.nextInt(30) == 12) {
						int x = r.nextInt(WIDTH - 2) + 1;
						int y = r.nextInt(HEIGHT - 2) + 1;
						tiles[x][y] = Tile.getTile(12);
						counterArtefact5++;
					}
				}
			}
		}

//		// Artefakt7

		if (los == 77) {

			// Amount
			int amount = 3;
			if (Main.chckbxNewCheckBox_1.isSelected() == true)
				amount = 35;

			// Frequency
			int frequency = 1;
			if (Main.chckbxNewCheckBox_1_1.isSelected())
				frequency = 3;

			// Doing
			for (int Frx = 0; Frx < frequency; Frx++) {
				if (counterArtefact6 < amount) {
					if (r.nextInt(10) == 5) {
						int x = r.nextInt(WIDTH - 2) + 1;
						int y = r.nextInt(HEIGHT - 2) + 1;
						tiles[x][y] = Tile.getTile(14);
						counterArtefact6++;
					}
				}
			}
		}

//		// Artefakt8

		if (reapaintArtefact) {
			addbackground();
			counterArtefact = 0;
			counterArtefact1 = 0;
			counterArtefact2 = 0;
			counterArtefact3 = 0;
			counterArtefact4 = 0;
			counterArtefact5 = 0;
			counterArtefact6 = 0;
			counterArtefact7 = 0;
			reapaintArtefact = false;
		}
		if (los == 65) {

			// Amount
			int amount = 1;
			if (Main.chckbxNewCheckBox_1.isSelected() == true)
				amount = 10;

			// Frequency
			int frequency = 1;
			if (Main.chckbxNewCheckBox_1_1.isSelected())
				frequency = 2;

			// Doing
			for (int Frx = 0; Frx < frequency; Frx++) {
				if (counterArtefact7 < amount) {
					if (r.nextInt(10) == 5) {
						int x = r.nextInt(WIDTH - 2) + 1;
						int y = r.nextInt(HEIGHT - 2) + 1;
						tiles[x][y] = Tile.getTile(16);
						counterArtefact7++;
					}
				}
			}
		}

	}

}
