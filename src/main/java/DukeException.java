package duke;

public class DukeException extends Exception {
	public DukeException() {
		super("Storage file is not found.");
	}
}