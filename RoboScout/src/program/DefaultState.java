package program;

import java.awt.Graphics2D;

import components.GraphicComponent;
import managers.ComponentManager;

public class DefaultState extends State {

	public DefaultState() {
		super("DEFAULT_STATE", "/data/default.res");
	}

	@Override
	public void initialize() {
	}

	@Override
	public void tick() {

	}

	@Override
	public void draw(Graphics2D g) {
		for (GraphicComponent c : ComponentManager.getGraphicComponents().values()) {
			c.draw(g);
		}
	}

}
