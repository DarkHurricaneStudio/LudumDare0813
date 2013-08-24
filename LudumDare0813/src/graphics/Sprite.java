package graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/** 
 * Sprite 1.1
 * @author robrock
 * 1.1 : fix the missing exception
 */
public class Sprite {

	// Fields
		/**
		 * 
		 */
		private BufferedImage sprite;
		
		/**
		 * 
		 */
		private BufferedImage completeSprite;

		private Graphics graphics;
		
	// Constructor
		/**
		 * 
		 * @param path
		 */
		public Sprite(String path,Graphics graphics) {
			try {
				this.completeSprite = ImageIO.read(new File(path));
			} catch (IOException e) {
				System.out.println("Erreur ! Fichier "+path+" introuvable");
				System.exit(1);
			}
			this.sprite = completeSprite;
			
			this.graphics = graphics;
		}

	//Methods
		
		public void display(int x,int y){
			this.graphics.drawImage(this.sprite,x,y,null);
		}
		/**
		 * 
		 * @param g
		 * @param x
		 * @param y
		 */
		public void display(int x,int y,Graphics g) {
			g.drawImage(sprite, x, y, null);
		}
		
		/**
		 * 
		 * @param x
		 * @param y
		 * @param width
		 * @param height
		 */
		public void clip(int x,int y,int width, int height){
			if (width <= this.completeSprite.getWidth() && height <= this.completeSprite.getHeight() && width > 0 && height > 0){
				this.sprite = this.completeSprite.getSubimage(x,y,width,height);
			}
		}
		
		/**
		 * 
		 */
		public void unClip(){
			this.sprite = this.completeSprite;
		}

		
	//Getters
		/**
		 * 
		 * @param x
		 * @param y
		 * @return
		 */
		public int getAlpha(int x,int y){
			int pix = sprite.getRGB(x, y);
			int res = (pix >>> 24) & 0xff;
			return res;
		}
		
		/**
		 * 
		 * @param x
		 * @param y
		 * @return
		 */
		public int getRed(int x,int y){
			int pix = sprite.getRGB(x, y);
			int res = (pix >>> 16) & 0xff;
			return res;
		}

		/**
		 * 
		 * @param x
		 * @param y
		 * @return
		 */
		public int getGreen(int x,int y){
			int pix = sprite.getRGB(x, y);
			int res = (pix >>> 8) & 0xff;
			return res;
		}

		/**
		 * 
		 * @param x
		 * @param y
		 * @return
		 */
		public int getBlue(int x,int y){
			int pix = sprite.getRGB(x, y);
			int res = pix & 0xff;
			return res;
		}
		
		/**
		 * 
		 * @return
		 */
		public int getWidth(){
			return this.sprite.getWidth();
		}
		
		/**
		 * 
		 * @return
		 */
		public int getCompleteWidth(){
			return this.completeSprite.getWidth();
		}
		
		/**
		 * 
		 * @return
		 */
		public int getHeight(){
			return this.sprite.getHeight();
		}
		
		/**
		 * 
		 * @return
		 */
		public int getCompleteHeight(){
			return this.completeSprite.getHeight();
		}
}
