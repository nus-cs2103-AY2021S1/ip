package juke.exception;

/**
 * Represents a DukeExceptions.DukeException when a user does not input a proper juke.task.
 */
public class UnknownTaskException extends DukeException {
    public UnknownTaskException(String message) {
        super(message);
    }
}
