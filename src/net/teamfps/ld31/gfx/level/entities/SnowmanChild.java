package net.teamfps.ld31.gfx.level.entities;

import java.util.ArrayList;
import java.util.List;

import net.teamfps.ld31.gfx.Game;
import net.teamfps.ld31.gfx.Sprite;
import net.teamfps.ld31.gfx.level.Level;
import net.teamfps.ld31.gfx.level.entities.task.DestroyTask;
import net.teamfps.ld31.gfx.level.entities.task.Task;

public class SnowmanChild extends Mob {
	private Snowman snowman;
	private List<Task> tasks = new ArrayList<Task>();

	public SnowmanChild(Snowman snowman, int x, int y, int lvl) {
		this.snowman = snowman;
		this.x = x;
		this.y = y;
		this.w = 100;
		this.h = 100;
		this.max_hp = 25 * (lvl * 0.6D);
		this.hp = max_hp;
		this.name = "Snowman Child";
		this.sprite = Sprite.snowman;
	}

	@Override
	public void init(Level level) {
		super.init(level);
		Fence[] f = level.getFence();
		Player p = level.getPlayer();
		tasks.add(new DestroyTask(this, f[2], 0.005D));
		tasks.add(new DestroyTask(this, f[1], 0.005D));
		tasks.add(new DestroyTask(this, f[0], 0.005D));
		tasks.add(new DestroyTask(this, p, 0.005D));
	}

	@Override
	public void update(Game game) {
		if (hp <= 0) {
			remove();
		}

		if (tasks.size() > 0) {
			Task t = tasks.get(0);
			if (t != null && t.activate()) {
				t.execute();
			}
		}
		for (int i = 0; i < tasks.size(); i++) {
			if (!tasks.get(i).activate()) {
				tasks.remove(i);
			}
		}
	}

	@Override
	public void render(Game game) {
		game.renderSprite(sprite, x, y, w, h);
		renderInfo(game, x, y);
	}

	public Snowman getSnowman() {
		return snowman;
	}

}
