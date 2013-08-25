package game;

import java.awt.Color;

import graphics.AnimatedSprite;

public abstract class LivingEntity extends Entity {

	//Fields
	/**
	 * false = left   true = right
	 */
	protected boolean direction;

	protected AnimatedSprite runningLeft,runningRight;
	
	protected Updater updater;
	
	// static bitches
	public static double GRAVITY = 5.0;
	public static double JUMPFORCE = 40.0;
	public static double RUNSPEED = 8.0; // player speed
	public static double WALKSPEED = 4.0;
	
	
//Constructor
	public LivingEntity(Updater updater, double posX,double posY,AnimatedSprite runningLeft,AnimatedSprite runningRight){
		super(posX,posY);
		
				
		this.direction = true;

		this.runningLeft = runningLeft;
		this.runningRight = runningRight;
		
		this.updater = updater;

	}
	
//Methods
	protected boolean levelCollision() {
		// we test if the player has a collision with the level
		// to know this, we will load the cache and check at the player position
		// But we can't check every pixel at every frame, we have to have high performance test
		// in this case, we will only check all edges pixels
		boolean collision = false;

		if (this.posX >= 0 && this.posX+this.width<= this.updater.getLevel().getCache().getWidth()
				&& this.posY >= 0 && this.posY+this.height <= this.updater.getLevel().getCache().getHeight()) {
			
			// left line
			for (int i = (int) this.posY; i <= (int) (this.posY+this.height);i++) {
				try {
					int color = this.updater.getLevel().getCache().getRGB((int)this.posX,i);
					// is it red = a wall ?
					if (color == (new Color(208,0,0).getRGB())) {
						this.posX +=1;
						collision = true;
					}
				} finally {}
			}

			// right line
			for (int i = (int) this.posY; i <= (int) (this.posY+this.height);i++) {
				try {
					int color = this.updater.getLevel().getCache().getRGB((int) this.posX + this.width,i);
					// is it red = a wall ?
					if (color == (new Color(208,0,0).getRGB())) {
						this.posX -= 1;
						collision = true;
					}
				} finally {}
			}
			// bottom line
			for (int i = (int) this.posX+1; i<(int)(this.posX+this.width);i++) {
				try {
					int color = this.updater.getLevel().getCache().getRGB(i,(int) this.posY+this.height);
					// is it red = a wall ?
					if (color == (new Color(208,0,0).getRGB())) {
						this.posY -= 1;
						collision = true;
					}
				} finally {}
			}



			// top line
			for (int i = (int) this.posX+1; i<(int)(this.posX+this.width);i++) {
				try {
					int color = this.updater.getLevel().getCache().getRGB(i,(int) this.posY);
					// is it red = a wall ?
					if (color == (new Color(208,0,0).getRGB())) {
						// we update the position !
						// we touch by the top, so it's a roof
						this.posX = i;
						this.posY +=this.speedY;
						collision = true;
					}
				} finally {}

			}

		}
		return collision;
	}
}
