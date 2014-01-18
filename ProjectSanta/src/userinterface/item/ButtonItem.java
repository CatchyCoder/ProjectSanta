package userinterface.item;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import projectsanta.main.SecretSanta;

import userinterface.InteractiveComponent;
import userinterface.page.Page;

public class ButtonItem extends InteractiveItem {
	
	private final JLabel BUTTON = new JLabel();
	
	private ImageIcon enterState, exitState;
	
	private boolean imageUse;
	
	private Color color;
	
	public ButtonItem(Page page, int xPos, int yPos, String nonHoverName, String hoverName) {
		super(page, xPos, yPos);
		
		imageUse = true;
		
		try {
			// Loading images
			exitState = new ImageIcon(getClass().getResource(this.PATH + nonHoverName));
			enterState = new ImageIcon(getClass().getResource(this.PATH + hoverName));
			
			// Visual settings
			BUTTON.setIcon(exitState);
			BUTTON.setBounds(xPos, yPos, exitState.getIconWidth(), exitState.getIconHeight());
			BUTTON.setCursor(new Cursor(Cursor.HAND_CURSOR));
			
			page.addItem(this);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ButtonItem(Page page, int xPos, int yPos, String text, int fontSize, Color color) {
		super(page, xPos, yPos);
		
		imageUse = false;
		this.color = color;
		
		try {
			// Visual settings
			this.setFont(fontSize);
			BUTTON.setText(text);
			BUTTON.setForeground(Color.BLACK);
			BUTTON.setCursor(new Cursor(Cursor.HAND_CURSOR));
			this.setSizeAndLoc(BUTTON);
			
			page.addItem(this);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent event) {
		if(imageUse) BUTTON.setIcon(enterState);
		else BUTTON.setForeground(color);
	}
	
	@Override
	public void mouseExited(MouseEvent event) {
		if(imageUse) BUTTON.setIcon(exitState);
		else BUTTON.setForeground(Color.BLACK);
	}
	
	@Override
	public JLabel getComponent() {
		return BUTTON;
	}
}