package levels;

import game.Monster;
import game.Updater;

import java.awt.image.BufferedImage;

public class Level {
	
	private BufferedImage cache;
	private BufferedImage background;
	private BufferedImage foreground;
	private byte[] other; // other stuff to be load ? like a text file, or another image
	private Monster[] mobs;
	
	
	private Updater updater;
	
	public Level(Updater u, String path) {
		this.updater = u;
		ZipReader.read(path, this);
		
		// test
		this.mobs = new Monster[1];
		this.mobs[0] = new Monster(u,600,100,null,null);
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
	
	public Monster[] getMobs() {
		return this.mobs;
	}
}
