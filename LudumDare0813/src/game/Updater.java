package game;

public class Updater {
	
	// fields
	
	private Player player;
	// all timeBonus from this level
	private Level level;
	
	
	
	// the damn constructor that construct the damn game
	public Updater() {
		this.player = new Player(this);
		this.level = new Level(this);
	}
	
	
	
	// methods
	public void update() {
		this.player.update(this);
	}

}
