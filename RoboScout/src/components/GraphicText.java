package components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class GraphicText extends GraphicComponent {

	private static final long serialVersionUID = 1L;

	private String defaultText;

	private Font font;
	private int fontSize;
	private int fontStyle;
	
	private Color color;
	
	public GraphicText(String name, int xPosition, int yPosition, int width, int height, String defaultText, Font font,
			int fontSize, int fontStyle, Color color) {
		super(name, xPosition, yPosition, width, height, null);

		this.defaultText = defaultText;

		this.font = font;
		this.fontSize = fontSize;
		this.fontStyle = fontStyle;
		
		this.color = color;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setFont(font.deriveFont(fontStyle, fontSize * SCALE));
		g.setColor(color);
		g.drawString(this.defaultText, getX(), getY());
	}

	public void setTextSize(int size) {
		this.font = this.font.deriveFont(size);
	}

	public void setTextStyle(int style) {
		this.font = this.font.deriveFont(style);
	}

	public void setTextColor(Color color) {
		this.color = color;
	}
	
	public void setDefaultText(String s) {
		this.defaultText = s;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
