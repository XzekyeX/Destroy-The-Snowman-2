package net.teamfps.ld31.gfx;

public class Status {
	private String name;
	private double value;
	private double max;
	private double price;

	public Status(String name, double value, double max, double price) {
		this.name = name;
		this.value = value;
		this.max = max;
		this.price = price;
	}

	public void IncreaseValue(double amount) {
		if (getValue() < getMax()) this.value += amount;
	}

	public void ReduceValue(double amount) {
		this.value -= amount;
	}

	public String getName() {
		return name;
	}

	public double getValue() {
		return value;
	}

	public double getMax() {
		return max;
	}
	
	public double getPrice() {
		return price;
	}
}