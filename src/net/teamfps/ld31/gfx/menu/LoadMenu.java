package net.teamfps.ld31.gfx.menu;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import net.teamfps.ld31.Input;
import net.teamfps.ld31.gfx.Button;
import net.teamfps.ld31.gfx.Game;

public class LoadMenu extends Menu {
	private Page page;

	private List<Page> pages = new ArrayList<Page>();
	
	private Button left;
	private Button right;
	
	public LoadMenu(Game game) {
		super(game);
		init();
		for (int i = 0; i < 4; i++) {
			pages.add(new Page("p" + i, i * (20 * 8), 8, 20));
		}
		page = getFirstPage();
	}
	
	private void init() {
		int y = game.getHeight() - 160;
		int x = game.getWidth() - 56;
		left = new Button("<--",12,4,16,20,y,32,20);
		right = new Button("-->",12,4,16,x,y,32,20);		
	}

	public Page getFirstPage() {
		if (pages.size() > 0) {
			return pages.get(0);
		}
		return null;
	}

	public Page getPage(int index) {
		if (index >= 0 && index <= pages.size() - 1) {
			return pages.get(index);
		}
		return page;
	}

	class Page {
		private List<Button> buttons = new ArrayList<Button>();
		private Button over = null;
		private String name;
		private int delay = 20;
		private int start = 0;
		private int listSize = 20;
		private int height = 8;

		public Page(String name, int start, int height, int listSize) {
			this.name = name;
			this.start = start;
			this.height = height;
			this.listSize = listSize;
			init();
		}

		private void init() {
			int s = 38;
			buttons.clear();
			for (int j = 0; j < height; j++) {
				for (int i = j * listSize; i < j * listSize + listSize; i++) {
					int x = ((game.getWidth() / 2) - ((listSize * s) / 2)) + (i * s) - (j * listSize * s);
					int y = 160 + j * 32;
					int m = i + start;
					buttons.add(new Button("" + m, 12, 4, 16, x, y, s - 4, 20));
				}
			}
		}

		public void update(Game game) {
			for (int i = 0; i < buttons.size(); i++) {
				if (buttons.get(i).isMouseOver()) {
					over = buttons.get(i);
				}
			}
			if (delay > 0) delay--;
			if (Input.equalsKey(KeyEvent.VK_L) && delay == 0) {
				delay = 20;
				init();
			}
		}

		public void render(Game game) {
			for (int i = 0; i < buttons.size(); i++) {
				buttons.get(i).render(game);
			}
		}

		public Button getOver() {
			return over;
		}

		@Override
		public String toString() {
			return "" + name;
		}
	}

	private int select = 0;
	private int delay = 20;

	@Override
	public void update() {
		if (delay > 0) delay--;
		if (Input.equalsKey(KeyEvent.VK_ESCAPE)) {
			game.setMenu(game.getMainMenu());
		}
		
		if ((Input.equalsKey(KeyEvent.VK_LEFT)|| (left != null && left.isPressed())) && delay == 0) {
			delay = 20;
			if (select > 0) select--;
		}
		
		if ((Input.equalsKey(KeyEvent.VK_RIGHT) || (right != null && right.isPressed())) && delay == 0) {
			delay = 20;
			int size = pages.size() - 1;
			if (size >= select) select++;
		}
		if(Input.equalsKey(KeyEvent.VK_I) && delay == 0){
			delay = 20;
			init();
		}
		page = getPage(select);
		if (page != null) {
			page.update(game);
		}
	}

	@Override
	public void render() {
		game.renderString("Page: " + page, 12, 12, 48, 0xffffff);
		if (page != null) {
			game.renderString("Mouse is over: " + page.getOver(), 12, 12, 32, 0xffffff);
			page.render(game);
		}
		if(left != null) left.render(game);
		if(right != null) right.render(game);
	}

}
