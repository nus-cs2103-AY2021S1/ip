package duke.exception;

/**
 * Exception class that gets thrown by supporting classes' methods.
 */
public class DukeException extends Exception {
	public DukeException(String string) {
		super(string);
	}
}