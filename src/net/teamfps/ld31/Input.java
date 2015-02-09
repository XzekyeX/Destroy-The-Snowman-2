package net.teamfps.ld31;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Input implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {
	public static int SCROLL = 0;
	private static boolean[] keys = new boolean[1024];
	public static int MX, MY, DX, DY;
	public static boolean ML = false;
	public static boolean MC = false;
	public static boolean MR = false;
	private static int key = -1;
	@Override
	public void keyPressed(KeyEvent e) {
		key = e.getKeyCode();
		keys[key] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		key = -1;
		int key = e.getKeyCode();
		keys[key] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		SCROLL = e.getWheelRotation();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		DX = e.getX();
		DY = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		MX = e.getX();
		MY = e.getY();
		DX = e.getX();
		DY = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			ML = true;
		}
		if (e.getButton() == MouseEvent.BUTTON2) {
			MC = true;
		}
		if (e.getButton() == MouseEvent.BUTTON3) {
			MR = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			ML = false;
		}
		if (e.getButton() == MouseEvent.BUTTON2) {
			MC = false;
		}
		if (e.getButton() == MouseEvent.BUTTON3) {
			MR = false;
		}
	}

	public static boolean equalsKey(int key) {
		if (key >= 0 && key <= keys.length) {
			return keys[key];
		}
		return false;
	}

	public static int getKey() {
		return key;
	}

}
