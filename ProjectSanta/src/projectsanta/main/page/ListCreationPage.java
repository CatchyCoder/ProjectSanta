package projectsanta.main.page;

import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import projectsanta.main.SecretSanta;

import userinterface.InteractiveComponent;
import userinterface.item.ButtonItem;
import userinterface.item.InteractiveItem;
import userinterface.page.Page;
import userinterface.util.TextList;

public class ListCreationPage extends Page implements InteractiveComponent {
	
	private static final long serialVersionUID = 1L;
	
	protected ButtonItem exitButton = new ButtonItem(this, SecretSanta.window.getWidth() - 22, 2, "exit.jpg", "exithover.jpg");
	protected ButtonItem minimizeButton = new ButtonItem(this, SecretSanta.window.getWidth() - 52, 2, "minimize.jpg", "minimizehover.jpg");
	
	private final String[] NAMES;
	
	private final int SWAPS = (int) Math.pow(10, 5);
	
	private TextList list = new TextList(this, 10, 10, 24, 0, false);
	
	public ListCreationPage(String[] names) {
		this.NAMES = names;
		
		shuffleNames();
	}
	
	private void shuffleNames() {
		for(int x = 0; x < SWAPS; x++) {
			int index, index2;
			do { // Pick two random indexes
				index = (int) (Math.random() * NAMES.length);
				index2 = (int) (Math.random() * NAMES.length);
			}
			while(index == index2);
			
			// Now swap two names based off those indexes
			String name = NAMES[index];
			NAMES[index] = NAMES[index2];
			NAMES[index2] = name;
		}
		
		// Now appending names to each name, so that way it shows
		// who each person has for Secret Santa...
		String firstName = NAMES[0];
		for(int x = 0; x < NAMES.length; x++) {
			if(x + 1 < NAMES.length) NAMES[x] += " has " + NAMES[x + 1];
			else NAMES[x] += " has " + firstName;
		}
		
		for(String name : NAMES) list.addListItem(name);
	}
	
	@Override
	public void handleMousePress(InteractiveItem item) {
		super.handleMousePress(item);
		
		if(item == exitButton) System.exit(0);
		else if(item == minimizeButton) SecretSanta.window.setExtendedState(JFrame.ICONIFIED);
	}
	
	@Override
	public void mousePressed(MouseEvent event) {
		super.mousePressed(event);
		
		SecretSanta.window.setVisiblePage(SecretSanta.mainPage);
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		super.mouseReleased(event);
	}

	@Override
	public void mouseEntered(MouseEvent event) {
		super.mouseEntered(event);
	}
	
	@Override
	public void mouseDragged(MouseEvent event) {
		super.mouseDragged(event);
	}
}
