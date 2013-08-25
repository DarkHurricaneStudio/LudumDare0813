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
	
	private double timer; // the amount of time acquired
	
	private double posX; // position for the scrolling
	private double posY; // position for scrolling
	
	private Level level;
	
	
	
	
	// the damn constructor that construct the damn game
	public Updater() {
		this.player = new Player(this);
		this.posX = this.player.getPosX()-MainPanel.GAME_WIDTH/2.0;
		this.posY = this.player.getPosY()-MainPanel.GAME_HEIGHT/2.0;
		this.level = new Level(this,"others/level.zip");
	}
	
	
	
	// methods
	public void update() {
		// update of the player. No, why so serious ?
		this.player.update(this);
		
		// update of the monsters
		// But, with a lot of monster, there will be too much operations ? oO
		// So, let's check only for nearest only
		for (int i=0; i<this.level.getMobs().length;i++) {
			if (this.player.distanceTo(this.level.getMobs()[i]) < MainPanel.GAME_WIDTH)
				this.level.getMobs()[i].update(this);
		}

		
		// we update position if the player is too far from the center
		if (this.player.getPosX()+this.posX <= MainPanel.GAME_WIDTH/3)
			this.posX += Player.RUNSPEED;
		else if (this.player.getPosX()+this.posX >= MainPanel.GAME_WIDTH*2/3.0)
			this.posX -= Player.RUNSPEED;
		
		// idem with the Y-axis
		if (this.player.getPosY()+this.posY <= MainPanel.GAME_HEIGHT/3)
			this.posY += Player.GRAVITY;
		else if (this.player.getPosY()+this.player.getHeight()+this.posY >= MainPanel.GAME_HEIGHT*2/3.0)
			this.posY -= Player.GRAVITY;
	}
	
	public BufferedImage render() {
		
		BufferedImage res = new BufferedImage(MainPanel.GAME_WIDTH,MainPanel.GAME_HEIGHT,BufferedImage.TYPE_INT_ARGB);
		Graphics g = res.getGraphics();
		g.setFont(MainPanel.font);
		
		// black background for in case of
		g.setColor(Color.black);
		g.fillRect(0, 0, MainPanel.GAME_WIDTH, MainPanel.GAME_HEIGHT);
		
		// image background
		g.drawImage(this.level.getBackground(),(int)this.posX,(int)this.posY,null);
		
		// mobs
		g.setColor(Color.yellow);
		for (int i=0; i<this.level.getMobs().length;i++) {
			g.fillRect((int)(this.level.getMobs()[i].getPosX()+this.posX),(int)(this.level.getMobs()[i].getPosY()+this.posY),this.level.getMobs()[i].getWidth(),this.level.getMobs()[i].getHeight());
		}
		
		g.setColor(Color.red);
		g.fillRect((int)(this.player.getPosX()+this.posX),(int)(this.player.getPosY()+this.posY),this.player.getWidth(),this.player.getHeight());
		
		// we display the timeBonus
		g.setColor(Color.blue);
		for (int i=0; i<this.level.getTimeBonus().length;i++) {
			g.fillRect((int)(this.level.getTimeBonus()[i].getPosX()+this.posX),(int)(this.level.getTimeBonus()[i].getPosY()+this.posY),this.level.getTimeBonus()[i].getWidth(),this.level.getTimeBonus()[i].getHeight());
		}
		
		return res;
	}
	
	public Level getLevel() {
		return this.level;
	}

}
