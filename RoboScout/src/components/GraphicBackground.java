package components;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class GraphicBackground extends GraphicComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GraphicBackground(String name, int xPosition, int yPosition, int width, int height,
			BufferedImage defaultImage) {
		super(name, xPosition, yPosition, width, height, defaultImage);
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(getCurrentImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
	
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		System.out.println("Hi");
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
