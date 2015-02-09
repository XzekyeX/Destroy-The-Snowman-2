package net.teamfps.ld31.gfx;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.WritableRaster;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
	private BufferedImage image;
	private String path;
	private int width, height;
	private int x, y;
	private int[] pixels;
	public static Sprite snowman = new Sprite("/Snowman.png");
	public static Sprite player = new Sprite("/player.png");
	public static Sprite player_duck = new Sprite("/player_duck.png");

	public static Sprite fence = new Sprite("/fence.png");
	public static Sprite half_fence = new Sprite("/half_fence.png");
	public static Sprite full_fence = new Sprite("/full_fence.png");

	private static Sprite volume_bar = new Sprite("/volume_bar.png");
	public static Sprite volume_minus = new Sprite(volume_bar, 0, 0, 89, 97);
	public static Sprite volume_add = new Sprite(volume_bar, 1, 0, 89, 97);
	public static Sprite volume_mute = new Sprite(volume_bar, 2, 0, 89, 97);

	public static Sprite[] volume = new Sprite[] { volume_minus, volume_add, volume_mute };

	public static Sprite ground = new Sprite("/ground.png");

	public Sprite(String path) {
		this.path = path;
		load();
	}

	public Sprite(int[] pixels, int width, int height) {
		this.width = width;
		this.height = height;
		this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		this.pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		for (int i = 0; i < this.pixels.length; i++) {
			int col = pixels[i];
			if (col != 0xffff00ff) this.pixels[i] = col;
		}
	}

	public Sprite(Sprite sprite, int x, int y, int width, int height) {
		this.width = width;
		this.height = height;
		this.x = x * width;
		this.y = y * height;
		this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		this.pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		int[] pixels = load(sprite);
		for (int i = 0; i < this.pixels.length; i++) {
			int col = pixels[i];
			if (col != 0xffff00ff) this.pixels[i] = col;
		}
	}

	private int[] load(Sprite sprite) {
		int[] pixels = new int[width * height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x + y * width] = sprite.pixels[(x + this.x) + (y + this.y) * sprite.width];
			}
		}
		return pixels;
	}

	public Sprite setColor(int color) {
		Color c = new Color(color);
		WritableRaster raster = getImage().getRaster();
		for (int x = 0; x < getWidth(); x++) {
			for (int y = 0; y < getHeight(); y++) {
				int[] pixels = raster.getPixel(x, y, (int[]) null);
				pixels[0] = c.getRed();
				pixels[1] = c.getGreen();
				pixels[2] = c.getBlue();
				raster.setPixel(x, y, pixels);
			}
		}
		return this;
	}

	public static BufferedImage toBufferedImage(Image img) {
		if (img instanceof BufferedImage) {
			return (BufferedImage) img;
		}
		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();
		return bimage;
	}

	public static Sprite rotate(Sprite sprite, double angle) {
		return new Sprite(rotate(sprite.pixels, sprite.width, sprite.height, angle), sprite.width, sprite.height);
	}

	public Sprite rotate(double angle) {
		return new Sprite(rotate(pixels, width, height, angle), width, height);
	}

	private static int[] rotate(int[] pixels, int width, int height, double angle) {
		int[] result = new int[width * height];
		double nx_x = rot_X(-angle, 1.0, 0.0);
		double nx_y = rot_Y(-angle, 1.0, 0.0);
		double ny_x = rot_X(-angle, 0.0, 1.0);
		double ny_y = rot_Y(-angle, 0.0, 1.0);
		double x0 = rot_X(-angle, -width / 2.0, -height / 2.0) + width / 2.0;
		double y0 = rot_Y(-angle, -width / 2.0, -height / 2.0) + height / 2.0;

		for (int y = 0; y < height; y++) {
			double x1 = x0;
			double y1 = y0;
			for (int x = 0; x < width; x++) {
				int xx = (int) x1;
				int yy = (int) y1;
				// int col = -1;
				boolean b = (xx < 0 || xx >= width || yy < 0 || yy >= height);
				result[x + y * width] = b ? 0xff00ff : pixels[xx + yy * width];
				// result[x + y * width] = col;
				x1 += nx_x;
				y1 += nx_y;
			}
			x0 += ny_x;
			y0 += ny_y;
		}

		return result;
	}

	private static double rot_X(double angle, double x, double y) {
		double cos = Math.cos(angle);
		double sin = Math.sin(angle);
		return x * cos + y * -sin;
	}

	private static double rot_Y(double angle, double x, double y) {
		double cos = Math.cos(angle);
		double sin = Math.sin(angle);
		return x * sin + y * cos;
	}

	private void load() {
		try {
			this.image = ImageIO.read(getClass().getResource(path));
			this.width = image.getWidth();
			this.height = image.getHeight();
			this.pixels = new int[width * height];
			this.image.getRGB(0, 0, this.width, this.height, pixels, 0, this.width);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public String getPath() {
		return path;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setRGB(int x, int y, int rgb) {
		if (getImage() != null) {
			getImage().setRGB(x, y, rgb);
		}
	}

}
