package net.teamfps.ld31.gfx.menu;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import net.teamfps.ld31.Input;
import net.teamfps.ld31.gfx.Button;
import net.teamfps.ld31.gfx.Game;

public class MainMenu extends Menu {
	// private Button play;
	// private Button exit;
	private List<Button> buttons = new ArrayList<Button>();
	private int selected;

	public MainMenu(Game game) {
		super(game);
		Button play = new Button("Play", 16, 40, 17, game.getWidth() / 2 - 64, game.getHeight() / 2 - 128, 128, 20);
		Button exit = new Button("Exit", 16, 40, 17, game.getWidth() / 2 - 64, game.getHeight() / 2 - (100), 128, 20);
		buttons.add(play);
		buttons.add(exit);
	}

	private int delay = 20;

	@Override
	public void update() {
		if (delay > 0) delay--;
		Button play = getButton("Play");
		Button exit = getButton("Exit");
		if (play != null && play.isPressed() && delay == 0) {
			delay = 20;
			game.setMenu(game.getPlayMenu());
		}
		if (exit != null && exit.isPressed()) {
			System.exit(1);
		}
		if (Input.equalsKey(KeyEvent.VK_UP) && delay == 0) {
			delay = 20;
			if (selected > 0) selected--;
		}
		if (Input.equalsKey(KeyEvent.VK_DOWN) && delay == 0) {
			delay = 20;
			if (selected < buttons.size() - 1) selected++;
		}
	}

	public Button getButton(int index) {
		if (index >= 0 && index <= buttons.size() - 1) {
			return buttons.get(index);
		}
		return null;
	}

	public Button getButton(String s) {
		for (int i = 0; i < buttons.size(); i++) {
			if (buttons.get(i).getText().contains(s)) {
				return buttons.get(i);
			}
		}
		return null;
	}

	@Override
	public void render() {
		// if (play != null) play.render(game);
		// if (exit != null) exit.render(game);
		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).render(game);
		}
		Button button = getButton(selected);
		System.out.println("button: " + button);
		if (button != null) {
			int bx = button.getX();
			int by = button.getY();
			int bw = button.getW();
			int bh = button.getH();
			game.renderString("<--", 12, bx + bw + 4, by + bh - 5, 0xffffff);
		}
	}

}
