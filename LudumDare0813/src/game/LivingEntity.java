package game;

import java.awt.Color;

import graphics.AnimatedSprite;

public abstract class LivingEntity extends Entity {

	//Fields
	/**
	 * false = left   true = right
	 */
	protected boolean direction;
	protected int state;
	protected AnimatedSprite runningLeft,runningRight;
	
	protected Updater updater;
	
	// static bitches
	public static double GRAVITY = 5.0;
	public static double JUMPFORCE = 35.0;
	public static double RUNSPEED = 4.0; // player speed
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
	protected boolean horizontalLevelCollision() {
		// to know if the player can move, we test every pixel for the next position (except the one where the player is, of course)
		int direction = (int) (this.speedX/Math.abs(this.speedX)); // -1 for left, 1 for right)

		double startPos = this.posX;
		if (direction == 1)
			startPos +=this.width;

		for (int i = 1; i <= Math.abs(this.speedX);i++) {
			for (int j = (int) this.posY;j< (this.posY+this.height-3) ;j++) { // we delete 3px because of the slopes
				
				// we test if the position is in the cache (if not, there will be a bad, bad bug
				if (startPos+i*direction >= 0 && startPos+i*direction <= this.updater.getLevel().getCache().getWidth() && j >= 0 && j <= this.updater.getLevel().getCache().getHeight()) {
					
					// we test this pixel.
					int color = this.updater.getLevel().getCache().getRGB((int) startPos+(i+1)*direction,j);
					// there is a collision ? bad
					if (color == (new Color(208,0,0).getRGB())) {
						// so this position cannot be joined, we get back to the previous line
						this.posX += (i-1)*direction;

						// and we have a collision, so we stop the speed
						this.speedX = 0;
						// and we stop the test
						return true;
					}
				}
			}

			
		}
		// we finish all the tests, we don't hit anything, it's good !
		// so let's move
		if (this.speedX != 0) {
			this.posX += this.speedX;
		}
		return true;	
			
	}
	
	protected boolean verticalLevelCollision() {
		// we do the same for the Y-axis
		int direction = (int) (this.speedY/Math.abs(this.speedY)); // -1 for top, 1 for bottom)
		double startPos = this.posY;
		if (direction == 1)
			startPos +=this.height;
		
		for (int i = -3; i <= Math.abs(this.speedY);i++) {
			for (int j = (int) this.posX; j<= (this.posX+this.width);j++) {
				
				// we check each pixel. if there is a collision, we do the same thing for the X-axis
				if (j >= 0 && j <= this.updater.getLevel().getCache().getWidth() && startPos+i*direction >= 0 && startPos+i*direction <= this.updater.getLevel().getCache().getHeight()) {
					
					// we test this pixel.
					int color = this.updater.getLevel().getCache().getRGB(j,(int) startPos+i*direction);
					// there is a collision ? bad
					if (color == (new Color(208,0,0).getRGB())) {
						// so this position cannot be joined, we get back to the previous line
						this.posY += (i)*direction;

						// and we have a collision, so we stop the speed
						this.speedY = 0;
						this.state = Player.STATE_STANDING;
						// and we stop the test
						return true;
					}
				}
			}
		}
		// we finish all the tests, we don't hit anything, it's good !
		// so let's move
		this.posY += this.speedY;
		return true;
	}
}
