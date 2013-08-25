package game;

import java.awt.Color;

import settings.Settings;
import graphics.AnimatedSprite;
import gui.InputHandler;
import gui.MainPanel;

public class Player extends LivingEntity {

	private int state;
	private boolean leftKey,rightKey,jumpKey;
	
	protected final static int STATE_STANDING=0;
	protected final static int STATE_RUNNING=1;
	protected final static int STATE_JUMPING=2;
	protected final static int STATE_FALLING=3;
	
	private AnimatedSprite standingLeft,standingRight,jumpingLeft,jumpingRight,fallingLeft,fallingRight;
	
	public Player(Updater u) {
		
		super(u,400,100,null,null);
		

		
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
		
		// we define the width and the height
		this.width = 64;
		this.height = 64;
		
	}
	
	public void jump(){
		if (this.state == Player.STATE_RUNNING || this.state == Player.STATE_STANDING){
			this.speedY = -LivingEntity.JUMPFORCE;
			this.state = Player.STATE_JUMPING;
		}
	}
	
	public void update(Updater u){
		//Key check
			if (InputHandler.input[Settings.jumpKeyCode]){
				this.jumpKey = true;
			}
			else {	
				this.jumpKey = false;
			}
			
			if (InputHandler.input[Settings.leftKeyCode])
				this.leftKey = true;
			else 
				this.leftKey = false;
			
			if (InputHandler.input[Settings.rightKeyCode])
				this.rightKey = true;
			else 
				this.rightKey = false;
		
			
		// we first update the  Y speed
		this.speedY += Player.GRAVITY;
		if (this.jumpKey) {
				this.jump();
		}	
		
		// now we do the X speed
		if (this.leftKey) {
			this.speedX = -LivingEntity.RUNSPEED;
			this.direction = false;
		} else if (this.rightKey){
			this.speedX = LivingEntity.RUNSPEED;
			this.direction = true;
		} else {
			this.speedX /= 2.0;
		}
			
		//State update
		
		// falling state
		if (this.speedY > 0) {
			this.state = Player.STATE_FALLING;
		}
		
		// running state
		if (this.speedX != 0 && this.speedY == 0 && this.state == Player.STATE_STANDING) {
			this.state = Player.STATE_RUNNING;
		}
		
		if (this.speedX == 0 && this.speedY == 0 && (this.state != Player.STATE_STANDING && this.state != Player.STATE_JUMPING)) {
			this.state = Player.STATE_STANDING;
		}
		
		
		// then we update the position
		this.posY += this.speedY;
		this.posX += this.speedX;
		// we check the level collision
		if (levelCollision()) {
			this.speedY = 0;
			this.state = Player.STATE_STANDING;
		}
		
		// we check TimeBonus collision
		this.testTimeBonusCollison();
		
	}
	
	public void testTimeBonusCollison() {
		// we check if the player is hitting a TimeBonus
		// If it's true, we delete it, and add time to the timer
    	// we test all corners of the rectangle representing the player with all corners of the obstacle
    	// we test first the corner with the most risk of hitting an obstacle
    	for (int i = 0; i < this.updater.getLevel().getTimeBonus().size();i++) {
    	// right corners
    	if (this.posX+this.width >= this.updater.getLevel().getTimeBonus().get(i).getPosX() && this.posX+this.width <= this.updater.getLevel().getTimeBonus().get(i).getPosX()+this.updater.getLevel().getTimeBonus().get(i).getWidth()) {
    		// top-right corner
    		if (this.posY >= this.updater.getLevel().getTimeBonus().get(i).getPosY() && this.posY <= this.updater.getLevel().getTimeBonus().get(i).getPosY()+this.updater.getLevel().getTimeBonus().get(i).getHeight()) {
    			this.updater.getLevel().getTimeBonus().removeElementAt(i);
    			this.updater.addTime();
    			break;
    		}
    		// bottom-right corner
    		if (this.posY+this.height >= this.updater.getLevel().getTimeBonus().get(i).getPosY() && this.posY+this.height <= this.updater.getLevel().getTimeBonus().get(i).getPosY()+this.updater.getLevel().getTimeBonus().get(i).getHeight()) {
    			this.updater.getLevel().getTimeBonus().removeElementAt(i);
    			this.updater.addTime();
    			break;
    		}
    	}
    	
    	// left corners
    	if (this.posX >= this.updater.getLevel().getTimeBonus().get(i).getPosX() && this.posX <= this.updater.getLevel().getTimeBonus().get(i).getPosX()+this.updater.getLevel().getTimeBonus().get(i).getWidth()) {
    		// top-left corner
    		if (this.posY >= this.updater.getLevel().getTimeBonus().get(i).getPosY() && this.posY <= this.updater.getLevel().getTimeBonus().get(i).getPosY()+this.updater.getLevel().getTimeBonus().get(i).getHeight()) {
    			this.updater.getLevel().getTimeBonus().removeElementAt(i);
    			this.updater.addTime();
    			break;
    		}
    		// bottom-left corner
    		if (this.posY+this.height >= this.updater.getLevel().getTimeBonus().get(i).getPosY() && this.posY+this.height <= this.updater.getLevel().getTimeBonus().get(i).getPosY()+this.updater.getLevel().getTimeBonus().get(i).getHeight()) {
    			this.updater.getLevel().getTimeBonus().removeElementAt(i);
    			this.updater.addTime();
    			break;
    		}
    	}
    	}
	}



}
