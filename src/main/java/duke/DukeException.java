package duke;

/**
 * The DukeException is a custom exception class for Duke.
 */
public class DukeException
        extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}