package net.teamfps.ld31.gfx.level.entities.task;

import net.teamfps.ld31.gfx.level.entities.Mob;

public class DestroyTask extends Task {
	private WalkTask task;
	private Mob walker;
	private Mob target;
	private double damage;

	public DestroyTask(Mob walker, Mob target, double damage) {
		super("Destroy Task");
		this.walker = walker;
		this.target = target;
		this.damage = damage;
		task = new WalkTask(walker, target.getX() + 64, target.getY(), true, false);
	}

	@Override
	public boolean activate() {
		return !target.isDead() && task.activate();
	}

	@Override
	public void execute() {
		task.execute();
		if (walker.Collision(target)) {
			target.hurt(damage);
		}
	}

}
