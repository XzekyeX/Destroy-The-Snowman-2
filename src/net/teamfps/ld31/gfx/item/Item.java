package net.teamfps.ld31.gfx.item;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import net.teamfps.ld31.Input;
import net.teamfps.ld31.gfx.Game;
import net.teamfps.ld31.gfx.Status;
import net.teamfps.ld31.gfx.level.entities.Mob;

public class Item {
	protected List<Status> stats = new ArrayList<Status>();
	protected String name;

	public Item(String name) {
		this.name = name;
	}

	private int delay = 20;
	private int x, y, w, h;
	private int sx, sy;

	public void update(Game game) {
		if (delay > 0) delay--;
		if (stats.size() > 0) {
			int m = 22;
			for (int i = 0; i < stats.size(); i++) {
				boolean over = isMouseOver(sx, sy + i * m, w, h);
				String name = "" + stats.get(i).getName();
				if (over) {
					System.out.println("" + name);
				}
			}
		}
	}

	private Color col = new Color(255, 255, 255, 64);

	public void render(Game game, int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		game.renderFillRect(x, y, w, h, col);
		game.renderRect(x, y, w, h, isMouseOver() ? 0xff0000 : 0xffffff);
		game.renderString(name, 12, x + 4, y + h - 2, 0xffffff);
		// if (isMouseOver() && (stats.size() > 0)) {
		// renderStats(game, x + w + 48, y, w, h);
		// }
	}

	public void renderStats(Game game, int x, int y, int w, int h) {
		this.sx = x;
		this.sy = y;
		int m = 22;
		int rh = stats.size() * m;
		game.renderRect(x - 16, y - 16, w + 32, rh + 32, 0xffffff);
		game.renderFillRect(x - 16, y - 16, w + 32, rh + 32, col);
		for (int i = 0; i < stats.size(); i++) {
			boolean over = isMouseOver(x, y + i * m, w, h);
			String name = "" + stats.get(i).getName();
			double lvl = stats.get(i).getLvl();
			double maxlvl = stats.get(i).getMaxLvl();
			double price = stats.get(i).getPrice();
			String level = "" + String.format("%.1f", lvl) + "/" + String.format("%.1f", maxlvl);
			game.renderFillRect(x, y + i * m, w, h, col);
			game.renderRect(x, y + i * m, w, h, over ? 0xff0000 : 0xffffff);
			game.renderString(name + " " + level, 12, x + 4, y + 14 + i * m, 0xffffff);
			if (over) {
				int ww = 128;
				int hh = 48;
				game.renderFillRect(x + w + 32, y + i * m, ww, hh, col);
				game.renderRect(x + w + 32, y + i * m, ww, hh, 0xffffff);
				game.renderRect(x + w + 32, y + i * m, ww, 20, 0xffffff);
				game.renderString("Price", 12, x + w + (ww / 2) + 8, y + 16 + i * m, 0xffffff);
				String p = "" + String.format("%.1f", price);
				game.renderString("" + p + "$", 12, x + w + 40, y + 40 + i * m, 0x00ff00);
			}
		}
	}

	public boolean use(Mob user) {
		return false;
	}

	public boolean isMouseOver(int x, int y, int w, int h) {
		boolean over = false;
		if (Input.DX >= x && Input.DX <= x + w && Input.DY >= y && Input.DY <= y + h) {
			over = true;
		}
		return over;
	}

	public boolean isMouseOver() {
		boolean over = false;
		if (Input.DX >= x && Input.DX <= x + w && Input.DY >= y && Input.DY <= y + h) {
			over = true;
		}
		return over;
	}

	public Item add(String name, double lvl, double max_lvl, double price) {
		stats.add(new Status(name, lvl, max_lvl, price));
		return this;
	}

	public Status getStatus(String name) {
		for (int i = 0; i < getStats().size(); i++) {
			if (getStats().get(i).getName().contains(name)) {
				return getStats().get(i);
			}
		}
		return null;
	}

	public List<Status> getStats() {
		return stats;
	}

	public String getName() {
		return name;
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
