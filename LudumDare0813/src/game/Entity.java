package game;


public abstract class Entity {

	//Fields
		protected double posX,posY,speedX;
		protected double speedY;
		

	//Constructor
		public Entity(double posX,double posY){
			this.posX = posX;
			this.posY = posY;
			this.speedX = 0;
			this.speedY = 0;
			
		}
		
	//Methods
		
		public abstract void update(Updater u);
}
