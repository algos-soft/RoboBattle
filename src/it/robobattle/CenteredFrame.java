package it.robobattle;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * JFrame che si centra automaticamente sullo schermo
 */
public class CenteredFrame extends JFrame {

	/**
	 * Centra il JFrame sullo schermo
	 */
	protected void centra() {
		
		// dimensione schermo
		Dimension dimScreen = Toolkit.getDefaultToolkit().getScreenSize();
		int wScreen=dimScreen.width;
		int hScreen=dimScreen.height;

		// dimensione frame
		int wFrame=this.getWidth();
		int hFrame=this.getHeight();
		
		// calcolo le nuove coordinate
		int x = (wScreen-wFrame)/2;
		int y = (hScreen-hFrame)/2;
		
		// sposto il frame
		setLocation(x, y);
		
	}

	@Override
	public void pack() {
		super.pack();
		centra();
	}
	
	
	
	
}
