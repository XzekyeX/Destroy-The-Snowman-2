package net.teamfps.ld31.gfx.level.entities;

import java.util.List;

import net.teamfps.ld31.gfx.Game;

public class Snowball extends Projectile {
	private Entity shooter;

	public Snowball(Entity shooter, int x, int y, double dir) {
		super(x, y, dir);
		this.shooter = shooter;
		this.w = 16;
		this.h = 16;
		this.range = 200.0D;
		this.damage = 10.0D;
		this.speed = 2.0D;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
		this.name = "Snowball";
	}

	public Snowball(Entity shooter, int x, int y, double dir, double speed, double range, double damage) {
		super(x, y, dir);
		this.shooter = shooter;
		this.w = 16;
		this.h = 16;
		this.range = range;
		this.damage = damage;
		this.speed = speed;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
		this.name = "Snowball";
	}

	public Snowball(Entity shooter, int x, int y, int w, int h, double dir, double speed, double range, double damage) {
		super(x, y, dir);
		this.shooter = shooter;
		this.w = w;
		this.h = h;
		this.range = range;
		this.damage = damage;
		this.speed = speed;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
		this.name = "Snowball";
	}

	@Override
	public void update(Game game) {
		// CollisionWithMobs();
		CollisionWithSnowman();
		CollisionWithPlayer();
		CollisionWithProjectiles();
		CollisionWithSnowmanChildren();
		move();
	}

	@Override
	public void render(Game game) {
		game.renderOval(x, y, w, h, 0xffffff);
		// screen.renderFillRect((int) x, (int) y, w, h, 0xffffff);
	}

	private void CollisionWithProjectiles() {
		if (level != null) {
			List<Projectile> projectiles = level.getProjectiles();
			for (int i = 0; i < projectiles.size(); i++) {
				Projectile m = projectiles.get(i);
				if (m != null && m != shooter && m != this) {
					if (Collision(m)) {
						m.hurt(damage);
						// m.remove();
						level.addText("-" + damage, 12, x, y, 64, 0xff0000);
						remove();
					}
				}
			}
		}
	}

	//
	// private void CollisionWithSnowmans() {
	// if (level != null) {
	// List<Snowman> snowmans = level.getSnowmans();
	// for (int i = 0; i < snowmans.size(); i++) {
	// Snowman sm = snowmans.get(i);
	// if (sm != null && sm != shooter) {
	// if (Collision(sm)) {
	// sm.hurt(damage);
	// level.addText("-" + damage, 12, x, y, 64, 0xff0000);
	// remove();
	// }
	// }
	// }
	// }
	// }

	//
	private void CollisionWithSnowmanChildren() {
		if (level != null) {
			List<SnowmanChild> children = level.getChildren();
			for (int i = 0; i < children.size(); i++) {
				SnowmanChild sc = children.get(i);
				if (sc != null && sc != shooter && sc.getSnowman() != shooter) {
					if (Collision(sc)) {
						sc.hurt(damage);
						level.addText("-" + damage, 12, x, y, 64, 0xff0000);
						remove();
					}
				}
			}
		}
	}

	private void CollisionWithSnowman() {
		if (level != null) {
			Snowman sm = level.getSnowman();
			if (sm != null && sm != shooter) {
				if (Collision(sm)) {
					sm.hurt(damage);
					level.addText("-" + damage, 12, x, y, 64, 0xff0000);
					remove();
				}
			}
		}
	}

	private void CollisionWithPlayer() {
		if (level != null) {
			Player p = level.getPlayer();
			if (p != null && p != shooter && !p.isDuck()) {
				if (Collision(p)) {
					p.hurt(damage);
					level.addText("-" + damage, 12, x, y, 64, 0xff0000);
					remove();
				}
			}
		}
	}

	//
	// @Override
	protected void move() {
		xx += nx;
		yy += ny;
		this.x = (int) xx;
		this.y = (int) yy;
		if (distance() >= range) {
			remove();
		}
	}

	private double distance() {
		double dist = 0;
		dist = Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x)) + ((yOrigin - y) * (yOrigin - y)));
		return dist;
	}

}
