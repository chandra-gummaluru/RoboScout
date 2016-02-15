package window;

import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

import components.GraphicComponent;
import exceptions.LoadException;
import loop.Loop;
import managers.ResourceManager;
import managers.StateManager;
import program.DefaultState;
import program.State;
import scaling.AspectRatio;

public abstract class Program {

	/**
	 * The name of the program.
	 */
	private static String name;

	/**
	 * The source path of the program.
	 */
	private static String sourcePath;

	/**
	 * The default window of the program.
	 */
	private static Window defaultWindow;

	/**
	 * The program loop.
	 */
	private static Loop programLoop;
	
	private static State defaultState = new DefaultState();

	/**
	 * Builds a program using information from resources.
	 */
	public static void buildProgram(String name, String sourcePath) {

		Program.name = name;
		Program.sourcePath = sourcePath;

		try {
			ResourceManager.initialize(Program.sourcePath);

			String title = ResourceManager.getText().get("PROGRAM_TITLE");
			int defaultWidth = ResourceManager.getNumericalValues().get("DEFAULT_WIDTH").intValue();
			AspectRatio aspectRatio = new AspectRatio(
					ResourceManager.getNumericalValues().get("ASPECT_WIDTH").intValue(),
					ResourceManager.getNumericalValues().get("ASPECT_HEIGHT").intValue());
			BufferedImage iconImage = ResourceManager.getImages().get("PROGRAM_ICON");
			
			defaultWindow = new Window(title, defaultWidth, aspectRatio, iconImage);
			programLoop = new Loop();

			// If the window cannot be built, display the error using a
			// message box.
		} catch (LoadException e) {
			JOptionPane.showMessageDialog(defaultWindow, "Unable to load program.", "Error",
					JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(defaultWindow, "Unable to load program.", "Error",
					JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}

	}

	public static void initalize() {
		defaultWindow.initialize();
		programLoop.initialize();
		GraphicComponent.initalize();
		StateManager.initialize();
	}
	
	public static Window getDefaultWindow() {
		return defaultWindow;
	}
	
	public static String getName() {
		return name;
	}
	
	public static State getDefaultState() {
		return defaultState;
	}
}
