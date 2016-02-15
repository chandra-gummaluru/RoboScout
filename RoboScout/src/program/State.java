package program;

import java.awt.Graphics2D;

public abstract class State {

	/**
	 * The name of this state.
	 */
	private final String NAME;

	/**
	 * The path of the resource file for this state.
	 */
	private final String PATH;

	public State(String name, String path) {
		this.NAME = name;
		this.PATH = path;
	}

	/**
	 * Initializes this state. This method is called when a new state is
	 * created.
	 */
	public abstract void initialize();

	/**
	 * Updates this state. This method is called repeatedly.
	 */
	public abstract void tick();

	/**
	 * Draws this state onto the screen.
	 *
	 * @param g
	 *            Graphics2D object that will be used for drawing to the screen.
	 */
	public abstract void draw(Graphics2D g);

	/**
	 * Gets the name of this state.
	 *
	 * @return The name of this state.
	 */
	public String getStateName() {
		return NAME;
	}

	/**
	 * Gets the resource path of this state.
	 *
	 * @return The resource path.
	 */
	public String getPath() {
		return PATH;
	}
}
