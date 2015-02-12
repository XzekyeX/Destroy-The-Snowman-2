package net.teamfps.ld31.gfx;

public class Status {
	private String name;
	private double lvl;
	private double max_lvl;
	private double price;

	public Status(String name, double lvl, double max_lvl, double price) {
		this.name = name;
		this.lvl = lvl;
		this.max_lvl = max_lvl;
		this.price = price;
	}

	public void IncreaseValue(double amount) {
		if (getLvl() < getMaxLvl()) this.lvl += amount;
	}

	public void ReduceValue(double amount) {
		this.lvl -= amount;
	}

	public String getName() {
		return name;
	}

	public double getLvl() {
		return lvl;
	}

	public double getMaxLvl() {
		return max_lvl;
	}

	public double getPrice() {
		return price;
	}
}