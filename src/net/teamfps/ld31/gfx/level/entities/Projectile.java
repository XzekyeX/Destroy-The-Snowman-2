package net.teamfps.ld31.gfx.level.entities;

import net.teamfps.ld31.gfx.Game;

public abstract class Projectile extends Mob {
	protected final int xOrigin, yOrigin;
	protected double angle;
	protected double xx, yy;
	protected double nx, ny;
	protected double speed, range, damage;
	protected Entity shooter;

	public Projectile(Entity shooter, int x, int y, double dir) {
		this.shooter = shooter;
		xOrigin = x;
		yOrigin = y;
		angle = dir;
		this.x = x;
		this.y = y;
		this.xx = x;
		this.yy = y;
		this.name = "Projectile";
		this.hp = 5;
		this.max_hp = hp;
	}

	@Override
	public abstract void update(Game game);

	@Override
	public abstract void render(Game game);

	protected abstract void move();

	public Entity getShooter() {
		return shooter;
	}

}
