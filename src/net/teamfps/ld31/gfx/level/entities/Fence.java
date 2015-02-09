package net.teamfps.ld31.gfx.level.entities;

import net.teamfps.ld31.gfx.Game;
import net.teamfps.ld31.gfx.Sprite;

public class Fence extends Mob {

	public Fence(int x, int y) {
		this.x = x;
		this.y = y;
		this.hp = 100;
		this.max_hp = hp;
		this.name = "Fence";
		this.w = 128;
		this.h = 64;
		this.sprite = Sprite.full_fence;
	}

	@Override
	public void update(Game game) {
		double one = max_hp / 3;
		double two = max_hp - one;
		if (hp <= two) {
			sprite = Sprite.half_fence;

		}
		if (hp <= one) {
			sprite = Sprite.fence;
		}
		if (hp <= 0) {
			remove();
		}
	}

	@Override
	public void render(Game game) {
		game.renderSprite(sprite, x, y, w, h);
		renderInfo(game, x, y + 62);
	}

}
