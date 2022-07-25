package Graphics;

public class Sprite {

	public int x, y, size, width, height;

	public SpriteSheet sp;

	public Sprite(int x, int y, int size, SpriteSheet sp) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.height = size;
		this.width = size;
		this.sp = sp;
	}

	public Sprite(int x, int y, int width, int height, SpriteSheet sp) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height; 
		this.sp = sp;
	}
}
