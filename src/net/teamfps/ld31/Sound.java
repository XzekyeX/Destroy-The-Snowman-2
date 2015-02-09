package net.teamfps.ld31;

import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
	private String path;
	private Clip clip;
	public static Sound hurt = new Sound("/bow.wav");
	public static Sound pickup = new Sound("/Pickup_Coin.wav");
	public static Sound powerup = new Sound("/Powerup.wav");
	public static Sound explosion = new Sound("/Explosion.wav");

	private static float volume = 1.0f;
	private static float max_volume;
	private static float min_volume;

	public Sound(String path) {
		this.path = path;
	}

	public Sound play() {
		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(getClass().getResource(path)));
			FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			max_volume = control.getMaximum();
			min_volume = control.getMinimum();
			control.setValue(volume);
			clip.start();
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
		return this;
	}

	public static void Increase(float amount) {
		if (max_volume <= volume) {
			volume = max_volume;
		} else {
			volume += amount;
		}
	}

	public static void Decrease(float amount) {
		if (min_volume >= volume) {
			volume = min_volume;
		} else {
			volume -= amount;
		}
	}

	public static void Mute() {
		volume = min_volume;
	}

	public static float getVolume() {
		return volume;
	}

}
