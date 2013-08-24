package game;


public abstract class Entity {

	//Fields
		protected double posX,posY,speedX;
		protected double speedY;
		protected int height, width;
		

	//Constructor
		public Entity(double posX,double posY){
			this.posX = posX;
			this.posY = posY;
			this.speedX = 0;
			this.speedY = 0;
			
		}
		
	//Methods
		
		public abstract void update(Updater u);
		
		public double distanceTo( Entity e) {
			double distance = Math.sqrt((this.posX-e.getPosX())*(this.posX-e.getPosX()) + (this.posX-e.getPosX())*(this.posY-e.getPosY()));
			return distance;
		}
		
		public double getPosX() {
			return this.posX;
		}
		
		public double getPosY() {
			return this.posY;
		}
		
		public int getWidth() {
			return this.width;
		}
		
		public int getHeight() {
			return this.height;
		}
		
		public double getSpeedY() {
			return this.speedY;
		}

}
