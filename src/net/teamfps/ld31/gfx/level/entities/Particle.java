package net.teamfps.ld31.gfx.level.entities;

import net.teamfps.ld31.gfx.Game;

public class Particle extends Entity {
	private int life;
	private int color;
	protected double xx, yy, zz;
	protected double xa, ya, za;

	public Particle(int x, int y, int w, int h, int life, int color) {
		this.x = x;
		this.y = y;
		this.xx = x;
		this.yy = y;
		this.w = w;
		this.h = h;
		this.life = life + (rand.nextInt(100) - 50);
		this.color = color;
		this.xa = rand.nextGaussian();
		this.ya = rand.nextGaussian();
		this.zz = rand.nextFloat() + 5.0f;
	}

	public Particle(int x, int y, int life) {
		this(x, y, 4, 4, life, 0xffffff);
	}

	// private int timer = 0;

	@Override
	public void update(Game game) {
		// timer++;
		// if (timer >= 2) {
		// timer = 0;
		// life -= 1;
		// }
		life--;
		if (life <= 0) {
			remove();
		}
		za -= 0.1;
		if (zz < 0) {
			za = 0;
			za *= -0.8;
			xa *= 0.4;
			ya *= 0.4;
		}
		move(xa, ya, za);
	}

	private void move(double x, double y, double z) {
		this.xx += x;
		this.yy += y;
		this.zz += z;
	}

	@Override
	public void render(Game game) {
		game.renderFillRect((int) xx, (int) yy - (int) zz, w, h, color);
	}

}
