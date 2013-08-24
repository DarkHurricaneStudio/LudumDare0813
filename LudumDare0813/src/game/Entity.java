package game;

import settings.Settings;
import graphics.AnimatedSprite;
import gui.InputHandler;

public class Entity {

	//Fields
		private double posX,posY,speedX,speedY;
		
		private int state,jumpForce;
		
		/**
		 * false = left   true = right
		 */
		private boolean direction;
		protected final static int STATE_STANDING=0;
		protected final static int STATE_RUNNING=1;
		protected final static int STATE_JUMPING=2;
		protected final static int STATE_FALLING=3;
		
		private AnimatedSprite standingLeft,standingRight,runningLeft,runningRight,jumpingLeft,jumpingRight,fallingLeft,fallingRight;
		
		private boolean leftKey,rightKey,jumpKey;
	//Constructor
		public Entity(double posX,double posY,int jumpForce,AnimatedSprite standingLeft,AnimatedSprite standingRight,AnimatedSprite runningLeft,AnimatedSprite runningRight,AnimatedSprite jumpingLeft,AnimatedSprite jumpingRight,AnimatedSprite fallingLeft,AnimatedSprite fallingRight){
			this.posX = posX;
			this.posY = posY;
			this.speedX = 0;
			this.speedY = 0;
			
			//State 0 is always standing
			this.state = Entity.STATE_STANDING;						
			this.jumpForce = jumpForce;
			this.direction = true;
			
			this.standingLeft = standingLeft;
			this.standingRight = standingRight;
			this.runningLeft = runningLeft;
			this.runningRight = runningRight;
			this.jumpingLeft = jumpingLeft;
			this.jumpingRight = jumpingRight;
			this.fallingLeft = fallingLeft;
			this.fallingRight = fallingRight;
		}
		
	//Methods
		public void Jump(){
			if (this.state == Entity.STATE_RUNNING || this.state == Entity.STATE_STANDING){
				this.speedY = -this.jumpForce;
			}
		}
		
		public void update(){
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
