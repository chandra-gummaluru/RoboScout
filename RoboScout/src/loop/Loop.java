package loop;

import window.Program;

public class Loop implements Runnable {

	/**
	 * Indicates whether the loop is running or not. The default is false.
	 */
	private boolean running = false;

	/**
	 * The main thread.
	 */
	private Thread thread;

	/**
	 * The refresh rate in number of frames per second. The default is 30.
	 */
	private final int FPS = 30;

	/**
	 * The target time in milliseconds for each frame.
	 */
	private final long targetTime = 1000 / FPS;

	/**
	 * The current frame rate.
	 */
	private double currentFPS = 0;

	/**
	 * Initializes and starts the game loop.
	 */
	public void initialize() {
		thread = new Thread(this);
		thread.start();
	}

	/**
	 * Pauses or resumes the game loop.
	 */
	public void togglePause() {
		running = !running;
	}

	@Override
	public void run() {

		togglePause();

		// The current frame count.
		int frames = 0;

		// The time (in nanoseconds) from the last update.
		long previousUpdate = 0;

		// This loop executes as long as the game is running.
		while (running) {
			// the point in time (in nanoseconds) in which an iteration of the
			// loop is started.
			long startTime;

			// the point in time (in nanoseconds) in which the same iteration of
			// the loop is finished.
			long endTime;

			// the elapsed time (in milliseconds) between the start and end the
			// iteration.
			long elapsedTime;

			// the time (in milliseconds) to wait, until the next iteration of
			// the loop.
			long waitTime;

			if (frames >= FPS) {
				// Calculate the amount of time (in seconds) it took, for the
				// target frames to display.
				currentFPS = FPS / ((System.nanoTime() - previousUpdate) / 1000000000.0);

				// Reset the frame count.
				frames = 0;
				previousUpdate = System.nanoTime();
			} else {
				frames++;
			}
			startTime = System.nanoTime();

			Program.getDefaultWindow().repaint();
			
			endTime = System.nanoTime();

			elapsedTime = (int) ((endTime - startTime) / 1000000);

			waitTime = (int) (targetTime - elapsedTime);

			try {
				if (waitTime > 0) {
					Thread.sleep(waitTime);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Returns the target frame rate.
	 * 
	 * @return The target frame rate.
	 */
	public int getFPS() {
		return this.FPS;
	}

	/**
	 * Returns the current frame rate.
	 * 
	 * @return The current frame rate.
	 */
	public double getCurrentFPS() {
		return currentFPS;
	}
}
