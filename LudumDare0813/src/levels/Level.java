package levels;

import game.Monster;
import game.TimeBonus;
import game.Updater;

import java.awt.image.BufferedImage;
import java.io.UnsupportedEncodingException;
import java.util.Vector;

public class Level {
	
	private BufferedImage cache;
	private BufferedImage background;
	private BufferedImage foreground;
	private Monster[] mobs;
	private TimeBonus[] timeBonus;
	private double spawnX;
	private double spawnY;
	
	
	private Updater updater;
	
	public Level(Updater u, String path) {
		this.updater = u;
		ZipReader.read(path, this);
		

	}
	


	public void loadTextFile(String data) {
		
		String[] lines;
		String[] stringBuffer;
		Vector<Monster> bufferMobs = new Vector<Monster>();
		Vector<TimeBonus> bufferTimer = new Vector<TimeBonus>();
		
		
		lines = data.split("/n");

		for (int i = 0; i < lines.length;i++) {
			stringBuffer = lines[i].split(":");
			switch (stringBuffer[0]) {
			case "Spawn":
				this.spawnX = Double.parseDouble(stringBuffer[1]);
				this.spawnY = Double.parseDouble(stringBuffer[2]);
				break;
			case "Monster":
				bufferMobs.add(new Monster(this.updater, Double.parseDouble(stringBuffer[1]), Double.parseDouble(stringBuffer[2])));
				break;
			case "TimeBonus":
				bufferTimer.add(new TimeBonus(Double.parseDouble(stringBuffer[1]), Double.parseDouble(stringBuffer[2])));
				break;
			}
		}
		
		// we have all ? Well, let's stock it in the real fields
		this.mobs = new Monster[bufferMobs.size()];
		for (int i = 0; i < bufferMobs.size(); i++) {
			this.mobs[i] = bufferMobs.get(i);
		}
		this.timeBonus = new TimeBonus[bufferTimer.size()];
		for (int i = 0; i < bufferTimer.size(); i++) {
			this.timeBonus[i] = bufferTimer.get(i);
		}
		
		
		
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
		if (this.mobs == null)
			this.mobs = new Monster[0];
		
		return this.mobs;
	}
}
