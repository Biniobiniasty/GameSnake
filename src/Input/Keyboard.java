package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	private static final int Count = 200;
	public static boolean keys[] = new boolean[Count];
	public static boolean keys_prev[] = new boolean[Count];

	public Keyboard() {
		for (int x = 0; x < Count; x++)
			keys[x] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) { 
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	public void update() {
		for (int x = 0; x < Count; x++) {
			if (!keys[x])
				keys_prev[x] = false;
		}
	}

	public static boolean getKeyOnce(int Key) {
		if (!keys_prev[Key] && keys[Key]) {
			keys_prev[Key] = true;
			return true;
		}
		return false;
	}

	public static boolean getKey(int Key) {
		return keys[Key];
	}
}
