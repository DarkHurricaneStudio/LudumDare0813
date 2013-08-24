package game;

import gui.MainPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Updater {
	
	// fields
	
	private Player player;
	
	
	
	
	// the damn constructor that construct the damn game
	public Updater() {
		this.player = new Player(this);
	}
	
	
	
	// methods
	public void update() {
		this.player.update(this);
	}
	
	public BufferedImage render() {
		
		BufferedImage res = new BufferedImage(MainPanel.GAME_WIDTH,MainPanel.GAME_HEIGHT,BufferedImage.TYPE_INT_ARGB);
		Graphics g = res.getGraphics();
		
		g.setColor(Color.yellow);
		g.fillRect(0, 0, MainPanel.GAME_WIDTH, MainPanel.GAME_HEIGHT);
		
		g.setColor(Color.red);
		g.fillRect((int)(this.player.getPosX()),(int)(this.player.getPosY()),this.player.getWidth(),this.player.getHeight());
		
		return res;
	}

}
