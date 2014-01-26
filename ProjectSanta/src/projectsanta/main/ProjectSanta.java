package projectsanta.main;

import java.awt.Dimension;

import projectsanta.main.page.MainMenuPage;
import userinterface.window.Window;

public class ProjectSanta {
	
	/* 									TODO:
	 *   * Update TextList so that it has only one constructor, but you
	 *   can call setEditButton(path, path) or setEditButton(text) to 
	 *   control the buttons, and use setEditable(boolean) to show the 
	 *   buttons or not.
	 *   * Update TextList so that you can control where the edit buttons
	 *   will be placed, maybe add parameter to setEditButton()?
	 */
	
	public static Dimension SIZE = new Dimension(800 * 2, 600);
	private static double SCALE = 1;
	
	public static Window window = new Window((int) (SIZE.width * SCALE), (int) (SIZE.height * SCALE));
	public static MainMenuPage mainPage = new MainMenuPage(window, 0, 0, 800, 500);
	
	public static final String FONT_NAME = "comic sans ms";
	
	public ProjectSanta() {
		// Loading the Main Menu
		window.setPage(mainPage);
		
		// Wait until everything is loaded to show program
		window.setVisible(true);
	}
}
