package userinterface.util;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JLabel;

import userinterface.item.ButtonItem;
import userinterface.item.Item;
import userinterface.item.TextItem;
import userinterface.page.Page;

public class TextList {
	
	private Page page;
	private int xPos, yPos, fontSize, spacing;
	private boolean editable;
	
	private final ArrayList<Item> LIST_ITEMS = new ArrayList<Item>();
	private final ArrayList<Item> LIST_BUTTONS = new ArrayList<Item>();
	
	public TextList(Page page, int xPos, int yPos, int fontSize, int spacing, boolean editable) {
		this.page = page;
		this.xPos = xPos;
		this.yPos = yPos;
		this.fontSize = fontSize;
		this.spacing = spacing;
		this.editable = editable;
	}
	
	public void addListItem(String text) {
		int listSize = LIST_ITEMS.size();
		text = (listSize + 1) + ".) " + text;
		int yValue;
		
		// If first item
		if(listSize == 0) yValue = yPos;
		else yValue = yPos + (LIST_ITEMS.get(0).getComponent().getHeight() * listSize + spacing * listSize);
		
		// Creating the list item
		LIST_ITEMS.add(new TextItem(page, xPos, yValue, text, fontSize));
		// Creating the list button, but only if the list is editable
		if(editable) LIST_BUTTONS.add(new ButtonItem(page, xPos - 40, yValue, "exit.jpg", "exithover.jpg"));
		
		// Need to refresh the page in order for the items to appear
		page.repaint();
	}
	
	public void removeListItem(int index) {
		// Removing the Items from the page
		page.removeItem(LIST_ITEMS.get(index));
		page.removeItem(LIST_BUTTONS.get(index));
		
		// Removing the Items from this list
		LIST_ITEMS.remove(index);
		LIST_BUTTONS.remove(index);
		
		for(int x = index; x < LIST_ITEMS.size(); x++) {
			// Shifting all the items below the deleted item back up in the list
			JLabel listItem = (JLabel) LIST_ITEMS.get(x).getComponent();
			Component listButton = LIST_BUTTONS.get(x).getComponent();
			
			int yValue = listItem.getLocation().y - listItem.getHeight() - spacing;
			listItem.setLocation(xPos, yValue);
			listButton.setLocation(listButton.getLocation().x, yValue);
			
			// Re-numbering the items in the list
			String text = listItem.getText();
			while(!text.startsWith(".")) text = text.substring(1, text.length());
			listItem.setText((x + 1) + text);
		}
		
		page.repaint();
	}
	
	public void setEditable(boolean value) {
		editable = value;
	}
	
	public boolean isEditable() {
		return editable;
	}
	
	public ArrayList<Item> getListItems(){
		return LIST_ITEMS;
	}
	
	public ArrayList<Item> getListButtons(){
		return LIST_BUTTONS;
	}
}
