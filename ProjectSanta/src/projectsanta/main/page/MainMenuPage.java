package projectsanta.main.page;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import projectsanta.main.ProjectSanta;

import userinterface.item.ButtonItem;
import userinterface.item.InteractiveItem;
import userinterface.item.Item;
import userinterface.item.TextBoxItem;
import userinterface.item.TextItem;
import userinterface.page.Page;
import userinterface.util.TextList;
import userinterface.window.Window;

public class MainMenuPage extends Page {
	
	private static final long serialVersionUID = 1L;
	
	protected ButtonItem exitButton = new ButtonItem(this, this.getWidth() - 22, 2, "exit.JPG", "exithover.JPG");
	protected ButtonItem minimizeButton = new ButtonItem(this, this.getWidth() - 52, 2, "minimize.JPG", "minimizehover.JPG");
	
	// The title
	private TextItem title = new TextItem(this, 20, 20, "Secret Santa", new Font(ProjectSanta.FONT_NAME, Font.BOLD, 42));
	
	// A prompt for the text box
	private TextItem textBoxPrompt = new TextItem(this, 10, 100, "Enter A Name:", new Font(ProjectSanta.FONT_NAME, Font.PLAIN, 24));
	
	// Text box to enter names
	private TextBoxItem textBox = new TextBoxItem(this, textBoxPrompt.getComponent().getX(), textBoxPrompt.getComponent().getY() + 35, 10, new Font(ProjectSanta.FONT_NAME, Font.PLAIN, 40));
	
	// List to display the names
	private TextList list = new TextList(this, 50, 200, new Font(ProjectSanta.FONT_NAME, Font.PLAIN, 24), 0);
	
	// Button to randomize the list
	private ButtonItem createButton = new ButtonItem(this, 400, 400, "Generate List", new Font(ProjectSanta.FONT_NAME, Font.BOLD, 30), Color.BLACK, Color.RED);
	
	public MainMenuPage(Window window, int x, int y, int width, int height) {
		super(window, x, y, width, height, "/menugraphics/");
	}
	
	@Override
	public void handleMousePress(InteractiveItem item) {
		super.handleMousePress(item);
		
		// Looking through the items in the list
		for(int x = 0; x < list.getListButtons().size(); x++) {
			Item buttonItem = list.getListButtons().get(x);
			
			// If an item button was pressed, remove the item
			if(item == buttonItem) {
				list.removeListItem(list.getListButtons().indexOf(buttonItem));
				break;
			}
		}
		
		// List can only be created if it has 2 or more items
		if(item == createButton && list.getListItems().size() > 1) {
			String[] names = new String[list.getListItems().size()];
			
			for(int x = 0; x < names.length; x++) {
				names[x] = ((JLabel) list.getListItems().get(x).getComponent()).getText();
				
				// Removing the number off of the name
				while(!names[x].startsWith(".")) names[x] = names[x].substring(1, names[x].length()); // Removes the number
				names[x] = names[x].substring(3, names[x].length()); // Removes the symbols ".) " (and following space)
			}
			ProjectSanta.window.addPage(new ListCreationPage(this.getWindow(), 800, 0, 800, 500, names));
		}
		if(item == exitButton) System.exit(0);
		else if(item == minimizeButton) ProjectSanta.window.setExtendedState(JFrame.ICONIFIED);
	}
	
	@Override
	public void handleActionEvent(InteractiveItem item) {
		if(item == textBox) {
			// Getting the JTextField, and its text value
			JTextField textBox = (JTextField) item.getComponent();
			String text = textBox.getText();
			
			// Don't add the item if it doesn't contain anything
			if(text.length() <= 0) return;
			
			list.addListItem(text);
			textBox.setText("");
		}
	}
	
	@Override
	public void handleKeyPress(InteractiveItem item, int key) {
		System.out.println("TERDFSFREFS");
	}
	
	@Override
	public void mousePressed(MouseEvent event) {
		super.mousePressed(event);
	}

	public void mouseReleased(MouseEvent event) {
		super.mouseReleased(event);
	}

	@Override
	public void mouseEntered(MouseEvent event) {
		super.mouseEntered(event);
	}
	
	@Override
	public void mouseExited(MouseEvent event) {}

	@Override
	public void mouseDragged(MouseEvent event) {
		super.mouseDragged(event);
	}

	@Override
	public void mouseMoved(MouseEvent event) {}

	@Override
	public void keyPressed(KeyEvent event, int key) {}
	
	@Override
	public void actionPerformed(ActionEvent event) {}
}
