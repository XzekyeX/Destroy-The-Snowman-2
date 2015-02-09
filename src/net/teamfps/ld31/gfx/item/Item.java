package net.teamfps.ld31.gfx.item;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import net.teamfps.ld31.Input;
import net.teamfps.ld31.Sound;
import net.teamfps.ld31.gfx.Game;
import net.teamfps.ld31.gfx.Status;

public class Item {
	protected List<Status> stats = new ArrayList<Status>();
	protected String name;
	protected int level;

	public Item(String name) {
		this.name = name;
	}

	public int getLevel() {
		int lvl = 0;
		for (int i = 0; i < getStats().size(); i++) {
			Status s = getStats().get(i);
			if (s != null) {
				lvl += (int) s.getValue();
			}
		}
		int calc = lvl / getStats().size();
		return calc;
	}
	
	public int getMaxLevel() {
		int lvl = 0;
		for (int i = 0; i < getStats().size(); i++) {
			Status s = getStats().get(i);
			if (s != null) {
				lvl += (int) s.getMax();
			}
		}
		int calc = lvl / getStats().size();
		return calc;		
	}

	private int delay = 20;
	private int yp = 0;
	private int h1 = 130;
	private int max = 0;
	private int scrollspeed = 10;

	public void update(Game game, int x, int y, int w, int h) {
		if (delay > 0) delay--;

		max = getStats().size() * 26;
		h1 = 200;
		if (-yp > max - h1) {
			yp = -max + h1;
		}
		if (yp > 0) {
			yp = 0;
		}

		if (isMouseOverCollision(x, y, w, h)) {
			if (Input.SCROLL == 1) {
				Input.SCROLL = 0;
				yp -= scrollspeed;
			}
			if (Input.SCROLL == -1) {
				Input.SCROLL = 0;
				yp += scrollspeed;
			}
		}
		// System.out.println("y

		for (int i = 0; i < getStats().size(); i++) {
			int y1 = (int) (yp + i * 26) + 48;
			if (!(y1 >= h1 + 32 || y1 <= 32)) {
				Status s = getStats().get(i);
				if (s != null) {
					if (isMouseOverCollision(x + 16, y + y1, w - 32, 20)) {
						if (Input.equalsKey(KeyEvent.VK_SHIFT)) {
							if (Input.ML) {
								double price = s.getPrice();
								double value = s.getValue();
								double max = s.getMax();
								int p = (int) (value * price);
								long m = game.getMoney();
								// int a = m - p;
								if (m >= p && !(max <= value)) {
									game.loseMoney(p);
									s.IncreaseValue(1.0D);
									System.out.println(s.getName() + " has Upragaded!");
								} else {
									System.out.println("Not Enough Money Or Maxium Upgrade!");
								}
							}
						} else {
							if (Input.ML && delay == 0) {
								delay = 20;
								double price = s.getPrice();
								double value = s.getValue();
								double max = s.getMax();
								int p = (int) (value * price);
								long m = game.getMoney();
								// int a = m - p;
								if (m >= p && !(max <= value)) {
									game.loseMoney(p);
									s.IncreaseValue(1.0D);
									System.out.println(s.getName() + " has Upragaded!");
									Sound.powerup.play();
								} else {
									System.out.println("Not Enough Money Or Maxium Upgrade!");
								}
							}
						}
					}
				}
			}
		}
	}

	public void render(Game screen, int x, int y, int w, int h) {
		screen.renderFillRect(x, y, w, h, new Color(0, 0, 0, 160));
		screen.renderRect(x, y, w, h, 0xffffff);
		screen.renderRect(x, y, w, 32, 0xffffff);
		screen.renderString(getName(), 16, x + 16, y + 24, 0xffffff);
		for (int i = 0; i < getStats().size(); i++) {
			int y1 = (int) (yp + i * 26) + 48;
			if (!(y1 >= h1 + 32 || y1 <= 32)) {
				int col = 0xffffff;
				Status s = getStats().get(i);
				if (s != null) {
					String name = "" + s.getName();
					double value = s.getValue();
					double max = s.getMax();
					double price = s.getPrice();
					if (isMouseOverCollision(x + 16, y + y1, w - 32, 20)) {
						col = 0xff0000;
						int p = (int) (value * price);
						long m = screen.getMoney();
						
						if (m >= p) {
							screen.renderString("Price: " + p + "€", 12, x + 16, y + h - 8, 0x00ff00);
						} else if(max <= value) {
							System.out.println("max: " + max + ", value: " + value);
							screen.renderString("Maximum upgrade!", 12, x + 16, y + h - 8, 0xff0000);														
						} else {
							screen.renderString("Not Enough Money! " + p + "€", 12, x + 16, y + h - 8, 0xff0000);							
						}
					}
					screen.renderRect(x + 16, y + y1, w - 32, 20, col);
					screen.renderString("" + name + ": " + value + "/" + max, 12, x + 20, y + y1 + 16, 0xffffff);
				}
			}
		}
	}

	public boolean isMouseOverCollision(int x, int y, int w, int h) {
		boolean over = false;
		if (Input.MX >= x && Input.MX <= x + w && Input.MY >= y && Input.MY <= y + h) {
			over = true;
		}
		return over;
	}

	public void add(String name, double value, double max, double price) {
		stats.add(new Status(name, value, max, price));
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

}
