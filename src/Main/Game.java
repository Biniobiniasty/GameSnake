package Main;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import Game.Manager;
import Graphics.Screen;
import Input.Keyboard;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	
	public final String TITLE = "Snake";
	public final int WIDTH;
	public final int HEIGHT;

	private final int FRAMERATE;
	private final int format_x, format_y;
	private final int rozdzielczosc;
	public static int rozdzielczoscX;
	public static int rozdzielczoscY;
	public static int WordSize;
	public static int score;

	private boolean RUNNING;
	private long framerate_timeNOW, framerate_timeLAST;
	private double framerate, delta, timer;
	private int UPS, FPS;
	private JFrame frame;

	private Screen screen;
	private Manager manager;
	private Keyboard keyboard;

	public Game(int width, int height, int format_x, int format_y, int rozdzielczosc, int WordSize) {

		// inicialization variable
		this.WIDTH = width;
		this.HEIGHT = height;
		this.format_x = format_x;
		this.format_y = format_y; 
		this.rozdzielczosc = rozdzielczosc;
		this.FRAMERATE = 60;
		this.RUNNING = false;
		this.framerate = 1000000000f / FRAMERATE;
		this.delta = 1.0f;
		this.timer = System.currentTimeMillis();
		this.framerate_timeLAST = System.nanoTime();
		this.framerate_timeNOW = System.nanoTime();
		this.WordSize = WordSize;
		Manager.exit = false;

		// set param
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
		setMinimumSize(new Dimension(WIDTH, HEIGHT));

		// create window
		frame = new JFrame(TITLE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setUndecorated(true);
		frame.add(this, new BorderLayout().CENTER);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);



		// Start
		rozdzielczoscX = format_x * rozdzielczosc;
		rozdzielczoscY = format_y * rozdzielczosc;
		screen = new Screen(rozdzielczoscX, rozdzielczoscY); 
		manager = new Manager();
		keyboard = new Keyboard();
		addKeyListener(keyboard);
		start();
	}

	private void start() {
		if (RUNNING)
			return;
		RUNNING = true;

		new Thread(this, "Snake").start();
	}

	private void stop() {
		if (!RUNNING)
			return;
		RUNNING = false;

		frame.dispose();
	}

	@Override
	public void run() {
		while (frame.isVisible() && !Manager.exit) {
			framerate_timeNOW = System.nanoTime();
			delta += (framerate_timeNOW - framerate_timeLAST) / framerate;
			framerate_timeLAST = framerate_timeNOW;

			if (delta > 1) {
				update();
				delta--;
				UPS++;
			}
			render();
			FPS++;

			if (System.currentTimeMillis() - timer >= 1000) {
				timer = System.currentTimeMillis();
				frame.setTitle(TITLE + " FPS: " + FPS + " | UPS: " + UPS);
				UPS = 0;
				FPS = 0;
			}
		}
		stop();
	}

	public void update() {
		keyboard.update();
		manager.update();
	}

	public void render() {

		BufferStrategy buffer = getBufferStrategy();
		if (buffer == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics graphics = buffer.getDrawGraphics();
		/////////////////////////|DRAWING|///////////////////////
		manager.render(screen);
		/////////////////////////|DRAWING|///////////////////////
		graphics.drawImage(screen.getImage(), 0, 0, WIDTH, HEIGHT, null);

		//////////////////////////|SCORE|////////////////////////
		graphics.setColor(Color.white);
		graphics.setFont(new Font("serif", Font.BOLD, 20));
		graphics.drawString(" Score: " + score, 0, 20);
		//////////////////////////|SCORE|////////////////////////
		
		graphics.dispose();
		buffer.show();
	}

}
