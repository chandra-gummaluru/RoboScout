package scaling;

/**
 * Implements fields and methods to create, use, and modify an aspect ratio.
 *
 */
public class AspectRatio {

	private double ratio;

	/**
	 * The constructor for a new AspectRatio object.
	 * 
	 * @param numerator
	 *            the numerator of the ratio.
	 * @param denominator
	 *            the denominator of the ratio.
	 */
	public AspectRatio(double numerator, double denominator) {
		this.ratio = numerator / denominator;
	}

	/**
	 * Calculates the height based on the specified width and the aspect ratio
	 * of this instance.
	 * 
	 * @param width
	 *            The width.
	 * @return The height.
	 */
	public int calculateHeight(int width) {
		return (int) (width / this.ratio);
	}

	/**
	 * Gets the ratio for this instance.
	 * 
	 * @return ratio
	 */
	public double getRatio() {
		return ratio;
	}

	/**
	 * Sets the ratio for this instance to the specified ratio.
	 * 
	 * @param ratio
	 *            the desired ratio.
	 */
	public void setRatio(double ratio) {
		this.ratio = ratio;
	}

}
