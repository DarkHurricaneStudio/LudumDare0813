package gui;

import graphics.Sprite;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MainMenuPanel extends CardPanel {

	//Fields
	private JButton playButton,exitButton;
	private Sprite playSprite,exitSprite,background;
	
	public final static String ID = "MAIN_MENU_PANEL";
	
	//Constructors
	public MainMenuPanel(MainPanel mainPanel){
		super(MainMenuPanel.ID,mainPanel);
		
		this.background = new Sprite("others/backMenu.png"); 
		this.playSprite = new Sprite("others/menuButton.png");
		this.exitSprite = new Sprite("others/menuButton.png");
		this.mainPanel = mainPanel;
		
		this.setLayout(null);
		
		this.playButton = new JButton(){
			
			public void paintComponent(Graphics g){
				Point p1 = MainMenuPanel.this.getLocationOnScreen();
				Point p2 = MouseInfo.getPointerInfo().getLocation();
				Point p = new Point(p2.x-p1.x-240,p2.y-p1.y-300);
				
				if (this.contains(p)){
					MainMenuPanel.this.playSprite.clip(300, 0, 300, 100);
				}
				else {
					MainMenuPanel.this.playSprite.clip(0, 0, 300, 100);
				}
			}
			
		};
		this.playButton.setBounds(240,300,300,100);
		this.playButton.setBorderPainted(false);
		this.playButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				MainMenuPanel.this.mainPanel.setPanel(GamePanel.ID);
			}
		});
		
		
		this.exitButton = new JButton(){
			public void paintComponent(Graphics g){
				Point p1 = MainMenuPanel.this.getLocationOnScreen();
				Point p2 = MouseInfo.getPointerInfo().getLocation();
				Point p = new Point(p2.x-p1.x-240,p2.y-p1.y-420);
				
				if (this.contains(p)){
					MainMenuPanel.this.exitSprite.clip(300, 0, 300, 100);
				}
				else {
					MainMenuPanel.this.exitSprite.clip(0, 0, 300, 100);
				}
			}
		};
		this.exitButton.setBounds(240,420,300,100);
		this.exitButton.setBorderPainted(false);
		this.exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		
		this.add(this.playButton);
		this.add(this.exitButton);
		
		
	}
	
	public void paintComponent(Graphics g){
		long initTime=System.nanoTime();
		
		this.background.display(0,0,g);
		g.setFont(MainPanel.font.deriveFont(25f));
		
		g.setColor(new Color(240,240,240));
		g.drawString("Lifetime", 300, 145);
		
		this.playSprite.display(240, 300, g);
		this.exitSprite.display(240, 420, g);
		
		g.setColor(Color.black);
		g.drawString("Play",348,355);
		g.drawString("Exit",348,475);
		
		while (System.nanoTime() - initTime < 1000000000 / 60) {			}
		this.repaint();
		
	}
}
