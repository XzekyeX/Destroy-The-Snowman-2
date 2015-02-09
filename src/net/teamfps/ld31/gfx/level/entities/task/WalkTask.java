package net.teamfps.ld31.gfx.level.entities.task;

import net.teamfps.ld31.gfx.level.entities.Mob;

public class WalkTask extends Task {

	private Mob walker;
	private int tx, ty;
	private boolean allowHorizontal = true;
	private boolean allowVertical = true;

	public WalkTask(Mob walker, int tx, int ty, boolean allowHorizontal, boolean allowVertical) {
		super("Walk Task");
		this.walker = walker;
		this.tx = tx;
		this.ty = ty;
		this.allowHorizontal = allowHorizontal;
		this.allowVertical = allowVertical;
	}

	@Override
	public boolean activate() {
		return !(walker.getX() == tx && walker.getY() == ty);
	}

	@Override
	public void execute() {
		int xa = 0;
		int ya = 0;
		if (allowHorizontal) {
			if (tx < walker.getX()) {
				xa = -1;
			}
			if (tx > walker.getX()) {
				xa = 1;
			}
		}
		if (allowVertical) {
			if (ty < walker.getY()) {
				ya = -1;
			}
			if (ty > walker.getY()) {
				ya = 1;
			}
		}
		walker.move(xa, ya);
	}
}
