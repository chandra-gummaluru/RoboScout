package exceptions;

public class LoadException extends Exception {

	private static final long serialVersionUID = 1L;

	private ExceptionType exceptionType;

	StackTraceElement[] stackTrace;

	public enum ExceptionType {

		/**
		 * The specified file is missing or cannot be located.
		 */
		MISSING_FILE("One or more of the data files are missing."),

		/**
		 * The specified file is corrupt. This can also occur if there file has
		 * bad syntax.
		 */
		CORRUPT_FILE("One or more of the data files are corrupt."),

		/**
		 * The specified file is in use.
		 */
		FILE_IN_USE("One or more of the data files are currently in use and unable to be read."),

		/**
		 * The specified argument is illegal.
		 */
		ILLEGAL_ARGUMENT("A illegal argument was specified while loading."),

		/**
		 * An unknown error.
		 */
		UNKNOWN_ERROR("Unknown error.");

		String details;

		/**
		 * @param details
		 *            The details of this error.
		 */
		ExceptionType(String details) {
			this.details = details;
		}

	}

	/**
	 * Constructs a new LoadException object.
	 * 
	 * @param exceptionType
	 *            The exception type.
	 * @param stackTrace
	 *            The stack trace.
	 */
	public LoadException(ExceptionType exceptionType, StackTraceElement[] stackTrace) {
		this.exceptionType = exceptionType;
		this.stackTrace = stackTrace;
	}

	/**
	 * Returns the exception details
	 */
	public String getMessage() {
		return exceptionType.details;
	}
}
