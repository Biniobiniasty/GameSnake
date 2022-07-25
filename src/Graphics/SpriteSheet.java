package Graphics;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class SpriteSheet {

	public static final SpriteSheet spriteSheet = new SpriteSheet("/Snake.png");

	public int WIDTH, HEIGHT;
	public int[] pixels;

	public SpriteSheet(String path) {

		try {
			
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path)); 
			this.WIDTH = image.getWidth();
			this.HEIGHT = image.getHeight(); 
			this.pixels = new int[WIDTH * HEIGHT];

			image.getRGB(0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH);

		} catch (Exception e) {
			System.out.println("Blad: " + e.getMessage());
			e.printStackTrace();
		}
		this.pixels = pixels;
	}
}
