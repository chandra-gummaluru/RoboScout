package components;

import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import window.Program;

public abstract class GraphicComponent extends JComponent implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;

	public static int SCALE;

	/**
	 * The name of the component.
	 */
	private String name;

	/**
	 * The current image of {@code this} instance.
	 */
	private BufferedImage currentImage;

	/**
	 * Indicates whether {@code this} instance can be dragged or not.
	 */
	private boolean draggable;

	public static void initalize() {
		SCALE = Program.getDefaultWindow().getScale();
	}

	/**
	 * Constructs a new GraphicComponent.
	 * 
	 * @param name
	 *            The name of {@code this} component.
	 * @param xPosition
	 *            The horizontal position of {@code this} component.
	 * @param yPosition
	 *            The vertical position of {@code this} component.
	 * @param width
	 *            The width of {@code this} component.
	 * @param height
	 *            The height of {@code this} component.
	 * @param currentImage
	 *            The current image of {@code this} component.
	 */
	public GraphicComponent(String name, int xPosition, int yPosition, int width, int height,
			BufferedImage currentImage) {

		this.name = name;
		
		this.setLocation(xPosition * SCALE, yPosition * SCALE);
		this.setSize(width * SCALE, height * SCALE);

		this.currentImage = currentImage;

		this.draggable = true;

		this.addMouseListener(this);
		
	}

	/**
	 * Draws {@code this} component.
	 * 
	 * @param g
	 *            The graphics context.
	 */
	public abstract void draw(Graphics2D g);

	/**
	 * Gets the name of {@code this} instance.
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the current image of {@code this} instance.
	 * 
	 * @return currentImage
	 */
	public BufferedImage getCurrentImage() {
		return this.currentImage;
	}

	/**
	 * Gets whether {@code this} instance can be dragged or not.
	 * 
	 * @return {@code true} if {@code this} instance can be dragged,
	 *         {@code false} otherwise.
	 */
	public boolean isDraggable() {
		return this.draggable;
	}

	/**
	 * Sets the current image of {@code this} instance to the specified
	 * {@code BufferedImage image};
	 * 
	 * @param image
	 *            The {@code BufferedImage} to set the current image of
	 *            {@code this} instance to.
	 */
	public void setCurrentImage(BufferedImage image) {
		this.currentImage = image;
	}

	/**
	 * Sets whether {@code this} instance can be dragged or not.
	 * 
	 * @param draggable
	 *            Set to {@code true} if {@code this} instance can be dragged,
	 *            {@code false} otherwise.
	 */
	public void setDraggable(boolean draggable) {
		this.draggable = draggable;
	}

}
