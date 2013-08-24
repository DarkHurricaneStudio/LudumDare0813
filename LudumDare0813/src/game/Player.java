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
	
	// statics fields
	public static double GRAVITY = 5.0;
	public static double JUMPFORCE = 40.0;
	public static double RUNSPEED = 15.0;
	
	
	
	private Updater updater;
	
	private AnimatedSprite standingLeft,standingRight,jumpingLeft,jumpingRight,fallingLeft,fallingRight;
	
	public Player(Updater u) {
		
		super(400,100,null,null);
		this.updater = u;
		

		
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
			this.speedY = -Player.JUMPFORCE;
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
			this.speedX = -Player.RUNSPEED;
			this.direction = false;
		} else if (this.rightKey){
			this.speedX = Player.RUNSPEED;
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
		//Sprite display
		
	}

	private boolean levelCollision() {
		// we test if the player has a collision with the level
		// to know this, we will load the cache and check at the player position
		// But we can't check every pixel at every frame, we have to have high performance test
		// in this case, we will only check all edges pixels
		boolean collision = false;

		if (this.posX >= 0 && this.posX+this.width<= this.updater.getLevel().getCache().getWidth()
			&& this.posY >= 0 && this.posY+this.height <= this.updater.getLevel().getCache().getHeight()) {
			// top line
			for (int i = (int) this.posX; i<(int)(this.posX+this.width);i++) {
				int color = this.updater.getLevel().getCache().getRGB(i,(int) this.posY);
				// is it red = a wall ?
				if (color == (new Color(208,0,0).getRGB())) {
					// we update the position !
					// we touch by the top, so it's a roof
					this.posX = i;
					this.posY +=this.speedY;
					collision = true;
				}

			}
			// bottom line
			for (int i = (int) this.posX; i<(int)(this.posX+this.width);i++) {
				int color = this.updater.getLevel().getCache().getRGB(i,(int) this.posY+this.height);
				// is it red = a wall ?
				if (color == (new Color(208,0,0).getRGB())) {
					this.posY -= 1;
					collision = true;
				}
			}
		}
		return collision;
	}

}
