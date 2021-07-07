package main.java.duke;

/**
 * An exception that is thrown when the priority specified for a task object is of the wrong format.
 */
public class PriorityException extends DukeException {
    /**
     * Creates an instance of PriorityException with the given error message when there occurs an
     * error with the specified priority.
     * @param message exception message
     */
    public PriorityException(String message) {
        super(message);
    }
}
