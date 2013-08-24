package levels;

import game.Updater;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Level {
	
	private BufferedImage cache;
	private BufferedImage background;
	private BufferedImage foreground;
	private byte[] other;
	
	private Updater updater;
	
	public Level(Updater u, String path) {
		this.updater = u;
		ZipReader.read(path, this);
	}
	
	public void setBackground(BufferedImage background) {
		this.background = background;
	}
	
	public void setForeground(BufferedImage foreground) {
		this.foreground = foreground;
	}
	
	public void setCache(BufferedImage cache) {
		this.cache = cache;
	}
	
	public void setOther(byte[] data) {
		this.other = other;
	}
	
	public BufferedImage getBackground() {
		return this.background;
	}
	
	public BufferedImage getForeground() {
		return this.foreground;
	}
	
	public BufferedImage getCache() {
		return this.cache;
	}
}
