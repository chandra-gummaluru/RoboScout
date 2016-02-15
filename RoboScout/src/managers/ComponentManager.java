package managers;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import components.GraphicBackground;
import components.GraphicButton;
import components.GraphicComponent;
import components.GraphicText;
import exceptions.LoadException;
import exceptions.LoadException.ExceptionType;
import window.Program;

public abstract class ComponentManager {

	private enum ComponentType {
		BACKGROUND, BUTTON, TEXT;
	}

	private static final String COMPONENT_DELIMITER = ":";
	private static final String PROPERTY_DELIMITER = ",";

	private static LinkedHashMap<String, GraphicComponent> components = new LinkedHashMap<String, GraphicComponent>();

	private static LinkedHashMap<String, GraphicBackground> graphicBackgrounds = new LinkedHashMap<String, GraphicBackground>();
	private static LinkedHashMap<String, GraphicButton> graphicButtons = new LinkedHashMap<String, GraphicButton>();
	private static LinkedHashMap<String, GraphicText> graphicText = new LinkedHashMap<String, GraphicText>();

	public static void loadComponents() throws LoadException {

		try {
			ArrayList<String> sourceData = ResourceManager.getBuildData();

			for (String s : sourceData) {

				String[] componentData = s.split(COMPONENT_DELIMITER);

				ComponentType componentType = ComponentType.valueOf(componentData[0]);

				String[] componentBuildData = componentData[1].split(PROPERTY_DELIMITER);

				switch (componentType) {
				case BACKGROUND:
					buildBackground(componentBuildData);
					break;
				case BUTTON:
					buildButton(componentBuildData);
					break;
				case TEXT:
					buildText(componentBuildData);
				default:
					break;

				}
			}
		} catch (IllegalArgumentException e) {
			throw new LoadException(ExceptionType.ILLEGAL_ARGUMENT, e.getStackTrace());
		}

	}

	private static void buildBackground(String[] componentBuildData) {

		String name = componentBuildData[0];

		// position
		int xPosition = Integer.parseInt(componentBuildData[1]);
		int yPosition = Integer.parseInt(componentBuildData[2]);

		// image
		BufferedImage defaultImage = ResourceManager.getImages().get(componentBuildData[3]);

		// dimensions
		int width = defaultImage.getWidth();
		int height = defaultImage.getHeight();

		graphicBackgrounds.put(name, new GraphicBackground(name, xPosition, yPosition, width, height, defaultImage));
		components.put(name, graphicBackgrounds.get(name));
		Program.getDefaultWindow().add(components.get(name));
		System.out.println(Program.getDefaultWindow().getComponentCount());
	}

	private static void buildButton(String[] componentBuildData) {
		String name = componentBuildData[0];

		int xPosition = Integer.parseInt(componentBuildData[1]);
		int yPosition = Integer.parseInt(componentBuildData[2]);

		int numberOfRows = Integer.parseInt(componentBuildData[3]);
		int numberOfColumns = Integer.parseInt(componentBuildData[4]);

		BufferedImage defaultImage = (BufferedImage) ResourceManager.getImages().get(componentBuildData[5]);

		int width = defaultImage.getWidth() / numberOfColumns;
		int height = defaultImage.getHeight() / numberOfRows;

		BufferedImage[] images = new BufferedImage[numberOfRows * numberOfColumns];

		for (int currentRow = 0; currentRow < numberOfRows; currentRow++) {
			for (int currentColumn = 0; currentColumn < numberOfColumns; currentColumn++) {
				images[(currentRow * numberOfColumns + currentColumn)] = defaultImage.getSubimage(currentColumn * width,
						currentRow * height, width, height);
			}
		}

		graphicButtons.put(name, new components.GraphicButton(name, xPosition, yPosition, width, height, images[0],
				images[1], images[2], images[3]));
		components.put(name, graphicButtons.get(name));
		Program.getDefaultWindow().add(components.get(name));
		System.out.println(Program.getDefaultWindow().getComponentCount());
	}

	private static void buildText(String[] componentBuildData) {

		String name = componentBuildData[0];

		// position
		int xPosition = Integer.parseInt(componentBuildData[1]);
		int yPosition = Integer.parseInt(componentBuildData[2]);

		// text
		String defaultText = componentBuildData[3];

		// font and color
		Font font = ResourceManager.getFonts().get(componentBuildData[4]);
		int fontSize = Integer.parseInt(componentBuildData[5]);
		int fontStyle = Integer.parseInt(componentBuildData[6]);

		Color color = ResourceManager.getColors().get(componentBuildData[7]);

		// dimensions
		int width = 5;
		int height = 5;

		graphicText.put(name, new GraphicText(name, xPosition, yPosition, width, height, defaultText, font, fontSize,
				fontStyle, color));
		components.put(name, graphicText.get(name));
		Program.getDefaultWindow().add(components.get(name));
		System.out.println(Program.getDefaultWindow().getComponentCount());
	}

	public static void clearComponents() {
		if (!graphicBackgrounds.isEmpty()) {
			graphicBackgrounds.clear();
		}
		if (!graphicButtons.isEmpty()) {
			graphicButtons.clear();
		}
		if (!graphicText.isEmpty()) {
			graphicText.clear();
		}
	}

	public static LinkedHashMap<String, GraphicBackground> getBackgrounds() {
		return graphicBackgrounds;
	}

	public static LinkedHashMap<String, GraphicButton> getButtons() {
		return graphicButtons;
	}

	public static LinkedHashMap<String, GraphicText> getText() {
		return graphicText;
	}

	public static LinkedHashMap<String, GraphicComponent> getGraphicComponents() {
		return components;
	}
}
