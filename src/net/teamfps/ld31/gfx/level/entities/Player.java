package net.teamfps.ld31.gfx.level.entities;

import java.awt.event.KeyEvent;

import net.teamfps.ld31.Input;
import net.teamfps.ld31.gfx.Game;
import net.teamfps.ld31.gfx.Sprite;

public class Player extends Mob {
	private int yp;

	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		this.yp = y;
		this.w = 66;
		this.h = 96;
		this.hp = 20;
		this.max_hp = hp;
		this.name = "Player";
		this.sprite = Sprite.player;
	}

	private boolean duck = false;
	private int fireRate = 20;

	@Override
	public void update(Game game) {
		if (hp <= 0) remove();
		duck = (Input.equalsKey(KeyEvent.VK_SHIFT));
		yp = duck ? y + 32 : y;
		if (fireRate > 0) fireRate--;
		if (Input.ML && fireRate == 0) {
			fireRate = 20;
			Shoot(x + (w / 2), yp + 16, new Mouse(Input.DX, Input.DY), 2, 700, 2);
		}
	}

	@Override
	public void render(Game game) {
		this.h = duck ? 64 : 96;
		game.renderSprite(duck ? Sprite.player_duck : sprite, x, yp, w, h, true);
		renderInfo(game, x, y);
	}

	public boolean isDuck() {
		return duck;
	}

}
