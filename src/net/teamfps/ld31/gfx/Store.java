package net.teamfps.ld31.gfx;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import net.teamfps.ld31.Input;
import net.teamfps.ld31.gfx.item.Item;

public class Store implements Screen {
	private Game game;
	private boolean open = false;
	private int x, y, w, h;
	private List<Item> items = new ArrayList<Item>();

	public Store(Game game, int x, int y, int w, int h) {
		this.game = game;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	private int delay = 20;
	private Item selected;

	@Override
	public void update() {
		if (delay > 0) delay--;
		if (Input.equalsKey(KeyEvent.VK_G) && delay == 0) {
			delay = 20;
			items.add(new Item("Fence").add("Hardness", 0, 100, 100000).add("Stickiness", 0, 100, 100));
		}
		for (int i = 0; i < items.size(); i++) {
			items.get(i).update(game);
			if (items.get(i).isMouseOver() && Input.ML) {
				selected = items.get(i);
			}
		}
		if (selected != null) {
			selected.update(game);
		}
	}

	@Override
	public void render() {
		game.renderRect(x, y, w, h, 0xffffff);
		game.renderFillRect(x, y, w, h, new Color(255, 255, 255, 64));
		game.renderRect(x, y, w, 48, 0xffffff);
		game.renderString("Store", 22, x + (w / 3), y + 32, 0xffffff);
		for (int i = 0; i < items.size(); i++) {
			items.get(i).render(game, x + 12, y + 64 + i * 22, w - 32, 16);
		}
		if (selected != null) {
			// x + w + 48, y, w, h
			selected.renderStats(game, selected.getX() + selected.getW() + 48, selected.getY(), selected.getW(), selected.getH());
		}
	}

	public Game getGame() {
		return game;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean b) {
		this.open = b;
	}

}
