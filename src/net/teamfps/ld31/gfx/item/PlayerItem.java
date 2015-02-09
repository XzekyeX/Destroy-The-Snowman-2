package net.teamfps.ld31.gfx.item;

public class PlayerItem extends Item {

	public PlayerItem() {
		super("Player");
		add("Maxium HP", 100, 1000, 470);
		add("FireRate", 8, 100, 470);
		add("Movement Speed", 1.0D, 10.0D, 710);
	}

}
