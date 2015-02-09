package net.teamfps.ld31.gfx.level.entities;

import net.teamfps.ld31.gfx.Game;

/**
 * @author Zekye
 *
 */
public class Text extends Entity {
	protected String msg = "";
	protected int fsize = 12;
	protected int color = 0xffffff;
	protected int visible = 0;
	protected int life = 32;

	public Text(String msg, int fsize, int color, int life, int x, int y) {
		this.x = x;
		this.y = y;
		this.msg = msg;
		this.fsize = fsize;
		this.color = color;
		this.life = life;
	}

	public Text(String msg, int fsize, int color, int x, int y) {
		this.x = x;
		this.y = y;
		this.msg = msg;
		this.fsize = fsize;
		this.color = color;
	}

	public Text(String msg, int color, int x, int y) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.msg = msg;
	}

	public Text(String msg, int x, int y) {
		this.x = x;
		this.y = y;
		this.msg = msg;
	}

	@Override
	public void update(Game game) {
		visible++;
		x += rand.nextInt(2) - 1;
		y += rand.nextInt(2) - 1;
		if (visible >= life) {
			visible = 0;
			remove();
		}
	}

	@Override
	public void render(Game game) {
		game.renderString(msg, fsize, x, y + fsize, color);
	}

}
