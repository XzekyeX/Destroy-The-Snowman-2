package net.teamfps.ld31.gfx.menu;

import net.teamfps.ld31.gfx.Game;

public abstract class Menu {
	public Game game;

	public Menu(Game game) {
		this.game = game;
	}

	public abstract void update();

	public abstract void render();

	public Game getGame() {
		return game;
	}
}
