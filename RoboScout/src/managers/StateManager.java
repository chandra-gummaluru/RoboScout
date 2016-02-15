package managers;

import java.awt.Graphics2D;

import exceptions.LoadException;
import program.State;
import window.Program;

public abstract class StateManager {

	private static boolean paused = false;

	/**
	 * The currently active state.
	 */
	private static State currentState;

	/**
	 * The previously active state.
	 */
	private static State previousState;

	/**
	 * The required state to switch to.
	 */
	private static State requiredState;

	/**
	 * Indicates whether a state is currently being loaded or not.
	 */
	private static boolean loading;

	/**
	 * Initializes the manager.
	 */
	public static void initialize() {
		setState(Program.getDefaultState());
	}

	/**
	 * Changes the current state to the specified state and unloads the previous
	 * state and all associated resources.
	 *
	 * @param state
	 *            the state to switch to.
	 */

	public static void setState(State state) {
		// if the specified state is null, return.
		if (state == null) {
			return;
		}

		// if a state is not already queued, set the required state to the
		// specified state.
		if (requiredState == null) {
			requiredState = state;
			//return;
		}

		loading = true;

		// unload the current state.
		if (state.getPath() != null) {
			unloadState();
		}
		
		// load the resources required by this state, if any.
		if (requiredState.getPath() != null) {
			loadState();
		}

		// set the previous state to the current state.
		previousState = currentState;
		
		// set the current state to the required state.
		currentState = requiredState;
		currentState.initialize();

		requiredState = null;
		loading = false;
	}

	/**
	 * Loads the current state and all associated resources.
	 *
	 */
	private static void loadState() {
		try {
			ResourceManager.loadResources((requiredState.getPath()));
			ResourceManager.loadBuildData(ResourceManager.getText().get("txt_build_config"));
			ComponentManager.loadComponents();
			
		} catch (LoadException e) {
			requiredState = null;
			// setState(new ErrorState(e.getMessage(), e.getStackTrace()));
		}
	}

	/**
	 * Unloads the current state and all associated resources.
	 */
	private static void unloadState() {
		ResourceManager.unloadResources();
		ComponentManager.clearComponents();
	}

	public static void togglePause() {
		paused = !paused;
	}

	/**
	 * Update physics, etc.
	 */
	public static void tick() {
		if (currentState != null) {
			currentState.tick();
		}
	}

	public static void draw(Graphics2D g) {
		if (currentState != null) {
			currentState.draw(g);
		}
	}

	public static State getCurrentState() {
		return currentState;
	}

	public static State getRequiredState() {
		return requiredState;
	}

	public static boolean isLoading() {
		return loading;
	}

	public static State getPreviousState() {
		return previousState;
	}
}
