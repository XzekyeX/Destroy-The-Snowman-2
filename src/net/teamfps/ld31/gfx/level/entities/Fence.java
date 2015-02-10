package net.teamfps.ld31.gfx.level.entities;

import net.teamfps.ld31.gfx.Game;
import net.teamfps.ld31.gfx.Sprite;

public class Fence extends Mob {
	public Fence(int x, int y, int part) {
		this.x = x;
		this.y = y;
		this.hp = 25;
		this.max_hp = hp;
		this.name = "Fence" + part;
		this.w = 42;
		this.h = 64;
		Sprite[] sprites = { Sprite.fence_part_0, Sprite.fence_part_1, Sprite.fence_part_2 };
		if (part >= 0 && part <= sprites.length - 1) this.sprite = sprites[part];
	}

	@Override
	public void update(Game game) {
		if (hp <= 0) {
			remove();
		}
	}

	@Override
	public void render(Game game) {
		game.renderSprite(sprite, x, y, w, h);
//		renderInfo(game, x, y + 62, true, false, false);
	}

}
