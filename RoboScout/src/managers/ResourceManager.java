package managers;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import exceptions.LoadException;
import exceptions.LoadException.ExceptionType;

public abstract class ResourceManager {

	// patterns and delimiters

	/**
	 * The default pattern for identifiers or tags within text files.
	 */
	private final static Pattern TAG_PATTERN = Pattern.compile("<.*>");

	/**
	 * The default header of a tag.
	 */
	private static final String TAG_HEADER = "<";

	/**
	 * The default footer of a tag.
	 */
	private static final String TAG_FOOTER = ">";

	/**
	 * The default delimiter for the extension of a text file.
	 */
	final static String EXTENSION_DELIMITER = ".";

	/**
	 * The default delimiter for each datum.
	 */
	final static String DATA_DELIMITER = ",";

	private enum FileType {

		/**
		 * A file type holding location information of the required resources.
		 */
		RESOURCE("res"),

		/**
		 * A text file holding build information for the required graphic
		 * objects.
		 */
		BUILD_DATA("cgf");

		private String extension;

		FileType(String extension) {
			this.extension = extension;
		}

		public String getExtension() {
			return this.extension;
		}

	}

	private enum ResourceType {
		TEXT, NUMBER, IMAGE, AUDIO, FONT, COLOR;
	}

	private static String sourcePath;

	// resource data

	/**
	 * The hash map to hold the text loaded in.
	 */
	private static HashMap<String, String> text = new HashMap<String, String>();

	/**
	 * The hash map to hold the numerical values loaded in.
	 */
	private static HashMap<String, Double> numbers = new HashMap<String, Double>();

	/**
	 * The hash map to hold the images loaded in.
	 */
	private static HashMap<String, BufferedImage> images = new HashMap<String, BufferedImage>();

	/**
	 * The hash map to hold the audio files loaded in.
	 */
	private static HashMap<String, Clip> audio = new HashMap<String, Clip>();

	/**
	 * The hash map to hold the font files loaded in.
	 */
	private static HashMap<String, Font> fonts = new HashMap<String, Font>();

	/**
	 * The hash map to hold the Color data loaded in.
	 */
	private static HashMap<String, Color> colors = new HashMap<String, Color>();

	// build data

	private static ArrayList<String> buildData = new ArrayList<String>();

	public static void initialize(String path) throws LoadException {
		sourcePath = path;
		loadResources(sourcePath);
	}

	@SuppressWarnings("unused")
	private static FileType getFileType(String path) {

		// Get the file type for the current file.
		String currentFileExtension = path
				.substring(path.lastIndexOf(EXTENSION_DELIMITER, path.length()), path.length())
				.replace(EXTENSION_DELIMITER, "");

		// Determine the file type of the specified file based on the extension.
		for (FileType fileType : FileType.values()) {
			if (currentFileExtension.equalsIgnoreCase(fileType.getExtension())) {
				return fileType;
			}
		}

		// If the file type is not recognized, return null.
		return null;
	}

	/**
	 * Loads in the specified resources from the specified resource file.
	 * 
	 * @param path
	 *            The path of the resource file.
	 * @throws LoadException
	 *             Occurs when this method is unable to execute.
	 */
	public static void loadResources(String path) throws LoadException {
		// Create a matcher to match the resource tag.
		Matcher tagMatcher = null;

		// Create a reader to read the specified file.
		BufferedReader reader = null;

		String currentLine;

		String[] lineData;

		String key;
		String resourcePath;

		String tag;

		ResourceType resourceType;
		try {
			reader = new BufferedReader(new InputStreamReader(getFileAsStream(path)));

			while ((currentLine = reader.readLine()) != null) {
				tagMatcher = TAG_PATTERN.matcher(currentLine);
				if (tagMatcher.find()) {
					tag = tagMatcher.group();
					resourceType = getRawResourceType(tag);
					lineData = currentLine.replace(tag, "").split(DATA_DELIMITER);

					key = lineData[0];
					resourcePath = lineData[1];

					String resourceData = resourcePath;

					switch (resourceType) {
					case TEXT:
						text.put(key, resourceData);
						break;
					case NUMBER:
						numbers.put(key, loadNumericalValue(resourceData));
						break;
					case IMAGE:
						images.put(key, loadImage(resourcePath));
						break;
					case AUDIO:
						audio.put(key, loadAudio(resourcePath));
						break;
					case FONT:
						fonts.put(key, loadFont(resourcePath));
						break;
					case COLOR:
						colors.put(key, loadColor(resourceData));
						break;
					}
				}
			}

			// Close the reader.
			reader.close();

			// Catch any exceptions and throw the respective LoadException.
		} catch (IOException e) {
			throw new LoadException(ExceptionType.FILE_IN_USE, e.getStackTrace());
		} catch (NumberFormatException e) {
			throw new LoadException(ExceptionType.CORRUPT_FILE, e.getStackTrace());
		}
	}

	/**
	 * Gets the raw resource type by removing any predefined tag headers and
	 * footers.
	 * 
	 * @param tag
	 *            The tag to format.
	 * @return The raw resource type.
	 */
	private static ResourceType getRawResourceType(String tag) {
		return ResourceType.valueOf(tag.replace(TAG_HEADER, "").replace(TAG_FOOTER, ""));
	}

	/**
	 * Loads a number into memory. It is recommended to use this method over
	 * {@code Double.parseDouble(String s)} since this method checks for errors
	 * and throws the appropriate exceptions.
	 * 
	 * @param data
	 *            The line of data to parse.
	 * @return the {@code double} representation of the specified data.
	 * @throws LoadException
	 *             Occurs when this method is unable to execute.
	 */
	private static double loadNumericalValue(String data) throws LoadException {
		Double number = null;

		try {
			number = Double.parseDouble(data);

			// Catch each of the possible errors and throw a load exception.
		} catch (NumberFormatException e) {
			throw new LoadException(ExceptionType.CORRUPT_FILE, e.getStackTrace());
		}
		return number;

	}

