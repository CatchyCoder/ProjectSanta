package userinterface.item;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTextField;

import projectsanta.main.ProjectSanta;

import userinterface.page.Page;

public abstract class Item {
	
	protected Page page;
	protected int xPos, yPos;
	
	protected final String PATH = "/menugraphics/";
	
	public Item(Page page, int xPos, int yPos) {
		this.page = page;
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public void setFont(int size) {
		getComponent().setFont(new Font(ProjectSanta.FONT, ProjectSanta.FONT_STYLE, size));
	}
	
	public void setSizeAndLoc(JLabel textLabel) {
		// Calculating how wide the JLabel should be (in pixels) using FontMetrics
		int width = textLabel.getFontMetrics(textLabel.getFont()).stringWidth(textLabel.getText());
		int fontSize = textLabel.getFont().getSize();
		int height = (int) (fontSize * ProjectSanta.FONT_SIZE_RATIO);
		textLabel.setBounds(xPos, yPos, width, height);
	}
	
	public void setSizeAndLoc(JTextField textField, int charLength) {
		Font font = textField.getFont();
		// Finding out the width of the widest character in this specific font using FontMetrics
		// This is used to determine the width of the JTextField
		int widestCharWidth = textField.getFontMetrics(font).getMaxAdvance();
		
		int width = widestCharWidth * charLength;
		int height = (int) (font.getSize() * ProjectSanta.FONT_SIZE_RATIO);
		textField.setBounds(xPos, yPos, width, height);
	}
	
	abstract public Component getComponent();
}
