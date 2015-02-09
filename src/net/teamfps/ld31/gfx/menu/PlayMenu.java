package net.teamfps.ld31.gfx.menu;

import net.teamfps.ld31.gfx.Game;
import net.teamfps.ld31.gfx.level.Level;

public class PlayMenu extends Menu {
	private Level level;

	public PlayMenu(Game game) {
		super(game);
		level = new Level(game);
	}

	@Override
	public void update() {
		if (level != null) {
			level.update();
		}
	}

	@Override
	public void render() {
		if (level != null) {
			level.render();
		}
	}

}
