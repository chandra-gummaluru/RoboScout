package window;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import managers.StateManager;
import scaling.AspectRatio;

/**
 * Implements fields and methods for a custom JPanel with a specified aspect
 * ratio and scale factor.
 * 
 * @version alpha1
 *
 */
public class Window extends JPanel implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;

	// panel related

	/**
	 * The default width of the window.
	 */
	private final int DEFAULT_WIDTH;

	/**
	 * The default height of the window.
	 */
	private final int DEFAULT_HEIGHT;

	/**
	 * The aspect ratio of the panel.
	 */
	private final AspectRatio ASPECT_RATIO;

	/**
	 * The title displayed on the title bar of the frame.
	 */
	private final String FRAME_TITLE;

	/**
	 * The frame of the window.
	 */
	private JFrame frame;

	/**
	 * The factor by which to scale the resolution of the game.
	 */
	private final int SCALE;

	/**
	 * The icon for the frame.
	 */
	private final BufferedImage ICON;

	/**
	 * The default color.
	 */
	private Color defaultColor = Color.BLACK;

	/**
	 * Creates a new GamePanel using a specified width, aspect ratio, and scale
	 * factor.
	 * 
	 * @param width
	 *            The width of the panel.
	 * @param scale
	 *            The factor by which to scale the resolution of the game.
	 */
	public Window(String title, int width, AspectRatio aspectRatio, BufferedImage icon) {
		super();

		// set the frame title
		this.FRAME_TITLE = title;

		this.DEFAULT_WIDTH = width;

		// set the required aspect ratio
		this.ASPECT_RATIO = aspectRatio;

		this.DEFAULT_HEIGHT = ASPECT_RATIO.calculateHeight(width);

		this.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		// initialize the frame using this panel as the content pane.
		this.frame = new JFrame();
		frame.setResizable(false);

		// set the content pane of the frame to this panel.
		frame.setContentPane(this);
		frame.pack();

		// set the icon of the frame.
		this.ICON = icon;
		frame.setIconImage(ICON);

		// set the scale to the preferred scale.
		this.SCALE = getPreferredScale();
	}

	/**
	 * Creates a new Window using a specified width, aspect ratio, and scale
	 * factor.
	 * 
	 * @param width
	 *            The width of the panel.
	 * @param scale
	 *            The factor by which to scale the resolution of the game.
	 */
	public Window(String title, int width, AspectRatio aspectRatio) {
		super();

		// set the frame title
		this.FRAME_TITLE = title;

		this.DEFAULT_WIDTH = width;

		// set the required aspect ratio
		this.ASPECT_RATIO = aspectRatio;

		this.DEFAULT_HEIGHT = ASPECT_RATIO.calculateHeight(width);

		this.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));

		// initialize the frame using this panel as the content pane.
		this.frame = new JFrame();
		frame.setResizable(false);

		// set the content pane of the frame to this panel.
		frame.setContentPane(this);
		frame.pack();

		// set the icon of the frame.
		this.ICON = null;

		// set the scale to the preferred scale.
		this.SCALE = getPreferredScale();
	}

	/**
	 * Initializes the window and makes it visible.
	 */
	public void initialize() {

		// Re-scale the panel based on the calculated scale factor.
		this.setPreferredSize(new Dimension(this.getWidth() * SCALE, this.getHeight() * SCALE));

		// Pack the frame.
		frame.pack();

		// Set the frame properties.
		frame.setTitle(FRAME_TITLE);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBackground(defaultColor);
		setFocusable(true);
		requestFocus();

		this.addMouseListener(this);
		this.addMouseMotionListener(this);

		this.setVisible(true);
		frame.setVisible(true);

	}

	/**
	 * Draws all components to the panel.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (StateManager.getCurrentState() != null) {
			StateManager.getCurrentState().draw((Graphics2D) g);
		}
	}

	/**
	 * Calculates the maximum scale factor that can be applied to the base
	 * resolution while still ensuring the entire window is visible on screen.
	 * 
	 * @return the preferred scale
	 */
	public int getPreferredScale() {

		// Get the width of the frame border by taking the difference of
		// the frame width and panel width.
		int borderWidth = frame.getWidth() - this.getWidth();

		// Get the height of the frame border by taking the difference of
		// the frame height and the panel height.
		int borderHeight = frame.getHeight() - this.getHeight();

		// Get the maximum width of the screen that can be used for display.
		int screenWidth = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;

		// Get the maximum height of the screen that can be used for display.
		int screenHeight = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;

		// The preferred scale for the window.
		int preferredScale = 1;

		// This loop continues until the next possible scale factor becomes too
		// large.
		while (this.getWidth() * (preferredScale + 1) < screenWidth - borderWidth
				&& this.getHeight() * (preferredScale + 1) < screenHeight - borderHeight) {
			// Increment the scale.
			preferredScale++;
		}

		// Return the maximum scale factor.
		return preferredScale;
	}

	/**
	 * Gets scale of window
	 * 
	 * @return Scale of window
	 */
	public int getScale() {
		return this.SCALE;
	}

	/**
	 * Gets the default width of window.
	 * 
	 * @return the default width of the window (the base width).
	 */
	public int getDefaultWidth() {
		return this.DEFAULT_WIDTH;
	}

	/**
	 * 
	 * Appends the specified component to the beginning of this container. This
	 * is a convenience method for addImpl. This method changes layout-related
	 * information, and therefore, invalidates the component hierarchy. If the
	 * container has already been displayed, the hierarchy must be validated
	 * thereafter in order to display the added component.
	 * 
	 * @param c
	 *            The component to be added.
	 * @return The component argument.
	 */
	@Override
	public Component add(Component c) {
		Component[] oldComponents = this.getComponents();
		this.removeAll();
		super.add(c);
		for (Component o : oldComponents) {
			super.add(o);
		}

		return c;
	}

	// mouse events

	/**
	 * Invoked when the mouse is clicked on this component. This method does
	 * nothing since {@code mousePressed} from {@code MouseMotionListener}
	 * already handles this.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// Do nothing.
	}

	/**
	 * Invoked when the mouse is pressed on this component.
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("Hi");
	}

	/**
	 * Invoked when the mouse is released on this component.
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
	}

	/**
	 * Invoked when the mouse enters this component.
	 */
	@Override
	public void mouseEntered(MouseEvent e) {

	}

	/**
	 * Invoked when the mouse leaves this component.
	 */
	@Override
	public void mouseExited(MouseEvent e) {
	}

	/**
	 * Invoked when the mouse is dragged over this component.
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
	}

	/**
	 * Invoked when the mouse is moved across this component.
	 */
	@Override
	public void mouseMoved(MouseEvent e) {

	}
}
