package projectsanta.main;

import java.awt.Dimension;
import java.awt.Font;

import projectsanta.main.page.MainMenuPage;

import userinterface.window.Window;

public class SecretSanta {
	
	/*
	 * This program was made completely function in 4 days, with 915 lines of
	 * code on 12-22-13, created by Clay Kuznia.
	 */
	
	/* 									TODO:
	 *   + Add actionPerformed(ActionEvent) to InputHandler. DONE
	 *   
	 *   * Make it so there can be more than one visible page at once.
	 *   
	 *   * Change the size of JLabels that contain text, so that they fit nice and 
	 *   snug around the letters, but keeping the letters visible so that they are 
	 *   not cut off. DONE
	 *   
	 *   * Make it so the path for images can be specified. (folders)
	 *   
	 *   BUGS:
	 *   * Make it so you can exit the program using <Escape> when a JTextField has focus
	 *   
	 *   * Fix the bug that won't show images when project is exported, MUST FIX
	 */
	
	public static Dimension SIZE = new Dimension(800, 600);
	private static double SCALE = 1;
	
	public static Window window = new Window((int) (SIZE.width * SCALE), (int) (SIZE.height * SCALE));
	
	
	public static final String FONT = "comic sans ms";
	public static final int FONT_STYLE = Font.BOLD;
	public static final double FONT_SIZE_RATIO = 1.55;
		
	public static MainMenuPage mainPage = new MainMenuPage();
	
	public SecretSanta() {
		// Loading the Main Menu
		window.setVisiblePage(mainPage);
		
		// Wait until everything is loaded to show program
		window.setVisible(true);
		window.requestFocus();
		window.repaint();
	}
}
