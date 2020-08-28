package duke;

/**
 * Represents the Exception object which would be thrown throughout the process of running Duke.
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
