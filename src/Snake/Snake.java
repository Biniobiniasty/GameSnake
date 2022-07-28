package Snake;

import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import Game.Camera;
import Game.Manager;
import Graphics.Screen;
import Input.Keyboard;
import Main.Files;
import Main.Game;
import Main.Main;
import World.Artefakt1;
import World.Artefakt2;
import World.Artefakt3;
import World.Artefakt4;
import World.Artefakt5;
import World.Artefakt6;
import World.Artefakt7;
import World.Artefakt8;
import World.EnemyBody;
import World.EnemyHead;
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

	private int speed, Tspeed;
	private int centerX, centerY;

	private int kierunekX, kierunekY;
	private int lastDirectionX, lastDirectionY;
	private int newDirectionX, newDirectionY;
	private boolean changeDirection;
	private boolean MaxSpeed;
	private int bodyTile, multiScore, headTile;
	private long TimebodyAndScore;
	private EnemySnake enemySnake;
	private int enemySpeed;

	private List<vector> abdomen;
	private long TimeNOW, TimeLAST;

	public Snake() {
		enemySpeed = 5000;
		speed = 200; // to less value for faster
		Tspeed = 200;
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
		bodyTile = 2;
		multiScore = 1;
		headTile = 17;
		enemySnake = new EnemySnake();
	}

	public void Lose() {
		Main.textPane.setText(Main.textPane.getText() + "\n" + Game.score + " score [" + Main.nick + "]");
		Manager.ChangeGameState(Manager.GAME_STATE_MENU);

		String[] stringScore = new String[100];
		int[] integerScore = new int[100];
		int index = 0;

		for (String scor : Main.textPane.getText().split("\n")) {
			StringBuilder liczba = new StringBuilder();
			liczba.setLength(0);
			for (int x = 0; x < scor.length(); x++)
				if (scor.toCharArray()[x] != ' ')
					liczba.append(scor.toCharArray()[x]);
				else
					break;
			if (liczba.length() <= 0)
				continue;
			integerScore[index] = Integer.parseInt(liczba.toString());
			stringScore[index] = scor;
			index++;
			if (index >= 99)
				break;
		}

		for (int x = 0; x < index; x++)
			for (int y = x; y < index; y++)
				if (integerScore[x] < integerScore[y]) {
					String Stmp = stringScore[x];
					int Itmp = integerScore[x];
					stringScore[x] = stringScore[y];
					integerScore[x] = integerScore[y];
					stringScore[y] = Stmp;
					integerScore[y] = Itmp;
				}

		StringBuilder resultScore = new StringBuilder();
		for (int x = 0; x < index; x++) {
			if (integerScore[x] != 0)
				resultScore.append(stringScore[x] + "\n");
		}

		Main.textPane.setText(resultScore.toString());

		try {
			Files.Save(Main.katalog.toString(), Main.textPane.getText());
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
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
	boolean ochrona = false;
	int Limit2level = 5000;

	public void update() {

		// Two enemy snake

		if (Game.score > Limit2level) {
			enemySnake.update();
			if(Keyboard.getKey(KeyEvent.VK_B))
			{
				bodyTile = 0;
				ochrona = true;
			}
			else
			{
				if(ochrona)
					bodyTile = 2;
				ochrona = false;
			}
		}

		TimeNOW = System.nanoTime();

		if (TimeNOW - TimeLAST > 1000000 * speed) {
			anime = 16;
			animeHead = 0;
			TimeLAST = TimeNOW;
			List<vector> refresh = new ArrayList<>();

			// Two enemy snake
			if (Game.score > Limit2level) {
				if(ochrona)
				{
					Game.score -= 3;
				}
				if (enemySpeed < Game.score) {
					EnemySnake.speed--;
					enemySpeed += 500;
				}
			}

			// Go ahead
			boolean Head = false;
			boolean addlength = false;
			boolean addlength2 = false;
			boolean cutlength = false;
			boolean cutlength_long = false;
			boolean addlength_long = false;
			boolean bodyAndScore = false;
			boolean bodyAndScoreTriple = false;
			boolean ArtefactRepaint = false;
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

					// Lose
					// Crash with enemy
					if ((Map.tiles[posX][posY] instanceof EnemyHead) || (Map.tiles[posX][posY] instanceof EnemyBody)) {

						if (Game.score > Limit2level) {
							
							if (!Keyboard.getKey(KeyEvent.VK_B)) 
								Lose();
							
						} else {
							Lose();
						}
					}

					// Check artefact1
					addlength = false;
					if (Map.tiles[posX][posY] instanceof Artefakt1) {
						addlength = true;
						Map.tiles[posX][posY] = Tile.getTile(6);
						Map.counterArtefact--;
						Game.score += (10 * multiScore);
						this.speed -= 3;
						if (speed < 50)
							speed = 50;
					}

					// Check Artefact2
					if (Map.tiles[posX][posY] instanceof Artefakt2) {
						addlength = true;
						addlength2 = true;
						Map.tiles[posX][posY] = Tile.getTile(6);
						Map.counterArtefact1--;
						Game.score += (15 * multiScore);
						this.speed -= 5;
						if (speed < 50)
							speed = 50;
					}

					// Check Artefact 3
					if (Map.tiles[posX][posY] instanceof Artefakt3) {
						cutlength = true;
						Map.tiles[posX][posY] = Tile.getTile(6);
						Map.counterArtefact2--;
						Game.score += (5 * multiScore);
						this.speed += 4;
						if (speed < 50)
							speed = 50;
					}

					// Check Artefact 4
					if (Map.tiles[posX][posY] instanceof Artefakt4) {
						addlength_long = true;
						Map.tiles[posX][posY] = Tile.getTile(6);
						Map.counterArtefact3--;
						Game.score += (200 * multiScore);
						this.speed -= 25;
						if (speed < 50)
							speed = 50;
					}

					// Check Artefact 5
					if (Map.tiles[posX][posY] instanceof Artefakt5) {
						cutlength_long = true;
						Map.tiles[posX][posY] = Tile.getTile(6);
						Map.counterArtefact4--;
						Game.score += (100 * multiScore);
						this.speed -= 10;
						if (speed < 50)
							speed = 50;
					}

					// Check Artefact 6
					if (Map.tiles[posX][posY] instanceof Artefakt6) {
						bodyAndScore = true;
						Map.tiles[posX][posY] = Tile.getTile(6);
						Map.counterArtefact5--;
						Game.score += (2 * multiScore);
						this.speed += 1;
						if (speed < 50)
							speed = 50;
					}

					// Check Artefact 7
					if (Map.tiles[posX][posY] instanceof Artefakt7) {
						bodyAndScoreTriple = true;
						Map.tiles[posX][posY] = Tile.getTile(6);
						Map.counterArtefact6--;
						Game.score += (3 * multiScore);
						this.speed += 5;
						if (speed < 50)
							speed = 50;
					}

					// Check Artefact 8
					if (Map.tiles[posX][posY] instanceof Artefakt8) {
						ArtefactRepaint = true;
						Map.tiles[posX][posY] = Tile.getTile(6);
						Map.counterArtefact7--;
						Game.score += (500 * multiScore);
						this.speed += 4;
						if (speed < 50)
							speed = 50;
					}

					// Lose:

					// Check colide band
					if (posX < 1 || posY < 1 || posX > Game.WordSize - 2 || posY > Game.WordSize - 2) {
						Lose();
					}
				}
				refresh.add(new vector(x.x, x.y, lastX, lastY));
				lastX = x.x;
				lastY = x.y;

				// Bit yourself
				if ((posX == x.x) && (posY == x.y)) {
					Lose();
				}

			}

			// Dzialanie Artefakt1 i Artefakt2
			if (!addlength) {
				refresh.remove(refresh.size() - 1);
			} else {
				speed -= 1;
				if (speed < 1)
					speed = 1;
			}

			// Dzialanie Artefakt2
			if (addlength2) {
				int lastx = refresh.get(refresh.size() - 1).x;
				int lasty = refresh.get(refresh.size() - 1).y;
				refresh.add(new vector(lastx - 1, lasty - 1, lastx - 2, lasty - 2));

			}

			// Dzialanie Artefakt3
			if (cutlength) {
				if (refresh.size() > 3)
					refresh.remove(refresh.size() - 1);
			}

			// Dzialanie Artefakt 4
			if (cutlength_long) {
				for (int x = 0; x < 2; x++)
					if (refresh.size() > 3)
						refresh.remove(refresh.size() - 1);
					else
						break;
			}

			// Dzialanie Artefakt 5
			if (addlength_long) {
				int lastx = refresh.get(refresh.size() - 1).x;
				int lasty = refresh.get(refresh.size() - 1).y;

				for (int x = 1; x <= 4; x++)
					refresh.add(new vector(lastx - x, lasty - x, lastx - 1 - x, lasty - 1 - x));

			}

			// Dzialanie Artefakt 6
			if (bodyAndScore) {

				// Niwelowanie dzialania Artefaktu 7 jezeli jest pod wplywem
				if (multiScore == 3)
					this.speed += 25;

				// Niwelowanie roznic jezeli wezmie ten sam Artefakt
				if (multiScore == 2)
					this.speed += 20;

				multiScore = 2;
				bodyTile = 13;
				this.speed -= 20;
				if (this.speed < 50)
					this.speed = 50;
				TimebodyAndScore = System.currentTimeMillis();
			}

			// Desactive Artefakt 6
			if (multiScore == 2) {
				if (System.currentTimeMillis() - TimebodyAndScore > 1000 * 17) {
					multiScore = 1;
					bodyTile = 2;
					this.speed += 20;
				}
			}

			// Dzialanie Artefakt 7
			if (bodyAndScoreTriple) {
				// Niwelowanie dzialania Artefaktu 6 jezeli jest pod wplywem
				if (multiScore == 2)
					this.speed += 20;

				// Niwelowanie roznic jezeli wezmie ten sam Artefakt
				if (multiScore == 3)
					this.speed += 25;

				multiScore = 3;
				bodyTile = 15;
				this.speed -= 25;
				if (this.speed < 50)
					this.speed = 50;
				TimebodyAndScore = System.currentTimeMillis();
			}

			// Desactive Artefakt 7
			if (multiScore == 3) {
				if (System.currentTimeMillis() - TimebodyAndScore > 1000 * 12) {
					multiScore = 1;
					bodyTile = 2;
					this.speed += 25;
				}
			}
			// Dzialanie Artefakt 8
			if (ArtefactRepaint) {
				// if this variable equals true map repaint and change to false
				Map.reapaintArtefact = true;
			}

			abdomen = refresh;
			changeDirection = true;
		}
		direction();

		// [space] - full speed
		if (Keyboard.getKey(KeyEvent.VK_SPACE)) {
			if (MaxSpeed) {
				Tspeed = speed;
				speed = 40;
			}
			MaxSpeed = false;
		} else {
			if (!MaxSpeed) {
				speed = Tspeed;
			}
			MaxSpeed = true;
		}
		if (Game.score >= 5000)
			headTile = 0;

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
				Tile.getTile(headTile).render(s, (x.lastX * 16) + (x.x - x.lastX) * animeHead,
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

					Tile.getTile(bodyTile).render(s, (int) XX, (int) YY, c);
				}
				Neck = true;
			}
		}
	}

}
