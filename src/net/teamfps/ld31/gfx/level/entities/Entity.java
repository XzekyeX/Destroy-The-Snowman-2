package net.teamfps.ld31.gfx.level.entities;

import java.util.Random;

import net.teamfps.ld31.Input;
import net.teamfps.ld31.gfx.Game;
import net.teamfps.ld31.gfx.level.Level;

public abstract class Entity {
	protected int x, y, w, h;
	protected String name;
	protected Level level;
	protected final Random rand = new Random();
	private boolean removed = false;
	
	public abstract void update(Game game);

	public abstract void render(Game game);

	public void init(Level level) {
		this.level = level;
	}

	public boolean isMouseOver() {
		boolean over = false;
		if (Input.DX >= x && Input.DX <= x + w && Input.DY >= y && Input.DY <= y + h) {
			over = true;
		}
		return over;
	}

	public boolean Collision(Entity e) {
		boolean col = false;
		int ex = e.getX();
		int ey = e.getY();
		int ew = e.getW();
		int eh = e.getH();
		if (x + w >= ex && x <= ex + ew && y + h >= ey && y <= ey + eh) {
			col = true;
		}
		return col;
	}

	public void remove() {
		removed = true;
	}

	public boolean isRemoved() {
		return removed;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "" + getName() + "(" + getX() + "," + getY() + "," + getW() + "," + getH() + ")";
	}
}
