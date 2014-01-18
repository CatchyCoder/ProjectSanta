package userinterface.page;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import projectsanta.main.SecretSanta;

import userinterface.InteractiveComponent;
import userinterface.item.ButtonItem;
import userinterface.item.InteractiveItem;
import userinterface.item.Item;
import userinterface.item.NoninteractiveItem;

public abstract class Page extends JPanel implements InteractiveComponent {
	
	private static final long serialVersionUID = 1L;
	
	private final ArrayList<Item> ITEMS = new ArrayList<Item>();
	
	private int lastX, lastY; // For dragging the program around
	
	public Page() {
		// Visual settings
		this.setBounds(0, 0, SecretSanta.window.getWidth(), SecretSanta.window.getHeight());
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		this.setFocusable(true);
		this.setVisible(false);
	}
	
	public void addItem(Item item) {
		// Adding JLabel to JPanel (Page), and to the Page's items list
		SecretSanta.window.addEventListeners(item.getComponent());
		this.add(item.getComponent());
		ITEMS.add(item);
		item.getComponent().setVisible(true);
		
		SecretSanta.window.refreshScreen();
	}
	
	public void removeItem(Item item) {
		SecretSanta.window.removeEventListeners(item.getComponent());
		this.remove(item.getComponent());
		ITEMS.remove(item);
		item = null;
		
		SecretSanta.window.refreshScreen();
	}
	
	public void resetItemStates(MouseEvent event) {
		for(Item item : ITEMS) {
			// If it is an item that you can interact with, reset it's state
			if(item instanceof InteractiveItem) ((InteractiveItem) item).mouseExited(event);
		}
	}
	
	public ArrayList<Item> getItems() {
		return ITEMS;
	}
	
	/* =======================
	 * = Input Methods Below =
	 * =======================
	 * 
	 * Defining all input methods below, that way any class that inherits
	 * Page can choose which methods it would like to implement,
	 * instead of having to implement them all. It acts as an "adapter" class,
	 * similar to MouseAdapter.
	 */
	
	public void handleMousePress(InteractiveItem item) {
		
	}
	
	public void handleKeyPress(InteractiveItem item, int key) {}
	
	public void handleActionEvent(InteractiveItem item) {}
	
	@Override
	public void mousePressed(MouseEvent event) {
		// Below is for dragging purposes
		lastX = event.getX();
		lastY = event.getY();
	}
	
	public void mousePressedNoninteractiveItem(MouseEvent event, int compX, int compY) {
		// Below is for dragging purposes when a NoninteractableItem is pressed
		lastX = event.getX() + compX;
		lastY = event.getY() + compY;
		
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	@Override
	public void mouseReleased(MouseEvent event) {
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	@Override
	public void mouseEntered(MouseEvent event) {}
	
	@Override
	public void mouseExited(MouseEvent event) {}
	
	@Override
	public void mouseDragged(MouseEvent event) {
		// Below adds the ability to drag program around on screen
		int x = event.getXOnScreen();
		int y = event.getYOnScreen();
		SecretSanta.window.setLocation(x - lastX, y - lastY);
		resetItemStates(event);
	}
	
	@Override
	public void mouseMoved(MouseEvent event) {}
	
	@Override
	public void keyPressed(KeyEvent event, int key) {}
	
	@Override
	public void actionPerformed(ActionEvent event) {}
}