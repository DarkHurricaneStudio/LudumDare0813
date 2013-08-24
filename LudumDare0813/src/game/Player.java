package game;

import settings.Settings;
import graphics.AnimatedSprite;
import gui.InputHandler;

public class Player extends LivingEntity {

	private int state,jumpForce;
	private boolean leftKey,rightKey,jumpKey;
	
	protected final static int STATE_STANDING=0;
	protected final static int STATE_RUNNING=1;
	protected final static int STATE_JUMPING=2;
	protected final static int STATE_FALLING=3;
	
	private Updater updater;
	
	private AnimatedSprite standingLeft,standingRight,jumpingLeft,jumpingRight,fallingLeft,fallingRight;
	
	public Player(Updater u) {
		
		super(0,0,null,null);
		this.updater = u;
		
		
		this.jumpForce = jumpForce;
		
		// we define the AnimatedSprite
		this.standingLeft = standingLeft;
		this.standingRight = standingRight;
		this.jumpingLeft = jumpingLeft;
		this.jumpingRight = jumpingRight;
		this.fallingLeft = fallingLeft;
		this.fallingRight = fallingRight;
		
		// we define the keys
		this.leftKey = false;
		this.rightKey = false;
		this.jumpKey = false;
		
	}
	
	public void Jump(){
		if (this.state == Player.STATE_RUNNING || this.state == Player.STATE_STANDING){
			this.speedY = -this.jumpForce;
		}
	}
	
	public void update(Updater u){
		//Key check
			if (InputHandler.input[Settings.jumpKeyCode])
				this.jumpKey = true;
			else 
				this.jumpKey = false;
			
			if (InputHandler.input[Settings.leftKeyCode])
				this.leftKey = true;
			else 
				this.leftKey = false;
			
			if (InputHandler.input[Settings.rightKeyCode])
				this.rightKey = true;
			else 
				this.rightKey = false;
			
		//State update
		
		//Position update
		
		//Direction update
		
		//Sprite display
		
	}
	
}
