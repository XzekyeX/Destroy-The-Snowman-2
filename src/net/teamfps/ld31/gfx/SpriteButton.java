package net.teamfps.ld31.gfx;

public class SpriteButton extends Button {
	protected Sprite sprite;

	public SpriteButton(Sprite sprite, int x, int y, int w, int h) {
		super("", 12, 0, 0, x, y, w, h);
		this.sprite = sprite;
	}

	@Override
	public void render(Game game) {
		game.renderSprite(sprite, x, y, w, h);
		game.renderRect(x, y, w, h, isMouseOver() ? 0x000000 : 0xffffff);
	}

}
