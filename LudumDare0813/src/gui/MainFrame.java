package gui;

import game.Updater;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

	//Fields
		private MainPanel mainPanel;
		
		private Updater updater;
		
	//Constructors
		public MainFrame(){
			this.updater =new Updater();
			this.mainPanel = new MainPanel(this.updater);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setContentPane(this.mainPanel);
			this.pack();
			this.setLocationRelativeTo(null);
			this.setVisible(true);
		}
	//Methods
}
