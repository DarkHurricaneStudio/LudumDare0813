package game;

import java.awt.Color;

import graphics.AnimatedSprite;


public class Monster extends LivingEntity {

	public Monster(Updater u, double posX, double posY, AnimatedSprite runningLeft,
			AnimatedSprite runningRight) {
		super(u, posX, posY, runningLeft, runningRight);
		this.height = 50;
		this.width = 50;
		this.direction = false; // always go to the left !
	}

	@Override
	public void update(Updater u) {
		// a monster has gravity too, as a everything on earth (except light, but light doesn't have mass. Or has it ?
		this.speedY += LivingEntity.GRAVITY;
		
		// and he moves in one direction
		if (this.direction)
			this.speedX = LivingEntity.WALKSPEED;
		else
			this.speedX = -LivingEntity.WALKSPEED;
		
		// we know update positions
		this.posY += this.speedY;
		this.posX += this.speedX;
		
		// but we hate monster walking threw walls, so let's check collisions !
		if (levelCollision())
			this.speedY = 0;
		

	}
	
	// Override of the LivingEntity for movements
	protected boolean levelCollision() {
		// we test if the player has a collision with the level
		// to know this, we will load the cache and check at the player position
		// But we can't check every pixel at every frame, we have to have high performance test
		// in this case, we will only check all edges pixels
		boolean collision = false;

		if (this.posX >= 0 && this.posX+this.width<= this.updater.getLevel().getCache().getWidth()
				&& this.posY >= 0 && this.posY+this.height <= this.updater.getLevel().getCache().getHeight()) {
			
			// bottom line
			for (int i = (int) this.posX; i<=(int)(this.posX+this.width);i++) {
				try {
					int color = this.updater.getLevel().getCache().getRGB(i,(int) this.posY+this.height);
					// is it red = a wall ?
					if (color == (new Color(208,0,0).getRGB())) {
						this.posY -= 1;
						collision = true;
					}
				} finally {}
			}

			// left line
			for (int i = (int) this.posY; i <= (int) (this.posY+this.height);i++) {
				try {
					int color = this.updater.getLevel().getCache().getRGB((int)this.posX,i);
					// is it red = a wall ?
					if (color == (new Color(208,0,0).getRGB())) {
						this.posX +=1;
						collision = true;
						this.direction = !this.direction;
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
						this.direction = !this.direction;
					}
				} finally {}
			}

			// top line
			for (int i = (int) this.posX; i<=(int)(this.posX+this.width);i++) {
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
