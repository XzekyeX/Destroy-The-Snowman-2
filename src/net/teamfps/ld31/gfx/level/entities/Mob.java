package net.teamfps.ld31.gfx.level.entities;

import net.teamfps.ld31.gfx.Game;
import net.teamfps.ld31.gfx.Sprite;

public abstract class Mob extends Entity {
	protected double hp, max_hp;
	protected int dir = 0;
	protected Sprite sprite;

	@Override
	public abstract void update(Game game);

	@Override
	public abstract void render(Game game);

	public void renderRect(Game game, int color) {
		game.renderRect(x, y, w, h, color);
	}

	public void move(int xa, int ya) {
		if (xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}
		if (level != null) {
			x += xa;
			y += ya;
		}
	}

	public double getHp() {
		return hp;
	}

	public double getMax_Hp() {
		return max_hp;
	}

	public void hurt(double amount) {
		if (hp > 0) hp -= amount;
	}

	public void heal(double amount) {
		hp += amount;
	}

	public void setHp(double hp) {
		this.hp = hp;
	}

	public void Shoot(int x, int y, Entity target) {
		// System.out.println("Shooting: " + target);
		double dx = (target.getX() - x);
		double dy = (target.getY() - y);
		double dir = Math.atan2(dy, dx);
		if (level != null) level.add(new Snowball(this, x, y, dir));
	}

	public void Shoot(int x, int y, Entity target, double speed, double range, double damage) {
		double dx = (target.getX() - x);
		double dy = (target.getY() - y);
		double dir = Math.atan2(dy, dx);
		if (level != null) level.add(new Snowball(this, x, y, dir, speed, range, damage));
	}

	public void Shoot(int x, int y, int size, Entity target, double speed, double range, double damage) {
		double dx = ((target.getX() + (target.getW() / 2)) - x);
		double dy = ((target.getY() + (target.getH() / 2)) - y);
		double dir = Math.atan2(dy, dx);
		if (level != null) level.add(new Snowball(this, x, y, size, size, dir, speed, range, damage));
	}

	public void renderInfo(Game game, int x, int y) {
		double max = (hp / max_hp);
		game.renderFillRect(x, y - 16, (int) (w * max), 8, 0xff0000);
		String hp = String.format("%.2f", getHp());
		String max_hp = String.format("%.2f", getMax_Hp());
		game.renderString("" + hp + "/" + max_hp, 12, x, y - 22, 0xffffff);
		game.renderString(getName(), 12, x, y - 42, 0xffffff);
	}

	public boolean isDead() {
		return (hp <= 0);
	}

}
