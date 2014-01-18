package userinterface.item;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import projectsanta.main.ProjectSanta;

import userinterface.InteractiveComponent;
import userinterface.page.Page;

public class TextItem extends NoninteractiveItem {
	
	private final JLabel TEXT = new JLabel();
	
	public TextItem(Page page, int xPos, int yPos, String text, int fontSize) {
		super(page, xPos, yPos);
		
		this.setFont(fontSize);
		TEXT.setText(text);
		TEXT.setForeground(Color.BLACK);
		TEXT.computeVisibleRect(TEXT.getBounds());
		this.setSizeAndLoc(TEXT);
		
		page.addItem(this);
	}

	@Override
	public JLabel getComponent() {
		return TEXT;
	}
}
