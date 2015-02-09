package net.teamfps.ld31.gfx.level.entities.task;

public abstract class Task {
	private String name;

	public Task(String name) {
		this.name = name;
	}

	public abstract boolean activate();

	public abstract void execute();

	public String getName() {
		return name;
	}
}