package Graphics;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import Game.Camera;

public class Screen {

	public final int WIDTH, HEIGHT;

	private BufferedImage image;
	private int[] pixels;

	public Screen(int width, int height) {
		this.WIDTH = width;
		this.HEIGHT = height;
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) (image.getRaster().getDataBuffer())).getData();
	}

	public void paintScreenOneColor(int color) {
		for (int x = 0; x < WIDTH * HEIGHT; x++)
			pixels[x] = color;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void paintString(String string)
	{
		
	}
	
	private void paintPixel(int x, int y, int color) {
		if (x < 0 || x >= WIDTH)
			return;
		if (y < 0 || y >= HEIGHT)
			return;
		if (color == 0xFFFF00FF)
			return;

		pixels[x + y * WIDTH] = color;
	}

	public void render(int px, int py, Sprite s, Camera c) {
		if (c == null) c = new Camera(0, 0); 
			for (int y = 0; y < s.height; y++)
				for (int x = 0; x < s.width; x++)
					paintPixel(px + x - c.x, py + y - c.y, s.sp.pixels[s.x + x + (s.y + y) * s.sp.WIDTH]);

	}

	public void render(int px, int py, Sprite s) {
		render(px, py, s, null);

	}

}
