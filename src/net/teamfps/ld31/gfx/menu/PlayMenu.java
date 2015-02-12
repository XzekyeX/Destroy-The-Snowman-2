package net.teamfps.ld31.gfx.menu;

import java.awt.event.KeyEvent;

import net.teamfps.ld31.Input;
import net.teamfps.ld31.gfx.Game;
import net.teamfps.ld31.gfx.Store;
import net.teamfps.ld31.gfx.level.Level;

public class PlayMenu extends Menu {
	private Level level;
	private Store store;

	public PlayMenu(Game game) {
		super(game);
		level = new Level(game);
		store = new Store(game, 64, 64, 256, 256);
	}

	private int delay = 20;

	@Override
	public void update() {
		if (level != null && store != null) {
			boolean e = (Input.equalsKey(KeyEvent.VK_E));
			if (e && delay == 0) {
				delay = 20;
				store.setOpen(!store.isOpen());
			}
			if (store.isOpen()) {
				store.update();
			} else {
				level.update();
			}
		}
		if (delay > 0) delay--;
	}

	@Override
	public void render() {
		if (level != null) {
			level.render();
		}
		if (store != null) {
			if (store.isOpen()) {
				store.render();
			}
		}
	}

}
