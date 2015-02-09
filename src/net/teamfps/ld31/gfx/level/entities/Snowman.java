package net.teamfps.ld31.gfx.level.entities;

import net.teamfps.ld31.gfx.Game;
import net.teamfps.ld31.gfx.Sprite;

public class Snowman extends Mob {

	public Snowman(int x, int y, int lvl) {
		this.x = x;
		this.y = y;
		this.w = 180;
		this.h = 180;
		this.max_hp = 100 * (lvl * 0.6D);
		this.hp = max_hp;
		this.name = "Snowman";
		this.sprite = Sprite.snowman;
	}

	private int ShootTimer = 0;
	private int SpawnTimer = 0;
	private int bullets = 32;
	private int Spawned = 0;
	private int Max_Spawn = 10;

	@Override
	public void update(Game game) {
		if (hp <= 0) remove();
		if (level != null && level.getPlayer() != null) {
			ShootTimer++;
			if (ShootTimer >= 32 && bullets > 0) {
				ShootTimer = 0;
				bullets--;
				Shoot(x + (w / 2), y + (h / 2), level.getPlayer(), 2, 700, 2);
				System.out.println("Bullets: " + bullets);
			}
			SpawnTimer++;
			if (level != null) {
				if (SpawnTimer >= 128) {
					SpawnTimer = 0;
					if (Spawned <= Max_Spawn * level.getLVL()) {
						Spawned++;
						level.add(new SnowmanChild(this, x, y + (h / 2), level.getLVL()));
					}
				}
			}

		}
	}

	@Override
	public void render(Game game) {
		game.renderSprite(sprite, x, y, w, h);
		renderInfo(game, x, y);
	}
}
