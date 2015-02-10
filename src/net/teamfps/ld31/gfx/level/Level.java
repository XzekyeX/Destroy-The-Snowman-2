package net.teamfps.ld31.gfx.level;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import net.teamfps.ld31.Input;
import net.teamfps.ld31.gfx.Game;
import net.teamfps.ld31.gfx.Sprite;
import net.teamfps.ld31.gfx.level.entities.Entity;
import net.teamfps.ld31.gfx.level.entities.Fence;
import net.teamfps.ld31.gfx.level.entities.Player;
import net.teamfps.ld31.gfx.level.entities.Projectile;
import net.teamfps.ld31.gfx.level.entities.Snowman;
import net.teamfps.ld31.gfx.level.entities.SnowmanChild;
import net.teamfps.ld31.gfx.level.entities.Text;

public class Level {
	private Game game;
	private List<Projectile> projectiles = new ArrayList<Projectile>();
	private List<SnowmanChild> children = new ArrayList<SnowmanChild>();
	private List<Text> texts = new ArrayList<Text>();
	private int lvl = 0;
	private Snowman sm;
	private Player player;
	private Fence[] fence = new Fence[3];

	public Level(Game game) {
		this.game = game;
		init();
	}

	public void add(Entity e) {
		e.init(this);
		if (e instanceof SnowmanChild) {
			children.add((SnowmanChild) e);
		} else if (e instanceof Projectile) {
			projectiles.add((Projectile) e);
		} else if (e instanceof Text) {
			texts.add((Text) e);
		}
	}

	private void init() {
		clearAll();
		int h = game.getHeight();
		lvl += 1;
		sm = new Snowman(game.getWidth() - 192, h - 256, lvl);
		sm.init(this);
		player = new Player(16, h - 128);
		player.init(this);
		for (int i = 0; i < fence.length; i++) {
			fence[i] = new Fence(128 + i * 42, h - 128, i);
		}
	}

	private void clearAll() {
		children.clear();
		projectiles.clear();
		texts.clear();
	}

	private int delay = 20;

	public void update() {
		if (delay > 0) delay--;
		if (Input.equalsKey(KeyEvent.VK_I) && delay == 0) {
			delay = 20;
			init();
		}
		if (sm != null) {
			sm.update(game);
			if (sm.isRemoved()) {
				sm = null;
				init();
				return;
			}
		}
		if (player != null) {
			player.update(game);
			if (player.isRemoved()) {
				player = null;
				lvl = 0;
				init();
				return;
			}
		}
		for (int i = 0; i < fence.length; i++) {
			if (fence[i] != null) {
				fence[i].update(game);
				if (fence[i].isRemoved()) {
					fence[i] = null;
				}
			}
		}
		for (int i = 0; i < children.size(); i++) {
			children.get(i).update(game);
			if (children.get(i).isRemoved()) {
				children.remove(i);
			}
		}

		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update(game);
			if (projectiles.get(i).isRemoved()) {
				projectiles.remove(i);
			}
		}
		for (int i = 0; i < texts.size(); i++) {
			texts.get(i).update(game);
			if (texts.get(i).isRemoved()) {
				texts.remove(i);
			}
		}
	}

	public void render() {
		game.renderSprite(Sprite.ground, 0, game.getHeight() - 90, game.getWidth(), 64);
		if (sm != null) sm.render(game);
		if (player != null) player.render(game);
		for (int i = 0; i < children.size(); i++) {
			children.get(i).render(game);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).render(game);
		}
		for (int i = 0; i < texts.size(); i++) {
			texts.get(i).render(game);
		}
		for (int i = 0; i < fence.length; i++) {
			if (fence[i] != null) {
				fence[i].render(game);
			}
		}
	}

	public void addText(String msg, int fsize, int x, int y, int life, int color) {
		add(new Text(msg, fsize, color, life, x, y));
	}

	public Game getGame() {
		return game;
	}

	public Player getPlayer() {
		return player;
	}

	public Snowman getSnowman() {
		return sm;
	}

	public List<Projectile> getProjectiles() {
		return projectiles;
	}

	public List<SnowmanChild> getChildren() {
		return children;
	}

	public int getLVL() {
		return lvl;
	}

}
