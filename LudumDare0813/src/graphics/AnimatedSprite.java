package graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class AnimatedSprite extends Sprite {

	// Fields
	/**
		 * 
		 */
	private final int cycleLength;

	/**
		 * 
		 */
	private int currentCycle;

	/**
		 * 
		 */
	private final int height;

	/**
		 * 
		 */
	private final int width;

	/**
		 * 
		 */
	private final int posY;

	/**
		 * 
		 */
	private final int waitingTime;

	/**
		 * 
		 */
	private int waitingCounter;

	// Constructors
	/**
	 * 
	 * @param path
	 * @param cycleLength
	 * @param states
	 */
	public AnimatedSprite(String path, int cycleLength,int y, int width, int height, int waitingTime) {
		super(path);

		this.cycleLength = cycleLength;
		this.currentCycle = 0;

		this.width = width;
		this.height = height;

		this.posY = y;

		this.waitingTime = waitingTime;
		this.waitingCounter = this.waitingTime;
		
		super.clip(this.currentCycle * this.width, posY, this.width,this.height);
	}

	// Methods
	/**
		 * 
		 */
	public void display(int x, int y, Graphics g) {
		this.waitingCounter--;

		if (this.waitingCounter == 0) {

			super.clip(this.currentCycle * this.width, posY, this.width,
					this.height);
			super.display(x, y,g);

			if (this.currentCycle == this.cycleLength - 1) {
				this.currentCycle = 0;
			} else {
				this.currentCycle++;
			}
		}

		this.waitingCounter = this.waitingTime;
	}
}
