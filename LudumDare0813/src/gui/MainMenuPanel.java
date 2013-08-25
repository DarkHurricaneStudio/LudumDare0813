package gui;

import graphics.Sprite;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JButton;

public class MainMenuPanel extends CardPanel {

	//Fields
	private JButton playButton,exitButton;
	private Sprite playSprite,exitSprite,background;
	
	public final static String ID = "MAIN_MENU_PANEL";
	
	//Constructors
	public MainMenuPanel(MainPanel mainPanel){
		super(MainMenuPanel.ID,mainPanel);
		
		this.setLayout(null);
		
		this.playButton = new JButton();
		this.playButton.setBounds(240,300,300,100);
		
		this.exitButton = new JButton();
		this.exitButton.setBounds(240,420,300,100);
		
		this.add(this.playButton);
		this.add(this.exitButton);
		
		//this.background = new 
	}
	
	public void paintComponent(Graphics g){
		
	}
}
