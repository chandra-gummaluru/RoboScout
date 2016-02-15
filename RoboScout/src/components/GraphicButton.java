package components;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import managers.InputManager;

public class GraphicButton extends GraphicComponent {

	private static final long serialVersionUID = 1L;

	private boolean enabled;

	private BufferedImage defaultImage;

	private BufferedImage highlightImage;

	private BufferedImage pressedImage;

	private BufferedImage disabledImage;

	public GraphicButton(String name, int xPosition, int yPosition, int width, int height, BufferedImage defaultImage,
			BufferedImage highlightImage, BufferedImage pressedImage, BufferedImage disabledImage) {
		super(name, xPosition, yPosition, width, height, defaultImage);
		this.enabled = true;

		this.defaultImage = defaultImage;
		this.highlightImage = highlightImage;
		this.pressedImage = pressedImage;
		this.disabledImage = disabledImage;
		
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	public void draw(Graphics2D g) {
		g.drawImage(getCurrentImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

	public void disable() {
		this.enabled = false;
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	public void enable() {
		this.enabled = true;
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (!enabled) {
			return;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (!enabled) {
			return;
		}
		setCurrentImage(pressedImage);
		InputManager.mousePressed(this);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (!enabled) {
			return;
		}
		if (this.contains(e.getPoint())) {
			setCurrentImage(highlightImage);
		} else {
			setCurrentImage(defaultImage);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (!enabled) {
			return;
		}
		setCurrentImage(highlightImage);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (!enabled) {
			return;
		}
		setCurrentImage(defaultImage);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (!enabled) {
			return;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (!enabled) {
			return;
		}
	}
}