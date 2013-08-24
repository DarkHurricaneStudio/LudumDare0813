package game;

public class Updater {
	
	// fields
	
	Player player;
	// all timeBonus from this level
	TimeBonus[] level;
	
	
	
	// the damn constructor that construct the damn game
	public Updater() {
		this.player = new Player(this);
	}
	
	
	
	// methods
	public void update() {
		this.player.update(this);
	}

}
