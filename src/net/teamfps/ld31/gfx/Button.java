package net.teamfps.ld31.gfx;

import net.teamfps.ld31.Input;

public class Button {
	protected int x, y, w, h;
	protected int fsize, tx, ty, color = 0x000000;
	protected String text;

	public Button(String text, int fsize, int tx, int ty, int x, int y, int w, int h) {
		this.text = text;
		this.fsize = fsize;
		this.tx = tx;
		this.ty = ty;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public boolean isMouseOver() {
		boolean over = false;
		if (Input.DX >= x && Input.DX <= x + w && Input.DY >= y && Input.DY <= y + h) {
			over = true;
		}
		return over;
	}

	public boolean isPressed() {
		return isMouseOver() & Input.ML;
	}

	public void render(Game game) {
		game.renderRect(x, y, w, h, isMouseOver() ? 0x000000 : 0xffffff);
		game.renderString(text, fsize, tx + x, ty + y, color);
	}

	@Override
	public String toString() {
		return "Button(" + text + ":" + x + "," + y + "," + w + "," + h + ")";
	}

	public String getText() {
		return text;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}

}
