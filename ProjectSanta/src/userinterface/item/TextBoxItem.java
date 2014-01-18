package userinterface.item;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;

import projectsanta.main.ProjectSanta;
import projectsanta.main.debug.Debug;

import userinterface.InteractiveComponent;
import userinterface.page.Page;

public class TextBoxItem extends InteractiveItem {
	
	private final JTextField TEXT_BOX = new JTextField();
	
	public TextBoxItem(Page page, int xPos, int yPos, int charLength, int fontSize) {
		super(page, xPos, yPos);
		
		// A dark green color
		Color textColor = new Color(0, 170, 0);
		
		// Visual settings
		this.setFont(fontSize);
		TEXT_BOX.setBackground(Color.WHITE);
		TEXT_BOX.setForeground(textColor);
		TEXT_BOX.setSelectionColor(textColor);
		TEXT_BOX.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		TEXT_BOX.setCaretColor(textColor);
		this.setSizeAndLoc(TEXT_BOX, charLength);
				
		page.addItem(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {}

	@Override
	public void keyPressed(KeyEvent event, int key) {
		/*for(KeyListener listener : TEXT_BOX.getKeyListeners()) {
			System.out.println("keyPressed");
			listener.keyPressed(kPressed);;
		}*/
		//System.out.println(TEXT_BOX.getListeners(ActionListener.class).length);
		
		
		
		/*int position = TEXT_BOX.getCaretPosition();
		String text = TEXT_BOX.getText();
		
		// ================
		// Moving the caret
		// ================
		if(key == KeyEvent.VK_LEFT ) {
			if(position > 0) TEXT_BOX.setCaretPosition(position - 1);
			else TEXT_BOX.setCaretPosition(text.length());
		}
		else if(key == KeyEvent.VK_RIGHT) {
			if(position < text.length()) TEXT_BOX.setCaretPosition(position + 1);
			else TEXT_BOX.setCaretPosition(0);
		}
		
		// =============
		// Deleting text
		// =============
		else if(key == KeyEvent.VK_BACK_SPACE) {
			// If a selection was made
			if(TEXT_BOX.getSelectedText() != null) {
				String selectedText = TEXT_BOX.getSelectedText();
				int startPos = position - selectedText.length();
				
				// If caret is to the right of selection
				if(startPos >= 0 && text.substring(startPos, position).equals(selectedText)) {
					int lengthFromEnd = text.length() - position;
					System.out.println("lengthFromEnd:" + lengthFromEnd);
					TEXT_BOX.setText(text.substring(0, position - selectedText.length()) + text.substring(position));
					System.out.println("New text: " + TEXT_BOX.getText() + " length: " + TEXT_BOX.getText().length());
					System.out.println("Caret pos before: " + TEXT_BOX.getCaretPosition());
					TEXT_BOX.setCaretPosition(TEXT_BOX.getText().length() - lengthFromEnd);
					System.out.println("Caret pos after: " + TEXT_BOX.getCaretPosition() + "\n");
				}
				// If caret is to the left of selection
				else {
					TEXT_BOX.setText(text.substring(0, position) + text.substring(position + selectedText.length()));
					TEXT_BOX.setCaretPosition(position);
				}
				
				// Reseting the caret, if that position still exists
				if(position <= TEXT_BOX.getText().length()) TEXT_BOX.setCaretPosition(position);
			}
			// Otherwise, erase the letter before the caret
			else {
				//
				if(position > 0) TEXT_BOX.setText(text.substring(0, position - 1) + text.substring(position, text.length()));
				// Move the caret back one, if that position exists
				if(position - 1 >= 0) TEXT_BOX.setCaretPosition(position - 1);
			}
		}
		//System.out.println(TEXT_BOX.getCaretPosition());*/
	}
	
	@Override
	public JTextField getComponent() {
		return TEXT_BOX;
	}
}
