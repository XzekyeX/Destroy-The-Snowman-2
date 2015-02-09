package net.teamfps.ld31.gfx;

/**
 * 
 * @author Zekye
 *
 */
public class Snowfield {
	private final float spread;
	private float speed;
	// private final float starX[];
	// private final float starY[];
	// private final float starZ[];
	private final Flake flakes[];
	private final int numSnowflakes;

	public Snowfield(int numSnowflakes, float spread, float speed) {
		this.numSnowflakes = numSnowflakes;
		this.spread = spread;
		this.speed = speed;
		flakes = new Flake[numSnowflakes];
		// starX = new float[numSnowflakes];
		// starY = new float[numSnowflakes];
		// starZ = new float[numSnowflakes];
	}

	private void InitStar(int index) {
		float x = 2 * ((float) Math.random() - 0.5f) * spread;
		float y = 2 * ((float) Math.random() - 0.5f) * spread;
		float z = ((float) Math.random() + 0.00001f) * spread;
		flakes[index] = new Flake(x, y, z);
		// starX[index] = 2 * ((float) Math.random() - 0.5f) * spread;
		// starY[index] = 2 * ((float) Math.random() - 0.5f) * spread;
		// starZ[index] = ((float) Math.random() + 0.00001f) * spread;
	}

	public void update() {
		for (int i = 0; i < numSnowflakes; i++) {
			if (flakes[i] != null) {
				flakes[i].Minus(0, 0, speed);
				if (flakes[i].getZ() <= 0) {
					InitStar(i);
				}
			} else {
				InitStar(i);
			}
		}
	}

	public void renderSnow(Game game) {
		float fov = 65.0f;
		final float tanHalfFOV = (float) Math.tan(Math.toRadians(fov / 2));
		int halfWidth = game.getWidth() / 2;
		int halfHeight = game.getHeight() / 2;
		for (int i = 0; i < numSnowflakes; i++) {
			float fx = flakes[i].getX();
			float fy = flakes[i].getY();
			float fz = flakes[i].getZ();
			int x = (int) ((fx / (fz * tanHalfFOV)) * halfWidth + halfWidth);
			int y = (int) ((fy / (fz * tanHalfFOV)) * halfHeight + halfWidth);
			int size = 4;
			game.renderFillRect(x, y, size, size, 0xffffff);
		}
	}

	public float getSpeed() {
		return speed;
	}

	class Flake {
		private float x, y, z;

		public Flake(float x, float y, float z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}

		public float getX() {
			return x;
		}

		public float getY() {
			return y;
		}

		public float getZ() {
			return z;
		}

		public void Plus(float x, float y, float z) {
			this.x += x;
			this.y += y;
			this.z += z;
		}

		public void Minus(float x, float y, float z) {
			this.x -= x;
			this.y -= y;
			this.z -= z;
		}

	}

	// class Snowflake {
	// protected float x, y, z, speed;
	// protected int size, color = 0xffffff;
	// private boolean removed = false;
	//
	// public Snowflake(float x, float y, float z, float speed, int size, int color) {
	// this.x = x;
	// this.y = y;
	// this.z = z;
	// this.speed = speed;
	// this.size = size;
	// this.color = color;
	// }
	//
	// public void update() {
	// z -= speed;
	// if (z <= 0) remove();
	// }
	//
	// public void render(Game game, float fov) {
	// final float tanHalfFOV = (float) Math.tan(Math.toRadians(fov / 2));
	// int halfWidth = game.getWidth() / 2;
	// int halfHeight = game.getHeight() / 2;
	// int xx = (int) ((x / (z * tanHalfFOV)) * halfWidth + halfWidth);
	// int yy = (int) ((y / (z * tanHalfFOV)) * halfHeight + halfWidth);
	// if (xx < 0 || xx >= game.getWidth() || yy < 0 || yy >= game.getHeight()) {
	// remove();
	// } else {
	// game.renderOval(xx, yy, size, size, color);
	// }
	// }
	//
	// public void remove() {
	// removed = true;
	// }
	//
	// public boolean isRemoved() {
	// return removed;
	// }
	//
	// public float getX() {
	// return x;
	// }
	//
	// public float getY() {
	// return y;
	// }
	//
	// public float getZ() {
	// return z;
	// }
	//
	// public int getSize() {
	// return size;
	// }
	//
	// public int getColor() {
	// return color;
	// }
	// }
}
