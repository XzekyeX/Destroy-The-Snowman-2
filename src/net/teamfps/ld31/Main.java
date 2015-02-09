package net.teamfps.ld31;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import net.teamfps.ld31.gfx.Game;
import net.teamfps.ld31.gfx.Snowfield;

public class Main extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	private static JFrame f = new JFrame("Destroy The Snowman 2!");
	private static int width = 800; // 1024 // 800
	private static int height = 600; // 768 // 600
	private Thread thread;
	private boolean running;

	private Game game;
	private Input input;
	private Snowfield snowfield;

	private void init() {
		game = new Game(width, height);
		input = new Input();
		snowfield = new Snowfield(1000, 15f, 0.025f);
		addKeyListener(input);
		addMouseListener(input);
		addMouseWheelListener(input);
		addMouseMotionListener(input);
	}

	public void start() {
		if (running) return;
		running = true;
		thread = new Thread(this, "Main Thread!");
		thread.start();
	}

	public void stop() {
		if (!running) return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		long timer = System.currentTimeMillis();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		init();
		requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				delta--;
				update();
				updates++;
			}
			render();
			frames++;
			if (System.currentTimeMillis() - timer >= 1000) {
				timer += 1000;
				// System.out.println("fps[" + frames + "], ups[" + updates + "]");
				setFpsAndUps(frames, updates);
				frames = 0;
				updates = 0;
			}
		}
	}

	private int fps, ups;

	private void setFpsAndUps(int frames, int updates) {
		this.fps = frames;
		this.ups = updates;
	}

	public String getFpsAndUps() {
		return "fps[" + fps + "], ups[" + ups + "]";
	}

	private int FocusTimer = 60;
	private boolean FocusShow = false;
	private boolean pause = false;

	private void update() {
		if (hadFocus) {
			game.update();
			FocusShow = false;
		} else {
			if (FocusTimer > 0) FocusTimer--;
		}
		if (snowfield != null) {
			snowfield.update();
		}
		// screen.setWidth(getWidth());
		// screen.setHeight(getHeight());
	}

	private boolean hadFocus = false;

	private void render() {
		if (hadFocus != hasFocus()) {
			hadFocus = !hadFocus;
		}
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(new Color(0x000000));
		g.fillRect(0, 0, getWidth(), getHeight());
		game.render(g);
		game.renderString(getFpsAndUps(), 12, 8, 16, 0xffff00);
		if (!hadFocus && FocusTimer == 0) {
			FocusTimer = 50;
			FocusShow = !FocusShow;
		}
		if (!hadFocus && !pause) {
			// screen.renderFillRect(0, 0, getWidth(), getHeight(), new Color(0, 0, 0, 160));
			if (FocusShow) {
				game.renderString("Click To Focus!", 32, getWidth() / 2 - 160, getHeight() / 2, 0xff00ff);
			}
		}
		if (snowfield != null) {
			snowfield.renderSnow(game);
		}
		//
		// screen.renderFillRect(getScreenWidth() / 2, 0, 2, getScreenHeight(), 0xff0000);
		//
		// screen.renderFillRect(0, getScreenHeight() / 2, getScreenWidth(), 2, 0xff0000);

		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		Main m = new Main();
		f.add(m, "Center");
		f.pack();
		f.setSize(new Dimension(width, height));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.setVisible(true);
		m.start();
	}

	public static int getScreenWidth() {
		return width;
	}

	public static int getScreenHeight() {
		return height;
	}

}
