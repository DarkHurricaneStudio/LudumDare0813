package gui;

import game.Updater;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;

public class MainPanel extends JPanel {
	
	// Fields
		/**
		 * Width (in pixels) of the game
		 */
		public final static int GAME_WIDTH = 780;
	
		/**
		 * Height (in pixels) of the game
		 */
		public final static int GAME_HEIGHT = 550;
	
		/**
		 * 
		 */
		private CardLayout layout;
	
		/**
		 * 
		 */
		private ArrayList<CardPanel> panels;
		
		/**
		 * 
		 */
		private Font font;
		
	// Constructors
		/**
		 * Build the main panel
		 */
		public MainPanel(Updater u) {
			super();
	
			this.setPreferredSize(new Dimension(MainPanel.GAME_WIDTH,
					MainPanel.GAME_HEIGHT));
	
			this.layout = new CardLayout();
			this.setLayout(this.layout);

			// we load the custom font
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			try {
				ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("others/joystix.ttf")));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.font = new Font("Joystix",Font.TRUETYPE_FONT,9);

			this.panels = new ArrayList<CardPanel>();
			this.panels.add(new GamePanel(this,u));
	
			for (int i = 0; i < this.panels.size(); i++) {
				this.panels.get(i);
				this.add(this.panels.get(i), this.panels.get(i).getID());
				// we had this font to the panel
				this.panels.get(i).setFont(this.font);
			}
	
			// Premier layout à afficher
			this.layout.show(this, GamePanel.ID);
			
		}

	// Methods
		public void setPanel(String ID) {
			this.layout.show(this, ID);
	
			for (int i = 0; i < this.panels.size(); i++) {
				if (this.panels.get(i).getID().compareTo(ID) == 0) {
					this.panels.get(i).requestFocus();
				}
			}
		}
}
