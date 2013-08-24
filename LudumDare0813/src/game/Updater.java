package game;

import gui.MainPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import levels.Level;
import levels.ZipReader;

public class Updater {
	
	// fields
	
	private Player player;
	
	private double timer; // the amount of time aquired
	
	private double posX; // position for the scrolling
	private double posY; // position for scrolling
	
	private Level level;
	
	
	
	
	// the damn constructor that construct the damn game
	public Updater() {
		this.player = new Player(this);
		this.posX = this.player.getPosX()-MainPanel.GAME_WIDTH/2.0;
		this.posY = this.player.getPosY()-MainPanel.GAME_HEIGHT/2.0;
		this.level = new Level(this,"C:/level.zip");
	}
	
	
	
	// methods
	public void update() {
		this.player.update(this);
		
		// we update position if the player is too far from the center
		if (this.player.getPosX()+this.posX <= MainPanel.GAME_WIDTH/3)
			this.posX += Player.RUNSPEED;
		else if (this.player.getPosX()+this.posX >= MainPanel.GAME_WIDTH*2/3.0)
			this.posX -= Player.RUNSPEED;
		
		// idem with the Y-axis
		if (this.player.getPosY()+this.posY <= MainPanel.GAME_HEIGHT/4)
			this.posY += this.player.getSpeedY();
		else if (this.player.getPosY()+this.player.getHeight()+this.posY >= MainPanel.GAME_HEIGHT*3/4.0)
			this.posY -= this.player.getSpeedY();
	}
	
	public BufferedImage render() {
		
		BufferedImage res = new BufferedImage(MainPanel.GAME_WIDTH,MainPanel.GAME_HEIGHT,BufferedImage.TYPE_INT_ARGB);
		Graphics g = res.getGraphics();
		
		// black background for in case of
		g.setColor(Color.black);
		g.fillRect(0, 0, MainPanel.GAME_WIDTH, MainPanel.GAME_HEIGHT);
		
		// image background
		g.drawImage(this.level.getBackground(),(int)this.posX,(int)this.posY,null);
		
		
		
		g.setColor(Color.red);
		g.fillRect((int)(this.player.getPosX()+this.posX),(int)(this.player.getPosY()+this.posY),this.player.getWidth(),this.player.getHeight());
		
		return res;
	}
	
	public Level getLevel() {
		return this.level;
	}

}
