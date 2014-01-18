package userinterface.input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import projectsanta.main.debug.Debug;

import userinterface.item.InteractiveItem;
import userinterface.item.Item;
import userinterface.page.Page;
import userinterface.window.Window;

/**
 * All mouse/key action methods work the same. Each one checks three things:
 * 1.) If the JFrame was acted on, otherwise...
 * 2.) If the JPanel was acted on, otherwise...
 * 3.) If the Items in the JPanel (JLabels) were acted on, otherwise...
 * 4.) An error message is displayed on the object that could not be found.
 */
public final class InputHandler extends KeyAdapter implements MouseListener, MouseMotionListener, ActionListener {
	
	private Window window;
	
	public InputHandler(Window window) {
		this.window = window;
	}
	
	@Override
	public void mousePressed(MouseEvent event) {
		Object source = event.getSource();
				
		if(source == window) window.mousePressed(event);
		else if(source == getVisiblePage()) getVisiblePage().mousePressed(event);
		else {
			Item item = getSourceItem(source);
			if(item instanceof InteractiveItem) {
				InteractiveItem actItem = (InteractiveItem) item;
				actItem.mousePressed(event);
				getVisiblePage().handleMousePress(actItem);
			}
			else getVisiblePage().mousePressedNoninteractiveItem(event, item.getComponent().getX(), item.getComponent().getY());
		}
		
		event.consume();
	}
	
	@Override
	public void mouseReleased(MouseEvent event) {
		Object source = event.getSource();
		
		if(source == window) window.mouseReleased(event);
		else if(source == getVisiblePage()) getVisiblePage().mouseReleased(event);
		else {
			Item item = getSourceItem(source);
			if(item instanceof InteractiveItem) ((InteractiveItem) item).mouseReleased(event);
			else getVisiblePage().mouseReleased(event);
		}
		
		event.consume();
	}
	
	@Override
	public void mouseClicked(MouseEvent event) {}
	
	@Override
	public void mouseEntered(MouseEvent event) {
		Object source = event.getSource();
		
		if(source == window) window.mouseEntered(event);
		else if(source == getVisiblePage()) getVisiblePage().mouseEntered(event);
		else {
			Item item = getSourceItem(source);
			if(item instanceof InteractiveItem) ((InteractiveItem) item).mouseEntered(event);
			else getVisiblePage().mouseEntered(event);
		}
		
		event.consume();
	}
	
	@Override
	public void mouseDragged(MouseEvent event) {
		Object source = event.getSource();
		
		if(source == window) window.mouseDragged(event);
		else if(source == getVisiblePage()) getVisiblePage().mouseDragged(event);
		else {
			Item item = getSourceItem(source);
			if(item instanceof InteractiveItem) ((InteractiveItem) item).mouseDragged(event);
			else getVisiblePage().mouseDragged(event);
		}
		
		event.consume();
	}

	@Override
	public void mouseMoved(MouseEvent event) {
		Object source = event.getSource();
		
		if(source == window) window.mouseMoved(event);
		else if(source == getVisiblePage()) getVisiblePage().mouseMoved(event);
		else {
			Item item = getSourceItem(source);
			if(item instanceof InteractiveItem) ((InteractiveItem) item).mouseMoved(event);
			else getVisiblePage().mouseMoved(event);
		}
		
		event.consume();
	}
	
	@Override
	public void mouseExited(MouseEvent event) {
		Object source = event.getSource();
		
		if(source == window) window.mouseExited(event);
		else if(source == getVisiblePage()) getVisiblePage().mouseExited(event);
		else {
			Item item = getSourceItem(source);
			if(item instanceof InteractiveItem) ((InteractiveItem) item).mouseExited(event);
			else getVisiblePage().mouseExited(event);
		}
		
		event.consume();
	}
	
	@Override
	public void keyPressed(KeyEvent event) {
		Object source = event.getSource();
		int key = event.getKeyCode();
		
		
		if(key == KeyEvent.VK_ESCAPE) System.exit(0);
		
		if(source == window) window.keyPressed(event, key);
		else if(source == getVisiblePage()) getVisiblePage().keyPressed(event, key);
		else {
			Item item = getSourceItem(source);
			if(item instanceof InteractiveItem) {
				InteractiveItem actItem = (InteractiveItem) item;
				actItem.keyPressed(event, key);
				getVisiblePage().handleKeyPress(actItem, key);
			}
			else getVisiblePage().keyPressed(event, key);
		}
		
		event.consume();
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		
		
		if(source == window) window.actionPerformed(event);
		else if(source == getVisiblePage()) getVisiblePage().actionPerformed(event);
		else {
			Item item = getSourceItem(source);
			if(item instanceof InteractiveItem) {
				InteractiveItem actItem = (InteractiveItem) item;
				actItem.actionPerformed(event);
				getVisiblePage().handleActionEvent(actItem);
			}
			else getVisiblePage().actionPerformed(event);
		}
	}
	
	/**
	 * Finds out what Item was acted on in the current visible page, and returns that Item.
	 * @param source
	 * @return item
	 */
	private Item getSourceItem(Object source) {
		// Finding the object that was acted on in the Page
		for(Item item : getVisiblePage().getItems()) {
			if(source == item.getComponent()) return item;
		}
		Debug.error("Object that was acted on was not found! Object = " + source);
		return null;
	}
	
	private Page getVisiblePage() {
		return window.getVisiblePage();
	}
}
