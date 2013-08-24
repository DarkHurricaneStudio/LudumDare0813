package game;

import graphics.AnimatedSprite;

public abstract class LivingEntity extends Entity {

	//Fields
	
	
	/**
	 * false = left   true = right
	 */
	private boolean direction;

	private AnimatedSprite runningLeft,runningRight;
	
//Constructor
	public LivingEntity(double posX,double posY,AnimatedSprite runningLeft,AnimatedSprite runningRight){
		super(posX,posY);
		
				
		this.direction = true;

		this.runningLeft = runningLeft;
		this.runningRight = runningRight;

	}
	
//Methods
}
