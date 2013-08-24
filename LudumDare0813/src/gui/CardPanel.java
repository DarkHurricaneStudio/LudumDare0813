package gui;

import javax.swing.JPanel;

public class CardPanel extends JPanel {
	
	//Fields
		private String id;
		protected MainPanel mainpanel;
			
	//Constructors
		public CardPanel(String id,MainPanel mainPanel){
			this.id = id;
		}
			
	//Methods
		public String getID() {
			return this.id;
		}
}
