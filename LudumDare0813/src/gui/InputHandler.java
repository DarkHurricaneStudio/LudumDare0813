package gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
	
	//Fields
    	public static boolean[] input = new boolean[65535];
    
    //Constructors
    
	//Methods
	    public void keyPressed(KeyEvent e){
	    	InputHandler.input[e.getKeyCode()] = true;
	    }
	    
	    public void keyReleased(KeyEvent e){
	    	InputHandler.input[e.getKeyCode()] = false;
	    }
	    
	    public void keyTyped(KeyEvent e){	       	
	    	
	    }
}
