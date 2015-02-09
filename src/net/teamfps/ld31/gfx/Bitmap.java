package net.teamfps.ld31.gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.io.IOException;

public class Bitmap {
	private int width, height;
	private Graphics g;
	private Font font;

	public Bitmap(int width, int height) {
		this.width = width;
		this.height = height;
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/nokiafc22.ttf"));
			ge.registerFont(font);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	public void initGFX(Graphics g) {
		this.g = g;
	}

	public void renderString(String str, int fsize, int x, int y, int color) {
		g.setColor(new Color(color));
		// g.setFont(new Font("Tahoma", 1, fsize));
		g.setFont(new Font("Nokia Cellphone FC", 1, fsize));
		g.drawString(str, x, y);
		g.setColor(new Color(0x000000));
	}

	public void renderRect(int x, int y, int w, int h, int color) {
		g.setColor(new Color(color));
		g.drawRect(x, y, w, h);
		g.setColor(new Color(0x000000));
	}

	public void renderFillRect(int x, int y, int w, int h, int color) {
		g.setColor(new Color(color));
		g.fillRect(x, y, w, h);
		g.setColor(new Color(0x000000));
	}

	public void renderFillRect(int x, int y, int w, int h, Color color) {
		g.setColor(color);
		g.fillRect(x, y, w, h);
		g.setColor(new Color(0x000000));
	}

	public void renderOval(int x, int y, int w, int h, int color) {
		g.setColor(new Color(color));
		g.fillOval(x, y, w, h);
		g.setColor(new Color(0x000000));
	}

	public void renderSprite(Sprite sprite, int x, int y, int w, int h) {
		if (sprite != null && sprite.getImage() != null) g.drawImage(sprite.getImage(), x, y, w, h, null);
	}

	public void renderSprite(Sprite sprite, int x, int y, int w, int h, boolean flipX) {
		if (flipX) {
			w = -w;
			x -= w;
		}
		if (sprite != null && sprite.getImage() != null) g.drawImage(sprite.getImage(), x, y, w, h, null);
	}

	public void renderSprite(Sprite sprite, int col, int x, int y, int w, int h, boolean flipX) {
		if (flipX) {
			w = -w;
			x -= w;
		}
		if (sprite != null && sprite.getImage() != null) {
			// sprite.setColor(col);
			g.drawImage(sprite.getImage(), x, y, w, h, null);
		}
	}

	public void renderLine(int x, int y, int x1, int y1, int color) {
		g.setColor(new Color(color));
		g.drawLine(x, y, x1, y1);
		g.setColor(new Color(0x000000));
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
