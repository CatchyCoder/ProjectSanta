package userinterface.window;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

import userinterface.InteractiveComponent;
import userinterface.input.InputHandler;
import userinterface.item.Item;
import userinterface.page.Page;

public class Window extends JFrame implements InteractiveComponent {
	
	private static final long serialVersionUID = 1L;
	
	private InputHandler inputHandler = new InputHandler(this);
	private Page visiblePage; // The page being viewed by the user
	
	public Window(int width, int height) {
		this.setSize(width, height);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setUndecorated(true); 
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setFocusable(true);
		this.setTitle("Secret Santa");
		this.setVisible(false);
		
		super.addMouseListener(inputHandler);
		super.addMouseMotionListener(inputHandler);
		super.addKeyListener(inputHandler);
	}
	
	@Override
	public void addMouseListener(MouseListener listener) {
		// Adding the listener
		super.addMouseListener(listener);
				
		// Adding the listener to the page and its items
		visiblePage.addMouseListener(listener);
		for(Item item : visiblePage.getItems()) item.getComponent().addMouseListener(listener);
	}
	
	@Override
	public void addMouseMotionListener(MouseMotionListener listener) {
		// Adding the listener to this window
		super.addMouseMotionListener(listener);
				
		// Adding the listener to the page and its items
		visiblePage.addMouseMotionListener(listener);
		for(Item item : visiblePage.getItems()) item.getComponent().addMouseMotionListener(listener);
	}
	
	@Override
	public void addKeyListener(KeyListener listener) {
		// Adding the listener
		super.addKeyListener(listener);
				
		// Adding the listener to the page and its items
		visiblePage.addKeyListener(listener);
		for(Item item : visiblePage.getItems()) item.getComponent().addKeyListener(listener);
	}
	
	public void addEventListeners(Component component) {
		// JTextField's don't use KeyListeners for the <enter> key, they use 
		// ActionListener, so we will use that instead.
		if(!(component instanceof JTextField)) {
			// Adding key listeners
			for(KeyListener listener : this.getKeyListeners()) component.addKeyListener(listener);
		}
		else {
			// Adding an ActionListener to that specific JTextField
			((JTextField) component).addActionListener(inputHandler);
		}
		
		// Adding mouse listeners
		for(MouseListener listener : this.getMouseListeners()) component.addMouseListener(listener);
		
		// Adding mouse motion listeners
		for(MouseMotionListener listener : this.getMouseMotionListeners()) component.addMouseMotionListener(listener);
	}
	
	public void removeEventListeners(Component component) {
		// Removing mouse Listeners
		while(component.getMouseListeners().length > 0) component.removeMouseListener(component.getMouseListeners()[0]);
		
		// Removing mouse motion listeners
		while(component.getMouseMotionListeners().length > 0) component.removeMouseMotionListener(component.getMouseMotionListeners()[0]);
		
		// Removing key listeners
		while(component.getKeyListeners().length > 0) component.removeKeyListener(component.getKeyListeners()[0]);
	}
	
	public void addPage(Page page) {
		addEventListeners(page);
		this.add(page);
		visiblePage = page;
		page.setVisible(true);
		
		refreshScreen();
		getFocus();
	}
	
	public void removePage(Page page) {
		if(page == null) return;
		
		removeEventListeners(page);
		this.remove(page);
		page = null;
		visiblePage = null;
		
		refreshScreen();
	}
	
	public void setVisiblePage(Page page) {
		removePage(visiblePage);
		addPage(page);
		
		refreshScreen();
	}
	
	public void refreshScreen() {
		if(visiblePage == null) return;
		
		// Refreshing screen
		this.repaint();
		visiblePage.repaint();
	}
	
	public void getFocus() {
		if(visiblePage == null) return;
		
		// Getting focus
		this.requestFocus();
		visiblePage.requestFocus();
	}
	
	/* =======================
	 * = Input Methods Below =
	 * =======================
	 */
	
	@Override
	public void mousePressed(MouseEvent event) {}

	@Override
	public void mouseReleased(MouseEvent event) {}

	@Override
	public void mouseEntered(MouseEvent event) {}

	@Override
	public void mouseExited(MouseEvent event) {}

	@Override
	public void mouseDragged(MouseEvent event) {}

	@Override
	public void mouseMoved(MouseEvent event) {}

	@Override
	public void keyPressed(KeyEvent event, int key) {}

	@Override
	public void actionPerformed(ActionEvent event) {}
	
	public Page getVisiblePage() {
		return visiblePage;
	}
}