	/**
	 * Loads an image into memory.
	 *
	 * @param path
	 *            The path of the image file.
	 * @return The BufferedImage object.
	 * @throws LoadException
	 *             Occurs when this method is unable to execute.
	 */
	private static BufferedImage loadImage(String path) throws LoadException {

		BufferedImage image = null;

		try {
			// Load in the image.

			image = ImageIO.read(getFileAsStream(path));

			// Catch each of the possible errors and throw a load exception.
		} catch (FileNotFoundException e) {
			throw new LoadException(ExceptionType.MISSING_FILE, e.getStackTrace());
		} catch (IllegalArgumentException e) {
			throw new LoadException(ExceptionType.ILLEGAL_ARGUMENT, e.getStackTrace());
		} catch (IOException e) {
			throw new LoadException(ExceptionType.FILE_IN_USE, e.getStackTrace());
		}
		// return the image.
		return image;
	}

	/**
	 * Loads an audio file into memory.
	 *
	 * @param path
	 *            The path of the audio file.
	 * @return
	 * @throws LoadException
	 *             Occurs when this method is unable to execute.
	 */
	private static Clip loadAudio(String path) throws LoadException {

		Clip clip = null;

		AudioInputStream audioStream;

		try {
			// get the audio input stream.
			audioStream = AudioSystem.getAudioInputStream(new BufferedInputStream(getFileAsStream(path)));

			// load the clip and prepare it for play back.
			clip = AudioSystem.getClip();
			clip.open(audioStream);

			// Catch each of the possible errors and throw a load exception.
		} catch (UnsupportedAudioFileException e) {
			throw new LoadException(ExceptionType.CORRUPT_FILE, e.getStackTrace());
		} catch (IOException e) {
			throw new LoadException(ExceptionType.FILE_IN_USE, e.getStackTrace());
		} catch (LineUnavailableException e) {
			throw new LoadException(ExceptionType.UNKNOWN_ERROR, e.getStackTrace());
		}
		return clip;
	}

	/**
	 * Loads a font file into memory.
	 *
	 * @param path
	 *            The path of the font file.
	 * @return The font object.
	 * @throws LoadException
	 *             Occurs when this method is unable to execute.
	 */
	private static Font loadFont(String path) throws LoadException {
		Font font = null;

		try {
			// load the font.
			font = Font.createFont(Font.TRUETYPE_FONT, getFileAsStream(path));

			// Catch each of the possible errors and throw a load exception.
		} catch (FontFormatException e) {
			throw new LoadException(ExceptionType.CORRUPT_FILE, e.getStackTrace());
		} catch (IOException e) {
			throw new LoadException(ExceptionType.FILE_IN_USE, e.getStackTrace());
		}

		return font;

	}

	/**
	 * Loads a Color into memory.
	 * 
	 * @param data
	 *            The line of data to parse.
	 * @return the {@code Color} representation of the specified data.
	 * @throws LoadException
	 *             Occurs when this method is unable to execute.
	 */
	private static Color loadColor(String data) throws LoadException {
		Color color = null;

		try {
			String[] rgbaValues = data.split("-");

			int r = Integer.parseInt(rgbaValues[0]);
			int g = Integer.parseInt(rgbaValues[1]);
			int b = Integer.parseInt(rgbaValues[2]);

			int a;

			if (rgbaValues.length > 3) {
				a = Integer.parseInt(rgbaValues[3]);
				color = new Color(r, g, b, a);
			} else {
				color = new Color(r, g, b);
			}

			// Catch each of the possible errors and throw a load exception.
		} catch (IndexOutOfBoundsException e) {
			throw new LoadException(ExceptionType.CORRUPT_FILE, e.getStackTrace());
		}
		return color;

	}

	/**
	 * Loads a raw file as an {@code InputStream} from source folder.
	 * 
	 * @param path
	 *            The path of the file.
	 * @return The file as an {@code InputStream}.
	 */
	private static InputStream getFileAsStream(String path) {
		return ResourceManager.class.getResourceAsStream(path);
	}

	public static void loadBuildData(String path) throws LoadException {

		// Create a reader to read the specified file.
		BufferedReader reader = new BufferedReader(new InputStreamReader(getFileAsStream(path)));

		String currentLine;

		try {
			reader = new BufferedReader(new InputStreamReader(getFileAsStream(path)));

			while ((currentLine = reader.readLine()) != null) {
				buildData.add(currentLine);
			}

		} catch (IOException e) {
			throw new LoadException(ExceptionType.FILE_IN_USE, e.getStackTrace());
		}
	}

	public static HashMap<String, String> getText() {
		return text;
	}

	public static HashMap<String, Double> getNumericalValues() {
		return numbers;
	}

	public static HashMap<String, Clip> getAudio() {
		return audio;
	}

	public static HashMap<String, BufferedImage> getImages() {
		return images;
	}

	public static HashMap<String, Font> getFonts() {
		return fonts;
	}

	public static HashMap<String, Color> getColors() {
		return colors;
	}

	public static ArrayList<String> getBuildData() {
		return buildData;
	}

	public static void unloadResources() {
		if (!text.isEmpty()) {
			text.clear();
		}
		if (!numbers.isEmpty()) {
			numbers.clear();
		}
		if (!images.isEmpty()) {
			images.clear();
		}
		if (!audio.isEmpty()) {
			audio.clear();
		}
		if (!fonts.isEmpty()) {
			fonts.clear();
		}
		if (!colors.isEmpty()) {
			colors.clear();
		}
	}
}
