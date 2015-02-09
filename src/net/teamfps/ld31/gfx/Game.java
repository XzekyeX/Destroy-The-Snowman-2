package net.teamfps.ld31.gfx;

import java.awt.Graphics;
import net.teamfps.ld31.Sound;
import net.teamfps.ld31.gfx.menu.LoadMenu;
import net.teamfps.ld31.gfx.menu.MainMenu;
import net.teamfps.ld31.gfx.menu.Menu;
import net.teamfps.ld31.gfx.menu.PlayMenu;

public class Game extends Bitmap implements Screen {
	private long money = 10000;
	private Menu menu;
	private MainMenu mainmenu;
	private PlayMenu playmenu;
	private LoadMenu loadmenu;

	// private Snowfield snowfield;
	// private SpriteButton[] volume;

	public Game(int width, int height) {
		super(width, height);
		// init();
		mainmenu = new MainMenu(this);
		playmenu = new PlayMenu(this);
		loadmenu = new LoadMenu(this);
		menu = mainmenu;
		// volume = new SpriteButton[Sprite.volume.length];
		// for (int i = 0; i < volume.length; i++) {
		// volume[i] = new SpriteButton(Sprite.volume[i], width - (160 + 16) + i * 52, height - (48 * 2), 48, 50);
		// }

		// snowfield = new Snowfield(1000,15f,0.025f);
	}

	private int delay = 20;

	public void update() {
		if (delay > 0) delay--;

		if (menu != null) {
			menu.update();
		}
		// if (volume != null) {
		// for (int i = 0; i < volume.length; i++) {
		// if (volume[i] != null) {
		// if (i == 0) {
		// if (volume[i].isPressed()) {
		// Sound.Decrease(0.5f);
		// System.out.println("Sound.volume: " + Sound.getVolume());
		// }
		// }
		// if (i == 1) {
		// if (volume[i].isPressed()) {
		// Sound.Increase(0.5f);
		// System.out.println("Sound.volume: " + Sound.getVolume());
		// }
		// }
		// if (i == 2) {
		// if (volume[i].isPressed()) {
		// Sound.Mute();
		// System.out.println("Sound.volume: " + Sound.getVolume());
		// }
		// }
		// }
		// }
		// }

	}

	public void render(Graphics g) {
		initGFX(g);
		render();
	}

	public void render() {
		renderFillRect(0, 0, getWidth(), getHeight(), 0x006cff);
		if (menu != null) {
			menu.render();
		}
		// if (volume != null) {
		// for (int i = 0; i < volume.length; i++) {
		// volume[i].render(this);
		// }
		// renderString("Volume: " + Sound.getVolume(), 12, getWidth() - 174, getHeight() - 32, 0xffff00);
		// }
	}

	public PlayMenu getPlayMenu() {
		return playmenu;
	}

	public MainMenu getMainMenu() {
		return mainmenu;
	}

	public LoadMenu getLoadMenu() {
		return loadmenu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public void addMoney(long amount) {
		this.money += amount;
	}

	public void loseMoney(long amount) {
		this.money -= amount;
	}

	public long getMoney() {
		return money;
	}

}
